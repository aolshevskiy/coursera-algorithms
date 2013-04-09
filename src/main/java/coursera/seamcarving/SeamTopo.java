package coursera.seamcarving;

class SeamTopo {
	private Iterable<Integer> order;    // topological order

	// topological sort in an edge-weighted digraph
	public SeamTopo(PseudoGraph G) {
		SeamDFS dfs = new SeamDFS(G);
		order = dfs.reversePost();
	}

	// return topological order if a DAG; null otherwise
	public Iterable<Integer> order() {
		return order;
	}

	// does digraph have a topological order?
	public boolean hasOrder() {
		return order != null;
	}
}
