import java.util.Arrays;


public class BinarySearch extends Search {
	
	public int search( String[] a, String x ) {
    	callCount++;
		int idx = Arrays.binarySearch( a, x );
		
		if( idx < 0 )
			return -( idx + 1 );
		else
			return idx;
	}
}
