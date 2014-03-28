/* 
 * Namn: Seabstian Lindgren & Robert Larsson
 * Grupp: 34
 * Datum: 2014-03-27
 */
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;

public class CollectionOps {

	/**
	 * 
	 * @param l
	 */
	public static <T> void print(Collection<T> l) {
		Iterator<T> iter = l.iterator();
		System.out.print("[");
		boolean comma = false;
		while (iter.hasNext()) {
			if (comma)
				System.out.print(",");
			Object c =  iter.next();
			System.out.print(c.toString());
			comma = true;
		}
		System.out.println("]");
	}
    
	/**
	 * Reverses the order of all elements in the given list
	 * Could've used Collections.reverse(list), but that felt like
	 * cheating.
	 * @param l The list that should have the order of its elements
	 * 			reversed
	 * @return A reference to the now reversed list l
	 */
	public static <T> List<T> reverse(List<T> l){
		for (int i = 0; i < l.size(); i++){
			l.add(i, l.get(l.size()-1));	// Index starts from 0, size from 1, so this takes the last element
			l.remove(l.size()-1);		// The last element is removed, l.size() is back to its starting value
		}
		return l;
	}

	/**
	 * Determines whether all elements in a collection is less than
	 * all elements in another collection. Both collections should contain
	 * the same type of objects.
	 * @param c1 The collection that should contain smaller elements
	 * @param c2 The collection that should contain greater elements
	 * @param comp The comparator for the objects in question that should be used
	 * @return True if all elements in c1 is less than all elements in c2
	 */
	public static <T> boolean less(	Collection<T> c1, Collection<T> c2,
									Comparator<T> comp){
		for (T t1 : c1)
			for (T t2 : c2)
				if (comp.compare(t1, t2) != -1)	//This aborts the loop the instant one element isn't less than the other, saving precious time.
					return false;	// Technically only two rows
		return true;
	}
	
	
    // Example
    public static <T1,T2> Collection<T2>
    							map(UnaryOp<T1,T2> functor,Collection<T1> c) 
    {
    	// Determine the dynamic type of the collection
    	Class<? extends Collection> cls = c.getClass();
    	try {
    		// Create an object of the same dynamic type as c
    		Collection<T2> result = cls.newInstance();
    		// Copy the elements and apply op to them
    		for ( T1 x : c )
    			result.add(functor.op(x));
    		return result;   
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    // Put your code for filter here ...
    /**
     * 
     * @param op
     * @param map
     * @return
     */
    public static <T> Collection<T> filter(UnaryPred<T> op, Collection<T> map) {
    	try {
    		Collection<T> list = map.getClass().newInstance();
    		for(T t : map) {
    			if(op.pred(t))
    				list.add(t);
    		}
    		return list;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    
}
