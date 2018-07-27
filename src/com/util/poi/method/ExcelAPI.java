package com.util.poi.method;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelAPI {
	InputStream is;
	private List<List<String>> data;
	private Sheet sheet;
	private Workbook workbook;

	public ExcelAPI() {
	}

	public ExcelAPI(File file) throws IOException {
		String fileName = file.getName();
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		if (!".xls".equals(suffix) && !".xlsx".equals(suffix)) {
			return;
		}
		is = new FileInputStream(file);
		data = new ArrayList<>();

		if (suffix.equals(".xlsx")) {
			workbook = new XSSFWorkbook(is);
		} else if (suffix.equals(".xls")) {
			workbook = new HSSFWorkbook(is);
		}

		sheet = workbook.createSheet();
		workbook.setForceFormulaRecalculation(true);
		sheet.setForceFormulaRecalculation(true);
	}

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public List<List<String>> getData() {
		return data;
	}

	public void setData(List<List<String>> data) {
		this.data = data;
	}

	public void openExcel() {
		// 循环每一页，并处理当前循环页
		for (Sheet tempSheet : workbook) {
			if (tempSheet == null) {
				continue;
			}
			int lastRowNum = tempSheet.getLastRowNum();
			// 处理当前页，循环读取每一行
			for (int rowNum = 0; rowNum <= lastRowNum; rowNum++) {
				Row row = tempSheet.getRow(rowNum);
				if (row == null) {
					break;
				}
				int minColIx = row.getFirstCellNum();
				int maxColIx = row.getLastCellNum();
				List<String> rowList = new ArrayList<String>();
				for (int colIx = minColIx; colIx < maxColIx; colIx++) {
					Cell cell = row.getCell(colIx);
					if (cell == null) {
						continue;
					}
					rowList.add(ExcelAPI.getCellValue(cell));
				}
				data.add(rowList);
			}
		}
	}

	public static String getCellValue(Cell cell) {
		String value = null;
		if (cell != null) {
			switch (cell.getCellTypeEnum()) {
			case NUMERIC: // 数字
				DecimalFormat df = new DecimalFormat("0");
				value = String.valueOf(df.format(cell.getNumericCellValue()));
				break;
			case STRING: // 字符串
				value = cell.getStringCellValue();
				break;
			case BOOLEAN: // Boolean
				value = cell.getBooleanCellValue() + "";
				break;
			case FORMULA: // 公式
				if (DateUtil.isCellDateFormatted(cell)) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
					value = dateFormat.format(cell.getDateCellValue());
				} else {
					value = String.valueOf(cell.getNumericCellValue());
				}
				break;
			case BLANK: // 空值
				value = "";
				break;
			case ERROR: // 故障
				value = "非法字符";
				break;
			default:
				value = "未知类型";
				break;
			}
		}
		return value;
	}
}
