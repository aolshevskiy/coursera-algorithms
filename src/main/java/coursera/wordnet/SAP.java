package coursera.wordnet;

import com.coursera.algorithms.algs4.Digraph;

public class SAP {
	private final Digraph g;

	public SAP(Digraph g) {
		this.g = new Digraph(g);
	}

	public int length(int v, int w) {
		checkRange(v); checkRange(w);
		return 0;
	}

	public int ancestor(int v, int w) {
		checkRange(v); checkRange(w);
		return 0;
	}

	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		checkRange(v); checkRange(w);
		return 0;
	}

	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		checkRange(v); checkRange(w);
		return 0;
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
