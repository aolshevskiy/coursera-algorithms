package coursera.exercise.graph;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class DfsVisitOrderTest {
	@DataProvider(name = "data")
	public Object[][] data() {
		return new Object[][] {
			{new String[]{
			"A:  F B E",
			"B:  C A F",
			"C:  B F",
			"D:  H",
			"E:  A",
			"F:  A B G C",
			"G:  H F",
			"H:  D G"},
			"A F B C G H D E"},

			{new String[]{
			"A:  F E B",
			"B:  A",
			"C:  H G",
			"D:  H",
			"E:  A F",
			"F:  A E G",
			"G:  H C F",
			"H:  G C D"},
			"A F E G H C D B"},

			{new String[]{
			"A:  B E",
			"B:  A C F",
			"C:  B F D G",
			"D:  G C H",
			"E:  A",
			"F:  B C",
			"G:  D C",
			"H:  D"},
			"A B C F D G H E"}};
	}

	@Test(dataProvider = "data")
	public void test(String[] in, String expected) {
		assertThat(new DfsVisitOrder(in, 'A').getVisitOrder()).isEqualTo(expected);
	}
}
