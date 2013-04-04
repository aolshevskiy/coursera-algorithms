package coursera.wordnet;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class OutcastTest {
	@DataProvider(name = "data")
	static Object[][] data() {
		return new Object[][] {{
			"horse zebra cat bear table",
			"table"
		},{
			"water soda bed orange_juice milk apple_juice tea coffee",
			"bed"
		},{
			"apple pear peach banana lime lemon blueberry strawberry mango watermelon potato",
			"potato"
		}};
	}

	@Test(dataProvider = "data")
	public void test(String nouns, String expected) {
		WordNet wordnet = new WordNet("/coursera/wordnet/synsets.txt", "/coursera/wordnet/hypernyms.txt");
		assertThat(new Outcast(wordnet).outcast(nouns.split(" "))).isEqualTo(expected);
	}
}
