
/**
 * A collection of utility functions for C style primitive list handling.
 *
 * @author(s)	Robert Larsson & Sebastian Lindgren
 * @version 2014-04-06
 */
public class Lists {
   
    // Create an empty list (a null terminated list head).
    public static ListNode mkEmpty() {
        return toList("");
    }
    
    // Returns true if l refers to a null terminated list head, false ow.
    public static boolean isEmpty(ListNode l) {
        if ( l == null )
            throw new ListsException("Lists: null passed to isEmpty");
        return l.next == null;
    }
    
    // Two lists are equal if both are empty, or if they have equal lengths
    // and contain pairwise equal elements at the same positions.
    public static boolean equals(ListNode l1,ListNode l2) {
        if ( isEmpty(l1) && isEmpty(l2) )
            return true;
        else if ( isEmpty(l1) || isEmpty(l2) )
            return false;
        else { // both lists are non-empty
            ListNode p1 = l1.next, p2 = l2.next;
            while ( p1 != null && p2 != null ) {
                char c1 = p1.element, c2 = p2.element;
                if ( p1.element != p2.element )
                    return false;
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1 == null && p2 == null;
        }
    }
    
    // Se f�rel. OH
    public static ListNode toList(String chars) {
        ListNode head, ptr1;     // head pekar alltid p� listans huvud
        head = new ListNode();   // Listans huvud (inneh�ller ej data)
        head.next = null;
        ptr1 = head;             // ptr pekar p� sista noden

        // Bygg en lista av tecken
        for ( int i = 0; i < chars.length(); i++ ) {
            ptr1.next = new ListNode();          // Addera en ny nod sist
            ptr1 = ptr1.next;                    // Flytta fram till den nya noden
            ptr1.element = chars.charAt(i);      // S�tt in tecknet
            ptr1.next = null;                    // Avsluta listan
        } 
        return head;
    }
    
    // Se f�rel. OH
    public static ListNode copy(ListNode l) {
        if ( l == null )
            throw new ListsException("Lists: null passed to copy");
        ListNode head,ptr1,ptr2;
        head = new ListNode();             // Kopian
        head.next = null;
        ptr1 = head;

        ptr2 = l.next;  // f�rsta listelementet i originallistan
        while ( ptr2 != null ) {
            ptr1.next = new ListNode();    // Ny nod i kopian
            ptr1 = ptr1.next;              // Flytta fram
            ptr1.element = ptr2.element;   // Kopiera tecknet
            ptr1.next = null;              // Avsluta
            ptr2 = ptr2.next;              // Flytta fram i originallistan
        }
        return head;
    }
    
    // Se f�rel. OH
    public static ListNode removeAll(ListNode l,char c) {
        if ( l == null )
            throw new ListsException("Lists: null passed to removeAll");
        ListNode p = l;
        while ( p.next != null ) {
            ListNode temp = p.next;      // Handtag p� n�sta nod
            if ( temp.element == c )     // Skall den tas bort?
                p.next = temp.next;      // L�nka f�rbi
            else
                p = p.next;              // Nej, g� vidare *
        }
        // * p f�r ej flyttas om den efterf�ljande noden togs bort!
        return l;
     }
    
    
    /////////////////////////////////////////////////
    // ------------------------------------------- //
    // ---------------- Uppgifter ---------------- //
    // ------------------------------------------- //
    /////////////////////////////////////////////////
    
    
    // Testmetod: JunitListTest.testToString()
    public static String toString(ListNode l) {
    	StringBuilder returnString = new StringBuilder();
    	ListNode currentNode = l.next;
    	while(currentNode != null){
//    		if(currentNode.element instanceof Object)	// For a scalable method; not compatible with char
//    			returnString.append(currentNode.element.toString());
//    		else
	    		returnString.append(currentNode.element);
    		currentNode = currentNode.next;
    	}
         return returnString.toString();
    }
    
    // Testmetod: JunitListTest.testContains()
    public static boolean contains(ListNode head,char c) {
    	ListNode currentNode = head.next;
    	while(currentNode != null) {
    		if (currentNode.element == c)
    			return true;
    		else
    			currentNode = currentNode.next;
    	}
        return false;
    }
    
    // Testmetod: JunitListTest.testCopyUpperCase()
    public static ListNode copyUpperCase(ListNode head) {
    	ListNode returnNode = new ListNode();
    	ListNode currentReturnNode = returnNode;
    	ListNode currentNode = head;
    	
    	while (currentNode.next != null) {
    		if (Character.isUpperCase(currentNode.element)) {
    			currentReturnNode.next = new ListNode();
    			currentReturnNode = currentReturnNode.next;
    			currentReturnNode.element = currentNode.element;
    		}
    		currentNode = currentNode.next;
    	}
    	
        return returnNode;
    }
    
    // Testmetod: JunitListTest.testAddFirst()
    public static ListNode addFirst(ListNode l,char c) {  
        ListNode newFirst = new ListNode();
        newFirst.element = c;
        newFirst.next = l.next;
        l.next = newFirst;
    	return l;
    }
         
    // This is a private utility method.
    private static ListNode getLastNode(ListNode head) {
        ListNode lastNode = head;
        while(lastNode.next != null)
        	lastNode = lastNode.next;
    	return lastNode;
    }
   
    // Testmetod: JunitListTest.testAddLast()
    public static ListNode addLast(ListNode l,char c) {  
    	ListNode newLastNode = new ListNode();
    	ListNode oldLastNode = getLastNode(l);
    	
    	newLastNode.element = c;
    	newLastNode.next = null;
    	oldLastNode.next = newLastNode;
    	
        return l;
    }
    
    // Testmetod: JunitListTest.testConcat()
    public static ListNode concat(ListNode l1,ListNode l2) {  
        getLastNode(l1).next = l2.next;
        l2 = mkEmpty();    	
    	return l1;
    }
    
    // Testmetod: JunitListTest.testAddAll()
    public static ListNode addAll(ListNode l1,ListNode l2) { 
    	ListNode l2Copy = copy(l2);
    	concat(l1, l2Copy.next);
        return l1;
    }
      
    // Testmetod: JunitListTest.testReverse()
    public static ListNode reverse(ListNode head) {  
    	ListNode reverseList = mkEmpty();
    	ListNode runner = head.next;
    	
    	while(runner != null){
    		addFirst(reverseList, runner.element);
    		runner = runner.next;
    	}
    		
        return reverseList;
    }
}