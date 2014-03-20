import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


//Author(s): Seabstian Lindgren & Robert Larsson
//Email:	seblind@student.chalmers.se & robla@student.chalmers.se
//Date:	

public class AngloTrainer {
	ArrayList<String> dictionary;
	private String randLetters;
	private String dictionaryFile;
	private Scanner reader;
	
	public AngloTrainer(String dictionaryFile) throws IOException {
	    dictionary = new ArrayList<String>();
	    loadDictionary(dictionaryFile);
	    this.dictionaryFile = dictionaryFile;
	    reader = new Scanner(System.in);
	}

	// use this to verify loadDictionary
	private void dumpDict() {
	    // Print out the dictionary at the screen.
		for(String g : dictionary) {
			System.out.println(g);
		}
	}

	private void loadDictionary( String fileName ) throws IOException {
		// Read the dictionary into a suitable container.
		// The file is a simple text file. One word per line.
		FileReader file = new FileReader(fileName);
		BufferedReader reader = new BufferedReader(file);
		String word = reader.readLine();
		
		// Check to see if we just managed to read a word from the file,
		// return from function if we didn't.
		if (word == null)
			return;
		do {
			dictionary.add(word);
			word = reader.readLine();
		} while (word != null);
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
	
	private void game() {
		System.out.println(this.dictionary.size() + " words loaded from " + dictionaryFile);
		System.out.println("The random letters are: " + randLetters);
		System.out.println("Try to build as many letters from these words as you can!");
		for(;;){
			if(includes(reader.nextLine(), randLetters))
				System.out.println("Ok!");
			else {
				
			}
		}
	}

    public static void main(String[] args) {
    	try {
			AngloTrainer trainer = new AngloTrainer("dictionary.txt");
	    	trainer.game();
		} catch (IOException e) {e.printStackTrace();}
    }
}
