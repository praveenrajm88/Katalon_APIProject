/**
 * Author : Anurag Deb
 * Description : Below code is for establishing a database connection, execute a query, close  the connection
 *  
 */

package utils
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import groovy.sql.Sql
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.sql.Connection
import java.sql.SQLException
import java.sql.SQLTimeoutException
import java.sql.SQLRecoverableException
import com.kms.katalon.core.logging.KeywordLogger


class DbUtilities {
	private static Connection connection = null;

	@Keyword
	def MemSQLConnect() {
		try{

			return Sql.newInstance(GlobalVariable.Host, GlobalVariable.User, GlobalVariable.Password,GlobalVariable.Driver);
		}
		finally {
			println("closing");
		}
	}
	@Keyword
	def GreenPlumConnect() {
		try{
			Class.forName(GlobalVariable.Driver)
			Connection conn = DriverManager.getConnection(GlobalVariable.Host , GlobalVariable.User , GlobalVariable.Password)
			return conn
		}
		finally {
			println("closing");
		}
	}
	@Keyword
	def OracleConnect(){

		//return Sql.newInstance("jdbc:oracle:thin:@//"+GlobalVariable.Host+":1521/"+GlobalVariable.Service_Name,GlobalVariable.Username,GlobalVariable.Password)

		try{
			String conn = "jdbc:oracle:thin:@//"+GlobalVariable.Host+":1521/"+GlobalVariable.Service_Name
			println conn

			if(connection != null && !connection.isClosed()){

				println ("Closing the previous existing connection")
				connection.close()

			}

			connection = DriverManager.getConnection(conn, GlobalVariable.Username,GlobalVariable.Password)

			return connection
		}

		catch(SQLRecoverableException e){
			println ("SQL Exception: " + e.getMessage() + "/" + e.getCause());
			println ("Kindly Check if the Profile is selected or not ")

		}

		catch (SQLException e2) {
			println("SQL CMD Exception: " + e2.getMessage() + "/" + e2.getCause());
		}

		catch (Exception e3) {
			println("Exception: " + e3.getMessage() + "/" + e3.getCause());
			println("Kindly Check if the Profile is selected or not")
		}
	}


	@Keyword
	def executeQuery(String queryString) {
		try{

			Statement stm = connection.createStatement()

			ResultSet rs = stm.executeQuery(queryString)
			//rs.fetchSize
			return rs
		}
		catch(Exception e)
		{
			println("SQL Exception: " + e.getMessage() + "/" + e.getCause());
			println("-------------Check if the Profile is selected or Not----------------")
		}

	}

	@Keyword
	def countQuery(String queryString) {
		try{
			def value =0
			Statement stm = connection.createStatement()
			ResultSet rs = stm.executeQuery(queryString)
			while (rs.next()){
				value =  rs.getString(1)
			}

			return value
		}

		catch(Exception e)
		{
			println("SQL Null Pointer Exception: " + e.getMessage() + "/" + e.getCause());
			println("-------------Check if the Profile is selected or Not----------------")
		}

	}



	/**
	 * Execute non-query (usually PROCEDURE/INSERT/UPDATE/DELETE..) on database will give True or False
	 * @param queryString a SQL statement
	 * @return single value result of SQL statement
	 * 
	 * This method is used for SQL statements which update the database in some way. 
	 * For example INSERT, UPDATE and DELETE statements. 
	 * All these statements are DML(Data Manipulation Language) statements. 
	 * This method can also be used for DDL(Data Definition Language) statements which return nothing. 
	 * For example CREATE and ALTER statements. 
	 * This method returns an int value which represents the number of rows affected by the query. 
	 * This value will be 0 for the statements which return nothing.
	 */



	@Keyword
	def executeProcedure(String queryString) {

		try{
			//println queryString
			Statement stm = connection.createStatement()

			//Object result = stm.execute(queryString)
			Object result = stm.executeUpdate(queryString)


			if (result == 0){
				println("Nothing returned")
				return result
			}

			else if (result ==1)
				println("Executed Successfully")
			else
				println("No of Rows Returned = "+ result)
			return result
		}

		catch (SQLTimeoutException e) {
			println("SQL CMD Timeout Exception: " + e.getCause())
			e.printStackTrace();
		}
		catch (SQLException e2) {
			println("SQL CMD Exception: " + e2.getMessage() + "/" + e2.getCause());
		}

		catch (Exception e3) {
			println("Exception: " + e3.getMessage() + "/" + e3.getCause());
		}


	}




	//Closing the connection

	@Keyword
	def closeDatabaseConnection() {

		if(connection != null && !connection.isClosed()){

			connection.close()

		}

		connection = null

	}


}
