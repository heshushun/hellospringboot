package springboot.hello.hellospringboot.common.utils;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

	/**
	 * ����excel�ļ�
	 *
	 * @param dataList  ���ݼ���
	 * @param excelName  excel������
	 * @param headers  ͷ����Ϣ
	 * @throws Exception
	 */
	public static XSSFWorkbook listToXlsx(List<Map<String, String>> dataList, String excelName, String[] headers) throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook();
		// ����sheet��
		XSSFSheet sheet = wb.createSheet(excelName);
		sheet.setDefaultColumnWidth(20);

		XSSFRow headerRow = sheet.createRow(0);
		XSSFCellStyle cellStyle = wb.createCellStyle();

		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 11);
		font.setFontName("����");
		font.setBold(true);

		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setFont(font);

		// ����ͷ��
		XSSFCell headCell = null;
		int headLen = headers.length;
		for (int i = 0; i < headLen; i++) {
			headCell = headerRow.createCell(i);
			headCell.setCellValue(headers[i]);
			headCell.setCellStyle(cellStyle);
		}

		// ����������Ԫ��
		int dataLen = dataList.size();
		XSSFRow dataRow = null;
		Map<String, String> dataMap = null;
		for (int i = 0; i < dataLen; i++) {
			dataRow = sheet.createRow(i + 1);
			dataMap = dataList.get(i);
			for (int j = 0; j < headLen; j++) {
				dataRow.createCell(j).setCellValue(dataMap.get(String.valueOf(j + 1)));
			}
		}
		return wb;
	}


}
