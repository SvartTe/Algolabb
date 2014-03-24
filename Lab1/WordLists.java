import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

// Author(s): Robert Larsson & Sebastian Lindgren
// Version:   Neunundneunzig
// Date:	  2014-03-23

public class WordLists {
	private Reader in = null;
	private ArrayList<String> words;
	private TreeMap<String, Integer> wordMap;
	private TreeSet<String> reverseSet;

	public WordLists(String inputFileName) throws IOException {
		// ... define!
		// Looks like we are expected to setup the reader in and then use 
		// getWord to read each word out of the file.
		FileReader file = new FileReader(inputFileName);
		in = new BufferedReader(file);
		wordMap = new TreeMap<String, Integer>();
		
		// Kickstart reading words
		String word = new String(getWord());
		
		do {
			if (word != null)
				words.add(getWord());
		}while(word != null);
	}
	
	private boolean isPunctuationChar(char c) {
	    final String punctChars = ",.:;?!";
	    return punctChars.indexOf(c) != -1;
	}
	
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
	
	private void computeWordFrequencies() {
		// define!
		for (String word : words) {
			if (wordMap.containsKey(word)) {
				Integer i = wordMap.get(word);
				i++;
			} else {
				wordMap.put(word, 1);
			}
		}
		// TODO Print to the file
	}
	

	private void computeFrequencyMap() {
          // define!
	}
	

	private void computeBackwardsOrder() {
	    // define!
		for (String word : words)
			reverseSet.add(new StringBuilder(word).reverse().toString());
		// TODO Print to the file
	}
	
	private void writeToFile(String fileName, Object list) throws IOException {
		FileWriter writer = new FileWriter(fileName);
		BufferedWriter buffWrite = new BufferedWriter(writer);
		if(list instanceof TreeSet){
			for(String g : (TreeSet<String>) list){
				buffWrite.write(g);
				buffWrite.newLine();
			}
			writer.close();
		}
		else if(list instanceof TreeMap){
			NavigableSet<String> seth = ((TreeMap<String, Integer>) list).navigableKeySet();
			for(String g : seth){
				buffWrite.write(g + "\t");
				buffWrite.write(((TreeMap<String, Integer>) list).get(g));
				buffWrite.newLine();
			}
			writer.close();
		}
	}

	public static void main(String[] args) throws IOException {
		WordLists wl = new WordLists(args[0]);  // arg[0] contains the input file name
		wl.computeWordFrequencies();
		wl.computeFrequencyMap();
		wl.computeBackwardsOrder();
		
		System.out.println("Finished!");
	}
}