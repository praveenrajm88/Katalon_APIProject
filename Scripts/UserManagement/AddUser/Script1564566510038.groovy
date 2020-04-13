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
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

WebUI.callTestCase(findTestCase('LoginToAutoBridge/AutoBridgeMainPage'), [:], FailureHandling.CONTINUE_ON_FAILURE)

//User management adding new user
WebUI.click(findTestObject('Administration/Administration'))

WebUI.click(findTestObject('Administration/UserManagement/UserManagement'))

WebUI.sendKeys(findTestObject('Administration/UserManagement/UserName'), 'pravesena')

WebUI.sendKeys(findTestObject('Administration/UserManagement/FullName'), 'praveenssraj')

WebUI.sendKeys(findTestObject('Administration/UserManagement/EmailId'), 'praveena@gmail.com')

WebUI.sendKeys(findTestObject('Administration/UserManagement/DomainName'), 'Asia')

WebUI.click(findTestObject('Administration/UserManagement/UserRole'))

WebUI.click(findTestObject('Administration/UserManagement/UserRoleValue'))

WebUI.click(findTestObject('Administration/UserManagement/IsActive'))

WebUI.click(findTestObject('Administration/UserManagement/IsActiveValue'))

WebUI.click(findTestObject('Administration/UserManagement/SaveButton'))

//'Getting the text from the alert and storing it in Variable'
//not_run: String AlertText = driver.switchTo().alert().getText()
AlertText = WebUI.getText(findTestObject('Administration/UserManagement/PopupMsg'))

//'Verifying the Actual and Expected text from Alert'
//WebUI.verifyEqual(AlertText, 'Record saved Successfully')
if (WebUI.getText(findTestObject('Administration/UserManagement/PopupMsg')) == 'User saved Successfully') {
    WebUI.comment('AlertText')

    WebUI.comment('User is created Successfully')
} else {
    WebUI.comment('User is Already Exist Please Create New User')
}

WebUI.takeScreenshot('C:\\Users\\praveenraj_maregowda\\Desktop\\AutoBridgeDataFiles\\AutomationScreenshots\\AddUser.png')

WebUI.click(findTestObject('Administration/UserManagement/PopupClickOk'), FailureHandling.CONTINUE_ON_FAILURE)

