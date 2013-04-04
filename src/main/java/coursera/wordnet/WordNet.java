package coursera.wordnet;

import com.coursera.algorithms.algs4.Digraph;
import com.coursera.algorithms.algs4.DirectedCycle;
import com.coursera.algorithms.stdlib.In;

import java.util.*;

public class WordNet {
	private final Map<String, List<Integer>> nouns = new HashMap<String,List<Integer>>();
	private final List<String> synsets = new ArrayList<String>();
	private final Digraph G;
	private final SAP sap;

	public WordNet(String synsets, String hypernyms) {
		int v = parseSynsets(synsets);
		G = parseHypernyms(hypernyms, v);
		sap = new SAP(G);
		if(hasCycle())
			throw new IllegalArgumentException();
	}

	public Iterable<String> nouns() {
		return Collections.unmodifiableSet(nouns.keySet());
	}

	public boolean isNoun(String word) {
		return nouns.containsKey(word);
	}

	public int distance(String nounA, String nounB) {
		checkNoun(nounA);checkNoun(nounB);
		return sap.length(nouns.get(nounA), nouns.get(nounB));
	}

	public String sap(String nounA, String nounB) {
		checkNoun(nounA);checkNoun(nounB);
		return synsets.get(sap.ancestor(nouns.get(nounA), nouns.get(nounB)));
	}

	private int parseSynsets(String synsets) {
		In in = new In(synsets);
		String line;
		int index = 0;
		while((line = in.readLine()) != null) {
			String[] chunks = line.split(",");
			this.synsets.add(chunks[1]);
			for(String noun: chunks[1].split(" ")) {
				List<Integer> bag = nouns.get(noun);
				if(bag == null) {
					bag = new ArrayList<Integer>();
					nouns.put(noun, bag);
				}
				bag.add(index);
			}
			index++;
		}
		return index;
	}

	private Digraph parseHypernyms(String hypernyms, int V) {
		In in = new In(hypernyms);
		Digraph graph = new Digraph(V);
		String line;
		int count = 0;
		while((line = in.readLine()) != null) {
			String[] chunks = line.split(",");
			int hyponym = Integer.parseInt(chunks[0]);
			for(int i = 1; i < chunks.length; i++) {
				int hypernym = Integer.parseInt(chunks[i]);
				graph.addEdge(hyponym, hypernym);
			}
			count++;
		}
		if(V - count > 1)
			throw new IllegalArgumentException();
		return graph;
	}

	private boolean hasCycle() {return new DirectedCycle(G).hasCycle();}

	private void checkNoun(String noun) {
		if(!isNoun(noun))
			throw new IllegalArgumentException();
	}
}
