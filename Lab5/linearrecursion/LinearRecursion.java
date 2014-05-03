import java.io.IOException;
/**
 * @author NN
 * @version 2012-05-
 */
public class LinearRecursion {
// A.1
    public static void reverseInput() {
        // toDo 
    }
        
// A.2
    public static int multiply(int m,int n) {
        // toDo 
        return 0;
    } 
    
// A.3
    public static int countDigits(int n) {
        // toDo 
        return 0;
    }
           
    public static ListNode cons( int element, ListNode l ) {
        return new ListNode( element, l );
    }
    
    public static String toString( ListNode l ) {
        return "[" + toStringRec(l) + "]";
    }
    
    public static String toStringRec( ListNode l ) {
        if ( l == null )
            return "";
        else {
            return "" + l.element + 
                ((l.next == null) ? "" : "," + toStringRec(l.next));
        }
    }
    
    public static void print( String prompt, ListNode l ) {
        System.out.println(prompt + ": " + toString(l));
    }

// A.4
 public static ListNode copy( ListNode l ) {
        // toDo 
        return null;
 }
    
// A.5  
 public static ListNode append( ListNode l1, ListNode l2 ) {
        // toDo
        return null;
 }
    
/**********************************************
 * Some test cases.
 * Uncomment as you proceed!
 * ********************************************/
    public static void main(String[] args) throws IOException {
// A.1
     reverseInput();
     System.out.println();
// A.2
//      System.out.println(multiply(5,7));
//      System.out.println(multiply(-5,7));
//      System.out.println(multiply(-5,7));
//      System.out.println(multiply(-5,-7));
//      System.out.println(multiply(0,7));
//      System.out.println(multiply(5,0));
// A.3
//      System.out.println(countDigits(0));
//      System.out.println(countDigits(5));
//      System.out.println(countDigits(123));
                
        // An array of some test lists
        ListNode[] ll = {
            null,
            cons(1,null),
            cons(2,cons(3,null)),
            cons(4,cons(5,cons(6,null)))
        };
// A.4      
//         System.out.println("test copy");
//         for ( int i = 0; i < ll.length; i++ ) {
//             ListNode l = cons(999,copy(ll[i]));
//             print("l",l);       // result
//             print("l"+i,ll[i]); // original should be untouched
//         }
// A.5     
//         System.out.println("test append from left"); 
//         for ( int i = 0; i < ll.length - 1; i++ ) {
//             ListNode l = append(ll[i],ll[ll.length-1]);
//             print("l",l);       // result
//             print("l"+i,ll[i]); // original should be untouched
//             print("l"+(ll.length-1),ll[ll.length-1]); // original should be untouched
//         }
        
//         System.out.println("test append from right"); 
//         for ( int i = 0; i < ll.length - 1; i++ ) {
//             ListNode l = append(ll[ll.length-1],ll[i]);
//             print("l",l);       // result
//             print("l"+(ll.length-1),ll[ll.length-1]); // original should be untouched
//             print("l"+i,ll[i]); // original should be untouched
//         }
	}
}
