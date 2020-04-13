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

WebUI.callTestCase(findTestCase('LoginToAutoBridge/AutoBridgeMainPage'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Report/ReportsHeader'))

WebUI.delay(5)


WebUI.click(findTestObject('CreateBridge/QuarterDropDown'))

//WebUI.click(findTestObject('CreateBridge/QuarterValue', [('SelectQuarter') : quarter]))
WebUI.click(findTestObject('CreateBridge/QuarterValue'))

WebUI.click(findTestObject('CreateBridge/CommodityDropDown'))

//WebUI.sendKeys(findTestObject('CreateBridge/CommodityDropDown'), findTestData('AutoBridgeDataFile/NewCreateBridgeData').getValue(2,4))
WebUI.click(findTestObject('CreateBridge/Commodity'))

WebUI.click(findTestObject('CreateBridge/CsgIsgServerDropDown'))

WebUI.click(findTestObject('CreateBridge/CsgIsgServerValue'))

WebUI.click(findTestObject('Report/RegionFilter'))

WebUI.click(findTestObject('Report/RegionValue'))

WebUI.click(findTestObject('Report/GenerateButton'))

WebUI.delay(10)

WebUI.click(findTestObject('Report/ExportReport'))

WebUI.click(findTestObject('Report/ExportPDF')) 

