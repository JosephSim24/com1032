package com1032.cw;


import java.util.ArrayList;
import java.util.List;

public class Process {
	
	private int pid = 0; // the id of the process
	private SegmentTable segmentTable;
	
	public Process (String processString) {
		pid ++;
		ArrayList<Segment> segmentList = new ArrayList<Segment>();
		
		//Parser Examples
		Parser P = new Parser();
		ArrayList<String>[] list = P.parseInputString(processString);
		
		
		for(int id = 1; id < list.length; id++) {
			int segmentSize = Integer.valueOf(list[id].get(0));
			Segment segment = new Segment(id, segmentSize);
			segmentList.add(segment);
		}
		
		segmentTable = new SegmentTable(segmentList);
		
	}

	/**
	 * 
	 * @param segments the list of segments that belong to teh process
	 */
	public void resize(String segments) {
		
	}
	
	/**
	 * TODO: return the segment with the input ID
	 * @param id is the segment ID of the process
	 */
	public Segment getSegment(int id) {
		
		Segment s = null;
		
		return s;
		
	}
	

	/**
	 * to print the details of segments of the process
	 */
	public void segmentTable() {
		
	}
	/**
	 * output the details of the process, which includes process Id and segment details
	 */
	public String toString() {
		return "Process and its segments details";
	}
}
