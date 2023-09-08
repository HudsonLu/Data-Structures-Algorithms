package comp352.assignment.two;
/** 
 *  @author Hudson Xingcheng Lu


 *  @version 02 June 2023
 * 
 * Written by: Hudson Xingcheng Lu 40254326
 * COMP352
 * Assignment # 2
 * Due Date Friday 02 June
*/


/**
Q2) Theory: Qualify This Structuring
How did the structuring pass you performed, specifically the reversals chosen, affect swaps and comparisons? 
Was anything else affected? Answer in less than 100 words.

	The structuring pass is an efficient algorithm that impacts the number of runs of length 2 and reversals. 
	It uses reversals in ascending order to minimize swaps and comparisons compared to subsequent insertion sorting. 
	Insertion sorting has best, average, and worst case complexities of θ(n^2) for comparisons and swaps. The structuring pass 
	has the same number of comparisons as the overall count. Its best, average, and worst case complexities are θ(n).
	

Q3) Theory: Size of Runs
How do you feel the size of the specific runs you recorded (DESCENDING order of length 2) impacted performance? 
Answer in less than 100 words.

	The size of the specific runs (DESCENDING order of length 2) definitively impacted the performance of the sorting algorithm 
	as they were adjacent. When comparing and determining these runs, this optimization made the algorithm more efficient as it 
	makes the sorting faster. This allowed the algorithm to skip the sorting of this specific run, resulting in a reduction of 
	the number of swaps or reversals required.


Q4) Theory: Doubly Linked Lists
What would implementing this as a Doubly Linked List do? 
How would the specified structuring affect results? Answer in less than 100 words.
 
 
	Implementing the sorting algorithm as a Doubly Linked List would result in a different data structure for organizing elements,
	thereby improving efficiency. With a Doubly Linked List, insertions and deletions can be performed more easily, eliminating the
	need for swapping elements. Instead, comparisons are made to determine the correct placement of nodes by linking them before 
	and after in the appropriate position. However, comparisons remain a necessary component of the algorithm to ensure the 
	sorting is done in the desired order.

 
 
 */

import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StructSort {

    public static void main(String[] args) {
     
    	String filename = args[0];
		
    	try {
    					
    		Scanner kb= new Scanner(new FileInputStream(filename));			
			String record1 = kb.nextLine();
			
   
	        String[] numberStrings = record1.split(" ");	        
	        int[] A_original = new int[numberStrings.length];	        
	        for (int i = 0; i < numberStrings.length; i++) {
	            A_original[i] = Integer.parseInt(numberStrings[i]);
	        }
		
        
        int[] A = A_original.clone();
        
 

        int count_compare = 0;
        int count_length_run = 1;
        int count_reversal = 0;
        int count_run2 = 0;
        int count_swap = 0;

        for (int j = 0; j < A.length - 1; j++) {
            count_compare++;

            if (A[j] < A[j + 1]) {
                count_length_run++;
            } else if (A[j] > A[j + 1]) {
                int temp = 0;
                int temp1 = 0;

                if (count_length_run == 1) {
                    j++;
                    count_compare++;
                    count_run2++;
                } else if (count_length_run == 2) {
                    temp = A[j];
                    A[j] = A[j - 1];
                    A[j - 1] = temp;
                    count_swap++;
                    count_length_run = 1;
                    count_reversal++;
                } else if (count_length_run == 4) {
                    temp = A[j];
                    temp1 = A[j - 1];

                    A[j] = A[j - 3];
                    A[j - 1] = A[j - 2];
                    A[j - 2] = temp1;
                    A[j - 3] = temp;
                    count_length_run = 1;
                    count_reversal++;
                    count_swap += 2;
                }
            }
        }
        int count_compare1 = count_compare;
        
        ///////////////////////////////
     

        for (int i = 1; i < A.length; i++) {
            int key = A[i];
            int j = i - 1;

            while (j >= 0 && A[j] < key) {
                A[j + 1] = A[j];
                j--;
                count_swap++;
                count_compare++;
            }

            A[j + 1] = key;
            count_compare++;
        }
        count_compare--;
        count_compare--;
        
        //////////////////////////////

        for (int number : A_original) {
            System.out.print(number + " ");
        }

        System.out.println("\nWe sorted in DESC order");
        System.out.println("We counted " + count_run2 + " DESC run of length 2");
        System.out.println("We performed " + count_reversal + " reversals of runs in INCR order");
        System.out.println("We performed " + count_compare1 + " compares during structuring");
        System.out.println("We performed " + count_compare + " compares overall");
        System.out.println("We performed " + count_swap + " swaps overall");
        for (int number : A) {
            System.out.print(number + " ");
        }
        
} 
    		
    	catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}   
        
    }
    	
}



































