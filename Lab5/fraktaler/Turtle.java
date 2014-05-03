public class Turtle {
    private Point position = new Point();
	private double direction = 0.0;
	private Canvas canvas;
	
	public Turtle(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public void walk(double distance) {
		Point oldPosition = position;
		jump(distance);
		canvas.drawLine((int)oldPosition.x,
		                (int)oldPosition.y,
		                (int)position.x,
		                (int)position.y);
	}
	
// Is this really necessary?
//	public void walkTo(Point p) {
//		canvas.drawLine((int)position.x,
//                        (int)position.y,
//                        (int)p.x,
//                        (int)p.y);
//		position = p;
//	}
	
	public void jump(double distance) {
		position = nextPos(distance);
		canvas.drawLine((int)position.x,
                        (int)position.y,
                        (int)position.x,
                        (int)position.y);

	}
	
	public void jumpTo(Point p) {
		position = p;
		canvas.drawLine((int)p.x,
                        (int)p.y,
                        (int)p.x,
                        (int)p.y);
	}
	
	public void turn(double angle) {
		direction = (direction + angle) % 360.0;
	}
	
	public void turnTo(double angle) {
		direction = angle;
	}
	
	private Point nextPos(double distance) {
		return new Point(position.x + Math.cos(toRad(direction))*distance,
		                    position.y + Math.sin(toRad(direction))*distance);
	}
	
	private double toRad(double degrees) {
		return (degrees/180)*Math.PI;
	}
}
