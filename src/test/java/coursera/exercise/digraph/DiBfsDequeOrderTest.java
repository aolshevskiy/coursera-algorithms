package coursera.exercise.digraph;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class DiBfsDequeOrderTest {
	@DataProvider(name = "data")
	static Object[][] data() {
		return new Object[][]{{
			new String[]{
			"A:  E",
			"B:  A F",
			"C:  B",
			"D:  C H",
			"E:  B",
			"F:  G C E",
			"G:  C H",
			"H:  C"},
			"A E B F G C H"}};
	}

	@Test(dataProvider = "data")
	public void test(String[] in, String expected) {
		assertThat(DiBfsDequeOrder.get(in)).isEqualTo(expected);
	}
}
