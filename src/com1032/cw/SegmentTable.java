package com1032.cw;


import java.util.ArrayList;

/**
 * This class defines the Segment Table of a Process.
 * 
 * @author Joseph Sim
 */

public class SegmentTable {
	
	private ArrayList<Segment> segments; // list of the segments in the table
	private int valid = 0; // validity of each segment depending on whether the segment is 
	// in the main memory or not
	
	/**
	 * Constructor of SegmentTable
	 * @param seg an array list of all the segments to be put in the table
	 */
	public SegmentTable(ArrayList<Segment> seg) {
		this.segments = seg;
	}
	
	
	/**
	 * A method which returns the size of the segment with a specific id.
	 * 
	 * @param id the ID of the segment which is to be returned
	 * @return The segment which has the given ID
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
	 * that are NOT in the memory
	 * 
	 * @return The size of all the segments in a segment table that are
	 * not in the memory
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
	 * A method which returns the total size of the segments in the segment table
	 * that are in the memory
	 * 
	 * @return The total size of all the segments in a segment table
	 */
	public int getSize() {
		int total = 0;
		for (Segment segment : segments) {
			if (segment.getInMemory() == true)
				total += segment.getSize();
			}
		return total;
	}
	
	
	private void setValidation(Segment seg) {
		if (seg.getInMemory() != false) {
			valid = 1;
		} else {
			valid = 0;
		}
	}
	
	
	/**
	 * A method that moves all segments into the memory
	 */
	public void moveAllInMemory() {
		for (Segment seg : segments) {
			seg.moveInMemory();
		}
	}
	
	/**
	 * A method that sets the base for a given segment
	 * @param segID the ID of the segment which the base is to be set
	 * @param base the base of the segment
	 */
	public void setBase(int segID, int base) {
		for (Segment seg : segments) {
			if (seg.getID() == segID) {
				seg.setBase(base);
			}
		}
	}
	
	/**
	 * A method that sets the base of all the segments in a segment table
	 * @param base the base which all the segments will have
	 */
	public void setBase(int base) {
		for (Segment seg : segments) {
			seg.setBase(base);
		}
	}
	
	/**
	 * A method that returns an array list of all the segments in a segment table
	 * @return An array list of all the segments that are in a segment table
	 */
	public ArrayList<Segment> getAllSegments() {
		return segments;
	}
	
	
	/**
	 * A method that returns an array list of all the segments in the main memory
	 * @return An array list of all the segments that are in the main memory
	 */
	public ArrayList<Segment> getSegmentsInMemory() {
		ArrayList<Segment> allSegments = new ArrayList<>();
		for (Segment seg : segments) {
			if (seg.getInMemory() == true) {
				allSegments.add(seg);
			}
		}
		return allSegments;
	}
	
	
	/**
	 * A method that returns the segments that are not in the memory
	 * @return An array list of segments that are not in the memory
	 */
	public ArrayList<Segment> getSegmentsNotInMemory() {
		ArrayList<Segment> allSegments = new ArrayList<>();
		for (Segment seg : segments) {
			if (seg.getInMemory() != true) {
				allSegments.add(seg);
			}
		}
		return allSegments;
	}
	
	
	/**
	 * A method that returns the number of segments that are in the main memory
	 * @return The number of segments, from a segment table, that are in the main memory
	 */
	public int getSegmentNum() {
		int num = 0;
		for (Segment seg : segments) {
			if (seg.getInMemory() != false) {
				num += seg.getSize();
			}
		}
		return num;
	}
	
	
	/**
	 * A method to display the details of all of the segments in the table
	 */
	public String toString() {
		String output = "Segment table:\nSid | base | limit | valid-invalid | permissions\n";
		for (int i = 0; i < segments.size(); i++) {
			output += segments.get(i).toString();
			setValidation(segments.get(i));
			output += "             " + valid + " |";
			output += " " + segments.get(i).getReadPerms();
			output += segments.get(i).getWritePerms();
			output += segments.get(i).getExecutePerms() + "\n";
		}
		return output;
	}
	
	
}
