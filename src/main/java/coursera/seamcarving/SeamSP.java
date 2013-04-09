package coursera.seamcarving;

import com.coursera.algorithms.algs4.DirectedEdge;
import com.coursera.algorithms.algs4.Stack;

class SeamSP {
	private double[] distTo;         // distTo[v] = distance  of shortest s->v path
	private DirectedEdge[] edgeTo;   // edgeTo[v] = last edge on shortest s->v path

	public SeamSP(PseudoGraph G, int s) {
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		for(int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;

		for(int v=0;v<G.V();v++) {
			for(DirectedEdge e : G.adj(v))
				relax(e);
		}
	}

	// relax edge e
	private void relax(DirectedEdge e) {
		int v = e.from(), w = e.to();
		if(distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
		}
	}

	// return length of the shortest path from s to v, infinity if no such path
	public double distTo(int v) {
		return distTo[v];
	}

	// is there a path from s to v?
	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	// return view of the shortest path from s to v, null if no such path
	public Iterable<DirectedEdge> pathTo(int v) {
		if(!hasPathTo(v)) return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}
}