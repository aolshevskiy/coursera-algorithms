package coursera.exercise.digraph;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static coursera.exercise.GraphUtil.alphaDigraph;
import static coursera.exercise.GraphUtil.toSymSeq;
import static org.fest.assertions.api.Assertions.assertThat;

public class TopologicalTest {
	@DataProvider(name = "data")
	static Object[][] data() {
		return new Object[][]{{
			new String[]{
			"A:  B E F",
			"B:  C F",
			"C:  D",
			"D:  H",
			"E:  F",
			"F:",
			"G:  H C D F B",
			"H:"},
			"G A E B F C D H"}};
	}

	@Test(dataProvider = "data")
	public void test(String[] in, String expected) {
		String actual = toSymSeq(new Topological(alphaDigraph(in)).order());
		assertThat(actual).isEqualTo(expected);
	}
}
