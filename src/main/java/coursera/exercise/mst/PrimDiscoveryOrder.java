package coursera.exercise.mst;

import com.coursera.algorithms.algs4.EdgeWeightedGraph;

import java.util.ArrayList;
import java.util.List;

class PrimDiscoveryOrder {
	static Iterable<Integer> get(EdgeWeightedGraph g, int s) {
		final List<Integer> result = new ArrayList<>();
		new PrimMST(g){
			@Override
			protected void beforeDequeue(double weight) {
				result.add((int)weight);
			}
		}.prim(s);
		return result;
	}
}
