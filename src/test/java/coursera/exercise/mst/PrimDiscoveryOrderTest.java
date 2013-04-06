package coursera.exercise.mst;

import com.coursera.algorithms.algs4.EdgeWeightedGraph;
import com.google.common.base.Joiner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static coursera.exercise.GraphUtil.edgeWeighted;
import static coursera.exercise.SymTableUtil.fromUpperChar;
import static org.fest.assertions.api.Assertions.assertThat;

public class PrimDiscoveryOrderTest {
	@DataProvider(name = "data")
	static Object[][] data() {
		return new Object[][]{{edgeWeighted(
			10,
			"A-F      10",
			"G-A       7",
			"B-A       4",
			"C-B      13",
			"H-B       9",
			"G-B       2",
			"D-C      16",
			"C-H      12",
			"E-D      14",
			"D-I      11",
			"D-H       6",
			"D-J       5",
			"J-E       3",
			"F-G      15",
			"H-G       1",
			"I-H      17",
			"I-J       8"
		),
			"D",
			"5 3 6 1 2 4 8 10 12"
		}, {edgeWeighted(
			10,
			"A-B       6",
			"F-A       4",
			"G-A       3",
			"G-B      13",
			"B-H       7",
			"B-C       1",
			"D-C      17",
			"I-C       8",
			"H-C       5",
			"D-I      11",
			"D-E      10",
			"J-E       9",
			"E-I       2",
			"F-G      15",
			"H-G      12",
			"H-I      14",
			"I-J      16"),
			"F",
			"4 3 6 1 5 8 2 9 10"
		}, {edgeWeighted(
			10,
			"A-B       9",
			"A-F       7",
			"B-F      15",
			"C-B      14",
			"G-B       6",
			"B-H       1",
			"C-H      12",
			"I-C      11",
			"C-D      10",
			"D-I       5",
			"D-E       4",
			"J-E       8",
			"E-I       3",
			"F-G      17",
			"H-G       2",
			"I-H      16",
			"I-J      13"
		),
			"B",
			"1 2 9 7 12 10 4 3 8"}
		};
	}

	@Test(dataProvider = "data")
	public void test(EdgeWeightedGraph g, String s, String expected) {
		assertThat(
			Joiner.on(" ").join(
				PrimDiscoveryOrder.get(g, fromUpperChar(s))))
			.isEqualTo(expected);
	}

}
