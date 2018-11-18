package springboot.hello.hellospringboot.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Assert;
import springboot.hello.hellospringboot.common.annotation.ExceVo;
import springboot.hello.hellospringboot.common.exception.RRException;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Excel 导入 工具类
 * </p>
 *
 * @author hss
 * @since 2018-11-17
 */
public class ExcelReadUtil<T> {

    private Class claze;

    public ExcelReadUtil(Class claze) {
        this.claze = claze;
    }

    /**
     * 基于注解读取excel
     * @param filePath 文件地址
     * @param rowIndex 从第几行开始读取数据
     * @return List<T> List 集合
     */
    public List<T> readExcel(String filePath, int rowIndex) throws Exception {
        POIFSFileSystem fs = null;
        List<T> list = new ArrayList<T>();//读取需要返回的列表
        T entity = null;                  //实体对象

        // fieldList为带实体类注解并排序好的字段集合
        List<Field> fieldList = getFieldList();
        int size = fieldList.size();
        Field field = null;      // 字段

        fs = new POIFSFileSystem(new FileInputStream(filePath));
        HSSFWorkbook workbook = new HSSFWorkbook(fs);   // 获取工作簿
        HSSFSheet sheet = workbook.getSheetAt(0);// 取工作簿的第一个sheet
        int rowLength = sheet.getLastRowNum()+1;       // 该sheet的总行数

        for (int i = 0; i <= rowLength - rowIndex; i++) {
            HSSFRow row = sheet.getRow(i + rowIndex - 1);//获取行的数据
            entity = (T) claze.newInstance();
            for (int j = 0; j < size; j++) {
                HSSFCell cell = row.getCell(j);//获取该行每一列的值
                field = fieldList.get(j);      //获取该值所对应的字段
                field.set(entity, covertAttrType(field, cell)); //对应起来 然后存入entity
            }
            list.add(entity); //每读完一行，存一行
        }

        return list;
    }


    /**
     * 获取带注解的字段 并且排序
     * @return
     */
    private List<Field> getFieldList() {
        Field[] fields = this.claze.getDeclaredFields();
        // 无序
        List<Field> fieldList = new ArrayList<Field>();
        // 排序后的字段
        List<Field> fieldSortList = new LinkedList<Field>();
        int length = fields.length;
        int sort = 0;
        Field field = null;

        // 获取带注解的字段
        for (int i = 0; i < length; i++) {
            field = fields[i];
            if (field.isAnnotationPresent(ExceVo.class)) {
                fieldList.add(field);
            }
        }

        Assert.assertNotNull("未获取到需要导出的字段", fieldList);
        length = fieldList.size();

        for (int i = 1; i <= length; i++) {
            for (int j = 0; j < length; j++) {
                field = fieldList.get(j);
                ExceVo exceVo = field.getAnnotation(ExceVo.class);
                field.setAccessible(true);
                sort = exceVo.sort();
                if (sort == i) {
                    fieldSortList.add(field);
                    continue;
                }
            }
        }
        return fieldSortList;
    }



    /**
     * 类型转换 将cell 单元格格式转为 字段类型
     */
    private Object covertAttrType(Field field, HSSFCell cell) {
        int type = cell.getCellType();
        if (type == Cell.CELL_TYPE_BLANK) {
            return null;
        }
        ExceVo exceVo = field.getAnnotation(ExceVo.class);

        // 字段类型
        String fieldType = field.getType().getSimpleName();
        try {
            if ("String".equals(fieldType)) {
                return getValue(cell);
            }else if ("Date".equals(fieldType)) {
                return DateUtils.parseDate(getValue(cell), new String[]{"yyyy-MM-dd"});
            }else if ("int".equals(fieldType) || "Integer".equals(fieldType)) {
                return Integer.parseInt(getValue(cell));
            }else if ("double".equals(fieldType) || "Double".equals(fieldType)) {
                return Double.parseDouble(getValue(cell));
            }
        }catch (Exception e) {
            if (e instanceof ParseException) {
                throw new RRException("时间格式转换失败", e);
            }else {
                e.printStackTrace();
                throw new RRException("格式转换失败");
            }
        }
        throw new RRException("excel 单元格 格式暂不支持");
    }


    /**
     * 格式转为String
     * @param cell
     * @return
     */
    public String getValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString().trim();
            case HSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date dt = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    return DateFormatUtils.format(dt, "yyyy-MM-dd");
                } else {
                    // 防止数值变成科学计数法
                    String strCell = "";
                    Double num = cell.getNumericCellValue();
                    BigDecimal bd = new BigDecimal(num.toString());
                    if (bd != null) {
                        strCell = bd.toPlainString();
                    }
                    // 去除 浮点型 自动加的 .0
                    if (strCell.endsWith(".0")) {
                        strCell = strCell.substring(0, strCell.indexOf("."));
                    }
                    return strCell;
                }
            case HSSFCell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case HSSFCell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case HSSFCell.CELL_TYPE_BLANK:
                return "";
            default:
                return "";
        }
    }
}
