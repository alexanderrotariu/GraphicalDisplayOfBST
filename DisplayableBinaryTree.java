import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
   This program demonstrates the graphical
   display of binary trees.
 */

//The frame code was mainly used from 
//Starting out with Java: From Control Structures through Data Structues
//4th edition, Gaddis & Muganda , Pearson

//THE FRAMEWORK OF THIS CODE WAS TAKEN FROM THE TEXTBOOK AS PROF. HURD ALLOWED US TO USE THIS FRAMEWORK.

//A majority of their framework was used to graphically display the Binary tree


public class DisplayableBinaryTree
{
    private static class Node
    {
        int value;          // Value stored in node
        Node left, right;    // Left and right child      
        
        /**
           Constructor for leaf nodes.
           @param val The value to initialize the node.
        */
        
        Node(int val)
        {
            value = val;
            left = null;
            right = null;
        }   
        
        /** 
           Constructor for non-leaf nodes.
           @param val The value to initialize the node.
           @param leftChild The link to the left child.
           @param rightChild The link to the right child.
        */

        Node(int val, Node leftChild, Node rightChild)
        {
            value = val;
            left = leftChild;
            right = rightChild;
        }
    }
    
    /**
       The BTreeDisplay class graphically displays
		 trees in a JPanel. The JPanel is recursively
		 partitioned into a top part displaying the root,
		 and two lowerparts displaying the left and 
		 right subtrees.
    */
    
    private class BTreeDisplay extends JPanel
    {
        /**
           Constructor.
           @param tree The root of the binary 
			  tree to display.
         */
        
        BTreeDisplay(Node tree)
        {           
           setBorder(BorderFactory.createEtchedBorder());
           setLayout(new BorderLayout());
           if (tree != null) 
           {          
               String value = String.valueOf(tree.value);  
               int pos = SwingConstants.CENTER;
               JLabel rootLabel = new JLabel(value, pos);                         
               add(rootLabel, BorderLayout.NORTH);
               JPanel panel = new JPanel(new GridLayout(1, 2));
               panel.add(new BTreeDisplay(tree.left));
               panel.add(new BTreeDisplay(tree.right));    
               add(panel);
           }       
        }   
    }
   
    private Node root;          // Root of binary tree
    
    /**
       Constructor.
    */
    
    public DisplayableBinaryTree()
    {
        //Node aNode = new Node('A');  
        //aNode.left = new Node('B');
        //Node dNode = new Node('D');
        //Node cNode = new Node('C', dNode, null);
        //aNode.right = cNode;
        //root = aNode;        

        
    }
    
    //A recursive binary search
    public static Node recursiveBinaryTree(int[] arr, int start, int end)
    {
        //BASE CASE
        if(end < start)
        {
            return null;
        }
        //Getting the middle from the root.
        int mid = (start + end) / 2;
        //Making a node root, this will be my root node that i place into the binary tree display.
        Node root = new Node(arr[mid]);
        //Compute the left side of the node. Recursivly call the method. crete new nodes and the left child to the first node.
        root.left = recursiveBinaryTree(arr, start, mid -1);
        //Compute the right side of the node. Recursivly call the method. crete new nodes and the right child to the first node.
        root.right = recursiveBinaryTree(arr, mid +1 , end);
        
        //At the end, return each root, thus giving all nodes and their children.
        return root;
    }
    
    /**
       The getView method creates and returns a 
       a graphical view of the binary tree.
       @return A panel that displays a view of the tree.
    */
    
    public JPanel getView()
    {
       return new BTreeDisplay(root);       
    }

    /**
       The main method simply creates a Displayable
       Binary tree and displays it.
    */

    public static void main(String [ ] args)
    {
        
        //Making a scanner 
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter a number. This will be the size of your. The array will have random values.");
        int userInput = input.nextInt();

        //Creating a array of size userInput and assigning random values
        int[] genArray = new int[userInput];

        //The for loop assigns random values
        for(int i = 0; i < genArray.length; i++)
        {
            //A int from 0 - 100
            genArray[i] = (int)(Math.random()*100);
        }

        //Displaying the array
        printArray(genArray);

        //SORTING THE ARRAY (ITERATIVE INSERTION SORT)
        InsertionSort(genArray);

        printArray(genArray);

        Node array1 = recursiveBinaryTree(genArray, 0, genArray.length-1);
     
        DisplayableBinaryTree binTree = new DisplayableBinaryTree();
        binTree.root = array1;
        String title = "Graphical Display of Binary Tree";		
        JFrame bFrame = new JFrame(title);                  
        bFrame.add(binTree.getView());
        bFrame.pack();
        bFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bFrame.setVisible(true);
    }
    
    
    //Display array
    public static void printArray(int [] arr)
    {
        System.out.println("Your array is: ");
        for(int i = 0; i < arr.length; i++)
        {
            System.out.println(arr[i]);
        }
        System.out.println("-------------------------------------------------");
    }
    
    //Insertion sort (ITERATIVE)
    public static void InsertionSort(int[] arr)
    {
        int len = arr.length;
        
        for(int i = 1 ; i < len; i++)
        {
            //Current int
            int search = arr[i];
            //Previous index
            int prev = i - 1;
        
            //Look ahead while loop for the actual sorting
            while(prev >= 0 && arr[prev]> search)
            {
                arr[prev + 1] = arr[prev];
                prev = prev - 1;
            }
            
            //Last value
            arr[prev+1] = search;

        }
        
    }
}