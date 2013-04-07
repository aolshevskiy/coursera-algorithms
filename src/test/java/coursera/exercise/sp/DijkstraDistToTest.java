package coursera.exercise.sp;

import com.coursera.algorithms.algs4.EdgeWeightedDigraph;
import com.google.common.base.Joiner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static coursera.exercise.GraphUtil.weightedDigraph;
import static coursera.exercise.SymTableUtil.fromUpperChar;
import static org.fest.assertions.api.Assertions.assertThat;

public class DijkstraDistToTest {
	@DataProvider(name = "data")
	static Object[][] data() {
		return new Object[][] {
			{weightedDigraph(
				8,
				"A->E    19",
				"A->F    24",
				"B->A    16",
				"B->F    42",
				"C->B    56",
				"D->C     2",
				"D->G    27",
				"D->H    42",
				"E->F     4",
				"F->G     2",
				"G->B    21",
				"G->C     1",
				"G->H     7"),
				'D', 'A',
				"64 48 2 0 83 88 27 34"}
		};
	}

	@Test(dataProvider = "data")
	public void test(EdgeWeightedDigraph g, char s, char t, String expected) {
		assertThat(
			Joiner.on(" ").join(DijkstraDistTo.get(g, fromUpperChar(s), fromUpperChar(t))))
			.isEqualTo(expected);
	}
}
