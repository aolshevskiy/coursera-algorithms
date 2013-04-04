package coursera.wordnet;

import com.coursera.algorithms.algs4.Digraph;
import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class SAPTest {
	private static Digraph GRAPH1() {
		return digraph(
			"13",
			"11",
			"7  3",
			"8  3",
			"3  1",
			"4  1",
			"5  1",
			"9  5",
			"10  5",
			"11 10",
			"12 10",
			"1  0",
			"2  0");
	}

	private static Digraph GRAPH2() {
		return digraph(
			"6",
			"6",
			"1 0",
			"1 2",
			"2 3",
			"3 4",
			"4 5",
			"5 0");
	}

	@DataProvider(name = "length")
	Object[][] lengthData() {
		return new Object[][]{{
			GRAPH1(),
			3, 11,
			4
		}, {
			GRAPH2(),
			1, 5,
			2
		}};
	}

	@Test(dataProvider = "length")
	public void length(Digraph g, int v, int w, int length) {
		assertThat(new SAP(g).length(v, w)).isEqualTo(length);
	}


	@DataProvider(name = "ancestor")
	Object[][] ancestorData() {
		return new Object[][]{{
			GRAPH1(),
			3, 11,
			1
		}, {
			GRAPH2(),
			1, 5,
			0
		}};
	}

	@Test(dataProvider = "ancestor")
	public void ancestor(Digraph g, int v, int w, int ancestor) {
		assertThat(new SAP(g).ancestor(v, w)).isEqualTo(ancestor);
	}

	private static final Splitter SPLITTER = Splitter.on(CharMatcher.WHITESPACE).omitEmptyStrings();

	private static final Function<String, Integer> TO_INT = new Function<String, Integer>() {
		@Override
		public Integer apply(String input) {
			return Integer.parseInt(input);
		}
	};

	private static Digraph digraph(String... input) {
		String[] ve = Arrays.copyOfRange(input, 0, 2);
		Digraph g = new Digraph(Integer.parseInt(ve[0]));
		for(String e: Arrays.copyOfRange(input, 2, Integer.parseInt(ve[1]) + 2)) {
			List<Integer> vw = ImmutableList.copyOf(Iterables.transform(SPLITTER.split(e), TO_INT));
			g.addEdge(vw.get(0), vw.get(1));
		}
		return g;
	}
}
