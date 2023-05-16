package com1032.cw;

/**
 * A class to represent a single segment of memory
 * 
 * @author Joseph Sim
 *
 */


public class Segment {
		private int id; // the id of the segment 
		private int size; // the size of the segment	
		private boolean inMemory = false; // the location of the segment
		private int base; // the base when the segment is in memory
		private char read = '-'; // the permission to read
		private char write = '-'; // the permission to write
		private char execute = '-'; // the permission to execute
		
		/**
		 * default constructor of a Segment
		 */
		public Segment() {
			super();
		}
		
		/**
		 * the constructor of Segment
		 * @param segmentID the id of the segment, must be greater than 0
		 * @param size the size of the segment, must be greater than 0
		 * @throws IllegalArgumentException when segmentID or size are invalid
		 */
		public Segment(int segmentID, int size) {
			super();
			if (segmentID < 1 || size < 1) {
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
		
		public boolean getInMemory() {
			return inMemory;
		}
		
		public int getBase() {
			return this.base;
		}
		
		public char getReadPerms() {
			return this.read;
		}
		
		public char getWritePerms() {
			return this.write;
		}
		
		public char getExecutePerms() {
			return this.execute;
		}
		
		
		
		public void setSize(int size) {
			this.size = size;
		}
		
		public void setBase(int base) {
			this.base = base;
		}
		
		public void moveInMemory() {
			inMemory = true;
		}
		
		public void moveOutMemory() {
			inMemory = false;
		}
		
		public void setReadPerms(char perm) {
			this.read = perm;
		}
		
		public void setWritePerms(char perm) {
			this.write = perm;
		}
		
		public void setExecutePerms(char perm) {
			this.execute = perm;
		}
		
		

		public String toString() {
			String output = " " + this.id;
			if (this.id < 10) {
				output += "  |";
			}
			else if (this.id >= 10) {
				output += " |";
			}
			if (inMemory == false) {
				output += "      |";
			}
			else if (this.base > 99 && this.base < 1000) {
				output += "  " + this.base + " |";
			} 
			else if (this.base <= 99) {
				output += "   " + this.base + " |";
			}
			if (this.size > 99 && this.size < 1000) {
				output += "   " + this.size + " |";
			}
			else if (this.size <= 99) {
				output += "    " + this.size + " |";
			}
			return output;
		}
}

