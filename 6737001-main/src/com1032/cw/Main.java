package com1032.cw;

import java.util.Arrays;

public class Main {

	
	public static void main(String[] args) {	
		
		// you should indicate the start and the end for each task
		System.out.println("Start Component B.1");

		//Create Memory with 1024 bytes total memory, and 124 bytes of OS memory
		// Your code might look differently
		Memory M = new Memory(1024, 124);
		// Show how the memory looks before we do anything
		System.out.println("Initial Main memory state:");
		M.memoryState();
		
		// create process examples for tasks B.1
		// only 3 examples are shown here for brevity
		Process p1 = new Process("1, 100, 200, 20");
        Process p2 = new Process("2, 70, 87, 20, 55");
        Process p3 = new Process("3, 10, 260, 40, 10, 70");
        
        // Now, attempt to allocate the first segment from P1 into memory
        // (Segment numbers start at 1.)
        System.out.println("Add Segment [P1, S1] to Main Memory from Process " + p1.toString());
        System.out.println("+ State Before");
        M.memoryState(); // show how the memory looks after the operations
        p1.segmentTable(); // show how the segment table looks after the operations

        M.allocate(p1, p1.getSegment(1)); // or some variant of this form to add Segment S1 of P1 in main memory
        
        System.out.println("- State After");
        M.memoryState(); // show how the memory looks after the operations
        p1.segmentTable(); // show how the segment table looks after the operations


        // Next, allocate all segments from P1 into memory
        // (This would skip over the already allocated [P1, S1]
        // Note that P1.S1 is already in the memory. Each segment can only be added once
        System.out.println("Add all segments of P1 to Main Memory .");
        M.allocate(p1); // or some variant of this form to all Segments of P1 in main memory.
        p1.segmentTable(); // show how the segment table looks after the operations

        M.memoryState(); // show how the memory looks after the operations
        // you can add P2 and P3 in here 

        
		System.out.println("Deallocate segment [P1 S1] from the main memory ");
		M.deallocate(p1,p1.getSegment(1));
		p1.segmentTable();
		M.memoryState();

		System.out.println("Deallocate P1 from the main memory");
		M.deallocate(p1);
		p1.segmentTable();
		M.memoryState();
		
		
		System.out.println("Resize Process " + p3.toString());
		p2.resize("10, -30, 40, 10, -20");		
		p2.segmentTable();
		M.memoryState();
		
		
        System.out.println("End Component B.1");
        
        
        System.out.println("Start Component B.2.2");
     // create a new process for tasks B.2.2
        Process p5 = new Process("5, [20; rwx], [70; r--], [50; -w-]");
        
        //TODO: more code to be added
        // ..... 
        System.out.println("End Component B.2.2");
        
        // TODO: continue to complete the rest of the code
	}
}
