import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {

       RandomizedQueue<String> queue = new RandomizedQueue<>();
       int nitems = 0;

       int k = Integer.parseInt(args[0]);


       while (!StdIn.isEmpty()) {
           queue.enqueue(StdIn.readString());
           nitems++;
       }

       if (k > nitems) {
           throw new IllegalArgumentException("Number of items to print is greater than the number of strings.");
       }

       for (int i = 0; i < k; i++) {

           System.out.println(queue.dequeue());

       }


    }
}
