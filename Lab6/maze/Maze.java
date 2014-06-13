import java.util.List;
import java.util.Random;


public class Maze extends Board {
	// A twisty maze of rooms, all alike.
	DisjointSets doors;
	ExtendedGraph extendo;
	
	public Maze( int rows, int cols ) {
		super(rows,cols);
		extendo = new ExtendedGraph();
		// Stop the user from making a single room house
		if (rows == 1 && cols == 1) {
			System.out.println("Don't be silly now... No one likes a smartass.");
			System.exit(-1);
		}
		doors = new DisjointSets(maxCell);
		// We how have a list of disjointed sets
		// We am go too far
	}

	public void create() {
		// Let us assume that ID 0 is the set starting position and
		// ID Board.maxCell is end position
		// Begin crashing
		Random rng = new Random();
		int rand1, rand2, rand3;
		int wallsBroken = 0;
		Point neighbor;
		Pair<Point, Point> pear;
		
		this.setChanged();
		notifyObservers();
		
		while(wallsBroken < maxCell - 1){		
															
			rand1 = rng.nextInt(maxCell);
			rand3 = rng.nextInt(3);
			
			switch(rand3){
			case 0:	// Case North
				if(isValid(new Point(getRow(rand1) - 1, getCol(rand1)))) {
					rand2 = rand1 - maxCol;
					break;
				}
			case 1:	// Case East
				if(isValid(new Point(getRow(rand1), getCol(rand1) + 1))){
					rand2 = rand1 + 1;
					break;
				}
			case 2: // Case South
				if(isValid(new Point(getRow(rand1) + 1, getCol(rand1)))){
					rand2 = rand1 + maxCol;
					break;
				}
			case 3: // Case West
				if(isValid(new Point(getRow(rand1), getCol(rand1) - 1))){
					rand2 = rand1 - 1;
					break;
				}
			default:
				rand2 = rng.nextInt(maxCell);
			}
			
				
			
			if(doors.find(rand1) != doors.find(rand2)){	// Make sure that there is not already a union
				neighbor = new Point(getRow(rand1), getCol(rand1));		// Create a point with the coordinates of rand1's cellID
				
				neighbor.move(neighbor.getDirection(new Point(getRow(rand2), getCol(rand2))));	// Move this point one step in rand2's direction
				if(getCellId(neighbor) == rand2){						// If it's then at rand2's cellID, they were neighbors
					pear = new Pair<Point, Point>(	new Point(getRow(rand1), getCol(rand1)),
													neighbor);
					doors.union(doors.find(rand1),doors.find(rand2));		// I now declare you husband/wife/Object and husband/wife/Object
					wallsBroken++;
					extendo.addEdge(rand1, rand2, 1);
					extendo.addEdge(rand2, rand1, 1);
					this.setChanged();
					notifyObservers(pear);
				}
			}
			
		}
	}

	public void search() {
		extendo.unweighted(0);
		List<Integer> rutor = extendo.getPath(maxCell-1);
		
		for(Integer punkter: rutor) {
			this.setChanged();
			notifyObservers(punkter);
		}
	}
}
