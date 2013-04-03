package coursera.exercise.graph;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static coursera.exercise.SymTableUtil.fromUpperChar;
import static coursera.exercise.GraphUtil.alphaGraph;
import static coursera.exercise.GraphUtil.toSymSeq;

class DfsVisitOrder {
	private final List<Integer> visitOrder;

	DfsVisitOrder(String[] in, char source) {
		final ImmutableList.Builder<Integer> builder = new ImmutableList.Builder<>();
		new DfsBackdoor(alphaGraph(in), fromUpperChar(source)) {
			@Override
			protected void beforeDfs(int v) {
				builder.add(v);
			}
		};
		visitOrder = builder.build();
	}

	String getVisitOrder() {
		return toSymSeq(visitOrder);
	}
}



