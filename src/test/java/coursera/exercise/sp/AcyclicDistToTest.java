package coursera.exercise.sp;

import com.coursera.algorithms.algs4.EdgeWeightedDigraph;
import com.coursera.algorithms.algs4.Topological;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static coursera.exercise.GraphUtil.weightedDigraph;
import static coursera.exercise.SymTableUtil.fromUpperChar;
import static org.fest.assertions.api.Assertions.assertThat;

public class AcyclicDistToTest {
	@DataProvider(name = "data")
	static Object[][] data() {
		return new Object[][]{
			{weightedDigraph(
				8,
				"B->A    19",
				"B->E     6",
				"C->B    31",
				"C->D    14",
				"D->H     4",
				"E->A    22",
				"F->B    30",
				"F->C     1",
				"F->E    24",
				"G->C    22",
				"G->D    38",
				"G->F    25",
				"G->H    49"),
				'G', 'B',
				"72 53 22 36 49 25 0 40"}
		};
	}

	@Test(dataProvider = "data")
	public void test(EdgeWeightedDigraph g, char source, final char target, String expected) {
		List<Integer> actual = new AcyclicSP(g, fromUpperChar(source)) {
			private List<Integer> actual;
			@Override
			void afterRelax(int v) {
				if(v == fromUpperChar(target))
					actual = doublesToIntList(distTo);
			}
		}.actual;
		assertThat(Joiner.on(" ").join(actual)).isEqualTo(expected);
	}

	static List<Integer> doublesToIntList(double[] ds) {
		ImmutableList.Builder<Integer> result = new ImmutableList.Builder<>();
		for(double d: ds)
			result.add((int)d);
		return result.build();
	}
}
