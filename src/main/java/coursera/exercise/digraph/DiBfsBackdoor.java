package coursera.exercise.digraph;

import com.coursera.algorithms.algs4.Digraph;
import com.coursera.algorithms.algs4.Queue;
import com.coursera.algorithms.algs4.Stack;
import com.coursera.algorithms.stdlib.In;
import com.coursera.algorithms.stdlib.StdOut;

/**
 * **********************************************************************
 * Compilation:  javac BreadthFirstDirectedPaths.java
 * Execution:    java BreadthFirstDirectedPaths V E
 * Dependencies: Digraph.java Queue.java Stack.java
 * <p/>
 * Run breadth first search on a digraph.
 * Runs in O(E + V) time.
 * <p/>
 * % java BreadthFirstDirectedPaths tinyDG.txt 3
 * 3 to 0 (2):  3->2->0
 * 3 to 1 (3):  3->2->0->1
 * 3 to 2 (1):  3->2
 * 3 to 3 (0):  3
 * 3 to 4 (2):  3->5->4
 * 3 to 5 (1):  3->5
 * 3 to 6 (-):  not connected
 * 3 to 7 (-):  not connected
 * 3 to 8 (-):  not connected
 * 3 to 9 (-):  not connected
 * 3 to 10 (-):  not connected
 * 3 to 11 (-):  not connected
 * 3 to 12 (-):  not connected
 * <p/>
 * ***********************************************************************
 */

class DiBfsBackdoor {
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked;  // marked[v] = is there an s->v path?
	private int[] edgeTo;      // edgeTo[v] = last edge on shortest s->v path
	private int[] distTo;      // distTo[v] = length of shortest s->v path

	// single source
	public DiBfsBackdoor(ExactDigraph G, int s) {
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		for(int v = 0; v < G.V(); v++) distTo[v] = INFINITY;
		bfs(G, s);
	}

	// multiple sources
	public DiBfsBackdoor(ExactDigraph G, Iterable<Integer> sources) {
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		for(int v = 0; v < G.V(); v++) distTo[v] = INFINITY;
		bfs(G, sources);
	}

	protected void postDequeue(int v) { }

	// BFS from single source
	private void bfs(ExactDigraph G, int s) {
		Queue<Integer> q = new Queue<Integer>();
		marked[s] = true;
		distTo[s] = 0;
		q.enqueue(s);
		while(!q.isEmpty()) {
			int v = q.dequeue();
			postDequeue(v);
			for(int w : G.adj(v)) {
				if(!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}
	}

	// BFS from multiple sources
	private void bfs(ExactDigraph G, Iterable<Integer> sources) {
		Queue<Integer> q = new Queue<Integer>();
		for(int s : sources) {
			marked[s] = true;
			distTo[s] = 0;
			q.enqueue(s);
		}
		while(!q.isEmpty()) {
			int v = q.dequeue();
			postDequeue(v);
			for(int w : G.adj(v)) {
				if(!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}
	}

	// length of shortest path from s (or sources) to v
	public int distTo(int v) {
		return distTo[v];
	}

	// is there a directed path from s (or sources) to v?
	public boolean hasPathTo(int v) {
		return marked[v];
	}

	// shortest path from s (or sources) to v; null if no such path
	public Iterable<Integer> pathTo(int v) {
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		int x;
		for(x = v; distTo[x] != 0; x = edgeTo[x])
			path.push(x);
		path.push(x);
		return path;
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		ExactDigraph G = new ExactDigraph(in);
		// com.coursera.algorithms.stdlib.StdOut.println(G);

		int s = Integer.parseInt(args[1]);
		DiBfsBackdoor bfs = new DiBfsBackdoor(G, s);

		for(int v = 0; v < G.V(); v++) {
			if(bfs.hasPathTo(v)) {
				StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
				for(int x : bfs.pathTo(v)) {
					if(x == s) StdOut.print(x);
					else StdOut.print("->" + x);
				}
				StdOut.println();
			} else {
				StdOut.printf("%d to %d (-):  not connected\n", s, v);
			}

		}
	}


}
