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
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement

//Create new bridge test case
//calling the login page testcase
/*
WebUI.callTestCase(findTestCase('LoginToAutoBridge/AutoBridgeMainPage'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('CreateBridge/CreateNewBridgeButton'))

WebUI.delay(10)

WebUI.click(findTestObject('CreateBridge/QuarterDropDown'))

//WebUI.click(findTestObject('CreateBridge/QuarterValue', [('SelectQuarter') : quarter]))
WebUI.click(findTestObject('CreateBridge/QuarterValue'))

WebUI.click(findTestObject('CreateBridge/CommodityDropDown'))

//WebUI.sendKeys(findTestObject('CreateBridge/CommodityDropDown'), findTestData('AutoBridgeDataFile/NewCreateBridgeData').getValue(2,4))
WebUI.click(findTestObject('CreateBridge/Commodity'))

WebUI.click(findTestObject('CreateBridge/CsgIsgServerDropDown'))

WebUI.click(findTestObject('CreateBridge/CsgIsgServerValue'))

WebUI.sendKeys(findTestObject('CreateBridge/UploadFile'), 'C:\\Users\\praveenraj_maregowda\\Desktop\\cfg.xlsx')

WebUI.click(findTestObject('CreateBridge/UploadButton'))

//AlertText = WebUI.getText(findTestObject('CreateBridge/Alertmsgvalue'))

//WebUI.comment(AlertText)

WebUI.click(findTestObject('CreateBridge/AlertMsgPopup'), FailureHandling.CONTINUE_ON_FAILURE)
*/
def sql = CustomKeywords.'utils.DbUtilities.MemSQLConnect'()

String query = 'select * from MTRC_SnOP.BP_CFG_PROFILE order by BP_CFG_PROFILE_ID desc limit 1'

println(query)

// create the java statement 
////Statement st = con.createStatement() 
// execute the query, and get a java resultset 
//ResultSet rs = st.executeQuery(selectquery) 
sql.eachRow(query, { def tp ->
        println(('The results from MeMSQL table will be :' + '\n') + [tp.BP_CFG_PROFILE_ID, tp.QUARTER_ID])
    })

//fieldname << [tp.Field, tp.Type]
def list = CustomKeywords.'utils.readDataFromDB.readData'('MTRC_SnOP.BP_CFG_PROFILE', 'BP_CFG_PROFILE_ID', sql)
println (list)
