package coursera.exercise.digraph; /*************************************************************************
 *  Compilation:  javac Digraph.java
 *  Execution:    java Digraph filename.txt
 *  Dependencies: Bag.java com.coursera.algorithms.stdlib.In.java com.coursera.algorithms.stdlib.StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/42directed/tinyDG.txt
 *
 *  A graph, implemented using an array of lists.
 *  Parallel edges and self-loops are permitted.
 *
 *  % java Digraph tinyDG.txt
 *  13 22
 *  0: 5 1 
 *  1: 
 *  2: 0 3 
 *  3: 5 2 
 *  4: 3 2 
 *  5: 4 
 *  6: 9 4 0 
 *  7: 6 8 
 *  8: 7 9 
 *  9: 11 10 
 *  10: 12 
 *  11: 4 12 
 *  12: 9 
 *
 *************************************************************************/


import com.coursera.algorithms.algs4.Bag;
import com.coursera.algorithms.algs4.Stack;
import com.coursera.algorithms.stdlib.In;
import com.coursera.algorithms.stdlib.StdOut;
import coursera.exercise.GraphUtil;

import static coursera.exercise.GraphUtil.newAdj;

/**
 * The <tt>Digraph</tt> class represents an directed graph of vertices
 * named 0 through V-1.
 * It supports the following operations: add an edge to the graph,
 * iterate over all of the neighbors incident to a vertex.
 * Parallel edges and self-loops are permitted.
 * <p/>
 * For additional documentation,
 * see <a href="http://algs4.cs.princeton.edu/42directed">Section 4.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */

public class ExactDigraph {
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	/**
	 * Create an empty digraph with V vertices.
	 *
	 * @throws IllegalArgumentException if V < 0
	 */
	@SuppressWarnings("unchecked")
	public ExactDigraph(int V) {
		this(V, newAdj(V));
	}

	public ExactDigraph(int V, Bag<Integer>[] bags) {
		if(V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		adj = bags;
		this.E = GraphUtil.computeE(adj);
	}

	/**
	 * Create a digraph from input stream.
	 */
	public ExactDigraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for(int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}

	/**
	 * com.coursera.algorithms.stdlib.Copy constructor.
	 */
	public ExactDigraph(ExactDigraph G) {
		this(G.V());
		this.E = G.E();
		for(int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<Integer>();
			for(int w : G.adj[v]) {
				reverse.push(w);
			}
			for(int w : reverse) {
				adj[v].add(w);
			}
		}
	}

	/**
	 * Return the number of vertices in the digraph.
	 */
	public int V() {
		return V;
	}

	/**
	 * Return the number of edges in the digraph.
	 */
	public int E() {
		return E;
	}

	/**
	 * Add the directed edge v->w to the digraph.
	 *
	 * @throws IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
	 */
	public void addEdge(int v, int w) {
		if(v < 0 || v >= V) throw new IndexOutOfBoundsException();
		if(w < 0 || w >= V) throw new IndexOutOfBoundsException();
		adj[v].add(w);
		E++;
	}

	/**
	 * Return the list of vertices pointed to from vertex v as an Iterable.
	 *
	 * @throws IndexOutOfBoundsException unless 0 <= v < V
	 */
	public Iterable<Integer> adj(int v) {
		if(v < 0 || v >= V) throw new IndexOutOfBoundsException();
		return adj[v];
	}

	/**
	 * Return the reverse of the digraph.
	 */
	public ExactDigraph reverse() {
		ExactDigraph R = new ExactDigraph(V);
		for(int v = 0; v < V; v++) {
			for(int w : adj(v)) {
				R.addEdge(w, v);
			}
		}
		return R;
	}

	/**
	 * Return a string representation of the digraph.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		String NEWLINE = System.getProperty("line.separator");
		s.append(V + " " + E + NEWLINE);
		for(int v = 0; v < V; v++) {
			s.append(String.format("%d: ", v));
			for(int w : adj[v]) {
				s.append(String.format("%d ", w));
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	/**
	 * Test client.
	 */
	public static void main(String[] args) {
		In in = new In(args[0]);
		ExactDigraph G = new ExactDigraph(in);
		StdOut.println(G);

		StdOut.println();
		for(int v = 0; v < G.V(); v++)
			for(int w : G.adj(v))
				StdOut.println(v + "->" + w);
	}

}
