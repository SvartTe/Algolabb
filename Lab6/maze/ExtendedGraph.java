import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;


public class ExtendedGraph extends Graph{

	ExtendedGraph(Maze o){
		super();
		for (int i = 0; i < ((Maze)o).maxCell; i++)
			vertexMap.put(i, new Vertex(i));		// The maze has been created, so all the cells get a vertex
	}
	
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
            listz = getPath( dest.prev );
        }
        listz.add(dest.name);
		return listz;
	}
}
