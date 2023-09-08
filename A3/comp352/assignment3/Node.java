package comp352.assignment3;
import java.util.*;


class Node implements Comparable<Node> {
	
    public char character;
    public int frequency;
    public Node left;
    public Node right;

    public Node(char character, int frequency, Node left, Node right) 
    {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public int compareTo(Node other) 
    {
        return this.frequency - other.frequency;
    }

    // Building Huffman tree
    static Node HuffmanTree(Map<Character, Integer> frequency) 
    {
        PriorityQueue <Node> priorityQueue = new PriorityQueue<>();
        
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) 
        {
            priorityQueue.offer(new Node(entry.getKey(), entry.getValue(), null, null));
        }

        while (priorityQueue.size() > 1) 
        {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();

            Node internNode = new Node('\0', left.frequency + right.frequency, left, right);
            priorityQueue.offer(internNode);
        }
        return priorityQueue.poll();
    }

    
    static Map<Character, String> HuffmanCodes(Node root) 
    {
        Map<Character, String> codes = new HashMap<>();
        HuffmanCodes(root, "", codes);
        return codes;
    }

    private static void HuffmanCodes(Node node, String currCode, Map<Character, String> codes) 
    {
        if (node == null) 
        {
            return;
        }

        if (node.left == null && node.right == null) 
        {
            codes.put(node.character, currCode);
        }

        HuffmanCodes(node.left, currCode + "0", codes);
        HuffmanCodes(node.right, currCode + "1", codes);
    }

    // Encode text 
    static String Encode(String text, Map <Character, String> huffmanCodes) 
    {
        String encodedText = "";
        for (char c : text.toCharArray()) 
        {
            encodedText += huffmanCodes.get(c);
        }
        return encodedText;
    }

    // Decode text 
    static String Decode(String encodedText, Node head) 
    {
        String decodedText = "";
        Node current = head;
        for (char c : encodedText.toCharArray()) 
        {
            if (c == '0') 
            {
                current = current.left;
            } 
           
            else 
            {
                current = current.right;
            }

            if ((current.left == null) && (current.right == null)) 
            {
                decodedText += current.character;
                current = head;
            }
        }
        return decodedText;
    }
} // end of Class
