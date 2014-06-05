package fraktaler;

public class Sierpinski extends Flake {

	@Override
	public void draw(Turtle turtle,int n,double size) {
		this.turtle = turtle;
		turtle.turnTo(0.0);
		// Draw a triangular structure of three fractals
		turtle.turn(-60.0);
		drawSide(n,size);
		turtle.turn(120.0);
		drawSide(n,size);
		turtle.turn(120.0);
		drawSide(n,size);	
	}

	private void drawSide(int n,double size) {
		if ( n <= 0 )
			turtle.walk(size);
		else {
			double l = size/2.0;
			
		}
	}
}
