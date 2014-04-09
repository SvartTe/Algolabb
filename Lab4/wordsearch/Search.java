import java.util.Calendar;

public abstract class Search {
	
	public abstract int search( String[] a, String x );

	public void printStatistics() {
        if ( callCount > 0 ) {
        	String tname = this.getClass().getName(); // The dynamic type
            System.out.println();
            long time = System.currentTimeMillis() - startTime;
            System.out.println( tname + " was called " + callCount + " times for a total of " + 
            		(float)time/1000.0 + " seconds" );
        }
    }
    
    protected int callCount = 0;
    private long startTime = System.currentTimeMillis();
}
