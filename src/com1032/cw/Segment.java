package com1032.cw;


// TODO: complete this Segment class

public class Segment {
		private int id; // the id of the segment 
		private int size; // the size of the segment		
		/**
		 * default constructor of a Segment
		 */
		public Segment() {
			//TODO: to be completed
		}
		
		/**
		 * the constructor of Segment
		 * @param segmentID the id of the segment
		 * @param size the size of the segment
		 */
		public Segment(int segmentID, int size) {
			super();
			id = segmentID;
			this.size = size;
			
		}

		public String toString() {
			//TODO: print the details of this segment
			return "The detail of segment:";
		}
}

