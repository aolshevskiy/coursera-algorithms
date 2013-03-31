package coursera.exercise.graph;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class BfsDequeOrderTest {
	@DataProvider(name = "data")
	public Object[][] data() {
		return new Object[][] {
			{new String[]{
			"A:  E",
			"B:  F G C E",
			"C:  D B",
			"D:  G C H",
			"E:  A B F",
			"F:  B G E",
			"G:  D B F",
			"H:  D"},
			"A E B F G C D H"},

			{new String[]{
			"A:  E F",
			"B:  F C G",
			"C:  G D B",
			"D:  G H C",
			"E:  A F",
			"F:  B E A",
			"G:  C D B",
			"H:  D"},
			"A E F B C G D H"},

			{new String[]{
			"A:  E B",
			"B:  A E",
			"C:  F G H D",
			"D:  C",
			"E:  F A B",
			"F:  C E G",
			"G:  C F H",
			"H:  C G"},
			"A E B F C G H D"}};
	}

	@Test(dataProvider = "data")
	public void test(String[] in, String expected) {
		assertThat(new BfsDequeOrder(in, 'A').getDequeueOrder()).isEqualTo(expected);
	}
}
