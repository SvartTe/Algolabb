import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Maze extends Board {
	// Someway to store rooms
	// How about a array?
	// A twisty maze of rooms, all alike.
	DisjointSets doors;
	
	public Maze( int rows, int cols ) {
		super(rows,cols);
		doors = new DisjointSets(maxCell);
		// We how have a list of points
		// We am go too far
		//    	 Implement this!
	}

	public void create() {
		//    	 Implement this method!
		// Let us assume that ID 0 is the set starting position and
		// ID Board.maxCell is end position
		// Begin crashing walls from position 0
		Random rng = new Random();
		boolean jobDone = false;
		int rand1, rand2;
		Point neighbor;
		Pair<Point, Point> pear;
		
		this.setChanged();
		notifyObservers();
		
		while(!jobDone){		// VERSION ! ONLY, continue until there's a passage between
															// entrance and exit. Not guaranteed to be connected to every cell
			rand1 = rng.nextInt(maxCell);
			rand2 = rng.nextInt(maxCell);
			
			while(rand1 == rand2)
				rand2 = rng.nextInt(maxCell);	// Make sure that rand1 and rand2 are different
			
			if(doors.find(rand1) != doors.find(rand2)){	// Make sure that there is not already a union
				neighbor = new Point(getRow(rand1), getCol(rand1));		// Create a point with the coordinates of rand1's cellID
				
				neighbor.move(neighbor.getDirection(new Point(getRow(rand2), getCol(rand2))));	// Move this point one step in rand2's direction
				if(getCellId(neighbor) == rand2){						// If it's then at rand2's cellID, they were neighbors
					pear = new Pair<Point, Point>(	new Point(getRow(rand1), getCol(rand1)),
													neighbor);
					doors.union(doors.find(rand1),doors.find(rand2));		// I now declare you husband/wife/Object and husband/wife/Object
					this.setChanged();
					notifyObservers(pear);
					for(int i = 1 ; i < maxCell ; i++){
						if(doors.find(0) != doors.find(i))
							break;						// WORK WORK
						if(i == maxCell - 1)
							jobDone = true;				// JOB DONE
					}
				}
			}
			
		}
	}

	public void search() {
		//    	 Implement this method!
	}

	//    ...
}
