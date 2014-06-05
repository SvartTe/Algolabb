package fraktaler;


public class Penta extends Flake {

	@Override
	public void draw(Turtle turtle,int n,double size) {
		this.turtle = turtle;
		turtle.turnTo(0.0);
		// Draw a triangular structure of three fractals
		turtle.turn(72.0);
		drawSide(n,size);
		turtle.turn(-72.0);
		drawSide(n,size);
		turtle.turn(-72.0);
		drawSide(n,size);
		turtle.turn(-72.0);
		drawSide(n,size);
		turtle.turn(-72.0);
		drawSide(n,size);
	}

	private void drawSide(int n,double size) {
		if ( n <= 0 )
			turtle.walk(size);
		else {
			double l = size/3.0;
			drawSide(n-1,l);
			turtle.turn(-108.0);
			drawSide(n-1,l);
			turtle.turn(72.0);
			drawSide(n-1,l);
			turtle.turn(72.0);
			drawSide(n-1,l);
			turtle.turn(72.0);
			drawSide(n-1,l);
			turtle.turn(-108.0);
			drawSide(n-1,l);
		}
	}

}
