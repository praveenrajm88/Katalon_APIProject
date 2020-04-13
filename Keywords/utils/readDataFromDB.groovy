package utils

import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat
import org.apache.poi.ss.usermodel.DateUtil as DateUtil
//import java.text.SimpleDateFormat as SimpleDateFormat

import com.kms.katalon.core.annotation.Keyword

import internal.GlobalVariable as GlobalVariable
//import internal.GlobalVariable

public class readDataFromDB {
	@Keyword
	def readData(String tbname, String col, Object sql){

		// create our mysql database connection
		String myDriver = 'org.gjt.mm.mysql.Driver'
		//String myUrl = 'jdbc:mysql://ddldlmem201.us.dell.com'
		Class.forName(myDriver)

		//Connection conn = DriverManager.getConnection(myUrl, 'svc_mtrc_supply', 'supply4mtr')
		Connection conn = DriverManager.getConnection(GlobalVariable.Host , GlobalVariable.User , GlobalVariable.Password)

		//String tbname = 'MTRC_Supply.Test_Spark_Priya'
		println ('columnname------------------------------'+col)
		println ('table name --------------------------'+ tbname)

		String selectquery = 'SELECT * FROM '+tbname+' order by '+col+' limit 10'
		println selectquery
		// create the java statement
		Statement st = conn.createStatement()
		// execute the query, and get a java resultset
		ResultSet rs = st.executeQuery(selectquery)
		println ('table name --------------------------'+ tbname)
		def query ='DESC '+tbname+''
		def fieldname=[]

		sql.eachRow(query, { def tp ->
			println('The results from MeMSQL table will be :'+'\n' +[tp.Field, tp.Type])
			fieldname << [tp.Field, tp.Type]
		})
		println fieldname
		println fieldname.size()-1

		def map_fname = [:]
		for (int i : 0..fieldname.size()-1){
			def fname=fieldname[i]
			println fieldname[i]
			println fname[0]
			println fname[1]
			map_fname.put(fname[0],fname[1])
			println map_fname
		}
		//println ('Structure of Table : ')+ map_fname
		//println ('No. of fields : ')+map_fname.size()

		//println mp
		/*mp=ml.collectEntries {
		 b -> [b.Field, b.Type]
		 }*/
		//mp.forEach((k,v)->println(k+", "+v))
		map_fname.each{ k, v -> println "${k}:${v}" }

		def list = []
		def dbvalues_list = []
		boolean empty = true;
		while (rs.next()) {
			//empty = false
			list = []
			for (entry in map_fname) {
				println entry
				String value = entry.getValue()
				String key = entry.getKey()
				println value //entry.getKey()
				println key //entry.getValue()

				String v="varchar"
				String i="int"
				String d = "date"
				String f = "float"
				println value.toLowerCase().contains("varchar")
				if (value.contains(v)){
					println (rs.getString('' + key + ''))
					def m = rs.getString('' + key + '')
					list << m.toString()
				}
				else if (value.contains(i))
				{
					println (rs.getInt('' + key + ''))
					list << rs.getInt('' + key + '')
				}
				else if (value.contains(d))
				{
					println (rs.getDate('' + key + ''))
					def dt = rs.getDate('' + key + '')
					SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy")
					dt = sdf.format(dt);
					println dt
					list << dt
				}
				else if (value.contains(f))
				{
					println (rs.getFloat('' + key + ''))
					list << rs.getFloat('' + key + '')
				}
				else if (value.contains("timestamp"))
				{
					println (rs.getTimestamp('' + key + ''))
					def dt = rs.getTimestamp('' + key + '')
					SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy")
					dt = sdf.format(dt);
					println dt
					list << dt
				}
				else
					println ('Datatype is not handled')

				println list

			}
			dbvalues_list << list
			println dbvalues_list
			//return dbvalues_list
		}

		return dbvalues_list
	}
}
