package edu.iit.nys;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dijkstra {
	
	HashMap<String,Stop> sp;
	
	public Dijkstra (){
		sp = new HashMap<String,Stop>();
	} //initialize store HashMap
	
public static void main(String[] args){
		Dijkstra dj= new Dijkstra();
		SearchIndex si = new SearchIndex();
		dj.readData();
		String origin =si.si1.get("Van Cortlandt Park - 242 St"); /*---------------  TEST   HERE-------------------*/
		String dest =si.si1.get("34 St - Penn Station");/*------------------  TEST   HERE-------------------*/
		dj.run(origin, dest);
		//System.out.println(path);
	}
	public void readData(){
		try{    
            Connection conn;

            conn = MysqlConnector.getMySqlConnection();
            Statement stmt = conn.createStatement(); //Create Statement entity
            String sql = "select src_stop_id,dest_stop_id,duration from subway_graph";    //SQL statements
            ResultSet rs = stmt.executeQuery(sql);// result store
                while (rs.next()){
                	String stopId = rs.getString(1);
                	if (sp.containsKey(stopId)) {
                		sp.get(stopId).scr.add(new Successor(rs.getString(2), rs.getInt(3)));
                	} else{
                		Stop stop = new Stop();
                		sp.put(rs.getString(1), stop);
                		stop.scr.add(new Successor(rs.getString(2), rs.getInt(3)));
                	}
                }
                rs.close();
                stmt.close();
                conn.close();
            }catch(Exception e)
            {
                e.printStackTrace();
            }/*
		for (String key: sp.keySet()) {
			System.out.println(key);
			Stop stop = sp.get(key);
			for (Successor suc: stop.scr)
				System.out.println("\t" + suc.stopId + " " + suc.duration);
		}
		// HashMap test*/
	}
	public List<String> run(String start, String dest){
		
		SearchIndex si = new SearchIndex();
		
		Set<String> Q = new HashSet<String>();//initialize
		for (String key: sp.keySet()) {
			sp.get(key).value = Integer.MAX_VALUE;
			sp.get(key).prevId = null;
			Q.add(key);
		}
		
		sp.get(start).value=0;//initialize source 
		
    	while(!Q.isEmpty()){
    		
	    	String u = mindist(Q);
	    	
	    	Q.remove(u);// remove finished node

	    	if (u == null)
	    		break;
	    	for(Successor s: sp.get(u).scr){
	    		int alt;
    			alt=sp.get(u).value+s.duration;//temp distance
    			if (sp.get(s.stopId) == null)
    				continue;
    			if(alt < sp.get(s.stopId).value){
    				
	    			sp.get(s.stopId).value = alt;
	    			
	    			sp.get(s.stopId).prevId = u;// alt compare to min dist
	    			
    			}
	    	}	    		
    	}
    	 List<String> path = new ArrayList<String>();
    	 
    	 while(dest != null){
    		 path.add(dest);
    		 System.out.println(si.si2.get(dest) + "\t " + sp.get(dest).value + "S");
    		 dest = sp.get(dest).prevId;
    	 }
    	 return path;
	}
	private String mindist(Set<String> q) {
		
		String u = null;
		
		int min=Integer.MAX_VALUE;
		
		for(String v: q){
			if( sp.get(v).value < min){
			
				min=sp.get(v).value;
			    u=v;
			}
		}
		return u;
	}// vertex in Q with min dist[u]
}
