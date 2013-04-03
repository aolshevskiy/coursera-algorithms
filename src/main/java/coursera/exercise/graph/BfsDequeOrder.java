package coursera.exercise.graph;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static coursera.exercise.SymTableUtil.fromUpperChar;
import static coursera.exercise.GraphUtil.alphaGraph;
import static coursera.exercise.GraphUtil.toSymSeq;

class BfsDequeOrder {
	private final List<Integer> dequeueOrder;

	BfsDequeOrder(String[] in, char source) {
		final ImmutableList.Builder<Integer> builder = new ImmutableList.Builder<>();
		new BfsBackdoor(alphaGraph(in), fromUpperChar(source)) {
			@Override
			protected void afterDequeue(int v) {
				builder.add(v);
			}
		};
		dequeueOrder = builder.build();
	}

	String getDequeueOrder() {
		return toSymSeq(dequeueOrder);
	}
}
