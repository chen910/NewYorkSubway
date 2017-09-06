package edu.iit.nys;

import java.sql.*;
import java.util.HashMap;


public class SearchIndex {
	
	HashMap<String,String> si1;
	HashMap<String,String> si2;
	
public SearchIndex(){
	si1 = new HashMap<String,String>();
	si2 = new HashMap<String,String>();
	
	try{    
        Connection conn;
        conn = MysqlConnector.getMySqlConnection();
        Statement stmt = conn.createStatement(); //Create Statement entity
        String sql = "select stop_id,stop_name from stop where location_type=1 ";    //SQL statements
        ResultSet rs1 = stmt.executeQuery(sql);// result store
            while (rs1.next()){
            	String stopId =rs1.getString(1);
            	String stopName =rs1.getString(2);
            	
            	si1.put(stopName, stopId);
            	//System.out.println(stopName + "/t" + stopId);
            	si2.put(stopId, stopName);
            
            }
            rs1.close();
            stmt.close();
            conn.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }

	}
}