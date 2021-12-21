package runners;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelReader;

public class MainRunner {

	public static void main(String[] args) throws InvalidFormatException, IOException {

//		createExampleFeature("test-auto", "Sheet1", "TestRekonWithExcel");
//		createExampleFeature("auto-transaction-data", "Recon", "ReportReconPCIn");
		createExampleFeature2("auto-transaction-data", "Recon", "ReportReconPCIn");
	}

	public static void createExampleFeature(String excelName, String sheetName, String featureFileName)
			throws InvalidFormatException, IOException {
		String userDir = System.getProperty("user.dir") + "\\";
		String filePath = userDir + "src\\test\\resources\\features\\ReportRecon\\" + featureFileName + ".feature";
		BufferedReader br = new BufferedReader(new FileReader(filePath));

		ExcelReader xlsReader = new ExcelReader();
		String excelFilePath = userDir + "xlsdata\\" + excelName + ".xlsx";
		Sheet sheet = xlsReader.getSheetByName(excelFilePath, sheetName);
		Integer lastRowNum = sheet.getLastRowNum();
		DataFormatter formatter = new DataFormatter();
		
		for (int i = 0; i < lastRowNum; i++) {
			Row row = sheet.getRow(i);
			if (StringUtils.isEmpty(String.valueOf(row.getCell(0))) == true
					|| StringUtils.isWhitespace(String.valueOf(row.getCell(0))) == true
					|| StringUtils.isBlank(String.valueOf(row.getCell(0))) == true
					|| String.valueOf(row.getCell(0)).length() == 0 || row.getCell(0) == null) {

				lastRowNum = i - 1;
			}
		}

		try {
			String line;
			int idLine = 0;
			String oldText = "";
			String newText = "";
			while ((line = br.readLine()) != null) {
//				System.out.println(idLine + " " + line);
				oldText += line + System.lineSeparator();
				idLine++;
				if (line.equalsIgnoreCase("Examples:")) {
					break;
				}
			}
			newText = oldText;
			for (int i = 0; i < lastRowNum + 1; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < row.getLastCellNum(); j++) {
					String val = formatter.formatCellValue(row.getCell(j));
					newText += "|" + val;
				}
				newText += "|" + System.lineSeparator();
			}
			System.out.println(newText);
			FileWriter writer = new FileWriter(filePath);
			writer.append(newText);
			writer.close();
		} catch (Exception e) {
			br.close();
		}
	}

	public static void createExampleFeature2(String excelName, String sheetName, String featureFileName)
			throws InvalidFormatException, IOException {
		String userDir = System.getProperty("user.dir") + "\\";
		String filePath = userDir + "src\\test\\resources\\features\\ReportRecon\\" + featureFileName + ".feature";
		BufferedReader br = new BufferedReader(new FileReader(filePath));

		ExcelReader xlsReader = new ExcelReader();
		String excelFilePath = userDir + "xlsdata\\" + excelName + ".xlsx";
		Sheet sheet = xlsReader.getSheetByName(excelFilePath, sheetName);
		Integer lastRowNum = sheet.getLastRowNum();
		DataFormatter formatter = new DataFormatter();

		for (int i = 0; i < lastRowNum; i++) {
			Row row = sheet.getRow(i);
			if (StringUtils.isEmpty(String.valueOf(row.getCell(0))) == true
					|| StringUtils.isWhitespace(String.valueOf(row.getCell(0))) == true
					|| StringUtils.isBlank(String.valueOf(row.getCell(0))) == true
					|| String.valueOf(row.getCell(0)).length() == 0 || row.getCell(0) == null) {

				lastRowNum = i - 1;
			}
		}

		try {
			String line;
			int idLine = 0;
			String oldText = "";
			String newText = "";
			while ((line = br.readLine()) != null) {
//				System.out.println(idLine + " " + line);
				oldText += line + System.lineSeparator();
				idLine++;
				if (line.equalsIgnoreCase("Examples:")) {
					break;
				}
			}
			newText = oldText;
//			for (int i = 1; i < lastRowNum + 1; i++) {
//				Row row = sheet.getRow(i);
//				for (int j = 0; j < row.getLastCellNum(); j++) {
//					String val = formatter.formatCellValue(row.getCell(j));
//					newText += "|" + val;
//				}
//				newText += "|" + System.lineSeparator();
//			}
			Integer iScen = null;
			for (int i = 1; i < lastRowNum + 1; i++) {
				if(iScen != null && (i==iScen || i==iScen-1)) {
					if (i == iScen) {
						iScen = null;
						continue;
					}
					continue;
				}
				Row row = sheet.getRow(i);
				if (formatter.formatCellValue(row.getCell(0))
						.equalsIgnoreCase(formatter.formatCellValue(sheet.getRow(i + 1).getCell(0)))
						&& formatter.formatCellValue(row.getCell(0))
								.equalsIgnoreCase(formatter.formatCellValue(sheet.getRow(i + 2).getCell(0)))) {
					iScen = i + 2;
					for (int j = 0; j < row.getLastCellNum(); j++) {
						String val1 = formatter.formatCellValue(sheet.getRow(i).getCell(j));
						String val2 = formatter.formatCellValue(sheet.getRow(i + 1).getCell(j));
						String val3 = formatter.formatCellValue(sheet.getRow(i + 2).getCell(j));
						newText += "|" + val1 + ";" + val2 + ";" + val3;
					}

				} else {
					for (int j = 0; j < row.getLastCellNum(); j++) {
						String val = formatter.formatCellValue(row.getCell(j));
						newText += "|" + val;
					}
				}
				newText += "|" + System.lineSeparator();
			}
			System.out.println(newText);
			FileWriter writer = new FileWriter(filePath);
			writer.append(newText);
			writer.close();
		} catch (Exception e) {
			br.close();
		}
	}
}
