package com1032.cw;

/**
 * A class to represent a single segment of memory
 * 
 * @author Joseph Sim
 *
 */


public class Segment {
		private int id; // the id of the segment 
		private int size; // the size of the segment	#
		private boolean inMemory = false;
		private int base;
		
		/**
		 * default constructor of a Segment
		 */
		public Segment() {
			super();
		}
		
		/**
		 * the constructor of Segment
		 * @param segmentID the id of the segment
		 * @param size the size of the segment
		 */
		public Segment(int segmentID, int size) {
			super();
			if (segmentID < 0 || size < 1) {
				throw new IllegalArgumentException("The segment size "
						+ "must be greater than 0 and the segment ID"
						+ "cannot be negative.");
			}
			id = segmentID;
			this.size = size;
			
		}
		
		public int getID() {
			return this.id;
		}
		
		public int getSize() {
			return this.size;
		}
		
		public void setSize(int size) {
			this.size = size;
		}
		
		public boolean getInMemory() {
			return inMemory;
		}
		
		public void moveInMemory() {
			inMemory = true;
		}
		
		public void moveOutMemory() {
			inMemory = false;
		}
		
		public void setBase(int base) {
			this.base = base;
		}

		public String toString() {
			String output = " " + this.id;
			if (inMemory != false) {
				output += "  |  " + this.base + " | " + this.size + "\n";
			} else {
				output += "  |      | " + this.size + "\n";
			}
			return output;
		}
}

