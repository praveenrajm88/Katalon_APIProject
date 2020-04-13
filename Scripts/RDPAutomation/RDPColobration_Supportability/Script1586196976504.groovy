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

WebUI.click(findTestObjects('RDPObjects/Supportability'))

int RowCount = findTestData('RDPAutomationDataFile/ReplenishmentTestData').getRowNumbers()
int ColCount = findTestData('RDPAutomationDataFile/SupportabilityTestData').getRowNumbers()

for (int i = 0; i <=RowCount;i++){
	for (int j =0;j<=4 ;j++){
	
	WebUI.sendKeys(findTestObject('RDPObjests/SupportabilityPRN'), findTestData('RDPAutomationDataFile/SupportabilityTestData').getValue(j+1, i))
	WebUI.sendKeys(findTestObject('RDPObjects/FGAField'),findTestData('RDPAutomationDataFile/ReplenishmentTestData').getValue(j + 2, i))
	
	WebUI.click(findTestObject('RDPObjects/FromWeek'))
}

}

