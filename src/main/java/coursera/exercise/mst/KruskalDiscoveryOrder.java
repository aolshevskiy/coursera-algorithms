package coursera.exercise.mst;

import com.coursera.algorithms.algs4.EdgeWeightedGraph;

import java.util.ArrayList;
import java.util.List;

class KruskalDiscoveryOrder {
	static List<Integer> get(EdgeWeightedGraph g) {
		final List<Integer> result = new ArrayList<>();
		new KruskalMST(g){
			@Override
			protected void beforeAdd(double weight) {
				result.add((int)weight);
			}
		};
		return result;
	}
}
