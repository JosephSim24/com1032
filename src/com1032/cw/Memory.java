package com1032.cw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

//The Memory Class 
public class Memory {

	/**
	 * Declare private variables and use the following methods and new methods
	 * to access them
	 */
	private int OSsize;
	private int total_size;
	private int hole;
	private LinkedHashMap<Segment, Integer> segmentProcess;
	
	/**
	 * Main Memory Constructor 
	 * @param size is total memory size 
	 */
	public Memory(int size, int os_size) {
		if (size < 1 || os_size < 1 || os_size > size) {
			throw new IllegalArgumentException("The size of the "
					+ "OS and main memory must be greater than zero"
					+ " and the OS size must be less than or equal to"
					+ " the main memory size.");
		}
		this.total_size = size;
		OSsize = os_size;
		hole = total_size - OSsize;
		segmentProcess = new LinkedHashMap<>();

	}
	
	/**
	 * Allocate a process to the memory
	 * @param process, a process to be allocated to the memory
	 * @return return 1 if successful, -1 otherwise with an error message
	 */
	public int allocate(Process process) {
		// make sure segments of a process are only allocated to the memory once
		// i.e, only allocate segments that aren't loaded in the memory
		int out = -1;
		if (process.getSize() <= hole) {
			hole -= process.getSize();
			out = 1;
			for (Segment seg : process.getSegments()) {
				segmentProcess.put(seg, process.getID());
			}
			process.moveAllInMemory();
			process.setAllBase(OSsize);
		}	else {
			System.out.println("Error: Process segments cannot be allocated because "
					+ "there is not enough free space available.");
		}
		
		return out;
	}
	/**
	 * add a segment of the process to the memory
	 * @param p the process with the segment 
	 * @param seg the segment to be allocated
	 */
	public void allocate(Process p, Segment seg) {
		if (p.getSegment(seg.getID()).getSize() <= hole) {
			hole -= p.getSegment(seg.getID()).getSize();
			p.getSegment(seg.getID()).moveInMemory();
			p.setBase(seg.getID(), OSsize);
			segmentProcess.put(seg, p.getID());
		}
		
	}
	/**
	 * remove a segment of the process from the memory
	 * @param p the process with the segment 
	 * @param seg the segment to be removed from the main memory
	 */
	public int deallocate(Process p, Segment seg) {
		int out = -1;
		if (segmentProcess.remove(seg, p.getID())) {
			for (Segment segment : p.getSegments()) {
				if (segment == seg) {
					segment.moveOutMemory();
					segment.setBase(0);
				}
			}
			out = 1;
			hole += seg.getSize();
			segmentProcess.remove(seg, p.getID());
		}
		if (out != 1) {
			System.out.println("Error: Process segment cannot be deallocated "
					+ "because it cannot be found.");
		}
		
		return out;
	}
    	
	/**
	 * Deallocate memory allocated to this process
	 * @param process the process to be deallocated
	 * @return return 1 if successful, -1 otherwise with an error message
	 */
	public int deallocate(Process process) {
		int out = -1;
		if (process.getSegments().size() > 0) {
			hole += process.getSegmentNum();
			for (Segment segment : process.getSegments()) {
				segmentProcess.remove(segment, process.getID());
				segment.moveOutMemory();
				segment.setBase(0);
			}
			out = 1;
		} else {
			System.out.println("Error: Process segments cannot be deallocated "
					+ "because they cannot be found.");
		}
		
		return out;
	}
	/**
	 * the process p will be updated
	 * @param p the input process to be updated/resized
	 * @return return 1 if successful, -1 otherwise with an error message
	 */
	public int resizeProcess (Process p) {
		
		return 1;
	}
    /**
     * function to display the state of memory to the console
     */
    public void memoryState() {
    	System.out.print("Memory State:\n[OS " + OSsize + "]");
    	if (hole < total_size - OSsize) {
    		System.out.print(" |");
    		for (Segment segment : segmentProcess.keySet()) {
    			System.out.print(" [P" + segmentProcess.get(segment) + " S" + 
    					segment.getID() + ": " +
    					segment.getSize() + "]");
    		}
    	} 
    	System.out.print(" | [Hole " + hole + "]\n");
    }
	
}
