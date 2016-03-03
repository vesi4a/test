package GraphFiles;
import java.util.*;

import java.util.ArrayList;

public class DiGraphAdjacencyList implements DiGraphADT
{
	int nNodes;
	List<Integer>[] nodes;
	
	public DiGraphAdjacencyList(int n)
	{
		nNodes = n;
		nodes = (ArrayList<Integer>[])new ArrayList[n];
		for(int i=0; i<nodes.length; i++){
			nodes[i] = new ArrayList<>();
		}
	}

	public int nNodes() 
	{
		return nNodes;
	}

	@Override
	public int nEdges() 
	{
		int nEdges = 0;
		for(int i=0; i< nodes.length; i++)
		{
			for(int j=0; j< nodes[i].size(); j++) 
			{
				nEdges++;
			}
		}
		return nEdges;
		
	}

	@Override
	public boolean addEdge(int node1, int node2) 
	{
		if(isEdge(node1, node2))
		{
			return false;
		}
		else
		{
			nodes[node1].add(node2);
			return true;
		}
	}

	@Override
	public boolean isEdge(int node1, int node2) 
	{
		if(nodes[node1].contains(node2))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public ArrayList<Integer> successors(int node) 
	{
		ArrayList<Integer> successorNodes = new ArrayList<Integer>();
		for(int i=0; i<nodes[node].size();i++){
			successorNodes.add(nodes[node].get(i));
		}
		return successorNodes;
	}

	@Override
	public ArrayList<Integer> predecessors(int node) {
		ArrayList<Integer> predeccessorNodes = new ArrayList<Integer>();
		for(int i=0; i<nodes.length; i++)
		{
			if(nodes[i].contains(node))
			{
				predeccessorNodes.add(i);
			}
		}
		return predeccessorNodes;
	}

	@Override
	public int outDegree(int node) 
	{
		return successors(node).size();
	}

	@Override
	public int inDegree(int node)
	{
		return predecessors(node).size();
	}

}
