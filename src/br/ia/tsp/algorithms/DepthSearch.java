/**
 * 
 */
package br.ia.tsp.algorithms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.moeaframework.problem.tsplib.TSPInstance;

/**
 * @author Priscylla
 *
 */
public class DepthSearch {
	
	private Stack<Integer> path;
	private TSPInstance instance;
	private double bestCost;/////////////
	private Stack<Integer> bestPath;///////////////
	private Integer sourceCity;//////////////////
	
	public DepthSearch(File instancePath) throws IOException {
		this.instance = new TSPInstance(instancePath);
	}

	public List<Integer> search(Integer source){
		path = new Stack<Integer>();
		bestPath = new Stack<Integer>();//////
		bestCost = Double.MAX_VALUE;//////////////////////
		sourceCity = source;//////////////////
		path.push(source);
		searchRec(source);
		//path.push(source);
		return StackToList(path);
	}
	
	private void searchRec(Integer node) {
		
		Integer nextNode = 0;
		List<Integer> successors = this.successorFunction(node);
		
		for (Integer integer : successors) {
			if(node.intValue()!=integer.intValue() && !path.contains(integer)){
				nextNode = integer;
//				if(!path.isEmpty()) path.pop();////////////
			}
		}
		path.push(nextNode);
		
		if(!isGoal()){
			searchRec(nextNode);
		} else {
			path.push(sourceCity);
			bestPath = (Stack<Integer>) path.clone();
			bestCost = cost(path);
//			path.pop();//////////////
		}
	}

	public boolean isGoal(){
		if(path.size()==instance.getDimension()){
				return true;
			}
		return false;
	}
	
	public List<Integer> successorFunction(Integer node){
		List<Integer> successorsList = new ArrayList<Integer>();
		for (int i = 1; i <= instance.getDimension(); i++) {
			double distance = instance.getDistanceTable().getDistanceBetween(node.intValue(), i);
			if(distance != 0){
				successorsList.add(new Integer(i));
			}
		}
		return successorsList;
	}
	
	public double cost(List<Integer> solutionPath) {
		double solutionCost = 0;
		 
		for (int i = 0; i < solutionPath.size()-1; i++) {
			solutionCost = solutionCost + instance.getDistanceTable().
					getDistanceBetween(solutionPath.get(i).intValue(), solutionPath.get(i+1).intValue());
		}
		return solutionCost;
	}
	
	public List<Integer> StackToList(Stack<Integer> stack){
		List<Integer> newList = new ArrayList<Integer>();
		while(!stack.isEmpty()){
			newList.add(stack.pop());
		}
		return newList;
	}

}
