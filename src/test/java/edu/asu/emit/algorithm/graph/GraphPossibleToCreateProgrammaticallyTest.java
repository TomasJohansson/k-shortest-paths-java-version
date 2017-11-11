package edu.asu.emit.algorithm.graph;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import edu.asu.emit.algorithm.graph.abstraction.BaseVertex;
import edu.asu.emit.algorithm.graph.shortestpaths.YenTopKShortestPathsAlg;

/**
 * @author Tomas Johansson
 * @see GraphPossibleToCreateProgrammatically
 */
public class GraphPossibleToCreateProgrammaticallyTest {

	@Test
	public void testGraphPossibleToCreateProgrammatically() {
		// The method YenTopKShortestPathsAlgTest.testYenShortestPathsAlg4MultipleGraphs is using 
		// similar test data as below but retrieving it from a file, and it was not doing any assertions 
		// but just printing the results to the output window.
		// However, the weights have been modified a little bit to make sure that no total weights will be 
		// the same, since equal values make the sort order less obvious when you use assertions.  
		
		// Each edge in the strings below are specified with three parts separated by a space as below:
		// "startVertexId endVertexId weight"		
		List<String> edgeData = Arrays.asList(
			"0 1 1",
			"1 3 1",
			"1 2 1",
			"4 0 0",
			"4 1 0",
			"4 3 0",
			"1 5 0.2",
			"3 5 0.1",
			"2 5 0"
		);
		List<EdgeYanQi> edges = getEdges(edgeData);		
		
		final GraphPossibleToCreateProgrammatically graph = new GraphPossibleToCreateProgrammatically(
			6, // the number of vertices
			edges
		);
		
		final YenTopKShortestPathsAlg yenAlg = new YenTopKShortestPathsAlg(graph, graph.getVertex(4), graph.getVertex(5));
		// The output below is copied from the output window after running the algorithm for graph with data in file "data/test_6_2"
		// with the test method "testYenShortestPathsAlg4MultipleGraphs" in test file "edu.asu.emit.qyan.test.YenTopKShortestPathsAlgTest"
		//	Path 0 : [4, 1, 5]:0.0
		//	Path 1 : [4, 3, 5]:0.0
		//	Path 2 : [4, 1, 2, 5]:1.0
		//	Path 3 : [4, 0, 1, 5]:1.0
		//	Path 4 : [4, 1, 3, 5]:1.0
		//	Path 5 : [4, 0, 1, 2, 5]:2.0
		//	Path 6 : [4, 0, 1, 3, 5]:2.0
		//	Result # :7

		// The assertions below are based on the output above
		assertExpectedPath(yenAlg.next(), 0.1, 4,3,5);
		assertExpectedPath(yenAlg.next(), 0.2, 4,1,5);		
		assertExpectedPath(yenAlg.next(), 1.0, 4,1,2,5);
		assertExpectedPath(yenAlg.next(), 1.1, 4,1,3,5);
		assertExpectedPath(yenAlg.next(), 1.2, 4,0,1,5);		
		assertExpectedPath(yenAlg.next(), 2.0, 4,0,1,2,5);
		assertExpectedPath(yenAlg.next(), 2.1, 4,0,1,3,5);
		assertFalse(yenAlg.hasNext());
	}

	private void assertExpectedPath(Path path, double expectedTotalCost, int... nodenames) {
		assertEquals(path.getWeight(), expectedTotalCost, SMALL_DELTA_VALUE_FOR_DOUBLE_CMOPARISONS);
		final List<BaseVertex> vertices = path.getVertexList();
		for (int i = 0; i < nodenames.length; i++) {
			assertEquals(vertices.get(i).getId(), nodenames[i]);
		}
	}

	private final static double SMALL_DELTA_VALUE_FOR_DOUBLE_CMOPARISONS = 0.00000001;
	
	/** 
	 * The string splitting code in this method are the same as in a method in class 'Graph'
	 * but since I am not the owner of that project I do not want to make many refactorings  
	 * that makes it deviate more from the original. Therefore the line splitting is duplicated here.     
	 * @see Graph#addEdgeFromStringWithEdgeNamesAndWeight(String)
	 */
	private List<EdgeYanQi> getEdges(List<String> lines) {
		List<EdgeYanQi> edges = new ArrayList<EdgeYanQi>(); 
		for (String line : lines) {
			String[] strList = line.trim().split("\\s");
			int startVertexId = Integer.parseInt(strList[0]);
			int endVertexId = Integer.parseInt(strList[1]);
			double weight = Double.parseDouble(strList[2]);
			edges.add(new EdgeYanQi(startVertexId, endVertexId, weight));
		}
		return edges;
	}	
}