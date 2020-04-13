
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String

import java.lang.Object


def static "utils.ReadAndWriteExcel.demoKey"(
    	String name	
     , 	String quater	) {
    (new utils.ReadAndWriteExcel()).demoKey(
        	name
         , 	quater)
}

def static "utils.ReadAndWriteExcel.readExcel"(
    	String filePath	
     , 	String fileName	
     , 	String sheetName	) {
    (new utils.ReadAndWriteExcel()).readExcel(
        	filePath
         , 	fileName
         , 	sheetName)
}

def static "utils.DataReadFromTable.ReadDataFromTable"(
    	int rowCount	
     , 	int columnCount	
     , 	String nextButton	
     , 	String tableName	) {
    (new utils.DataReadFromTable()).ReadDataFromTable(
        	rowCount
         , 	columnCount
         , 	nextButton
         , 	tableName)
}

def static "utils.SearchSelect.Search"() {
    (new utils.SearchSelect()).Search()
}

def static "utils.DbUtilities.MemSQLConnect"() {
    (new utils.DbUtilities()).MemSQLConnect()
}

def static "utils.DbUtilities.GreenPlumConnect"() {
    (new utils.DbUtilities()).GreenPlumConnect()
}

def static "utils.DbUtilities.OracleConnect"() {
    (new utils.DbUtilities()).OracleConnect()
}

def static "utils.DbUtilities.executeQuery"(
    	String queryString	) {
    (new utils.DbUtilities()).executeQuery(
        	queryString)
}

def static "utils.DbUtilities.countQuery"(
    	String queryString	) {
    (new utils.DbUtilities()).countQuery(
        	queryString)
}

def static "utils.DbUtilities.executeProcedure"(
    	String queryString	) {
    (new utils.DbUtilities()).executeProcedure(
        	queryString)
}

def static "utils.DbUtilities.closeDatabaseConnection"() {
    (new utils.DbUtilities()).closeDatabaseConnection()
}

def static "utils.readDataFromDB.readData"(
    	String tbname	
     , 	String col	
     , 	Object sql	) {
    (new utils.readDataFromDB()).readData(
        	tbname
         , 	col
         , 	sql)
}
