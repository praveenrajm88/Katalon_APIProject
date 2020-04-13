package utils

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static org.junit.Assert.*
import java.awt.List as List
import org.junit.Test as Test
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import internal.GlobalVariable as GlobalVariable

import groovy.sql.Sql as Sql
import org.apache.poi.xssf.usermodel.XSSFCell as XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow as XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import java.lang.String as String
import org.openqa.selenium.Keys as Keys

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class DataReadFromTable {

	@Keyword
	public void ReadDataFromTable(int rowCount,int columnCount,String nextButton,String tableName){
		Map<Object,Object> map= new HashMap<Object,Object>()
		List<String> list = new ArrayList<String>()
		WebDriver driver = DriverFactory.getWebDriver()
		for(int tRow=1;tRow<rowCount+1 ;tRow++){
			if(tRow%5==0){
				WebUI.click(findTestObject(nextButton))
			}
			for(int tCol=1;tCol<columnCount ;tCol++){
				map.put(tRow,driver.findElement(By.xpath('//*[@id="'+tableName+'"]/tbody/tr['+tRow+']/td['+tCol+']')).getText())
				list.add(tRow,driver.findElement(By.xpath('//*[@id="'+tableName+'"]/tbody/tr['+tRow+']/td['+tCol+']')).getText())
			println(map)
			println(list)
				}
		}
	}
}
