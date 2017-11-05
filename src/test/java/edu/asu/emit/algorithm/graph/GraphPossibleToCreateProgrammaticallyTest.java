package edu.asu.emit.algorithm.graph;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

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
		// the same test data as below but retrieving it from a file, and is not currently doing any assertions 
		// but just printing the results to the output window. 		

		// The below code is corresponding to the graph defined in file "data/test_6_2"
		final GraphPossibleToCreateProgrammatically graph = new GraphPossibleToCreateProgrammatically(
			6, // the number of vertices 
			Arrays.asList(
				"0 1 1",
				"1 3 1",
				"1 2 1",
				"4 0 0",
				"4 1 0",
				"4 3 0",
				"1 5 0",
				"3 5 0",
				"2 5 0"
			)
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
		assertExpectedPath(yenAlg.next(), 0.0, 4,1,5);
		assertExpectedPath(yenAlg.next(), 0.0, 4,3,5);
		assertExpectedPath(yenAlg.next(), 1.0, 4,1,2,5);
		assertExpectedPath(yenAlg.next(), 1.0, 4,0,1,5);
		assertExpectedPath(yenAlg.next(), 1.0, 4,1,3,5);
		assertExpectedPath(yenAlg.next(), 2.0, 4,0,1,2,5);
		assertExpectedPath(yenAlg.next(), 2.0, 4,0,1,3,5);
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
}