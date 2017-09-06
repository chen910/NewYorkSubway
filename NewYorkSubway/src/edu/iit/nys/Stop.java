package edu.iit.nys;

import java.util.ArrayList;
import java.util.List;

public class Stop {
	public String prevId;
	public int value; 
	public List<Successor> scr;
	public Stop() {
		scr = new ArrayList<Successor>();
	}
}

class Successor{
	public Successor(String s, int w) {
		stopId = s;
		duration = w;
	}
	public String stopId;
	public int duration;
}