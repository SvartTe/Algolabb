import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;


//Author(s): Seabstian Lindgren & Robert Larsson
//Email:	 seblind@student.chalmers.se & robla@student.chalmers.se
//Date:		 2014-03-23

public class AngloTrainer {
	// Use a treeset to organize dictionary alphabetically
	private TreeSet<String> dictionary;
	private String randLetters;
	private String sortLetters;
	private String dictionaryFile;
	private Scanner reader;
	private int longestWord;

	/**
	 * 
	 * @param dictionaryFile Name of dictionaryfile to load
	 * @throws IOException If dictionaryfile not found or empty
	 */
	public AngloTrainer(String dictionaryFile) throws IOException {
	    dictionary = new TreeSet<String>();
	    loadDictionary(dictionaryFile);
	    this.dictionaryFile = dictionaryFile;
	    randLetters = randomLetters(longestWord);
	    sortLetters = sortLetters(randLetters);
	    reader = new Scanner(System.in);
	}

	// use this to verify loadDictionary
	private void dumpDict() {
	    // Print out the dictionary at the screen.
		for(String g : dictionary) {
			System.out.println(g);
		}
	}

	/**
	 * Fills dictionary with words from the file fileName
	 * @param fileName Name of the dictionaryfile to load
	 * @throws IOException If the filename does not exist or is empty
	 */
	private void loadDictionary( String fileName ) throws IOException {
		// Read the dictionary into a suitable container.
		// The file is a simple text file. One word per line.
		FileReader file = new FileReader(fileName);
		BufferedReader fileRead = new BufferedReader(file);
		String word = fileRead.readLine();
		
		// Check to see if we just managed to read a word from the file,
		// return from function if we didn't.
		if (word == null) {
			fileRead.close();
			throw new IOException("The file is empty, long live the file!");
		}
		do {
			dictionary.add(word);
			if (word.length() > longestWord)
				longestWord = word.length();
			word = fileRead.readLine();
		} while (word != null);
		// Print out the length of the longest word for debug-purposes
		System.out.println("Longest word in dictionary: " + longestWord);
		fileRead.close();
	}

	private String randomLetters( int length ) {
		// this makes vovels a little more likely
		String letters = "aabcdeefghiijklmnoopqrstuuvwxyyz";  
		StringBuffer buf = new StringBuffer(length);
		Random randomGenerator = new Random();
		for ( int i = 0; i < length; i++ ) 
			buf.append( letters.charAt(randomGenerator.nextInt(letters.length())));
		
		return buf.toString();
	}
	
	
	/* Def. includes	
	 * Let #(x,s) = the number of occurrences of the charcter x in the string s.
	 * includes(a,b) holds iff for every character x in b, #(x,b) <= #(x,a)
	 * 
	 * A neccessary precondition for includes is that both strings are sorted
	 * in ascending order.
	 */
	private boolean includes( String a, String b ) {
		if ( b == null || b.length() == 0 )
			return true;
		else if ( a == null || a.length() == 0 )
			return false;
		
		//precondition: a.length() > 0 && b.length() > 0
		int i = 0, j = 0;
		while ( j < b.length() ) {
			if (i >= a.length() || b.charAt(j) < a.charAt(i))
				return false;
			else if (b.charAt(j) == a.charAt(i)) {
				i++; j++;
			} else if (b.charAt(j) > a.charAt(i))
				i++;
		}
		//postcondition: j == b.length()
		return true;
	}
	
     // This is just for demonstration purposes.
	private void testIncludes() { 
		//                                            expected value
		System.out.println(includes("abc",""));		//t
		System.out.println(includes("","abc"));		//f
		System.out.println(includes("abc","abc"));	//t
		System.out.println(includes("abc","bcd"));	//f
		System.out.println(includes("abc","a"));	//t
		System.out.println(includes("abc","b"));	//t
		System.out.println(includes("abc","c"));	//t
		System.out.println(includes("abc","ab"));	//t
		System.out.println(includes("abc","bc"));	//t
		System.out.println(includes("abc","ac"));	//t
		System.out.println(includes("abc","abcd"));	//f
		System.out.println(includes("abc","abd"));	//f
		System.out.println(includes("abc","d"));	//f
		System.out.println(includes("",""));		//t
		System.out.println(includes("abc","ca"));	//f
		System.out.println(includes("abc","bac"));	//f
		System.out.println(includes("abc","abbc"));	//f
		System.out.println(includes("abbc","abc"));	//t
		System.out.println(includes(null,null));    //t
		System.out.println(includes(null,""));	    //t
		System.out.println(includes(null,"abc"));	//f
		System.out.println(includes("",null));		//t
		System.out.println(includes("abc",null));   //t
	}
	
	/**
	 * Gameloop, handles logic of the game, input etc.
	 */
	private void game() {
		System.out.println(this.dictionary.size() + " words loaded from " + dictionaryFile);
		System.out.println("The random letters are: " + randLetters);
		System.out.println("Try to build as many letters from these words as you can!");
		for(;;){
			// Take input and then sort it alphabetically into separate variable
			String inputWord = reader.nextLine(); 
			String sortedWord = sortLetters(inputWord);
			
			if(includes(sortLetters, sortedWord)) {
				if (dictionary.contains(new String(inputWord))) {
					// Word was in the dictionary, that's good.
					System.out.println("Ok!");
				} else {
					// Word wasn't in the dictionary
					System.out.println("Your suggestion was not found "
									 + "in the dictionary.");
					break;
				}
			} else {
				// User input letters not in given random string
				System.out.println("Your suggestion did not match the given " 
								 + "letters.");
				break;
			}
		}
		// Print out all legal combinations in dictionary
		System.out.println("I found:");
		for (String word : dictionary) {
			String testingWord = sortLetters(word);
			if (includes(sortLetters, testingWord)) {
				System.out.println(word);
			}
		}
	}
	
	/**
	 * Takes a given string and returns it sorted in alphabetical order
	 * @param input The string you wish to sort
	 * @return The string sorted alphabetically
	 */
	private String sortLetters(String input) {
		char[] sortingWord = input.toCharArray();
		Arrays.sort(sortingWord);
		return new String(sortingWord);
	}

    public static void main(String[] args) {
    	try {
			AngloTrainer trainer = new AngloTrainer("dictionary.txt");
	    	trainer.game();
		} catch (IOException e) {e.printStackTrace();}
    }
}
