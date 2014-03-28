import java.util.ArrayList;



public class Main {
    public static void main(String[] args) {    
         ArrayList<String> names = new ArrayList<String>();
         
//         IntegerComparator comp = new IntegerComparator();
         
         // Three lists of Integers
         ArrayList<Integer> small = new ArrayList<Integer>();
         ArrayList<Integer> large = new ArrayList<Integer>();
         ArrayList<Integer> mixed = new ArrayList<Integer>();
         
         small.add(1);
         small.add(-2);
         small.add(3);
         small.add(4);
         small.add(5);
         
         large.add(100);
         large.add(200);
         large.add(300);
         large.add(400);
         large.add(500);
         large.add(600);
         large.add(700);
         large.add(800);
         
         mixed.add(15);
         mixed.add(-15);
         mixed.add(150);
         mixed.add(25);
         mixed.add(45);
         mixed.add(-155);
         
         // Test print for an empty list
         CollectionOps.print(names); System.out.println();

         // Test print for a list containing one element
         names.add("a");
         CollectionOps.print(names); System.out.println();

         // Test print for a list containing more than one element
         names.add("b");
         names.add("c");
         CollectionOps.print(names); System.out.println();
         
         // Test the return value from reverse
         CollectionOps.print(CollectionOps.reverse(names));
         System.out.println();
         // Test that reverse mutates its argument
         CollectionOps.print(names);
         System.out.println();

         // Write code to test less here
         IntegerComparator comp = new IntegerComparator();
         System.out.println("Testing less: ");
         // True
         System.out.println("Small - Large: " + CollectionOps.less(small, large, comp));
         // False
         System.out.println("Large - Small: " + CollectionOps.less(large, small, comp));
         // False
         System.out.println("Mixed - Large: " + CollectionOps.less(mixed, large, comp));
         // False
         System.out.println("Mixed - Small: " + CollectionOps.less(mixed, small, comp));

         // Write code to test map here
             
         // Write code to test filter here
    }
    
}
