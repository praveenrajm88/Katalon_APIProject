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

WebUI.callTestCase(findTestCase('CreateBridge/CreateNewBridge'), [('Quarter') : '', ('Commodity') : '', ('CSGISGServer') : ''], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('CreateBridge/ProceedToBridgeProfile'))

quartertext = WebUI.getText(findTestObject('BridgeProfile/QuarterNonEditValue'))

commoditytext = WebUI.getText(findTestObject('BridgeProfile/CommodilyNonEditValue'))

CSGISGtext = WebUI.getText(findTestObject('BridgeProfile/CSGISGServerNonEditValue'))

WebUI.comment(quartertext)

WebUI.comment(commoditytext)

WebUI.comment(CSGISGtext)

WebUI.delay(5)

WebUI.click(findTestObject('BridgeProfile/CFGUploadHeader'))

CFGQuater = WebUI.getText(findTestObject('CreateBridge/QuarterValue'))

CFGCommodity = WebUI.getText(findTestObject('CreateBridge/CommodityDropDown'))

CFGServer = WebUI.getText(findTestObject('CreateBridge/CsgIsgServerValue'))

WebUI.comment(CFGQuater)

WebUI.comment(CFGCommodity)

WebUI.comment(CFGServer)

if (quartertext == CFGQuater) { 
	println("true")
    if (commoditytext == CFGCommodity) {println("true")
        if (CSGISGtext == CFGServer) {println("true")
        }
    }
}

