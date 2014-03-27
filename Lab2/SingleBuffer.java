/*
 *	Namn:	Sebastian Lindgren & Robert Larsson
 *	Grupp:	
 *	Datum:	
 *
 */

public class SingleBuffer<T> {
	private T t;
	
	public boolean put(T t){
		if(this.t == null) {
			this.t = t;
			return true;
		}
		return false;
	}
	
	public T get(){
		return t;
	}
	
}