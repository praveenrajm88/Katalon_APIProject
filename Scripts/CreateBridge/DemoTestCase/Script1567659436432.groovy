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


//Test data Prep  > You have get this data from Excel as per implementation
WebUI.callTestCase(findTestCase('LoginToAutoBridge/AutoBridgeMainPage'), [:], FailureHandling.STOP_ON_FAILURE)


//get data form excel

//def fromExcele = 'cfg.xlsx,FY20Q4,HDD'

String[] fromExceleList= new String[3]

int r = findTestData('AutoBridgeDataFile/InprogressBridgeData').getRowNumbers()

int col = findTestData('AutoBridgeDataFile/InprogressBridgeData').getColumnNumbers()

//WebUI.delay(5)

WebUI.click(findTestObject('BridgeProfile/InProgressButton'))

for (int i = 0; i < 3; i++) {
	fromExceleList[i] = findTestData('AutoBridgeDataFile/InprogressBridgeData').getValue(i + 1, 1)
}

//Dont hard code Xpath, insted you use profiles, Configfile or Global variables
def xpathForNext = '//button[@class=\'mat-paginator-navigation-next mat-icon-button\']'

//Dynamic  Test Object creation
TestObject nextBtn = new TestObject()

nextBtn.addProperty('xpath', ConditionType.EQUALS, xpathForNext)

WebDriver driver = DriverFactory.getWebDriver()

int page = 1

int line = 1

int matchCount = 0

def matched = false

boolean nextPageSeach = true

/*Make it as Method where ever required e.g : you can use same method to verify or search
		 *This method with search for a Array  match with first N columns in the table
		 * While > runs till match is found in all page
		 * for > runs till match is found in a page */
while (WebUI.verifyElementClickable(findTestObject('BridgeProfile/NextPageButton'), FailureHandling.OPTIONAL)) {

    for (int tRow = 1; tRow <= 5; tRow++) {
        matchCount = 0

        for (int tCol = 1; tCol <= 3; tCol++) {
            String colSting = driver.findElement(By.xpath(((('//*[@id="bridgeTable"]/tbody/tr[' + line) + ']/td[') + tCol) +']')).getText()

            if (colSting.equalsIgnoreCase(fromExceleList[(tCol - 1)])) {
                println('Matched : ' + colSting)

                matched = true

                matchCount++
            } else {
                break
            }
        }
        
        if (matched && (matchCount == 3)) {
            driver.findElement(By.xpath(('//*[@id="bridgeTable"]/tbody/tr[' + line) + ']/td[7]')).click()

            nextPageSeach = false

            break
        } else {
            line++
        }
    }
    
    if (nextPageSeach == true) {
		WebUI.delay(10)
        WebUI.click(nextBtn)

        page++

        line = 1
    } else {
        break
    }
}

println((('Match Fount in ' + page) + ' Line : ') + line)

