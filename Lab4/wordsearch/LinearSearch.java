
public class LinearSearch extends Search {

    public int search( String[] a, String x ) {
    	callCount++;
      	int i = 0;
    	while ( i < a.length && ! a[ i ].equals( x ) )
    		i++;
    	return i;
    }
}
