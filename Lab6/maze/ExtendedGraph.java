import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;


public class ExtendedGraph extends Graph implements Observer{

	public List<Integer> getPath (int destName) {
		// Test if destName exists
		Vertex w = vertexMap.get( destName );
        if( w == null )
            throw new NoSuchElementException( "Destination vertex not found" );
        else if( w.dist == INFINITY ) {
            System.out.println( destName + " is unreachable" );
            System.exit(-1);
        }
        // Do MAGIC
		return getPath(w);
	}

	private List<Integer> getPath (Vertex dest) {
		// Go rekursiv
		List<Integer> listz = new ArrayList<Integer>();
		
		if( dest.prev != null )
        {
            listz.addAll(getPath( dest.prev ));
        }
        listz.add(dest.name);
		return listz;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(	arg instanceof Pair<?,?> && 
				( ((Pair) arg).first instanceof Point && 
				((Pair) arg).second instanceof Point )){
				Point first = (Point) ((Pair) arg).first;	// To avoid cast with small variable
				Point second = (Point) ((Pair) arg).second;
				Maze majs = (Maze)o;
				vertexMap.get(majs.getCellId(first)).adj.add(new Edge(vertexMap.get(majs.getCellId(second)), 1.0));
				vertexMap.get(majs.getCellId(second)).adj.add(new Edge(vertexMap.get(majs.getCellId(first)), 1.0));
		} 
		else {
			for (int i = 0; i < ((Maze)o).maxCell; i++)
				vertexMap.put(i, new Vertex(i));		// The maze has been created, so all the cells get a vertex
		}
	}

}
