package comp352.datastructures;

/** 
 *  @author Hudson Xingcheng Lu


 *  @version 26 May 2023
 * 
 * Written by: Hudson Xingcheng Lu 40254326
 * COMP352
 * Assignment # 1
 * Due Date Friday 26 May
*/


/**
  
Q2) Theory: Qualify This Recursion 
 	In your own words, discuss the trade-offs between using recursion and using an iterative approach for this particular assignment. 
 	Limit your answer to 150 words.
 	
 	
The recursive definition for this particular assignment is
notFib(n) = { notFib(n - 5) + notFib(n - 4) + (2 * notFib(n - 2)) (if n >= 6)
            { 1 (if n<=5 && n>= 0) //base case 	
 	
It reduces a problem repeatedly into simpler versions of the same problem until it reaches the base case that can be solved. 
Then, it recalls the version before (the less simple) and returns the solved version. This process continues repeatedly until 
the original problem is solved. An iterative approach would declare and initialize a variable to 0. Then, it uses a loop to add 
the value of each iteration until it reaches the end of the condition set, and it exits the loop. Finally, it returns the variable 
which refers to the sum of all the iterations. Despite being more intuitive, like for Fibonacci numbers, recursion can be 
less efficient than the iterative approach as it involves the overhead of function calls and maintaining a call stack, which can potentially 
result in stack overflow and increased execution time if a large input size is given. 	
 	


Q3) Theory: Impact of Size
	What sort of issues could potentially arise when working with very large sequences in your program? 
	How could these issues be mitigated? 
	Limit your answer to 150 words.
	
	
The issues arising from large sequences revolve around memory usage and program execution time. 
Indeed, it will consume a substantial amount of memory to store the values and be time-consuming to execute the given tasks. 
It could potentially lead to out-of-memory errors, particularly when employing recursion, 
as the number of recursive function calls grows exponentially. There are many approaches to mitigate these issues, 
such as managing memory usage, implementing memorization, optimizing computations using advanced formulas, algorithms, 
libraries, and data structures designed for generating Fibonacci sequences. Indeed, arbitrary precision arithmetic reduces 
computational complexity and memory usage, as it provides accurate computation and storage of values. 
Another approach is memorization, achieved by caching previously computed values to avoid redundant calculations, as it reuses intermediate results. 
Lastly, we can reduce memory consumption and improve execution time by storing only the essential values and discarding 
any intermediate results that are no longer needed.	
	

 
 */


public class FibonacciSequenceInspector {
	private static int count = 0;

    public static void main(String[] args) {
       
    if (args.length == 1)
    {		
        int n = Integer.parseInt(args[0]);
        
        int[] sequence = new int[n];
        generateSequence(n, sequence);
        
        for (int i = 0; i < n; i++) {
            System.out.print(sequence[i] + " ");
        }
        
        System.out.println();
        System.out.println("Calls: " + count);
    }
    }
    public static int notFib(int n) {
        count++;

        if (n <= 0) {
            return 0;
        }

        if (n <= 5) {
            return 1;
        } else {
            return notFib(n - 5) + notFib(n - 4) + (2 * notFib(n - 2));
        }
    }

    public static void generateSequence(int n, int[] sequence) {
        for (int i = 0; i < n; i++) {
            sequence[i] = notFib(i + 1);
        }
    }

}
