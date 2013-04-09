package coursera.seamcarving;

import com.coursera.algorithms.algs4.DirectedEdge;
import com.coursera.algorithms.algs4.Queue;
import com.coursera.algorithms.algs4.Stack;

class SeamDFS {
	private boolean[] marked;          // marked[v] = has v been marked in dfs?
	private int[] pre;                 // pre[v]    = preorder  number of v
	private int[] post;                // post[v]   = postorder number of v
	private Queue<Integer> preorder;   // vertices in preorder
	private Queue<Integer> postorder;  // vertices in postorder
	private int preCounter;            // counter or preorder numbering
	private int postCounter;           // counter for postorder numbering

	// depth-first search preorder and postorder in an edge-weighted digraph
	public SeamDFS(PseudoGraph G) {
		pre = new int[G.V()];
		post = new int[G.V()];
		postorder = new Queue<Integer>();
		preorder = new Queue<Integer>();
		marked = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++)
			if(!marked[v]) dfs(G, v);
	}

	private void dfs(PseudoGraph G, int v) {
		marked[v] = true;
		pre[v] = preCounter++;
		preorder.enqueue(v);
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if(!marked[w]) {
				dfs(G, w);
			}
		}
		postorder.enqueue(v);
		post[v] = postCounter++;
	}

	// return vertices in reverse postorder as an Iterable
	public Iterable<Integer> reversePost() {
		Stack<Integer> reverse = new Stack<Integer>();
		for(int v : postorder)
			reverse.push(v);
		return reverse;
	}
}
