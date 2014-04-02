import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Collection;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

// Author(s): Robert Larsson & Sebastian Lindgren
// Version:   Luftballon
// Date:	  2014-04-02

public class WordLists {
	private Reader in = null;
	private ArrayList<String> words;
	private TreeMap<String, Integer> wordMap;
	private TreeSet<String> reverseSet;
	private TreeMap<Integer, TreeSet<String>> freqMap;

	public WordLists(String inputFileName) throws IOException {
		// ... define!
		// Looks like we are expected to setup the reader in and then use 
		// getWord to read each word out of the file.
		FileReader file = new FileReader(inputFileName);
		in = new BufferedReader(file);
		words = new ArrayList<>();
		wordMap = new TreeMap<>();
		freqMap = new TreeMap<>();
		reverseSet = new TreeSet<>();
		
		// Kickstart reading words
		String word = getWord();
		
		do {
			if (word != null)
				words.add(word);
			word = getWord();
		}while(word != null);
	}
	
	private boolean isPunctuationChar(char c) {
	    final String punctChars = ",.:;?!";
	    return punctChars.indexOf(c) != -1;
	}
	
	/**
	 * 
	 * @return word read from file
	 * @throws IOException
	 */
	private String getWord() throws IOException {
		int state = 0;
		int i;
		String word = "";
		while ( true ) {
			i = in.read();
			char c = (char)i;
			switch ( state ) {
			case 0:
				if ( i == -1 )
					return null;
				if ( Character.isLetter( c ) ) {
					word += Character.toLowerCase( c );
					state = 1;
				}
				break;
			case 1:
				if ( i == -1 || isPunctuationChar( c ) || Character.isWhitespace( c ) )
					return word;
				else if ( Character.isLetter( c ) ) 
					word += Character.toLowerCase( c );
				else {
					word = "";
					state = 0;
				}
			}
		}
	}
	
	private String reverse(String s) {
	    // define!
		return s;
	}
	
	/**
	 *	Count how often words are used and store 
	 */
	private void computeWordFrequencies() {
		for (String word : words) {
			if (wordMap.containsKey(word)) {
				Integer i = wordMap.get(word);
				i++;
				wordMap.put(word, i);
			} else {
				wordMap.put(word, 1);
			}
		}
		try {writeToFile("alfaSorted.txt", wordMap);}
		catch (IOException e) {e.printStackTrace();}
	}
	

	/**
	 * Manually iterate over words and put them into sets for how often they're
	 * used.
	 */
	private void computeFrequencyMap() {
		for (String word : wordMap.keySet()) {
			Integer hits = wordMap.get(word);
			if (!freqMap.containsKey(hits)) {
				TreeSet<String> wordSet = new TreeSet<>();
				wordSet.add(word);
				freqMap.put(hits, wordSet);
			} else {
				freqMap.get(hits).add(word);
			}
		}
		// TODO Print to the file
		try {
			writeToFile("frequencySorted.txt", freqMap);
		}	//Ger NPE än så länge
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private void computeBackwardsOrder() {
		for (String word : words)
			reverseSet.add(new StringBuilder(word).reverse().toString());
		try {
			writeToFile("backwardsSorted.txt", reverseSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writing method for the reverseSet
	 * @param filename The name of the file the list should be written to
	 * @param set The list that is to be written
	 * @throws IOException
	 */
	private void writeToFile(String filename, TreeSet<String> set) throws IOException {
		PrintWriter writer = new PrintWriter(filename);
		for(String g : (TreeSet<String>) set){
			writer.println(new StringBuilder(g).reverse().toString());
		}
		writer.flush();
		writer.close();
	}
	
	/**
	 * Writes list to a file with different syntax
	 * @param fileName The name of the file the list should be written to
	 * @param map The list that is to be written
	 * @throws IOException
	 */
	private void writeToFile(String fileName, TreeMap map)
			throws IOException {
		PrintWriter writer = new PrintWriter(fileName);
		if (map.firstKey() instanceof Integer) { // This ungeneralized test required because
												// of the specified syntax
			NavigableSet<Integer> navSet = ((TreeMap<Integer, TreeSet<String>>) map).descendingKeySet();
			for (Integer i : navSet) {
				writer.println(i + ":");
				for(String g : (TreeSet<String>) map.get(i))
					writer.println("\t" + g);
			}
		} else if (map.firstKey() instanceof String) {
			NavigableSet<String> navSet = ((TreeMap<String, Integer>) map).navigableKeySet();
			for (String g : navSet) {
				writer.println(g + "\t"
						+ ((TreeMap<String, Integer>) map).get(g));
			}
		}
		writer.flush();
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		WordLists wl = new WordLists("args[0]");  // arg[0] contains the input file name
		wl.computeWordFrequencies();
		wl.computeFrequencyMap();
		wl.computeBackwardsOrder();
		
		System.out.println("Finished!");
	}
	
}