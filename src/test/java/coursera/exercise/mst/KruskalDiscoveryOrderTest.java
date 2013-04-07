package coursera.exercise.mst;

import com.coursera.algorithms.algs4.EdgeWeightedGraph;
import com.google.common.base.Joiner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static coursera.exercise.GraphUtil.weightedGraph;
import static org.fest.assertions.api.Assertions.assertThat;

public class KruskalDiscoveryOrderTest {
	@DataProvider(name = "data")
	static Object[][] data() {
		return new Object[][]{{weightedGraph(
			10,
			"A-B    16",
			"A-F    15",
			"F-B     8",
			"B-G     6",
			"C-B     2",
			"C-D    14",
			"G-C     5",
			"C-H     4",
			"D-J    13",
			"D-E    11",
			"D-H     7",
			"I-D     3",
			"E-J    17",
			"G-F    12",
			"H-G     1",
			"I-H    10",
			"J-I     9"),
			"1 2 3 4 7 8 9 11 15"
		}, {weightedGraph(
			10,
			"A-B    16",
			"F-A    15",
			"G-B    12",
			"F-B     9",
			"B-C     5",
			"H-B     4",
			"D-C     6",
			"C-H     3",
			"D-J    13",
			"D-E     8",
			"D-I     7",
			"H-D     1",
			"E-J    10",
			"F-G    14",
			"G-H    11",
			"I-H     2",
			"I-J    17"
		),
			"1 2 3 4 8 9 10 11 15"
		}, {weightedGraph(
			10,
			"B-A    12",
			"A-G    10",
			"A-F     7",
			"B-H     5",
			"C-B     3",
			"G-B     2",
			"C-D    14",
			"C-I     8",
			"C-H     6",
			"E-D    13",
			"D-I     9",
			"E-J    17",
			"E-I     1",
			"G-F    15",
			"G-H     4",
			"I-H    11",
			"J-I    16"
		),
			"1 2 3 4 7 8 9 10 16"}
		};
	}

	@Test(dataProvider = "data")
	public void test(EdgeWeightedGraph g, String expected) {
		assertThat(
			Joiner.on(" ").join(
				KruskalDiscoveryOrder.get(g)))
			.isEqualTo(expected);
	}

}
