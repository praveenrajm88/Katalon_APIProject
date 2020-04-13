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



WebUI.click(findTestObject('BridgeProfile/InProgressButton'))

String[] fromExceleList = new String[4]

for (int i = 0; i < 4; i++) {
    (fromExceleList[i]) = findTestData('AutoBridgeDataFile/InprogressBridgeData').getValue(i + 1, 1)
}


WebDriver driver = DriverFactory.getWebDriver()


/*take total count in the list and divide by the page num if mod == 0  than click on next page button  */
String BridgeCount = WebUI.getText(findTestObject('BridgeProfile/BridgeCount'))

int Totalcount = Integer.parseInt(BridgeCount.split('of')[1].toString().trim())


Boolean matched=false;
int pageCount=Math.round(Totalcount/5)
for (int page = 1; page <pageCount; page++) {
	for(int trow =1;trow<=5;trow++){

	if (((driver.findElement(By.xpath(('//*[@id="bridgeTable"]/tbody/tr[' + trow) + ']/td[1]')).getText().equalsIgnoreCase(

		fromExceleList[0]) && driver.findElement(By.xpath(('//*[@id="bridgeTable"]/tbody/tr[' + trow) + ']/td[2]')).getText().equalsIgnoreCase(

		fromExceleList[1])) && driver.findElement(By.xpath(('//*[@id="bridgeTable"]/tbody/tr[' + trow) + ']/td[3]')).getText().equalsIgnoreCase(

		fromExceleList[2])) && driver.findElement(By.xpath(('//*[@id="bridgeTable"]/tbody/tr[' + trow) + ']/td[6]')).getText().contains(fromExceleList[3]))

	  {

		driver.findElement(By.xpath(('//*[@id="bridgeTable"]/tbody/tr[' + trow) + ']/td[7]')).click()
		matched=true;
		 break

	}
	if (trow == 5) {

		WebUI.click(findTestObject('BridgeProfile/NextPageButton'))

	}
	}
	
	if(matched.equals(true)){
break
}

}

WebUI.click(findTestObject('BridgeProfile/EditButton'))

WebUI.delay(5)

	//to update and enter the value in consensus and commit and save
	int	excelRowCount = findTestData('AutoBridgeDataFile/ConsensusData').getRowNumbers()
	
	String[] fromExceleupdateList= new String[3]
		
	for(int i = 1 ;i<= excelRowCount;i++){
		for (int j = 0; j < 3; j++) {
			(fromExceleupdateList[j]) = findTestData('AutoBridgeDataFile/ConsensusData').getValue(j + 1, i)
		}

		for (int tRow =i+1; tRow <=5; tRow++) {
	
		if (
			driver.findElement(By.xpath('//app-consences[@mode="show"]/mat-card/mat-card-content/table/tr[' + tRow + ']/td[1]')).getText().equalsIgnoreCase(fromExceleupdateList[0]))
			
			{
				driver.findElement(By.xpath('//app-consences[@mode="show"]/mat-card/mat-card-content/table/tr['+ tRow +']/td[4]/mat-form-field/div/div/div[3]/input')).sendKeys(fromExceleupdateList[1].toString())
				
				driver.findElement(By.xpath('//app-consences[@mode="show"]/mat-card/mat-card-content/table/tr['+ tRow +']/td[5]/mat-form-field/div/div/div[3]/input')).sendKeys(fromExceleupdateList[2].toString())
					
				break
			}
		break
		}	
	}
		
		WebUI.click(findTestObject('BridgeProfile/ConsensusSaveButton'))
		WebUI.delay(5)
		
		//To add the CFG data in the bridge profile 
	//WebUI.scrollToElement(findTestObject('BridgeProfile/ProfileAddRowButton', 10))

//WebUI.click(findTestObject('BridgeProfile/ProfileAddRowButton'))

WebUI.delay(5)

//to update and enter the value in consensus and commit and save
int	CFGexcelRowCount = findTestData('AutoBridgeDataFile/ProfileCFGListData').getRowNumbers()

	
String[] fromExcelCFGList= new String[5]

	for(int i = 1 ;i<= CFGexcelRowCount;i++){
		for (int j = 0; j < 5; j++) {
			(fromExcelCFGList[j]) = findTestData('AutoBridgeDataFile/ProfileCFGListData').getValue(j + 1, i)
		}
		
		for (int tRow =i+1; tRow <=5; tRow++) {

		WebUI.click(findTestObject('BridgeProfile/ProfileAddRowButton'))
	
		driver.findElement(By.xpath('//*[@class="bridge-profile-holder"]/table/tr['+ tRow +']/td[1]/mat-form-field/div/div/div/input')).sendKeys(fromExcelCFGList[0].toString())

		driver.findElement(By.xpath('//*[@class="bridge-profile-holder"]/table/tr['+ tRow +']/td[2]/input')).sendKeys(fromExcelCFGList[1].toString())
			
		driver.findElement(By.xpath('//*[@class="bridge-profile-holder"]/table/tr['+ tRow +']/td[3]/input')).sendKeys(fromExcelCFGList[2].toString())
		
		driver.findElement(By.xpath('//*[@class="bridge-profile-holder"]/table/tr['+ tRow +']/td[4]/input')).sendKeys(fromExcelCFGList[3].toString())
		
		driver.findElement(By.xpath('//*[@class="bridge-profile-holder"]/table/tr['+ tRow +']/td[5]/input')).sendKeys(fromExcelCFGList[4].toString())
		 
		WebUI.click(findTestObject('BridgeProfile/CFGSaveButton'))
	break
	}
	}
WebUI.click(findTestObject('BridgeProfile/ProfileSaveButton'))

if (WebUI.getText(findTestObject('BridgeProfile/BridgeProfileAlertMsg')).contains('CFG profile saved'))
{
   WebUI.comment('Bridge CFG is saved Successfully')
   
   WebUI.takeScreenshot('C:\\Users\\praveenraj_maregowda\\Desktop\\AutoBridgeDataFiles\\AutomationScreenshots\\CreateNewBridge.png')
   
   WebUI.click(findTestObject('CreateBridge/BridgeProfileAlertMsgPopup'), FailureHandling.CONTINUE_ON_FAILURE)
   
}

WebUI.click(findTestObject('BridgeProfile/ProfileSubmitButton'))

WebUI.click(findTestObject('BridgeProfile/BridgeProfileRunButton'))

println('Inprogress Bridge is edited and submited successfully')
