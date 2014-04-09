import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

// Last update: UH 2009-04-16

// WordSearch class interface: solve word search puzzle.
// Modified by UH to make the wordlist search method optional.
//
// CONSTRUCTION: with no initializer
// ******************PUBLIC OPERATIONS******************
// int solvePuzzle( )   --> Print all words found in the
//                          puzzle; return number of matches

public class UHWordSearch {
    private int rows;
    private int columns;
    private char [ ][ ] theBoard;
    private String [ ] theWords;
    private int maxWordLength = 0;
    private BufferedReader puzzleStream;
    private BufferedReader wordStream;
    private BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
    private Search searchAlg;   
    private final boolean PRINT_WORDS = false;		// turns printing on/off
    private final boolean PREFIX_TESTING = false;	// turns prefix testing on/off, on => binary search
    private boolean BINARY_SEARCH = false;			// true => binary search, false => linear search
   
    /**
     * Constructor for WordSearch class.
     * Prompts for and reads puzzle and dictionary files.
     */
    public UHWordSearch( ) throws IOException
    {
        puzzleStream = openFile( "Enter puzzle file" );
        wordStream   = openFile( "Enter dictionary name" );
        System.out.println( "Reading files..." );
        readPuzzle( );
        readWords( );
        
        // UH: don't change this
        if ( PREFIX_TESTING )
        	BINARY_SEARCH = true;
    }

    /**
     * Routine to solve the word search puzzle.
     * Performs checks in all eight directions.
     * @return number of matches
     */
    public int solvePuzzle( )
    {
        searchAlg = BINARY_SEARCH ? new BinarySearch() : new LinearSearch();
        int matches = 0;
        for( int r = 0; r < rows; r++ )
            for( int c = 0; c < columns; c++ )
                for( int rd = -1; rd <= 1; rd++ )
                    for( int cd = -1; cd <= 1; cd++ )
                        if( rd != 0 || cd != 0 )
                            matches += solveDirection( r, c, rd, cd );
        searchAlg.printStatistics();
        return matches;
    }


    /**
     * Search the grid from a starting point and direction.
     * @return number of matches
     * @author UH 2006-04-06: Adapted the algorithm so it can use different
     * search strategies in the dictionary
     */
    private int solveDirection( int baseRow, int baseCol, int rowDelta, int colDelta )
    {
        String charSequence = "";
        int numMatches = 0;
        int searchResult;

        charSequence += theBoard[ baseRow ][ baseCol ];

        for( int i = baseRow + rowDelta, j = baseCol + colDelta;
                 i >= 0 && j >= 0 && i < rows && j < columns;
                 i += rowDelta, j += colDelta )
        {
            charSequence += theBoard[ i ][ j ];
            if ( charSequence.length() > maxWordLength )
            	break;
            
            searchResult = searchAlg.search( theWords, charSequence );
            
            if( searchResult == theWords.length ) {   // corrected by UH 2007-05-02
                // either linear searched failed or binary search failed because charSequence
                // is larger than the largest word in theWords
            	if ( searchAlg instanceof BinarySearch )
            		break;	  // binary search failed and it makes no sense to extend charSequence any further
            	else
            		continue; // linear search failed but an extension of charSequence may succeed
            }
            
            // precondition: 0 <= searchResult < theWords.length
            // At this point one, and only one, of three conditions holds:
            // 1. Linear search succeeded
            // 2. Binary search succeded
            // 3. Binary search failed at the insertion point for charSequence, 
            //    which means that theWords[ searchResult ] is the least element greater than charSequence
            
            if( PREFIX_TESTING && ! theWords[ searchResult ].startsWith( charSequence ) )
            	break;

            if( theWords[ searchResult ].equals( charSequence ) ) {
//              if( theWords[ searchResult ].length( ) < 2 )
//                  continue;
            	numMatches++;
            	if ( PRINT_WORDS ) 
            		System.out.println( "Found " + charSequence + " at " +
            				baseRow + " " + baseCol + " to " + i + " " + j );
            }
        }

        return numMatches;
    }

    /**
     * Print a prompt and open a file.
     * Retry until open is successful.
     * Program exits if end of file is hit.
     */
    private BufferedReader openFile( String message )
    {
        String fileName = "";
        FileReader theFile;
        BufferedReader fileIn = null;

        do
        {
            System.out.println( message + ": " );

            try
            {
                fileName = in.readLine( );
                if( fileName == null )
                     System.exit( 0 );
                theFile = new FileReader( fileName );
                fileIn  = new BufferedReader( theFile );
            }
            catch( IOException e )
              { System.err.println( "Cannot open " + fileName ); }
        } while( fileIn == null );

        System.out.println( "Opened " + fileName );
        return fileIn;
    }
        
    /**
     * Routine to read the grid.
     * Checks to ensure that the grid is rectangular.
     * Checks to make sure that capacity is not exceeded is omitted.
     */
    private void readPuzzle( ) throws IOException
    {
        String oneLine;
        List<String> puzzleLines = new ArrayList<String>( );

        if( ( oneLine = puzzleStream.readLine( ) ) == null )
            throw new IOException( "No lines in puzzle file" );

        columns = oneLine.length( );
        puzzleLines.add( oneLine );

        while( ( oneLine = puzzleStream.readLine( ) ) != null )
        {
            if( oneLine.length( ) != columns )
                System.err.println( "Puzzle is not rectangular; skipping row" );
            else
                puzzleLines.add( oneLine );
        }
        
        rows = puzzleLines.size( );
        theBoard = new char[ rows ][ columns ];
        
        int r = 0;
        for( String theLine : puzzleLines )
            theBoard[ r++ ] = theLine.toCharArray( );
    }

    /**
     * Routine to read the dictionary.
     * Error message is printed if dictionary is not sorted.
     */
    private void readWords( ) throws IOException
    {
        List<String> words = new ArrayList<String>( );

        String lastWord = null;
        String thisWord;
        
        while( ( thisWord = wordStream.readLine( ) ) != null )
        {
            if( lastWord != null && thisWord.compareTo( lastWord ) < 0 )
            {
                System.err.println( "Dictionary is not sorted... skipping" );
                continue;
            }
            words.add( thisWord );
            lastWord = thisWord;
            if ( thisWord.length() > maxWordLength )
            	maxWordLength = thisWord.length();
        }
        
        theWords = new String[ words.size( ) ];
           theWords = words.toArray( theWords );
		System.out.println( words.size() + " words loaded, max word length " 
                                  +  maxWordLength );
    }

    public static void main( String [ ] args )
    {
        UHWordSearch p = null;
        
        try
        {
            p = new UHWordSearch( );
        }
        catch( IOException e )
        {
            System.out.println( "IO Error: " );
            e.printStackTrace( );
            return;
        }
        System.out.println( "Solving..." );
        System.out.println( p.solvePuzzle( ) + " matches found" );
    }
}
