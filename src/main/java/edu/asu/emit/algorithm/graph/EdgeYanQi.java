package edu.asu.emit.algorithm.graph;

/**
 * The purpose of the class suffix "YanQi" is just to very clearly indicate (without having to look at the full name including the package name) 
 * that it originates from the project https://github.com/yan-qi/k-shortest-paths-java-version
 * The class is intended to be used among other "Edge" interfaces/types from the project https://github.com/TomasJohansson/adapters-shortest-paths/tree/master/adapters-shortest-paths-impl-yanqi  
 * @author Tomas Johansson
 */
public final class EdgeYanQi {
	private final int startVertexId;
	private final int endVertexId;
	private final double weight;

	public EdgeYanQi(final int startVertexId, final int endVertexId, final double weight) {
		this.startVertexId = startVertexId;
		this.endVertexId = endVertexId;
		this.weight = weight;
	}

	public int getStartVertexId() {
		return startVertexId;
	}

	public int getEndVertexId() {
		return endVertexId;
	}

	public double getWeight() {
		return weight;
	}
}