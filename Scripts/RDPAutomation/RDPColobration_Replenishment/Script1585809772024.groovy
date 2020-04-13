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

WebUI.callTestCase(findTestCase('RDPAutomation/RDP_HomePage'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('RDPObjects/Collaboration'))

WebUI.click(findTestObject('RDPObjects/Replenishment'))

int excelRowCount = findTestData('RDPAutomationDataFile/ReplenishmentTestData').getRowNumbers()

for (int i = 1; i <= excelRowCount; i++) {
    for (int j = 0; j < 8; j++) {
       // (fromExceleupdateList[j]) = findTestData('RDPAutomationDataFile/ReplenishmentTestData').getValue(j + 1, i)
  
		WebUI.sendKeys(findTestObject('RDPObjects/PRNField'),findTestData('RDPAutomationDataFile/ReplenishmentTestData').getValue(j + 1, i))
		
		WebUI.sendKeys(findTestObject('RDPObjects/FGAField'),findTestData('RDPAutomationDataFile/ReplenishmentTestData').getValue(j + 2, i))	
		
		WebUI.click(findTestObject('RDPObjects/FromWeek'))
		
		WebUI.selectOptionByLabel(findTestObject('RDPObjects/FromWeek'), findTestData('RDPAutomationDataFile/ReplenishmentTestData').getValue(j + 3, i), false)
		
		WebUI.click(findTestObject('RDPObjects/ToWeek'))
		
		WebUI.selectOptionByLabel(findTestObject('RDPObjects/ToWeek'), findTestData('RDPAutomationDataFile/ReplenishmentTestData').getValue(j + 4, i), false)
		
		WebUI.click(findTestObject('RDPObjects/Retailer'))
		
		WebUI.selectOptionByLabel(findTestObject('RDPObjects/Retailer'), findTestData('RDPAutomationDataFile/ReplenishmentTestData').getValue(j + 5, i), false)
		
		WebUI.click(findTestObject('RDPObjects/Product'))
		
		WebUI.selectOptionByLabel(findTestObject('RDPObjects/Product'), findTestData('RDPAutomationDataFile/ReplenishmentTestData').getValue(j + 6, i), false)
		
		WebUI.click(findTestObject('RDPObjects/Season'))
		
		WebUI.selectOptionByLabel(findTestObject('RDPObjects/Season'), findTestData('RDPAutomationDataFile/ReplenishmentTestData').getValue(j + 7, i), false)
		
		WebUI.click(findTestObject('RDPObjects/Platform'))
		
		WebUI.selectOptionByLabel(findTestObject('RDPObjects/Platform'), findTestData('RDPAutomationDataFile/ReplenishmentTestData').getValue(j + 8, i), false)
		
		WebUI.click(findTestObject('RDPObjects/Wave'))
		
		WebUI.selectOptionByValue(findTestObject('RDPObjects/Wave'), findTestData('RDPAutomationDataFile/ReplenishmentTestData').getValue(j + 9, i), false)
		
		WebUI.click(findTestObject('RDPObjects/SearchButton'))
	}
}
 
