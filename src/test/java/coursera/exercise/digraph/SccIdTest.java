package coursera.exercise.digraph;

import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static coursera.exercise.GraphUtil.alphaDigraph;
import static coursera.exercise.GraphUtil.toSymSeq;
import static org.fest.assertions.api.Assertions.assertThat;

public class SccIdTest {
	@DataProvider(name = "data")
	static Object[][] data() {
		return new Object[][]{{
			new String[]{
			"A:  B G",
			"B:  G",
			"C:  D B H G",
			"D:  I",
			"E:  D",
			"F:  A",
			"G:  F",
			"H:  G D",
			"I:  H E J",
			"J:  E"},
			"0 0 2 1 1 0 0 1 1 1"}};
	}

	@Test(dataProvider = "data")
	public void test(String[] in, String expected) {
		String actual = Joiner.on(" ").join(Ints.asList(new KosarajuSharirSCC(alphaDigraph(in)).getId()));
		assertThat(actual).isEqualTo(expected);
	}
}
