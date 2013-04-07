package coursera.exercise.sp;

import com.coursera.algorithms.algs4.EdgeWeightedDigraph;
import com.google.common.base.Joiner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static coursera.exercise.GraphUtil.weightedDigraph;
import static coursera.exercise.SymTableUtil.fromUpperChar;
import static coursera.exercise.sp.AcyclicDistToTest.doublesToIntList;
import static org.fest.assertions.api.Assertions.assertThat;

public class BellmanFordDistToTest {
	@DataProvider(name = "data")
	static Object[][] data() {
		return new Object[][]{
			{weightedDigraph(
				8,
				"B->F    21",
				"B->E     5",
				"B->G     2",
				"B->A    28",
				"C->D    33",
				"C->B    12",
				"E->F     9",
				"E->A    25",
				"G->C     7",
				"G->F    31",
				"H->G    37",
				"H->D    83",
				"H->C    46"),
				'H', 3,
				"86 56 44 77 63 68 37 0"}
		};
	}

	@Test(dataProvider = "data")
	public void test(EdgeWeightedDigraph g, char source, final int passCount, String expected) {
		List<Integer> actual = new BellmanFordSP(g, fromUpperChar(source)) {
			private int i = 0;
			private List<Integer> result;
			@Override
			void afterPass(){
				i++;
				if(passCount == i)
					result = doublesToIntList(distTo);
			}}.result;
		assertThat(Joiner.on(" ").join(actual)).isEqualTo(expected);
	}
}
