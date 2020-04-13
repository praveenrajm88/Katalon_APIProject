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
import org.apache.poi.ss.usermodel.Cell as Cell
import org.apache.poi.ss.usermodel.Row as Row
import java.io.File as File
import org.apache.poi.hssf.usermodel.HSSFWorkbook as HSSFWorkbook
import org.apache.poi.ss.usermodel.Sheet as Sheet
import org.apache.poi.ss.usermodel.Workbook as Workbook

//Test data Prep  > You have get this data from Excel as per implementation
WebUI.callTestCase(findTestCase('LoginToAutoBridge/AutoBridgeMainPage'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebUI.click(findTestObject('BridgeProfile/CompletedButton'))

WebUI.delay(10)

String[] fromExceleList = new String[4]

for (int i = 0; i < 4; i++) {
    (fromExceleList[i]) = findTestData('AutoBridgeDataFile/CompletedBridgeData').getValue(i + 1, 1)
}

//Dont hard code Xpath, insted you use profiles, Configfile or Global variables
def xpathForNext = '//button[@class=\'mat-paginator-navigation-next mat-icon-button\']'

WebDriver driver = DriverFactory.getWebDriver()

/*take total count in the list and divide by the page num if mod == 0  than click on next page button  */
String BridgeCount = WebUI.getText(findTestObject('BridgeProfile/BridgeCount'))

int Totalcount = Integer.parseInt(BridgeCount.split('of')[1].toString().trim())

//CustomKeywords.'utils.DataReadFromTable.ReadDataFromTable'(Totalcount, 7, 'BridgeProfile/NextPageButton', 'bridgeTable')


for (int tRow = 1; tRow < (Totalcount + 1); tRow++) {
    if ((tRow % 5) == 0) {
        WebUI.click(findTestObject('BridgeProfile/NextPageButton'))
    }
    
    if (((driver.findElement(By.xpath(('//*[@id="bridgeTable"]/tbody/tr[' + tRow) + ']/td[1]')).getText().equalsIgnoreCase(
        fromExceleList[0]) && driver.findElement(By.xpath(('//*[@id="bridgeTable"]/tbody/tr[' + tRow) + ']/td[2]')).getText().equalsIgnoreCase(
        fromExceleList[1])) && driver.findElement(By.xpath(('//*[@id="bridgeTable"]/tbody/tr[' + tRow) + ']/td[3]')).getText().equalsIgnoreCase(
        fromExceleList[2])) && driver.findElement(By.xpath(('//*[@id="bridgeTable"]/tbody/tr[' + tRow) + ']/td[6]')).getText().contains(fromExceleList[3])) 
	{
        driver.findElement(By.xpath(('//*[@id="bridgeTable"]/tbody/tr[' + tRow) + ']/td[7]')).click()

        break
    }
}

WebUI.click(findTestObject('BridgeProfile/CopyButton'))

WebUI.click(findTestObject('BridgeProfile/CopyBridgeQuarterSelect'))

WebUI.click(findTestObject('CreateBridge/QuarterValue'))

WebUI.click(findTestObject('BridgeProfile/CopyBridgeOKButton'))

WebUI.getText(findTestObject('CreateBridge/Alertmsgvalue'))

if (WebUI.getText(findTestObject('CreateBridge/Alertmsgvalue')) == 'Bridge profile has been copied') {
    WebUI.comment('The Bridge is copied successfully')
} else {
    WebUI.comment('Error in copy Bridge')
}

