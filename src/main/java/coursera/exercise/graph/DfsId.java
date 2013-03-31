package coursera.exercise.graph;

import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;

import static coursera.exercise.GraphUtil.alphaGraph;

public class DfsId {
	static String get(String[] in) {
		return Joiner.on(" ").join(Ints.asList(new CCBackdoor(alphaGraph(in)).getId()));
	}
}
