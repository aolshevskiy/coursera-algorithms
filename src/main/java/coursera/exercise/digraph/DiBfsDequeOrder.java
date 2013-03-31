package coursera.exercise.digraph;

import com.google.common.collect.ImmutableList;

import static coursera.exercise.GraphUtil.alphaDigraph;
import static coursera.exercise.GraphUtil.toSymSeq;

class DiBfsDequeOrder {
	static String get(String[] in) {
		final ImmutableList.Builder<Integer> result = ImmutableList.builder();
		new DiBfsBackdoor(alphaDigraph(in), 0) {
			@Override
			protected void postDequeue(int v) {
				result.add(v);
			}
		};
		return toSymSeq(result.build());
	}
}
