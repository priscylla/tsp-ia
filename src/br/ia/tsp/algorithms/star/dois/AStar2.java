package br.ia.tsp.algorithms.star.dois;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.moeaframework.problem.tsplib.DistanceTable;
import org.moeaframework.problem.tsplib.TSPInstance;

/**
 * This is A* Search Class which performs A* search
 * to find optimal tour of the graph, using Straight Line Distance Heuristics on a HashMap created by the parser class.
 * this class also creates a traversal log of visited nodes.
 * 
 *  
 * */
public class AStar2 {
	
	
	
	public AStar2(File instancePath) throws IOException {
		instance = new TSPInstance(instancePath);
        distances = instance.getDistanceTable();
	}

	private TSPInstance instance;
	private DistanceTable distances;
	
	private Integer sourceCity;
	List<NodeInfo> path = new ArrayList<NodeInfo>();
	
	/**
	 * frontier is a FIFO priority queue
	 * child is a node that is removed from the parent's childList
	 * childList is a list of nodes that a parent is connected to.
	 * endtour is the tour from the the last visited node back to the source node which is also the goal node
	 * 
	 */
	
	PriorityQueue<NodeInfo> frontier = new PriorityQueue<NodeInfo>(1, new Comparator<NodeInfo>() {

		@Override
		public int compare(NodeInfo a, NodeInfo b) {
			return Double.valueOf(a.f).compareTo(b.f);
		}
	} );
	
	
	Node child;
	List<Node> childList;
	
	NodeInfo childData, endTour;
	NodeInfo node;
	double gcost, hcost;
	
	/**
	 * heuristicCost takes in a string value child, and finds its straight line
	 * distance from the goal node
	 * 
	 * @param child : a variable of type String which represents a child name.
	 * @return: returns the straight line distance of child from the goal node
	 * Retorna a distância em linha reta do no até o destino
	 */
	private double heuristicCost(int child) {
		
		double h = distances.getDistanceBetween(child, sourceCity.intValue());
		return h;
	}
	
	private void addChildren(List<Node> children, boolean last) {
		int i = 0;

		if (last == false) {
			while (i < children.size()) {
				child = children.get(i);

				if (!(node.path.contains(child.name))) {

					gcost = child.sld + node.g;
					
					hcost = heuristicCost(child.number);

					/**
					 * getting the ancestorList of parent and putting it in the
					 * child
					 */

					childData = new NodeInfo(child.name, node.path, gcost,
							hcost);
					childData.path.add(node.nodeName);
					childData.f = gcost + hcost;
					frontier.add(childData);
				}
				i++;
			}
		} else {

			int j = 0;
			while (j < children.size()) {
				double gcost = 0.0;
				child = children.get(j);
				if (child.number == sourceCity.intValue()) {
					gcost = child.sld + node.g;
					endTour = new NodeInfo(child.name, node.path, gcost);
					endTour.path.add(node.nodeName);
					endTour.path.add(child.name);
					endTour.f = endTour.g + endTour.h;
					break;
				}
				j++;
			}
		}
	}
	
	public void search(Integer source) {
		
		this.sourceCity = source;
		
		node = new NodeInfo(source.toString()); 

		frontier.add(node);

		while (true) {


			if (frontier.peek() == null) {
				return;
			}

			/**
			 * Removing the front of the queue frontier
			 */
			node = frontier.remove();

			if (node.path.size() == (instance.getDimension() - 1)) {
				
				
				path.add(node);

				childList = this.successorFunction(Integer.valueOf(node.nodeName));
				addChildren(childList, true);
				/**
				 * Writing the optimal path in the log as well as output file.
				 */
				
//				path.add(endTour);
				System.out.println(path);

				return;
			} else {
				path.add(node);
			}

			childList = this.successorFunction(Integer.valueOf(node.nodeName));
			addChildren(childList, false);

		}

	}
	
	public List<Node> successorFunction(Integer node){
		List<Node> successorsList = new ArrayList<Node>();
		for (int i = 1; i <= instance.getDimension(); i++) {
			double distance = instance.getDistanceTable().getDistanceBetween(node.intValue(), i);
			if(distance != 0){
				successorsList.add(new Node(""+i, distances.getDistanceBetween(i, sourceCity.intValue())));
			}
		}
		return successorsList;
	}

}
