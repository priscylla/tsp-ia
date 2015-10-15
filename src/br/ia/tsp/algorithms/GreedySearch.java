/**
 * 
 */
package br.ia.tsp.algorithms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.moeaframework.problem.tsplib.TSPInstance;

/**
 * @author Priscylla
 *
 */
public class GreedySearch {
	
	
	private TSPInstance instance;
	private List<Integer> path;
	
	
	public GreedySearch(File instancePath) throws IOException {
		this.instance = new TSPInstance(instancePath);
	}
	
	public List<Integer> search(Integer source){
		this.path = new ArrayList<Integer>();
		path.add(source);
		searchRec(source);
		path.add(source);
		return path;
	}
	
	public void searchRec(Integer source){
		
		double shortestDistance = Double.MAX_VALUE;
		int nextNode = 0;
		for (int i = 1; i <= instance.getDimension(); i++) {
			double distance = instance.getDistanceTable().getDistanceBetween(source.intValue(), i);
			if(source.intValue()!=i && distance < shortestDistance && !path.contains(Integer.valueOf(i))){
				nextNode = i;
				shortestDistance = distance;
			}
		}
		path.add(Integer.valueOf(nextNode));
		
		if(path.size()<instance.getDimension()){
			searchRec(Integer.valueOf(nextNode));
		}
	}
	
	
	
	public double cost(List<Integer> solutionPath) throws IOException{
		double solutionCost = 0;
		 
		for (int i = 0; i < solutionPath.size()-1; i++) {
			solutionCost = solutionCost + instance.getDistanceTable().
					getDistanceBetween(solutionPath.get(i).intValue(), solutionPath.get(i+1).intValue());
		}
		return solutionCost;
	}

}
