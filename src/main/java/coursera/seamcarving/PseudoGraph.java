package coursera.seamcarving;

import com.coursera.algorithms.algs4.DirectedEdge;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class PseudoGraph {
	private final int x;
	private final int y;
	private final double[][] energy;

	PseudoGraph(double[][] energy) {
		x = energy[0].length;
		y = energy.length;
		this.energy = energy;
	}

	int V() {
		return x * y + 2;
	}

	private int p(int x, int y) {
		return y * this.x + x + 1;
	}

	private double energy(int w) {
		if(w == x * y + 1)
			return SeamUtil.MAX_ENERGY;
		return energy[(w - 1) / x][(w - 1) % x];
	}

	Iterable<DirectedEdge> adj(int v) {
		if(v == 0)
			return new RangeIterable(v, p(0, 0), p(x - 1, 0));
		if(v == x * y + 1) {
			@SuppressWarnings("unchecked")
			List<DirectedEdge> result = Collections.EMPTY_LIST;
			return result;
		}
		int vy = (v - 1) / x;
		if(vy == y - 1)
			return Arrays.asList(new DirectedEdge(v, x * y + 1, energy(x * y + 1)));
		int vx = (v - 1) % x;
		int s = 0, e = 0;
		if(vx != 0)
			s = -1;
		if(vx != x - 1)
			e = 1;
		int rp = p(vx, vy + 1);
		return new RangeIterable(v, rp + s, rp + e);
	}

	private class RangeIterable implements Iterable<DirectedEdge> {
		private final int src;
		private final int s;
		private final int e;

		RangeIterable(int src, int s, int e) {
			this.src = src;
			this.s = s;
			this.e = e;
		}

		@Override
		public Iterator<DirectedEdge> iterator() {
			return new Iterator<DirectedEdge>() {
				private int i = s;

				@Override
				public boolean hasNext() {
					return i <= e;
				}

				@Override
				public DirectedEdge next() {
					DirectedEdge result = new DirectedEdge(src, i, energy(i));
					i++;
					return result;
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}
			};
		}
	}

	/**
	 * Return a string representation of this graph.
	 */
	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder s = new StringBuilder();
		s.append(V() + NEWLINE);
		for(int v = 0; v < V(); v++) {
			s.append(v + ": ");
			for(DirectedEdge e : adj(v)) {
				s.append(e + "  ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
}
