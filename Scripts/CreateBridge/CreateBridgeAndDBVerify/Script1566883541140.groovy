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

/*def con = CustomKeywords.'utils.DbUtilities.MemSQLConnect'()
 
 String selectquery = 'select * from MTRC_SnOP.BP_CFG_PROFILE order by BP_CFG_PROFILE_ID desc'
 
 println selectquery
 // create the java statement
 Statement st = con.createStatement()
 // execute the query, and get a java resultset
 ResultSet rs = st.executeQuery(selectquery)
 
 con.eachRow(query, { def tp ->
	 println('The results from MeMSQL table will be :'+'\n' +[tp.BP_CFG_PROFILE_ID , tp.QUARTER_ID])
  })
 //fieldname << [tp.Field, tp.Type]
 */
 
 