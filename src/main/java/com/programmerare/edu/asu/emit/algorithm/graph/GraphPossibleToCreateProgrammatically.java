package com.programmerare.edu.asu.emit.algorithm.graph;

import java.util.List;

import edu.asu.emit.algorithm.graph.Graph;

/**
 * The purpose of creating this class was to make it possible to create a Graph programmatically 
 * as an alternative to creating it from textfile data input.
 * The base class 'Graph' has a method 'importFromFile' and I did not want modify more of the existing code than needed. 
 * (for two reasons: to minimize risk of introducing bugs and to make it more likely that a future pull request will be accepted)
 *
 * When looking in that method, I could see it does three things:
 * 	(well, at least kind of, since it depends on how you count 'things' but I think the code can at least be divided into three parts as below) 
 * 1. invoke the method 'clear'
 * 2. set the number of vertices (but also some more things at the same time, i.e. not just setting an instance variable)
 * 3. add the edges from string lines with three items in each row, the two vertex names and the weight
 * 
 * The method 'clear' was already public so it could have been used without subclassing.
 * However, the method used in step 3 above is named 'addEdge' and is protected.
 * To be able to invoke it (without changing its access level to public) I created this subclass.
 * There were a few lines of code involved in step 2 above, and instead of copying those
 * rows into this subclass, I extracted those lines of code into a new protected method 'setNumberOfVertices' used below. 
 * 
 * @author Tomas Johansson
 */
public final class GraphPossibleToCreateProgrammatically extends Graph {
	 
	/**
	 * There is a requirement for the input graph. 
	 * The ids of vertices must be consecutive.
	 * The previous two sentences was copied from method {@link #importFromFile(String)} in the base class
	 * which does basically the same thing as this constructor but it has dependency to input existing in a file. 
	 * 
	 * @param numberOfVertices self descriptive name but note that they must be consecutive as mentioned above. 
	 * @param linesWithEdgeNamesAndWeight a list of strings, and each such string must have three parts separated with a space, 
	 * 		the id for the start and end vertex for the edge, and the weight for the edge 
	 */
	public GraphPossibleToCreateProgrammatically(final int numberOfVertices, final List<EdgeYanQi> edges) {
		clear();
		setNumberOfVertices(numberOfVertices);
		for (EdgeYanQi edge : edges) {
			addEdge(edge.getStartVertexId(), edge.getEndVertexId(), edge.getWeight());
		}		
	}	
}