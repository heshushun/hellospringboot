package springboot.hello.hellospringboot.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Assert;
import springboot.hello.hellospringboot.common.annotation.ExceVo;
import springboot.hello.hellospringboot.common.exception.RRException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Excel 导出 工具类
 * </p>
 *
 * @author hss
 * @since 2018-11-17
 */
public class ExcelExportUtil<T> {

    private Class claze;

    public ExcelExportUtil(Class claze) {
        this.claze = claze;
    }

    /**
     * 基于模板导出
     * @param fileName 文件名称
     * @param templet 模板名称
     * @param objs 导出实体集合
     * @param rowIndex excel第几行开始导出
     */
    public void export(String fileName,String templet, List<T[]> objs, int rowIndex) {
        POIFSFileSystem fs = null;
        FileOutputStream os = null;
        try {
            fs = new POIFSFileSystem(Thread.currentThread().getContextClassLoader().getResourceAsStream(templet));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(fs);
            HSSFCellStyle style = setCellStyle(workbook);
            HSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = 0; i < objs.size(); i++) {
                HSSFRow row = sheet.createRow(i +  rowIndex - 1);
                for (int j = 0; j < objs.get(i).length; j++) {
                    HSSFCell cell = row.createCell(j);
                    cell.setCellStyle(style);
                    cell.setCellValue(objs.get(i)[j].toString());
                }
            }
            os = new FileOutputStream(new File("D:\\"+fileName+".xls"));
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 基于注解导出
     * 不需要自己封装每列的值
     * @param fileName 文件名称
     * @param templet 模板名称
     * @param objs 导出实体集合
     * @param rowIndex excel第几行开始导出
     */
    public void exportAsAop(String fileName,String templet, List<T> objs, int rowIndex) {
        POIFSFileSystem fs = null;
        FileOutputStream os = null;
        try {
            // classpath 下的模板excel文件
            fs = new POIFSFileSystem(Thread.currentThread().getContextClassLoader().getResourceAsStream(templet));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // 带注解并排序好的字段
            List<Field> fieldList = getFieldList();

            HSSFWorkbook workbook = new HSSFWorkbook(fs);
            HSSFCellStyle style = setCellStyle(workbook);
            HSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = 0; i < objs.size(); i++) {
                HSSFRow row = sheet.createRow(i + rowIndex - 1);
                for (int j = 0; j < fieldList.size(); j++) {
                    HSSFCell cell = row.createCell(j);
                    cell.setCellStyle(style);
                    Field field = fieldList.get(j);

                    String fieldValue = covertAttrType(field, objs.get(i));
                    cell.setCellValue(fieldValue);
                }
            }
            os = new FileOutputStream(new File("D:\\"+fileName+".xls"));
            workbook.write(os);
        } catch (Exception e) {
            throw new RRException("导出失败",e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 基于注解导出 2  （测试结果）
     * 不需要自己封装每列的值
     * @param fileName 文件名称
     * @param templet 模板名称
     * @param objs 导出实体集合
     * @param rowIndex excel第几行开始导出
     */
    public HSSFWorkbook exportAsAop2(String fileName, String templet, List<T> objs, int rowIndex, Integer[] testresultCount) {
        POIFSFileSystem fs = null;
        try {
            // classpath 下的模板excel文件
            fs = new POIFSFileSystem(Thread.currentThread().getContextClassLoader().getResourceAsStream(templet));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // 带注解并排序好的字段
            List<Field> fieldList = getFieldList();

            HSSFWorkbook workbook = new HSSFWorkbook(fs);
            //XSSFWorkbook workbook = new XSSFWorkbook();
            HSSFCellStyle style = setCellStyle(workbook);
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow row_count = sheet.getRow(2);

            for (int k = 0;  k<=2; k++){
                HSSFCell cell_count = row_count.getCell((k*2)+1);
                cell_count.setCellStyle(style);
                cell_count.setCellValue(testresultCount[k]);
            }

            for (int i = 0; i < objs.size(); i++) {
                HSSFRow row = sheet.createRow(i + rowIndex - 1);
                for (int j = 0; j < fieldList.size(); j++) {
                    HSSFCell cell = row.createCell(j);
                    cell.setCellStyle(style);
                    Field field = fieldList.get(j);

                    String fieldValue = covertAttrType(field, objs.get(i));
                    cell.setCellValue(fieldValue);
                }
            }

            return workbook;
        } catch (Exception e) {
            throw new RRException("导出失败",e);
        }
    }

    /**
     * 设置样式
     * @param workbook
     */
    private HSSFCellStyle setCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
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
     * 类型转换 转为String
     */
    private String covertAttrType(Field field, T obj) throws IllegalAccessException {
        if (field.get(obj) == null) {
            return "";
        }
        ExceVo exceVo = field.getAnnotation(ExceVo.class);
        String type = field.getType().getSimpleName();
        String format = exceVo.format();
        if ("Date".equals(type)) {
            return DateFormatUtils.format((Date)field.get(obj), "yyyy-MM-dd");
        }else {
            return field.get(obj).toString();
        }
    }
}

