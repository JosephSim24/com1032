package com1032.cw;

/**
 * A class to represent a process with segments
 * 
 * @author Joseph Sim
 */

import java.util.ArrayList;

public class Process {
	
	private int pid = 0; // The id of the process
	private SegmentTable segmentTable; // Segment table of all segments in a process
	private ArrayList<Segment> fragments; // Array list of all fragments in a process
	
	/**
	 * Process constructor
	 * @param processString which contains the process ID and however many segments
	 * are wanting to be created with their specified size
	 */
	public Process (String processString) {
		ArrayList<Segment> segmentList = new ArrayList<Segment>();
		
		// Parser Examples
		Parser P = new Parser();
		ArrayList<String>[] list = P.parseInputString(processString);
		
		this.pid = Integer.parseInt(list[0].get(0));
		
		for(int id = 1; id < list.length; id++) {
			int segmentSize = Integer.valueOf(list[id].get(0));
			Segment segment = new Segment(id, segmentSize);
			if (String.valueOf(list[id]).contains(" ")) {
				if (String.valueOf(list[id].get(1)).contains("r")) {
					segment.setReadPerms('r');
				}
				if (String.valueOf(list[id].get(1)).contains("w")) {
					segment.setWritePerms('w');
				}
				if (String.valueOf(list[id].get(1)).contains("x")) {
					segment.setExecutePerms('x');
				}
			}
			segmentList.add(segment);
		}
		
		segmentTable = new SegmentTable(segmentList);
		fragments = new ArrayList<>();
		
	}

	/**
	 * A method that resizes a number of given segments
	 * @param segments the list of segments that belong to the process
	 */
	public void resize(String segments) {
		ArrayList<Integer> size = new ArrayList<>();
		Parser P = new Parser();
		ArrayList<String>[] list = P.parseInputString(segments); // The string in the parameter
		// is formatted to then be stored
		
		for (int resize = 0; resize < list.length; resize++) {
			int segmentResize = Integer.valueOf(list[resize].get(0)); // Creates an array list of all the
			size.add(segmentResize); // values in the parameter.
		}
		
		ArrayList<Segment> segmentList = getAllSegments();
		
		if (size.size() > segmentList.size()) { // An error occurs when the number of segment changes that are
			System.out.println("Error: The number of segments entered are too " // Inputed are too many for the
					+ "many, please enter less."); // number of segments
		} else {
			for (int i = 0; i < size.size(); i++) { // Loops through for the number of numbers which are inputed
				int temp = segmentList.get(i).getSize() + size.get(i);
				if (temp == 0) { // If the size of the segment becomes zero, then the segment is added to an array
					fragments.add(segmentList.get(i)); // list of fragments and deleted from the list of segments
				}
				else if (size.get(i) < 0) { // If the size of the segments is reduced but is more than zero, then
					segmentList.get(i).setSize(size.get(i) * -1); // a fragment is created for that change of size
					fragments.add(segmentList.get(i));
				}
				else if (temp < 0) { // If the size of the segment is less than zero then an error occurs
					System.out.println("Error: The size of a segment is negative!");
					i = segmentList.size() - 1;
				} 
				segmentList.get(i).setSize(temp); // If the segment size 
				// increases then the size is simply increased by the respective amount
			}
			for (Segment segment : segmentList) {
				if (segment.getSize() == 0) {
					segmentList.remove(segment);
				}
			}
			segmentTable = new SegmentTable(segmentList); // The segment table is now the new list, segmentList,
			//once all the resizes have been considered
		}
	}
	
	/**
	 * A method that returns a Segment with the given ID
	 * @param id is the segment ID of the process
	 */
	public Segment getSegment(int id) {
		return segmentTable.getSegment(id);
	}	
	
	/**
	 * A method that returns the total size of the segments that are NOT
	 * in the memory
	 * 
	 * @return The size of the segments not already in the memory
	 */
	public int getTotalSize() {
		return segmentTable.getTotalSize();
	}
	
	/**
	 * A method that returns the total size of the segments that are
	 * in the memory
	 * 
	 * @return The size of the segments in the memory
	 */
	public int getSize() {
		return segmentTable.getSize();
	}
	
	
	/**
	 * A method that returns all the fragments in this process
	 * @return An array list with all the fragments in this process
	 */
	public ArrayList<Segment> getFragments() {
		return this.fragments;
	}
	
	
	public int getID() {
		return this.pid;
	}
	
	 /**
	  * A method that moves all the segments in a process to the memory
	  */
	public void moveAllInMemory() {
		segmentTable.moveAllInMemory();
	}
	
	/**
	 * A method that sets the base of all the segments in a process
	 * @param base the base to be set for all segments in a process
	 */
	public void setAllBase(int base) {
		segmentTable.setBase(base);
	}
	
	/**
	 * A method that sets the base of a specific segment
	 * @param id the ID of a segment
	 * @param base the base to be set for that segment
	 */
	public void setBase(int id, int base) {
		segmentTable.setBase(id, base);
	}
	

	/**
	 * to print the details of segments of the process
	 */
	public void segmentTable() {
		System.out.println("P" + pid + " " + segmentTable.toString());
	}
	
	/**
	 * A method that returns the number of segments that are in the memory
	 * @return The number of segments that are in the main memory
	 */
	public int getSegmentNum() {
		return segmentTable.getSegmentNum();
	}
	
	/**
	 * Returns the segments that are in the memory
	 * @return Array list of segments that are stored in the memory
	 */
	public ArrayList<Segment> getSegmentsInMemory() {
		return segmentTable.getSegmentsInMemory();
	}
	
	
	/**
	 * A method that returns all of the segments in a segment table
	 * @return An array list of all the segments in a segment table
	 */
	public ArrayList<Segment> getAllSegments() {
		return segmentTable.getAllSegments();
	}
	
	
	/**
	 * A method to set the read permissions of a segment within a process
	 * @param s the segment who's permissions are to be changed
	 * @param c the permission, either a 'r' for has permission to read or
	 * a '-' for doesn't have permission
	 */
	public void setReadPerms(Segment s, char c) {
		s.setReadPerms(c);
	}
	
	
	/**
	 * A method to set the write permissions of a segment within a process
	 * @param s the segment who's permissions are to be changed
	 * @param c the permission, either a 'w' for has permission to write or
	 * a '-' for doesn't have permission
	 */
	public void setWritePerms(Segment s, char c) {
		s.setWritePerms(c);
	}
	
	
	/**
	 * A method to set the execute permissions of a segment within a process
	 * @param s the segment who's permissions are to be changed
	 * @param c the permission, either a 'x' for has permission to execute or
	 * a '-' for doesn't have permission
	 */
	public void setExecutePerms(Segment s, char c) {
		s.setExecutePerms(c);
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
