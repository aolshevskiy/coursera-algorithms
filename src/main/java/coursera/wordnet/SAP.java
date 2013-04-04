package coursera.wordnet;

import com.coursera.algorithms.algs4.BreadthFirstDirectedPaths;
import com.coursera.algorithms.algs4.Digraph;

import static java.util.Arrays.asList;

public class SAP {
	private final Digraph g;

	public SAP(Digraph g) {
		this.g = new Digraph(g);
	}

	public int length(int v, int w) {
		checkRange(v); checkRange(w);
		return length(asList(v), asList(w));
	}

	public int ancestor(int v, int w) {
		checkRange(v); checkRange(w);
		return ancestor(asList(v), asList(w));
	}

	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		checkRange(v); checkRange(w);
		return new DBFS(g, v, w).getLength();
	}

	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		checkRange(v); checkRange(w);
		return new DBFS(g, v, w).getAncestor();
	}

	private void checkRange(Iterable<Integer> vs) {
		for(int v: vs)
			checkRange(v);
	}

	private void checkRange(int v) {
		if(v >= g.V())
			throw new IndexOutOfBoundsException();
	}
}

class DBFS {
	private final int vs;
	private final BreadthFirstDirectedPaths v, w;
	private int a = -1;
	private int l = -1;

	DBFS(Digraph g, Iterable<Integer> v, Iterable<Integer> w) {
		vs = g.V();
		this.v = new BreadthFirstDirectedPaths(g, v);
		this.w = new BreadthFirstDirectedPaths(g, w);
		computeAncestor();
	}

	private boolean isAncestor(int a) {
		return v.hasPathTo(a) && w.hasPathTo(a);
	}

	private int pathLen(int a) {
		return v.distTo(a) + w.distTo(a);
	}

	private void computeAncestor() {
		for(int a = 0; a < vs; a++)
			if(isAncestor(a) && (l == -1 || l > pathLen(a))) {
				l = pathLen(a);
				this.a = a;
			}
	}

	int getAncestor() {
		return a;
	}

	int getLength() {
		return l;
	}
}
