package ca.concordia.comp352.hashing;

/** 
* @author Hudson Xingcheng Lu
* @version 25 June 2023
* 
* Written by: Hudson Xingcheng Lu 40254326
* COMP352
* Assignment # 4
* Due Date Sunday 25 June
*/    

// Theory Questions

//1. What factors can lead to an inefficient hash table and how did you mitigate them in your design? 
//   Give specific examples from your subclass implementation.
//   
// The subclass TotallyReliableHASH.java provided in the assignment uses linear probing for collision resolution, 
// which can lead to clustering. An inefficient hash table can result from a costly hash function that produces numerous collisions and clustering. 
// To mitigate these issues in my design (EfficientHash.java), I implemented a quadratic function (tries * tries) to calculate 
// the next probe position using the line "index = (index + tries * tries) % getTableSize()". This approach reduces consecutive collisions 
// and improves the distribution of values in the hash table. By avoiding clustering with items that have different homes, items with different 
// hash values are placed in different slots.
//
//2. How does the size of the hash table affect its performance in terms of both time and space complexity? 
//   How did you choose the size of your hash table?
//		   
// The size of the hash table directly impacts its performance in terms of time and space complexity. 
// It involves a trade-off between space and time efficiency. A larger table size improves performance by reducing collisions 
// and enhancing element distribution, but it also increases space complexity and memory usage. Conversely, a smaller table 
// requires less memory but leads to more collisions and poorer performance. Sparse clustering can be beneficial as long as we 
// don't need to store every possible key. Collisions are addressed through collision resolution policies. For my hash table 
// implementation, I chose a large size by setting the size equal to the number of elements using the determineTableSize() method. 
// This approach allows for a 1-to-1 mapping relationship, minimizing collisions and ensuring better performance by quickly 
// locating elements through a single slot mapping.
//
//3. Discuss the time complexity of your hash table operations: insertion, deletion, and lookup. 
//   How do these complexities compare to those of the TRHASH version?
//		   
// The time complexity of hash table operations depends on factors such as the hash function, the number of collisions, 
// the collision resolution strategy, and the size of the hash table. In the EfficientHash subclass, the use of quadratic 
// probing for collision resolution ensures an even distribution of values across the hash space. This results in a constant 
// time complexity of O(1) for insertion, deletion, and lookup operations. Similarly, the TRHASH version also has a time complexity 
// of O(1) since it uses linear probing for collision resolution, which is a simple approach. However, linear probing can lead 
// to more collisions and lower performance compared to quadratic probing. When running both codes, the EfficientHash implementation 
// showed an improvement in performance time compared to the TRHASH version. The EfficientHash implementation took approximately 
// 1500 to 2000 ms to build the hash table, while the TRHASH version varied between 3000 to 4000 ms. This indicates nearly a halving
// of the time required for building the hash table, which demonstrates the improved efficiency of the EfficientHash 
// The comparison results between the two codes are --> HashSpeed: 39 and HashSize: 100.

import main.stuff.MyHash;
import main.stuff.NotFoundException;
import main.stuff.TooFullException;

public class EfficientHash extends MyHash {
	
    public EfficientHash(String filename) 
    {
        super(filename);
    }    
    @Override
    protected void insert(String str) throws TooFullException 
    {
        try 
        {
            find(str);
        } 
        catch (NotFoundException e) 
        {
            put(e.getIndex(), str);
            incSize();
        }
    }
    @Override
    protected String find(String str) throws NotFoundException, TooFullException 
    {
        int index = hash(str);
        int tries = 0;
        while (++tries <= getTableSize() + 1) 
        {
            if (get(index) == null)
            {
                throw new NotFoundException(index);
            }
            if (get(index).equals(str)) 
            {
                return str;
            }          
            index = ( (index + tries * tries) % getTableSize() ); // Quadratic probing
        }
        throw new TooFullException(str);
    }
    
    @Override
    protected int hash(String str) 
    {   
        int hash = 0;
        for (int i = 0; i < str.length(); i++) 
        {
            hash = ((hash * 31 + str.charAt(i)) % getTableSize());
        }
        return hash;
    }  
    @Override
    protected int determineTableSize(int i) 
    {
        return i;
    }
    
    
} // end of class

/**
 * It is important to note that the programs accept filenames as input. You should not hardcode filenames or file content in your program.

To compile the code you can run:
javac main/stuff/*.java
javac ca/concordia/comp352/hashing/EfficientHash.java

To run your code:
java main.stuff.Driver ca.concordia.comp352.hashing.EfficientHash dataE6.txt test100.txt

To run the provided Totally Reliable Hash:
java main.stuff.Driver main.stuff.TotallyReliableHASH dataE6.txt test100.txt

To compare your hash implementation to the TRHASH implementation:
java main.stuff.CompTime main.stuff.TotallyReliableHASH ca.concordia.comp352.hashing.EfficientHash dataE6.txt

See how much better you can do!

In addition to the programming task, you will also need to answer three theory questions related to the assignment.


 */





