package GraphFiles;
import java.util.Scanner;
/** This is a Test application that is not required for this assignment. It is made only to test some things.
 * Do not take it into consideration.
 * @author Veselin Genchev
 *
 */
public class TestApp 
{
	public static void main(String [ ] args)
	{
		System.out.println("Please enter the number of nodes of your graph: ");
		Scanner in = new Scanner(System.in);
		int NumberOfNodes = in.nextInt();
		DiGraphADT graph;
		graph = new DiGraphAdjacencyList(NumberOfNodes);
		String option = in.nextLine();
		while(!option.equals("quit"))
		{
			switch(option)
			{
			case "nodes": System.out.println(graph.nNodes());
			break;
			case "edges": System.out.println(graph.nEdges());
			break;
			case "add": System.out.println("Enter the source node: ");
			int node1 = in.nextInt();
			System.out.println("Enter the destination node: ");
			int node2 = in.nextInt();
			graph.addEdge(node1, node2);
			break;
			case "is": System.out.println("Enter the source node: ");
			node1 = in.nextInt();
			System.out.println("Enter the destination node: ");
			node2 = in.nextInt();
			boolean is =graph.isEdge(node1, node2);
			System.out.println(is);
			break;
			case "succ": System.out.println("Enter the node: ");
			int node = in.nextInt();
			System.out.println("Successors: " + graph.successors(node));
			break;
			case "pred": System.out.println("Enter the node: ");
			node = in.nextInt();
			System.out.println("Predecessors: "+ graph.predecessors(node));
			break;
			case "indegree": System.out.println("Enter the node: ");
			node = in.nextInt();
			System.out.println("Indegree: "+ graph.inDegree(node));
			break;
			case "outdegree": System.out.println("Enter the node: ");
			node = in.nextInt();
			System.out.println("Outdegree: " + graph.outDegree(node));
			break;
			}
			option = in.nextLine();
			
		}
		System.out.println("You exited the program");
		System.exit(0);
	}
}
