package utils

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
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

public class SearchSelect {

	@Keyword
	def Search(){
		//Navigation
		WebUI.openBrowser('')

		WebUI.navigateToUrl('http://ausdwe2open01.aus.amer.dell.com:8080/#/autoBridging')

		WebUI.click(findTestObject('Object Repository/Page_PpcmApp/a_Automated Bridging'))

		WebUI.click(findTestObject('Object Repository/Page_PpcmApp/div_S_Tentu'))

		WebUI.click(findTestObject('Object Repository/Page_PpcmApp/span_PraveenRaj Maregowda'))

		//Test data Prep  > You have get this data from Excel as per implementation
		def fromExcele = 'CLIENT-NEW709.xlsx,CY00Q4,SSD'

		String[] fromExceleList = fromExcele.split(',')

		//Dont hard code Xpath, insted you use profiles, Configfile or Global variables
		def xpathForNext = '//button[@class=\'mat-paginator-navigation-next mat-icon-button\']'

		//Dynamic  Test Object creation
		TestObject nextBtn = new TestObject()

		nextBtn.addProperty('xpath', ConditionType.EQUALS, xpathForNext)

		WebUI.switchToFrame(findTestObject('Object Repository/Page_PpcmApp/iframe'), 10, FailureHandling.OPTIONAL)

		//Extracting driver from DriverFactory
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
		while (WebUI.verifyElementClickable(nextBtn, FailureHandling.OPTIONAL)) {
			//List<WebElement> NumberOfRows=driver.findElements(By.xpath("//*[@id=\"bridgeTable\"]/tbody/tr/td[1]"))
			for (int tRow = 1; tRow <= 5; tRow++) {
				matchCount = 0

				for (int tCol = 1; tCol <= 3; tCol++) {
					String colSting = driver.findElement(By.xpath(((('//*[@id="bridgeTable"]/tbody/tr[' + line) + ']/td[') + j) +
							']')).getText()

					if (colSting.equalsIgnoreCase(fromExceleList[(j - 1)])) {
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
				WebUI.click(nextBtn)

				page++

				line = 1
			} else {
				break
			}
		}

		println((('Match Fount in ' + page) + ' Line : ') + line)


	}
}
