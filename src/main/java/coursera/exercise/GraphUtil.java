package coursera.exercise;

import com.coursera.algorithms.algs4.Bag;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import coursera.exercise.digraph.ExactDigraph;
import coursera.exercise.graph.ExactGraph;

import java.util.Arrays;
import java.util.List;

import static coursera.exercise.SymTableUtil.fromUpperChar;
import static coursera.exercise.SymTableUtil.toUpperChar;

public class GraphUtil {
	public static ExactGraph alphaGraph(String[] in) {
		return new ExactGraph(in.length, toBags(in));
	}

	public static ExactDigraph alphaDigraph(String[] in) {
		return new ExactDigraph(in.length, toBags(in));
	}

	private static Bag<Integer>[] toBags(String[] in) {
		Bag<Integer>[] bags = newAdj(in.length);
		for(String line : in) {
			List<String> pair = ImmutableList.copyOf(Splitter.on(":").split(line));
			int s = fromUpperChar(pair.get(0));
			Iterable<String> edges = ImmutableList.copyOf(Splitter.on(" ").omitEmptyStrings().split(pair.get(1))).reverse();
			for(String t : edges)
				bags[s].add(fromUpperChar(t));
		}
		return bags;
	}

	private static final Function<Integer,Character> TO_UPPER_CHAR = new Function<Integer, Character>() {
		@Override
		public Character apply(Integer input) {
			return toUpperChar(input);
		}
	};

	public static String toSymSeq(Iterable<Integer> seq) {
		return Joiner.on(" ").join(Iterables.transform(seq, TO_UPPER_CHAR));
	}

	public static Bag<Integer>[] newAdj(int V) {
		@SuppressWarnings("unchecked")
		Bag<Integer>[] adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<>();
		return adj;
	}

	private static final Function<Bag<Integer>,Integer> TO_COUNTS = new Function<Bag<Integer>, Integer>() {
		@Override
		public Integer apply(Bag<Integer> input) {
			return input.size();
		}
	};

	public static int computeE(Bag<Integer>[] adj) {
		Iterable<Integer> counts = Iterables.transform(Arrays.asList(adj), TO_COUNTS);
		int result = 0;
		for(int c : counts)
			result += c;
		return result;
	}
}
