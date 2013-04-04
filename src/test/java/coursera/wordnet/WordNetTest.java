package coursera.wordnet;

import org.testng.annotations.Test;

public class WordNetTest {
	@Test
	public void test() {
		new WordNet("/coursera/wordnet/synsets.txt", "/coursera/wordnet/hypernyms.txt");
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void cycle() {
		new WordNet("/coursera/wordnet/synsets3.txt", "/coursera/wordnet/hypernymsInvalidCycle.txt");
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void twoRoots() {
		new WordNet("/coursera/wordnet/synsets3.txt", "/coursera/wordnet/hypernymsInvalidTwoRoots.txt");
	}
}
