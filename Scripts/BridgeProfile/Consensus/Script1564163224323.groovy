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
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('BridgeProfile/EditInProgressBridge'),[:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebDriver driver = DriverFactory.getWebDriver()

WebElement Table = driver.findElement(By.xpath('//table[@class=\'w-100 bridge-table static-table ng-untouched ng-pristine ng-valid\']'))

List<WebElement> rows_table = Table.findElements(By.tagName('tr'))

int rows_count = rows_table.size()

String celltext = ''

String Region = findTestData('AutoBridgeDataFile/ConsensusData').getValue(1, 1)
String Consensus = findTestData('AutoBridgeDataFile/ConsensusData').getValue(2, 1)
String Comment = findTestData('AutoBridgeDataFile/ConsensusData').getValue(3, 1)

for (int row = 1; row < rows_count; row++) {
    List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName('td'))

    int columns_count = Columns_row.size()

    for (int column = 0; column < columns_count; column++) {
        celltext = Columns_row.get(column).getText() 
		if (Columns_row.get(1).getText().equals(Region) 
			//	&& Columns_row.get(2).getText().equalsIgnoreCase(commodity)
			//	&& Columns_row.get(5).getText().contains(status)
				)
		{
			WebUI.delay(5)
			WebUI.sendKeys(Columns_row.get(3), Consensus)

		}
		
    }
}

