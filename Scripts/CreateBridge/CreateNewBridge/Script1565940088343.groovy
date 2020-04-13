import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import groovy.sql.Sql as Sql
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.apache.poi.xssf.usermodel.XSSFCell as XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow as XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import java.lang.String as String
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement

//Create new bridge test case
//calling the login page testcase

WebUI.callTestCase(findTestCase('LoginToAutoBridge/AutoBridgeMainPage'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('CreateBridge/CreateNewBridgeButton'))

WebUI.delay(10)


WebUI.click(findTestObject('CreateBridge/QuarterDropDown'))

WebDriver driver = DriverFactory.getWebDriver()

WebUI.delay(5)

//ExcelData data = findTestData('AutoBridgeDataFile/NewCreateBridgeData')

//test case is working fine . i want to pass the value form the excel now i have hardcoded .
	
//CustomKeywords.'utils.readDataFromDB.readData'('C:\\Users\\praveenraj_maregowda\\Desktop\\AutoBridgeDataFiles', 'NewCreateBridgeData', 'Sheet1')

/*String quartervalue = findTestData('AutoBridgeDataFile/NewCreateBridgeData').getValue(1, 1)
String commodityvalue = findTestData('AutoBridgeDataFile/NewCreateBridgeData').getValue(2, 1)
String server = findTestData('AutoBridgeDataFile/NewCreateBridgeData').getValue(3, 1)
*/

//WebElement Table = driver.findElement(By.xpath('//span[contains(text(),'+mapRegion(1,1)+"')]'))
//WebUI.click(findTestObject('CreateBridge/QuarterValue', [('SelectQuarter') : quarter]))
WebUI.click(findTestObject('CreateBridge/QuarterValue'))

//WebUI.click(driver.findElement(By.xpath('//span[contains(text(),'+quartervalue+')]')))

WebUI.click(findTestObject('CreateBridge/CommodityDropDown'))

//WebUI.sendKeys(findTestObject('CreateBridge/CommodityDropDown'),
//String quarter=  findTestData('AutoBridgeDataFile/NewCreateBridgeData').getValue(2,4)
//WebUI.click(driver.findElement(By.xpath('//span[contains(text(),'+WebUI.click(driver.findElement(By.xpath('//span[contains(text(),'+quartervalue+')]')))+')]')))
WebUI.click(findTestObject('CreateBridge/Commodity'))

WebUI.click(findTestObject('CreateBridge/CsgIsgServerDropDown'))

WebUI.click(findTestObject('CreateBridge/CsgIsgServerValue'))
//WebUI.click(driver.findElement(By.xpath('//span[contains(text(),'+server+')]')))

WebUI.sendKeys(findTestObject('CreateBridge/UploadFile'), 'C:\\Users\\praveenraj_maregowda\\Desktop\\cfg.xlsx')

WebUI.click(findTestObject('CreateBridge/UploadButton'))

String AlertText = WebUI.getText(findTestObject('CreateBridge/Alertmsgvalue'))

WebUI.comment(AlertText)

if (WebUI.getText(findTestObject('CreateBridge/Alertmsgvalue')).contains('File Uploaded Successfully'))
 {
	WebUI.comment('Bridge is created Successfully')
	
	WebUI.takeScreenshot('C:\\Users\\praveenraj_maregowda\\Desktop\\AutoBridgeDataFiles\\AutomationScreenshots\\CreateNewBridge.png')
	
	WebUI.click(findTestObject('CreateBridge/AlertMsgPopup'), FailureHandling.CONTINUE_ON_FAILURE)
	
}
 else if (WebUI.getText(findTestObject('CreateBridge/Alertmsgvalue')).contains('Error in the Uploaded File'))
 {
	 WebUI.comment('Error in the Uploaded File download and verify the error')
 
	 WebUI.takeScreenshot('C:\\Users\\praveenraj_maregowda\\Desktop\\AutoBridgeDataFiles\\AutomationScreenshots\\CreateNewBridge.png')
 	
	 WebUI.click(findTestObject('CreateBridge/AlertMsgPopup'), FailureHandling.CONTINUE_ON_FAILURE)
	 
	 WebUI.scrollToElement(findTestObject('CreateBridge/DownloadErrorFileButton'), 10)
	 
	 WebUI.click(findTestObject('CreateBridge/DownloadErrorFileButton'))
 }

