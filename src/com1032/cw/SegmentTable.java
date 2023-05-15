package com1032.cw;


import java.util.ArrayList;

/**
 * This class defines the Segment Table of a Process.
 */

public class SegmentTable {
	
	private ArrayList<Segment> segments;
	
	/*
	 * Constructor of SegmentTable
	 */
	public SegmentTable(ArrayList<Segment> seg) {
		this.segments = seg;
		
	}
	
	
	/*
	 * display the details of all of the segments in the table
	 */
	public String toString() {
		String output = "Segment table:\nSid | base | limit\n";
		for (Segment segment : segments) {
			output += segment.toString();
		}
		return output;
	}
	
	/**
	 * A method which returns the size of the segment with a specific id.
	 * 
	 * @param segment id
	 * @return An integer referring to the size of the segment with ID id
	 */
	public Segment getSegment(int id) {
		for (Segment segment : segments) {
			if (segment.getID() == id) {
				return segment;
			}
		}
		return null;
	}
	
	/**
	 * A method which returns the total size of the segments in the segment table
	 * that aren't in the memory
	 * 
	 * @return The total size of all the segments in a segment table
	 */
	public int getTotalSize() {
		int total = 0;
		for (Segment segment : segments) {
			if (segment.getInMemory() == false)
				total += segment.getSize();
			}
		return total;
	}
	
	
	/**
	 * A method that moves all segments into the memory
	 */
	public void moveAllInMemory() {
		for (Segment seg : segments) {
			seg.moveInMemory();
		}
	}
	
	public void setBase(int segID, int base) {
		for (Segment seg : segments) {
			if (seg.getID() == segID) {
				seg.setBase(base);
			}
		}
	}
	
	public void setBase(int base) {
		for (Segment seg : segments) {
			seg.setBase(base);
		}
	}
	
	
	public ArrayList<Segment> getAllSegments() {
		ArrayList<Segment> allSegments = new ArrayList<>();
		for (Segment seg : segments) {
			if (seg.getInMemory() != false) {
				allSegments.add(seg);
			}
		}
		return segments;
	}
	
	
	public int getSegmentNum() {
		int num = 0;
		for (Segment seg : segments) {
			if (seg.getInMemory() != false) {
				num += seg.getSize();
			}
		}
		return num;
	}
	
}
