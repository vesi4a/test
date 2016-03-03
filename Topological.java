package GraphFiles;
import java.util.*;
import java.io.*;

/**
 * Class Topological 
 * The main method creates and populates a graph from edges in file
 * Edges.txt. It then calls method topologicalOrder() which you are asked to
 * code using the algorithm presented in lectures, making use of a queue.
 * 
 * @author I.Ross 
 */
public class Topological
{
    // instance variables - do not alter
    private DiGraphADT g;
    private int n;
    
    /**
     * Constructor for objects of class Topological - do not alter except
     * that you may wish to replace the instance of DiGraphEdgeList with an 
     * instance of a different implementation of DiGraphADT
     */
    public Topological()
    {
        // initialise instance variables
        n = 10;   // example graph used here has 10 nodes
        g = new DiGraphEdgeList(n);
        try {
            readGraph();
        }
        catch (Exception e){
            System.out.println("Invalid file content");
        }
    }

    /**
    * populates the graph taking data from file Edges.txt - do not alter
    */
    private void readGraph() throws Exception 
    {
        int firstNode, secNode;
        Reader r = new BufferedReader(new FileReader("Edges.txt"));
        StreamTokenizer st = new StreamTokenizer(r);
        st.parseNumbers();
        st.nextToken();
        while (st.ttype != StreamTokenizer.TT_EOF) {
          firstNode = (int) st.nval;
          st.nextToken();
          secNode = (int) st.nval;
          System.out.println(firstNode + " " + secNode);
          g.addEdge(firstNode,secNode);
          st.nextToken();
        }
    }

   /** prints a topological ordering of the nodes in the graph, using 
    * the improved algorithm presented in lectures. This algorithm makes use
    * of a queue holding nodes of degree 0
   */
    public void topologicalOrder()
    {
        int[] countArray = new int[n];       //array which initially will hold the in-degrees of the nodes
        Queue queue = new LinkedList();      // temporary queue which will hold the non-printed nodes of count 0
        Queue finalOrder = new LinkedList(); // a queue which will hold the final order of the nodes. Don't really need that.
        for(int i=0; i<g.nNodes();i++)       // For all the nodes in the graph add their in-degrees in the count array.
        {
        	countArray[i]=g.inDegree(i);
        }
        for(int i=0; i<countArray.length;i++) //Place all nodes with in-degree 0 from the array to the temporary queue.
        {
        	if(countArray[i]==0)
        	{
        		queue.add(i);
        	}
        }
        while(queue.isEmpty()==false)         //While Temporary queue is not empty
        {
        	int v = (int)queue.element();     //Get the head of this queue which represents a queue
        	System.out.print(v + " ");        //Print it and remove it from the temporary queue
        	queue.remove(v);                  
        	finalOrder.add(v);                //Add it to the final order queue. Don't really need to do that, because we already...
        									  //...printed the node on the console.
        	for(int i=0; i<g.successors(v).size();i++)     //For all the nodes which are held in the successors ArrayList
        	{
        		countArray[g.successors(v).get(i)]--;      //Decrement the count of this node in the count array
        		if(countArray[g.successors(v).get(i)]==0)  //If the count of the node is 0 add it to the temporary queue
        		{
        			queue.add(g.successors(v).get(i));     //g.successors(v).get(i) is the NODE number which is also the index...
        		}                                          //...in the count array.
        	}
        	
        }    
    }
    
    /** creates and populates a graph and then prints a topological
     * ordering of the nodes  - do not alter
     */    
    public static void main(String[] args)
    {
        Topological gr = new Topological();
        gr.topologicalOrder();
    }
}

