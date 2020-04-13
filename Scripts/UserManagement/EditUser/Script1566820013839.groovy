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


WebUI.callTestCase(findTestCase('LoginToAutoBridge/AutoBridgeMainPage'), [:], FailureHandling.STOP_ON_FAILURE)
//User management adding new user
WebUI.click(findTestObject('Administration/Administration'))

WebUI.click(findTestObject('Administration/UserManagement/UserManagement'))

WebUI.click(findTestObject('Administration/UserManagement/EditUser'))

WebUI.delay(5)

int	UserexcelRowCount = findTestData('AutoBridgeDataFile/CreateNewUsersData').getRowNumbers()

WebDriver driver = DriverFactory.getWebDriver()

String[] fromExcelUserList= new String[6]
	
	for(int i = 1 ;i<= UserexcelRowCount;i++){
		for (int j = 0; j <=5; j++) {
			(fromExcelUserList[j]) = findTestData('AutoBridgeDataFile/CreateNewUsersData').getValue(j + 1, i)
		}

	//	for (int tRow =1; tRow <=i; tRow++) {
			WebUI.clearText(findTestObject('Administration/UserManagement/SearchUser'))
			WebUI.sendKeys(findTestObject('Administration/UserManagement/SearchUser'),(fromExcelUserList[0].toString()))
		String a =driver.findElement(By.xpath('//*[@class="w-100 bridge-table mat-table"]/tbody/tr['+ i +']/td[1]')).getText()
		String b =driver.findElement(By.xpath('//*[@class="w-100 bridge-table mat-table"]/tbody/tr['+ i +']/td[2]')).getText()
		String c =driver.findElement(By.xpath('//*[@class="w-100 bridge-table mat-table"]/tbody/tr['+ i +']/td[3]')).getText()
		String d =driver.findElement(By.xpath('//*[@class="w-100 bridge-table mat-table"]/tbody/tr['+ i +']/td[4]')).getText()
		String e =driver.findElement(By.xpath('//*[@class="w-100 bridge-table mat-table"]/tbody/tr['+ i +']/td[5]')).getText()
		
			if (
				driver.findElement(By.xpath('//*[@class="w-100 bridge-table mat-table"]/tbody/tr['+ i +']/td[1]')).getText().equalsIgnoreCase(fromExcelUserList[0])
			&&	driver.findElement(By.xpath('//*[@class="w-100 bridge-table mat-table"]/tbody/tr['+ i +']/td[2]')).getText().equalsIgnoreCase(fromExcelUserList[1])
			&&	driver.findElement(By.xpath('//*[@class="w-100 bridge-table mat-table"]/tbody/tr['+ i +']/td[3]')).getText().equalsIgnoreCase(fromExcelUserList[2])
			&&	driver.findElement(By.xpath('//*[@class="w-100 bridge-table mat-table"]/tbody/tr['+ i +']/td[4]')).getText().equalsIgnoreCase(fromExcelUserList[3])
			&&	driver.findElement(By.xpath('//*[@class="w-100 bridge-table mat-table"]/tbody/tr['+ i +']/td[5]')).getText().equalsIgnoreCase(fromExcelUserList[4])
											
				) 
				
				   {
				 	 driver.findElement(By.xpath('//*[@class="w-100 bridge-table mat-table"]/tbody/tr[' + i + ']/td[7]/div/button[1]/span')).click()
				  println('click on edit button')
				  WebUI.clearText(findTestObject('Administration/UserManagement/FullName'))
				  
				  WebUI.sendKeys(findTestObject('Administration/UserManagement/FullName'), (fromExcelUserList[1]).toString())
				  
				  WebUI.clearText(findTestObject('Administration/UserManagement/DomainName'))
				  
				  WebUI.sendKeys(findTestObject('Administration/UserManagement/DomainName'), (fromExcelUserList[2]).toString())
				  
				  WebUI.clearText(findTestObject('Administration/UserManagement/EmailId'))
				  
				  WebUI.sendKeys(findTestObject('Administration/UserManagement/EmailId'), (fromExcelUserList[3]).toString())
				  
				//  WebUI.clearText(findTestObject('Administration/UserManagement/UserRole'))
				  
				  WebUI.click(findTestObject('Administration/UserManagement/UserRole'))
				  
				  WebUI.click(findTestObject('Administration/UserManagement/UserRole'),(fromExcelUserList[4]).toString())
				  
				  WebUI.sendKeys(findTestObject('Administration/UserManagement/IsActive'), (fromExcelUserList[5]).toString())
		
				  WebUI.click(findTestObject('Administration/UserManagement/UserUpdateButton'))
					//User edited Successfully
				  
				     }
break
	}



