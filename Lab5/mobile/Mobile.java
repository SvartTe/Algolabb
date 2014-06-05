package mobile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Mobile {
	
	private enum MobileType { SIMPLE, COMPOSITE }
	private MobileType type;
	private float weight;                   // Simple
	private float leftLength, rightLength;  // Composite
	private Mobile left, right;
	
	public Mobile( float weight ) {
		type = MobileType.SIMPLE;
		this.weight = weight;
		left = null;
		right = null;
		
	}
	
	public Mobile( Mobile left, float leftLength, Mobile right, float rightLength ) {
		type = MobileType.COMPOSITE;
		this.left = left;
		this.right = right;
	    this.leftLength = leftLength;
	    this.rightLength = rightLength;	
	}
	
	// Return the total mass of the mobile
	public float getWeight() {
		if ( isSimple() )
			return weight;
		else
			return left.getWeight() + right.getWeight();
	}  
	
	// Return the maximum height of the mobile
	public int getHeight() {
		if (isSimple())
			return 1;
		else
			return Math.max(left.getHeight(), right.getHeight() + 1);
	}  
	
	// Print the leaves of the mobile
	public void flatten()  {
	      if (isSimple())
	    	  System.out.print(weight + " ");
	      else {
	    	  // Change the order here to print backwards
	    	  left.flatten();
	    	  right.flatten();
	      }
	}  
	
//	Print a structured view of the mobile
	public void prettyPrint() {
		if (isSimple())
			System.out.print("(" + weight + ")");
		else {
			System.out.print("[");
			left.prettyPrint();
			System.out.print(", " + leftLength + " ");
			right.prettyPrint();
			System.out.print(", " + rightLength + " ");
			System.out.print("]");
		}
	}
	
// Determine if the mobile is balanced
	public boolean isBalanced() {
		final double eps = 0.000001;
		return isSimple() ||
		    left.isBalanced() && right.isBalanced() &&
		        Math.abs( leftLength * left.getWeight() -
				rightLength * right.getWeight() ) < eps;
	}   

// Determine if two mobiles are equal	
	public boolean equals(  Object rhs ) {
		// Is rhs a mobile?
		if (!(rhs instanceof Mobile) || rhs == null)
			return false;
		// To avoid cast with small variable, do a permanent cast
		Mobile other = (Mobile)rhs;
		/* Floats are slightly unpredictable. We need something small to 
		use when comparing floats to allow for this unpredictability */
		final double negligible = 0.00000001;
		
		if (isSimple() != other.isSimple())
			return false;
		else if (isSimple() && other.isSimple())
			return (Math.abs(weight - other.weight) <= negligible);
		
		if (Math.abs(rightLength - other.rightLength) < negligible
			&& Math.abs(leftLength - other.leftLength) < negligible)
			return (right.equals(other.right) && left.equals(other.left));
		
		// If all else fails, they're probably not equal anyways
	    return false;
	}
	
//	Return a clone of this mobile
	public Mobile clone() {
		Mobile m;
		if (isSimple())
			m = new Mobile(weight);
		else
			m = new Mobile(left.clone(), this.leftLength, right.clone(), this.rightLength);
         return m;
	}
	
// Change this mobile to its mirror image
	public void mirror() {
		float valueBuffer;
		Mobile mobileBuffer;
		
		if (!isSimple()) {
			valueBuffer = leftLength;
			leftLength = rightLength;
			rightLength = valueBuffer;
			
			mobileBuffer = left;
			left = right;
			right = mobileBuffer;
			
			right.mirror();
			left.mirror();
		}
	}
	
	private boolean isSimple() { 
		return type == MobileType.SIMPLE; 
	}
	
	public static void main(String[] args) {
		Mobile  m1 = new Mobile( 1 ),
		        m2 = new Mobile( new Mobile( 2 ), 6,  new Mobile( 3 ), 4 ),
		        m = new Mobile( m1, 10, m2, 2 );
	
		System.out.println("Total mass: " + m.getWeight() );

		System.out.println("Height:     " + m.getHeight() );
		m.flatten(); System.out.println();
		m.prettyPrint(); System.out.println();
		if ( m.isBalanced() )
			System.out.println("Balanced!");
		else
			System.out.println("Not balanced!");
	
		Mobile m22 = new Mobile( new Mobile( 2 ), 6,  new Mobile( 3 ), 4 ),
		       m3 = new Mobile( m1, 10, m22, 2 );
		if ( m.equals(m3) )
			System.out.println("Equal!");		// They should be!
		else
			System.out.println("Not equal!");
		
		Mobile c = m.clone();
		if ( c.equals(m) )
			System.out.println("Equal!");		// They should be!
		else
			System.out.println("Not equal!");

		if ( c == m )
			System.out.println("Identical!");	// They should definately not!
		else
			System.out.println("Not identical!");
		
		m.mirror();
		m.prettyPrint(); System.out.println();
		m.mirror();
		m.prettyPrint(); System.out.println();

	}
}
