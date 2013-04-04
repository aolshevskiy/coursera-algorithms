package coursera.wordnet;

public class Outcast {
	private final WordNet wordnet;

	public Outcast(WordNet wordnet) {
		this.wordnet = wordnet;
	}

	public String outcast(String[] nouns) {
		int maxDistance = -1;
		String maxNoun = null;
		for(String noun: nouns) {
			int distance = 0;
			for(String otherNoun: nouns)
				distance += wordnet.distance(noun, otherNoun);
			if(distance > maxDistance) {
				maxDistance = distance;
				maxNoun = noun;
			}
		}
		return maxNoun;
	}
}
