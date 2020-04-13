package utils
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import internal.GlobalVariable
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ReadAndWriteExcel {


	@Keyword
	//public void writeDataIntoExcel(String name) throws IOException{
	public void demoKey(String name,String quater) throws IOException{
		FileInputStream fis = new FileInputStream("C:\\Users\\praveenraj_maregowda\\Desktop\\BridgeListFromAutomation.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		Row row = sheet.createRow(rowCount+1);
		Cell column1 = row.createCell(0);
		//	Cell cell2 = row.createCell(1);

		cell.setCellType("FileName");
		cell.setCellValue(name);
		cell1.setCellType("Quater");
		cell1.setCellValue(name);
		FileOutputStream fos = new FileOutputStream("C:\\Users\\praveenraj_maregowda\\Desktop\\BridgeListFromAutomation.xlsx");
		workbook.write(fos);
		fos.close();
	}

	public  Map <Object,Object> writeXLSXFile(String rowCount,String columnCount,String data) throws IOException {
		Map <Object,Object> map = new HashMap<Object,Object>();
		XSSFWorkbook wbObj = new XSSFWorkbook();
		XSSFSheet sheet = wbObj.createSheet("BridgeList");
		for (int row = 0; row < rowCount; row++) {
			Row rowObj = sheet.createRow(row);

			for (int col = 0; col < columnCount; col++) {
				Cell cell = rowObj.createCell(col);
				cell.setCellValue(data);
				map.put(row,data);
				//	logger.info("Writing " + row + " " + col + "  " + data);
			}
		}
		FileOutputStream fileOut = new FileOutputStream("C:\\Users\\praveenraj_maregowda\\Desktop\\BridgeListFromAutomation.xlsx");
		//FileOutputStream fileOut = new FileOutputStream(C\Users\\praveenraj_maregowda\\Desktop\\BridgeListFromAutomation.xlsx);
		wbObj.write(fileOut);
		//	wbObj.close();
		fileOut.flush();
		fileOut.close();
		return map;
	}


	public  Map <Object,Object> readFromXLSXFile(String SheetName,String path) throws IOException {
		Map <Object,Object> map = new HashMap<Object,Object>();
		XSSFWorkbook wbObj = new XSSFWorkbook();
		XSSFSheet sheet = wbObj.createSheet(SheetName);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		int columnCount=sheet.
				//for (int row = 0; row < rowCount; row++) {
				Row rowObj = sheet.createRow(row);

		for (int col = 0; col < columnCount; col++) {
			Cell cell = rowObj.createCell(col);
			cell.setCellValue(data);
			map.put(row,data);
			//	logger.info("Writing " + row + " " + col + "  " + data);
			//}
		}
		FileOutputStream fileOut = new FileOutputStream("C:\\Users\\praveenraj_maregowda\\Desktop\\BridgeListFromAutomation.xlsx");
		//FileOutputStream fileOut = new FileOutputStream(C\Users\\praveenraj_maregowda\\Desktop\\BridgeListFromAutomation.xlsx);
		wbObj.write(fileOut);
		//	wbObj.close();
		fileOut.flush();
		fileOut.close();
		return map;
	}

	////////////Read Data From Excel File//////////
	@Keyword
	public void readExcel(String filePath,String fileName,String sheetName) throws IOException{

		//Create an object of File class to open xlsx file

		File file =  new File(filePath+"\\"+fileName);

		//Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook Workbook = null;

		//Find the file extension by splitting file name in substring  and getting only extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		//Check condition if the file is xlsx file

		if(fileExtensionName.equals(".xlsx")){

			//If it is xlsx file then create object of XSSFWorkbook class

			Workbook = new XSSFWorkbook(inputStream);

		}

		//Check condition if the file is xls file

		else if(fileExtensionName.equals(".xls")){

			//If it is xls file then create object of HSSFWorkbook class

			Workbook = new HSSFWorkbook(inputStream);

		}

		//Read sheet inside the workbook by its name

		Sheet Sheet = Workbook.getSheet(sheetName);

		//Find number of rows in excel file

		int rowCount = Sheet.getLastRowNum()-Sheet.getFirstRowNum();

		//Create a loop over all the rows of excel file to read it

		for (int i = 0; i < rowCount+1; i++) {

			Row row = Sheet.getRow(i);

			//Create a loop to print cell values in a row

			for (int j = 0; j < row.getLastCellNum(); j++) {

				//Print Excel data in console

				System.out.print(row.getCell(j).getStringCellValue()+"|| ");

			}

			System.out.println();
		}

	}

	/*		//Main function is calling readExcel function to read data from excel file
	 public static void main(String...strings) throws IOException{
	 //Create an object of ReadGuru99ExcelFile class
	 ReadExcelFile objExcelFile = new ReadExcelFile();
	 //Prepare the path of excel file
	 String filePath = System.getProperty("user.dir")+"\\src\\excelExportAndFileIO";
	 //Call read file method of the class to read data
	 objExcelFile.readExcel(filePath,"ExportExcel.xlsx","ExcelGuru99Demo");
	 }*/

}

