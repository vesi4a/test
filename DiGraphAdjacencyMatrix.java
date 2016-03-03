package GraphFiles;

import java.util.ArrayList;

public class DiGraphAdjacencyMatrix implements DiGraphADT
{
	private int nNodes;
	boolean[][] matrix;
	/* Constructor for the DiGraphAdjacencyMatrix representation which takes
	 * a single parameter n for the number of nodes in a graph and creates the
	 * matrix
	 */
	public DiGraphAdjacencyMatrix(int n)
	{
		matrix = new boolean[n][n];
		nNodes = n;
	}
	/** returns the number of nodes in the graph*/
	
	public int nNodes() 
	{
		return nNodes;
	}

	/** This method returns the number of edges in a given graph*/
	public int nEdges() 
	{
		int nEdges = 0;
		for(int i=0; i<matrix.length; i++)
		{
			for(int j = 0; j<matrix.length; j++) 
			{
				if(matrix[i][j]==true)
				{
					nEdges++;
				}
			}
		}
		return nEdges;
	}

	 /** This method adds an edge from node1 to node2
    If the edge is already present in the graph the method returns false, because the edge should not be duplicated
    If the edge already existed in the graph then the method should 
    return false. Otherwise it should return true.
    node1 is the source node
    node2 is destination node
    The method returns true if the edge was not previously present. Otherwise returns false.
    */
	public boolean addEdge(int node1, int node2) 
	{
		if(isEdge(node1,node2))
		{
			return false;
		}
		else
		{
			matrix[node1][node2]= true;
			return true;
		}
	}

	/** This method returns true iff there is an edge from node1 to node2
    node1 is the source node
    node2 is the destination node
    They are both passed as parameters
    */
	
	public boolean isEdge(int node1, int node2) 
	{
		if(matrix[node1][node2]==true) 
		{
			return true;
		}
		else
			return false;
	}

	/** This method shows all the successors of a given node in a graph
    The node is passed as a parameter and the method
    returns an ArrayList of Integers which represent the successor nodes of the given node
    */
	public ArrayList<Integer> successors(int node) 
	{
		ArrayList<Integer> successorNodes = new ArrayList<Integer>();
		for(int i=0; i<matrix.length; i++)
		{
			if(matrix[node][i]==true)
			successorNodes.add(i);
		}
		return successorNodes;
	}

	/** This method shows all the predecessors of a given node in a graph
    The node is passed as a parameter and the method
    returns an ArrayList of Integers which represent the predecessor nodes of the given node
    */
	public ArrayList<Integer> predecessors(int node)
	{
		ArrayList<Integer> predecessorNodes = new ArrayList<Integer>();
		for(int i=0; i<matrix.length; i++)
		{
			if(matrix[i][node]==true)
				predecessorNodes.add(i);
		}
		return predecessorNodes;
	}

	/** This method shows the outdegree of a given node in a graph
    the node is passed as a parameter and
    the outDegree is returned
    */
	public int outDegree(int node)
	{
		return successors(node).size();
	}

	/** This method shows the InDegree of a given node in a graph
    the node is passed as a parameter and
    the outDegree is returned
    */
	public int inDegree(int node) 
	{
		return predecessors(node).size();
	}

}
