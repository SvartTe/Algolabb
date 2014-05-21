import java.util.ArrayList;
import java.util.List;


public class Maze extends Board {
	// Someway to store rooms
	// How about a array?
	// A twisty maze of rooms, all alike.
	List<Pair> roomsndoors;
	
	public Maze( int rows, int cols ) {
		super(rows,cols);
		roomsndoors = new ArrayList<>();
		for (int x = 0; x < rows; x++)
			for (int y = 0; y < cols; y++){
				roomsndoors.add(new Pair<Point, DisjointSets>(new Point(x, y), new DisjointSets(1)));
			}
		
		// We how have a list of points
		// We am go too far
		
		create();
		//    	 Implement this!
	}

	public void create() {
		//    	 Implement this method!
		// Let us assume that ID 0 is the set starting position and
		// ID Board.maxCell is end position
		// Begin crashing walls from position 0
		
		
	}

	public void search() {
		//    	 Implement this method!
	}

	//    ...
}
