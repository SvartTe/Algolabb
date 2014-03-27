/*
 *	Namn:	Sebastian Lindgren & Robert Larsson
 *	Grupp:	34
 *	Datum:	2014-03-27
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
		T i = t;
		t = null;
		return i;
	}
	
}