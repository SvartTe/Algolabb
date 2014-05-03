public abstract class  Flake {
	protected Turtle turtle = null; // This will be initiated by draw in subclasses
	
    public abstract void draw(Turtle turtle, int level,double size);
}
