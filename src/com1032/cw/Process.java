package com1032.cw;


import java.util.ArrayList;
import java.util.List;

public class Process {
	
	private int pid = 0; // the id of the process
	private SegmentTable segmentTable;
	
	public Process (String processString) {
		ArrayList<Segment> segmentList = new ArrayList<Segment>();
		
		// Parser Examples
		Parser P = new Parser();
		ArrayList<String>[] list = P.parseInputString(processString);
		
		this.pid = Integer.parseInt(list[0].get(0));
		
		for(int id = 1; id < list.length; id++) {
			int segmentSize = Integer.valueOf(list[id].get(0));
			Segment segment = new Segment(id, segmentSize);
			segmentList.add(segment);
		}
		
		segmentTable = new SegmentTable(segmentList);
		
	}

	/**
	 * 
	 * @param segments the list of segments that belong to the process
	 */
	public void resize(String segments) {
		ArrayList<Integer> size = new ArrayList<>();
		Parser P = new Parser();
		ArrayList<String>[] list = P.parseInputString(segments);
		
		//size.add(Integer.parseInt(list[0].get(0)));
		
		for (int resize = 0; resize < list.length; resize++) {
			int segmentResize = Integer.valueOf(list[resize].get(0));
			size.add(segmentResize);
		}
		
		ArrayList<Segment> segmentList = getSegments();
		
		if (size.size() > segmentList.size()) {
			System.out.println("Error: The number of segments entered are too "
					+ "many, please enter less.");
		} else {
			for (int i = 0; i < segmentList.size(); i++) {
				segmentList.get(i).setSize(segmentList.get(i).getSize() + size.get(i));
				if (segmentList.get(i).getSize() < 0) {
					System.out.println("Error: The size of a segment is negative!");
				} else if (segmentList.get(i).getSize() == 0) {
					segmentList.remove(i);
					segmentTable = new SegmentTable(segmentList);
				}
			}
		}
	}
	
	/**
	 * TODO: return the segment with the input ID
	 * @param id is the segment ID of the process
	 */
	public Segment getSegment(int id) {
		return segmentTable.getSegment(id);
	}	
	
	/**
	 * A method that returns the total size of the segments that are not
	 * in the memory
	 * 
	 * @return The size of the segments not already in the memory
	 */
	public int getSize() {
		return segmentTable.getTotalSize();
	}
	
	public int getID() {
		return this.pid;
	}
	
	public void moveAllInMemory() {
		segmentTable.moveAllInMemory();
	}
	
	
	public void setAllBase(int base) {
		segmentTable.setBase(base);
	}
	
	public void setBase(int id, int base) {
		segmentTable.setBase(id, base);
	}
	

	/**
	 * to print the details of segments of the process
	 */
	public void segmentTable() {
		System.out.println("P" + pid + " " + segmentTable.toString());
	}
	
	
	public int getSegmentNum() {
		return segmentTable.getSegmentNum();
	}
	
	
	public ArrayList<Segment> getSegments() {
		return segmentTable.getAllSegments();
	}
	
	/**
	 * output the details of the process, which includes process Id and segment details
	 */
	public String toString() {
		String output = "P" + pid + " (" + pid;
		ArrayList<Segment> allSegments = segmentTable.getAllSegments();
		for (Segment seg : allSegments) {
			output += ", " + seg.getSize();
		}
		output += ")";
		return output;
	}
}
