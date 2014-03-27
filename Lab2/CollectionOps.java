import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class CollectionOps {

    // Put your code for print here ...
	public static <T> void print(Collection<T> l) {
		Iterator iter = l.iterator();
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
    
    // Put your code for reverse here ...
	public static <T> List<T> reverse(List<T> l){
		for (int i = 0; i < l.size(); i++){
			l.add(0, l.get(l.size()-1));	// Index starts from 0, size from 1, so this takes the last element
			l.remove(l.size()-1);		// The last element is removed, l.size() is back to its starting value
		}
		return l;
	}

	// Put your code for less here ...
	public static <T> boolean less(	Collection<T> c1, Collection<T> c2,
									Comparator<T> comp){
		
		
		return false;
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

}
