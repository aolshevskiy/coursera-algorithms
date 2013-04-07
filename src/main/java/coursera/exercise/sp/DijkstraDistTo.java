package coursera.exercise.sp;

import com.coursera.algorithms.algs4.EdgeWeightedDigraph;
import com.google.common.primitives.Doubles;

import java.util.ArrayList;
import java.util.List;

import static coursera.exercise.SymTableUtil.toUpperChar;

class DijkstraDistTo {
	private DijkstraDistTo() {}

	static List<Integer> get(EdgeWeightedDigraph g, int s, final int t) {
		final List<Integer> result = new ArrayList<>();
		new DijkstraSP(g, s){
			@Override
			protected void afterRelax(int v) {
				if(v == t)
					for(double i: distTo)
						result.add((int)i);
			}
		};
		return result;
	}
}
