package fraktaler;

public class ConcaveKoch extends Flake {

	@Override
	public void draw(Turtle turtle, int level, double size) {
		this.turtle = turtle;
		turtle.turnTo(0.0);
		// Draw a triangular structure of three fractals
		turtle.turn(-60.0);
		drawSide(level,size);
		turtle.turn(120.0);
		drawSide(level,size);
		turtle.turn(120.0);
		drawSide(level,size);	

	}
	
	private void drawSide(int n,double size) {
		if ( n <= 0 )
			turtle.walk(size);
		else {
			double l = size/3.0;
			drawSide(n-1,l);
			turtle.turn(60.0);
			drawSide(n-1,l);
			turtle.turn(-120);
			drawSide(n-1,l);
			turtle.turn(60);
			drawSide(n-1,l);
		}
	}

}
