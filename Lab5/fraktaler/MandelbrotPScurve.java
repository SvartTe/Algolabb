// Benoit Mandelbrot's Peano Snow Flake Curve

public class MandelbrotPScurve extends Flake {
	private static int LEFT = 0;
	//private static int RIGHT = 1;	 // not used actually
	private Turtle turtle;
		
	public void draw(Turtle turtle,int n,double size) {
		this.turtle = turtle;
		turtle.turnTo(0.0);
		drawPsPart(n,LEFT,size);
	}
	
	private void drawPsPart(int n,int turnSide,double size) {
		if ( n == 0 )
			turtle.walk(size);
		else {
			turnToLeft(turnSide,-60.0);
			drawPsPart(n - 1, other(turnSide), size / 3.0);
			drawPsPart(n - 1, turnSide, size / 3.0);
			turnToRight(turnSide, -60.0);
			drawPsPart(n - 1, turnSide, size / 3.0);
			turnToRight(turnSide, -60.0);
			drawPsPart(n - 1, turnSide, size / 3.0);
			turnToRight(turnSide, -150.0);
			drawPsPart(n - 1, turnSide, shorter(size / 3.0));
			drawPsPart(n - 1, other(turnSide), shorter(size / 3.0));
			turnToLeft(turnSide, -60.0);
			drawPsPart(n - 1, other(turnSide), shorter(size / 3.0));
			turnToLeft(turnSide, -60.0);
			drawPsPart(n - 1, other(turnSide), shorter(size / 3.0));
			turnToLeft(turnSide, -90.0);
			drawPsPart(n - 1, turnSide, size / 3.0);
			turnToRight(turnSide, -150.0);
			drawPsPart(n - 1, turnSide, shorter(size / 3.0));
			drawPsPart(n - 1, other(turnSide), shorter(size / 3.0));
			turnToLeft(turnSide, -150.0);
			drawPsPart(n - 1, other(turnSide), size / 3.0);
			drawPsPart(n - 1, turnSide, size/3.0);
		}
	}
	
	private void turnToLeft(int hand, double angle) {
		if ( hand == LEFT )
			turtle.turn(angle);
		else
			turtle.turn(-angle);
	}
	
	private void turnToRight(int hand, double angle) {
		turnToLeft(hand,-angle);
	}
	
	private int other(int direction) {
		return  (direction + 1) % 2;
	}
	
	private double shorter(double size) {
		return size/(2.0*Math.cos(Math.PI/6.0));
	}
}
