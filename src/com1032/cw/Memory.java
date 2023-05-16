package com1032.cw;

import java.util.ArrayList;

/**
 * A class to represent main memory where segments and processes can be stored
 * 
 * @author Joseph Sim
 */

import java.util.LinkedHashMap;

//The Memory Class 
public class Memory {

	/**
	 * Declare private variables and use the following methods and new methods
	 * to access them
	 */
	private int OSsize; // the size of the OS
	private int total_size; // the total size of the memory
	private int hole; // the size of the free space available in the memory
	private LinkedHashMap<Segment, Process> segmentProcess; // A hash map which links
	// the segment and a process
	private ArrayList<Segment> fragments; // An array list which contains all of the 
	// segments which are fragments i.e. to be compacted later on as part of the hole
	
	/**
	 * Main Memory Constructor 
	 * 
	 * @param size which is the total size of the memory, must be greater than 0
	 * @param os_size which is the size of the OS, must be greater than 0
	 * @throws IllegalArgumentException if size or os_size is invalid
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
		fragments = new ArrayList<>();

	}
	
	/**
	 * Allocate a process to the memory
	 * @param process, a process to be allocated to the memory
	 * @return return 1 if successful, -1 otherwise with an error message
	 */
	public int allocate(Process process) {
		int out = -1;
		if (process.getTotalSize() <= hole) {
			hole -= process.getTotalSize(); // Hole is reduced by the total size of the
			// segments which are to be allocated
			out = 1;
			for (Segment seg : process.getAllSegments()) {
				if (!segmentProcess.containsKey(seg)) {
					segmentProcess.put(seg, process);
				}
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
	 * Allocate a segment of the process to the memory
	 * @param p the process with the segment 
	 * @param seg the segment to be allocated
	 */
	public void allocate(Process p, Segment seg) {
		if (p.getSegment(seg.getID()).getSize() <= hole) {
			hole -= p.getSegment(seg.getID()).getSize(); // Hole reduced by segment size
			
			p.getSegment(seg.getID()).moveInMemory(); // Segment is moved into the memory
			p.setBase(seg.getID(), OSsize); // Segment's base is set
			segmentProcess.put(seg, p);
		}
		
	}
	/**
	 * Remove a segment of the process from the memory
	 * @param p the process with the segment 
	 * @param seg the segment to be removed from the main memory
	 * @returns 1 if the segment is successfully removed else -1 is returned
	 */
	public int deallocate(Process p, Segment seg) {
		int out = -1;
		if (segmentProcess.get(seg) == p) { // Checks that the process
			// in the parameter exists where the segment is a part of that process
			for (Segment segment : p.getSegmentsInMemory()) {
				if (segment == seg) {
					segment.moveOutMemory(); // Segment is moved out of the memory
				}
			}
			out = 1;
			fragments.add(seg); // Segment is added to the array list of fragments
			segmentProcess.replace(seg, null); // Hash map value with the key seg is replaced
			// as null, which will be helpful later on, rather than removed the segment from the
			// hash map
		}
		if (out != 1) { // If the segment cannot be found within the given process, an error occurs
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
		if (process.getSegmentsInMemory().size() > 0) { // Checks that the given process has any 
			// segments that are in the memory
			for (Segment segment : process.getSegmentsInMemory()) {
				fragments.add(segment); // fragments if the hash map value is not zero, i.e. makes sure the 
				segmentProcess.replace(segment, null); // segment is not in the memory when it shouldn't be
				segment.moveOutMemory(); // Moves the segment out of the memory
			}
			out = 1;
		} else { // If the given process has no segments in the memory, an error occurs
			System.out.println("Error: Process segments cannot be deallocated "
					+ "because they cannot be found.");
		}
		
		return out;
	}
	
	/**
	 * A method that resizes the hole. When segments are resized, this method
	 * must be called to correct the size of the hole
	 * @param p, the process which has been resized
	 */
	public void resizeProcess (Process p) {
		hole = total_size - OSsize;
		ArrayList<Segment> tempList = p.getFragments(); // This method returns all the fragments
		for (Segment seg : tempList) { // which need to be accounted for when correcting the hole.
			if (seg.getInMemory() == true) { // If the given fragment is in the memory, then it is
				if (!fragments.contains(seg)) { // added to the array list fragments if it isn't already
					fragments.add(seg); // in the array list.
				}
			}
		}
		for (Segment seg : fragments) { // Loops through the array list fragments and takes away each fragment's
			hole -= seg.getSize(); // size from the hole.
		}
		for (Segment seg : segmentProcess.keySet()) { // Loops through the segments which have been allocated to
			if (segmentProcess.get(seg) != null) { // the memory and takes away these from the hole.
				hole -= seg.getSize();
			}
		}
	}
	
	
	/**
	 * A method to compact the memory blocks
	 */
	public void compactMemory() {
		for (Segment segment : segmentProcess.keySet()) { // Loops through the hash map and any keys which appear
			if (fragments.contains(segment)) { // in the array list fragments are added to the hole and then
				hole += segment.getSize(); // removed from the fragments array list.
				fragments.remove(segment);
			}
		}
	}
	
	
	/**
	 * A method that returns the details of a segment if the memory has permission to do so
	 * @param p the process where the segment is located
	 * @param s the segment which is being looked up
	 * @return 1 if the memory has permission to look up this segment and -1 otherwise
	 */
	public int getSegment(Process p, Segment s) {
		int temp = -1;
		if (s.getExecutePerms() == 'x') {
			System.out.println("Segment " + s.getID() + " has a size of " + s.getSize());
			temp = 1;
		} else {
			System.out.println("Memory does not have permission to look up this segment");
		}
		return temp;
	}
	
	
    /**
     * function to display the state of memory to the console
     */
    public void memoryState() {
    	System.out.print("Memory State:\n[OS: " + OSsize + "]");
    	if (!segmentProcess.isEmpty()) { // Checks that any segments have been allocated
    		System.out.print(" |");
    		for (Segment segment : segmentProcess.keySet()) { // If the segment is in the array list fragments, then
    			if (fragments.contains(segment) && segment.getSize() > 0) { // that means that segment is fragmented
    				System.out.print(" [Hole: " + segment.getSize() + "]");
    			}
    			else if (segmentProcess.get(segment) != null && segment.getSize() > 0) {
    				System.out.print(" [P" + segmentProcess.get(segment).getID() + " S" + 
    						segment.getID() + ": " +
    						segment.getSize() + "]");
    			} 
    		}
    	} 
    	System.out.print(" | [Hole: " + hole + "]\n");
    }
	
}
