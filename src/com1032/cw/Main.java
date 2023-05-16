package com1032.cw;


public class Main {

	
	public static void main(String[] args) {	
		
		// You should indicate the start and the end for each task
		System.out.println("Start Component B.1\n");

		// Create Memory with 1024 bytes total memory, and 124 bytes of OS memory
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
        System.out.println("\nAdd Segment [P1, S1] to Main Memory from Process " + p1.toString());
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
        
        
        // Testing the same for P2
        System.out.println("\nAdd Segment [P2, S1] to Main Memory from " + p2.toString());
        System.out.println("+ State Before");
        M.memoryState();
        System.out.println();
        p2.segmentTable();
        
        M.allocate(p2, p2.getSegment(1));
        
        System.out.println("- State After");
        M.memoryState();
        p2.segmentTable();
        
        System.out.println("Add all segments of P2 to Main Memory .");
        M.allocate(p2);
        p2.segmentTable();
        
        M.memoryState();
        System.out.println();
        
        
        
        // Testing the same for P3
        System.out.println("Add Segment [P3, S1] to Main Memory from " + p3.toString());
        System.out.println("+ State Before");
        M.memoryState();
        System.out.println();
        p3.segmentTable();
        
        M.allocate(p3, p3.getSegment(1));
        
        System.out.println("- State After");
        M.memoryState();
        p3.segmentTable();
        
        // Expecting error: not enough free space in memory
        System.out.println("Add all segments of P3 to Main Memory ");
        M.allocate(p3);
        p3.segmentTable();
        
        M.memoryState();
        System.out.println();
        
        
        
        
        // Attempt to deallocate one segment from the memory
		System.out.println("Deallocate segment [P1 S1] from the main memory ");
		M.deallocate(p1,p1.getSegment(1));
		p1.segmentTable();
		M.memoryState();

		// Attempt to deallocate one process from the memory
		System.out.println();
		System.out.println("Deallocate P1 from the main memory");
		M.deallocate(p1);
		p1.segmentTable();
		M.memoryState();
		
		// Attempt to deallocate another segment from the memory
		System.out.println("Deallocate segment [P2 S2] from the main memory\n");
		M.deallocate(p2,p2.getSegment(2));
		p2.segmentTable();
		M.memoryState();
		
		
		// Attempt to resize the segments of a process
		System.out.println();
		System.out.println("Resize Process " + p3.toString());
		p3.resize("10, -30, 40, 10, -20");	
		
		// This method must be called to resize the processes after resizing the segments
		M.resizeProcess(p3);
		
		
		p3.segmentTable();
		M.memoryState();
		System.out.println();
		
		
		// Attempting to resize the segments of process 2, where one
		// will go to zero and therefore needs to be deleted
		System.out.println("+ State Before");
		p2.segmentTable();
		
		System.out.println();
		System.out.println("Resize Process " + p2.toString());
		p2.resize("15, 10, -20, 25");
		
		M.resizeProcess(p2);
		
		
		System.out.println("- State After");
		p2.segmentTable();
		M.memoryState();
		
		
        System.out.println("\nEnd Component B.1");
        
        
        
        
        System.out.println();
        System.out.println("Start Component B.2.2");
     // create a new process for tasks B.2.2
        Process p5 = new Process("5, [20; rwx], [70; r--], [50; -w-]");
        
        // Show the segment table for the new process
        p5.segmentTable();
        
        
        // Looking up each segment
        M.getSegment(p5, p5.getSegment(1));
        M.getSegment(p5, p5.getSegment(2));
        M.getSegment(p5, p5.getSegment(3));
        
        
        System.out.println("End Component B.2.2");
        
        
        
        System.out.println();
        System.out.println("Start Component B.2.4");
        System.out.println();
        
        // Shows the memory state before compacting
        M.memoryState();
        
        System.out.println("\nRunning compaction...");
        M.compactMemory();
        
        // Shows the memory state after compacting
        M.memoryState();
        
        System.out.println("\nEnd Component B.2.4");
        
	}
}
