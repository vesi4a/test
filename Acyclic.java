package GraphFiles;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.*;

public class Acyclic 
{
	private DiGraphADT g;
	private int n;
	public Acyclic()
	{
		n=10;
		g = new DiGraphEdgeList(n);
		try {
            readGraph();
        }
        catch (Exception e){
            System.out.println("Invalid file content");
        }
				
	}
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
	 public boolean isAcyclic()
	 {
		 boolean cycle = false;
		 int[] countArray = new int[n];       //array which initially will hold the in-degrees of the nodes
         Queue queue = new LinkedList();      // temporary queue which will hold the non-printed nodes of count 0
         Queue finalOrder = new LinkedList(); // a queue which will hold the final order of the nodes. Here we will need that to determine if the graph contains a cycle
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
         	queue.remove(v);                  
         	finalOrder.add(v);                //Add it to the final order queue. We need that later to check if the graph contains a cycle.
         	for(int i=0; i<g.successors(v).size();i++)     //For all the nodes which are held in the successors ArrayList
         	{
         		countArray[g.successors(v).get(i)]--;      //Decrement the count of this node in the count array
         		if(countArray[g.successors(v).get(i)]==0)  //If the count of the node is 0 add it to the temporary queue
         		{
         			queue.add(g.successors(v).get(i));     //g.successors(v).get(i) is the NODE number which is also the index...
         		}                                          //...in the count array.
         	}	
         }  
         if(finalOrder.size()!= countArray.length)         //If the finalOrder queue size is not equal to the countArray size(these are all the nodes in the graph)...
         {                                                 //...this means that not all nodes are visited and that there is a cycle somewhere in the graph.
        	 cycle = true;
         }
		 return cycle;
		 
	 }
	 public static void main(String[] args)
	    {
	        Acyclic DAG = new Acyclic();
	        boolean containsCycle = DAG.isAcyclic();
	        if(containsCycle)
	        	System.out.println("This graph contains a cycle");
	        else
	        	System.out.println("This graph does not contain a cycle");
	    }
}
