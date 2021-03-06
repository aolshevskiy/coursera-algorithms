package coursera.exercise.digraph;

import com.coursera.algorithms.algs4.Queue;
import com.coursera.algorithms.stdlib.In;
import com.coursera.algorithms.stdlib.StdOut;

/**
 * **********************************************************************
 * Compilation:  javac KosarajuSharirSCC.java
 * Execution:    java KosarajuSharirSCC filename.txt
 * Dependencies: Digraph.java TransitiveClosure.java com.coursera.algorithms.stdlib.StdOut.java com.coursera.algorithms.stdlib.In.java
 * Data files:   http://algs4.cs.princeton.edu/42directed/tinyDG.txt
 * <p/>
 * Compute the strongly-connected components of a digraph using the
 * Kosaraju-Sharir algorithm.
 * <p/>
 * Runs in O(E + V) time.
 * <p/>
 * % java KosarajuSCC tinyDG.txt
 * 5 components
 * 1
 * 0 2 3 4 5
 * 9 10 11 12
 * 6
 * 7 8
 * <p/>
 * % java KosarajuSharirSCC mediumDG.txt
 * 10 components
 * 21
 * 2 5 6 8 9 11 12 13 15 16 18 19 22 23 25 26 28 29 30 31 32 33 34 35 37 38 39 40 42 43 44 46 47 48 49
 * 14
 * 3 4 17 20 24 27 36
 * 41
 * 7
 * 45
 * 1
 * 0
 * 10
 * <p/>
 * ***********************************************************************
 */

class KosarajuSharirSCC {
	private boolean[] marked;     // marked[v] = has vertex v been visited?
	private int[] id;             // id[v] = id of strong component containing v
	private int count;            // number of strongly-connected components


	public KosarajuSharirSCC(ExactDigraph G) {

		// compute reverse postorder of reverse graph
		DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());

		// run DFS on G, using reverse postorder to guide calculation
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for(int v : dfs.reversePost()) {
			if(!marked[v]) {
				dfs(G, v);
				count++;
			}
		}

		// check that id[] gives strong components
		assert check(G);
	}

	// DFS on graph G
	private void dfs(ExactDigraph G, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v)) {
			if(!marked[w]) dfs(G, w);
		}
	}

	// return the number of strongly connected components
	public int count() { return count; }

	// are v and w strongly connected?
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}

	// id of strong component containing v
	public int id(int v) {
		return id[v];
	}

	// does the id[] array contain the strongly connected components?
	private boolean check(ExactDigraph G) {
		TransitiveClosure tc = new TransitiveClosure(G);
		for(int v = 0; v < G.V(); v++) {
			for(int w = 0; w < G.V(); w++) {
				if(stronglyConnected(v, w) != (tc.reachable(v, w) && tc.reachable(w, v)))
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		ExactDigraph G = new ExactDigraph(in);
		KosarajuSharirSCC scc = new KosarajuSharirSCC(G);

		// number of connected components
		int M = scc.count();
		StdOut.println(M + " components");

		// compute list of vertices in each strong component
		@SuppressWarnings("unchecked")
		Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
		for(int i = 0; i < M; i++) {
			components[i] = new Queue<Integer>();
		}
		for(int v = 0; v < G.V(); v++) {
			components[scc.id(v)].enqueue(v);
		}

		// print results
		for(int i = 0; i < M; i++) {
			for(int v : components[i]) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}

	}

	public int[] getId() {
		return id;
	}
}
