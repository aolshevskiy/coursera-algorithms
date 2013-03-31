package coursera.exercise.graph;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class DfsIdTest {
	@DataProvider(name = "data")
	public Object[][] data() {
		return new Object[][] {
			{new String[]{
			"A:  F B G",
			"B:  C A G",
			"C:  B G",
			"D:  E J",
			"E:  D J",
			"F:  A G",
			"G:  F A C B",
			"H:  I",
			"I:  H",
			"J:  E D"},
			"0 0 0 1 1 0 0 2 2 1"},

			{new String[]{
			"A:  B F",
			"B:  H A G F",
			"C:  I",
			"D:  E J",
			"E:  D J",
			"F:  B A G",
			"G:  B H F",
			"H:  B G",
			"I:  C",
			"J:  E D"},
			"0 0 1 2 2 0 0 0 1 2"},

			{new String[]{
			"A:  F",
			"B:  G",
			"C:  I H D",
			"D:  E J I C",
			"E:  D J",
			"F:  A",
			"G:  B",
			"H:  C I",
			"I:  J C H D",
			"J:  I D E"},
			"0 1 2 2 2 0 1 2 2 2"}};
	}

	@Test(dataProvider = "data")
	public void test(String[] in, String expected) {
		assertThat(DfsId.get(in)).isEqualTo(expected);
	}
}
