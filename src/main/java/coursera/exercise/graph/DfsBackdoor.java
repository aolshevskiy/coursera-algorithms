package coursera.exercise.graph;

/**
 * **********************************************************************
 * Compilation:  javac DepthFirstSearch.java
 * Execution:    java DepthFirstSearch filename.txt s
 * Dependencies: ExactGraph.java com.coursera.algorithms.stdlib.StdOut.java
 * Data files:   http://algs4.cs.princeton.edu/41undirected/tinyG.txt
 * <p/>
 * Run depth first search on an undirected ExactGraph.
 * Runs in O(E + V) time.
 * <p/>
 * % java DepthFirstSearch tinyG.txt 0
 * 0 1 2 3 4 5 6
 * NOT connected
 * <p/>
 * % java DepthFirstSearch tinyG.txt 9
 * 9 10 11 12
 * NOT connected
 * <p/>
 * ***********************************************************************
 */

public class DfsBackdoor {
	private boolean[] marked;    // marked[v] = is there an s-v path?
	private int count;           // number of vertices connected to s

	// single source
	public DfsBackdoor(ExactGraph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}

	protected void beforeDfs(int v) {	}

	// depth first search from v
	private void dfs(ExactGraph G, int v) {
		beforeDfs(v);
		count++;
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w);
			}
		}
	}

	// is there an s-v path?
	public boolean marked(int v) {
		return marked[v];
	}

	// number of vertices connected to s
	public int count() {
		return count;
	}
}
