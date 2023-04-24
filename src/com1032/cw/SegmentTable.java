package com1032.cw;


import java.util.ArrayList;

/**
 * This class defines the Segment Table of a Process.
 * TODO: this class is incomplete
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

		return "Segment table details: ";
	}

}
