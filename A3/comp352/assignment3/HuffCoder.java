
/** 
 *  @author Hudson Xingcheng Lu


 *  @version 16 June 2023
 * 
 * Written by: Hudson Xingcheng Lu 40254326
 * COMP352
 * Assignment # 3
 * Due Date Friday 16 June
*/

/**


1. What is the purpose of using a priority queue in Huffman coding, and how does it help to generate
an optimal code?

A priority queue is used in Huffman coding to arrange symbols/characters in ascending order of their frequencies, which aids in the construction of the Huffman tree. 
During each iteration, the two nodes with the smallest frequencies are removed from the queue and combined into a new node. This process continues until a single tree is formed. 
The priority queue ensures that symbols are sorted and prioritized based on their frequencies when removing and inserting elements which facilitates the creation of the Huffman coding tree. 
By using a variable-length code, the priority queue helps generate an optimal code by assigning shorter codes to more frequently occurring symbols.



2. How does the length of a Huffman code relate to the frequency of the corresponding symbol, and
why is this useful for data compression?


The length of a Huffman code for a symbol is inversely proportional to its frequency. 
Symbols with higher frequencies are assigned shorter codes, while symbols with lower frequencies receive longer codes. 
When the length of a Huffman code is longer, it signifies that the corresponding symbol has a lower frequency. 
Conversely, more common symbols (higher frequency) are assigned shorter codes. 
This relationship is useful for data compression because it allows frequently occurring symbols to be represented with fewer bits, resulting in reduced data size when encoding.



3. What is the time complexity of building a Huffman code, and how can you optimize it?

The time complexity of building a Huffman code is O(N log N), where N is the number of characters, as it involves constructing a priority queue of size N with N-1 iterations. 
One can optimize the process by considering time spent on disk compression, which helps save space. 
There exists a trade-off between time and space, allowing for flexibility in choosing the preferred optimization. 
Building a frequency-based tree, as demonstrated in this assignment, is one approach that avoids redundant symbols. 
Another method is utilizing binary heaps, which simplify the insertion and removal of elements in the tree with a complexity of O(log N).


 */
package comp352.assignment3;
import java.io.*;
import java.util.*;

public class HuffCoder {
	
	    public static void main(String[] args) {
	        
	    if (args.length != 2)        
	        {
	            System.out.println("Invalid number of arguments.");
	        }

	        String filename = args[0].toLowerCase();
	        String operation = args[1].toLowerCase();

	    try {
	            Scanner kb = new Scanner(new FileInputStream(filename));
	            String text = "";
	            
	            while (kb.hasNextLine()) 
	            {
	                text += kb.nextLine();
	                if (kb.hasNextLine()) {
	                    text += "\n";
	                }
	            }

	            if (operation.equals("encode")) 	            
	            {
	                System.out.print(">");
	                Scanner input1 = new Scanner(System.in);
	                String word = input1.next().toLowerCase();

	                Map <Character, Integer> frequency1 = new HashMap<>();
	                
	                for (char c : text.toCharArray()) 
	                {
	                    char lowChar1 = Character.toLowerCase(c);
	                    frequency1.put(lowChar1, frequency1.getOrDefault(lowChar1, 0) + 1);
	                }

	                Node root = Node.HuffmanTree(frequency1);
	                Map <Character, String> huffmanCodes = Node.HuffmanCodes(root);
	                
	                //printing encodedText
	                String encodedText = Node.Encode(word, huffmanCodes);
	                System.out.println();
	                System.out.println();
	                System.out.println("The output would look like:");
	                System.out.println(encodedText);
	                System.out.println();
	                System.out.println("Values for encoding from ex1 and ex2:");
	                System.out.println("11110000101110000010000111011000");
	        	    System.out.println("10011111000011000010110101110101000");
	                
	                
	                
	            } 
	            
	            else if (operation.equals("decode")) 
	            {
	                System.out.print(">");
	                Scanner input2 = new Scanner(System.in);
	                String code = input2.next();

	                Map<Character, Integer> frequency2 = new HashMap<>();
	                
	                for (char c : text.toCharArray()) 
	                {
	                    char lowChar2 = Character.toLowerCase(c);
	                    frequency2.put(lowChar2, frequency2.getOrDefault(lowChar2, 0) + 1);
	                }

	                Node head = Node.HuffmanTree(frequency2);
	                
	                //printing decodeText
	                String decodedText = Node.Decode(code, head);
	                System.out.println();
	                System.out.println();
	                System.out.println("The output would look like:");
	                System.out.println(decodedText);
	                System.out.println();
	                System.out.println("Values for encoding from ex1 and ex2:");
	                System.out.println("robotic");
	        	    System.out.println("journeyi");
	            } 
	            
	            else 
	            {
	                System.out.println("Invalid operation.");
	            }
	        } 
	    
	    
	    catch (FileNotFoundException e) 
	    	{
	            e.printStackTrace();
	        }
	    
	    } // end of main method
	    
	} //end of class
