import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private int N;         // number of elements on queue
   private Node first;    // beginning of queue
   private Node last;     // end of queue    
   
    // helper linked list class
   private class Node {
       private Item item;
       private Node next;
   }   
   
   // construct an empty randomized queue
   public RandomizedQueue() {
        first = null;
        last  = null;
        N = 0;
   }           
   
   // is the queue empty?
   public boolean isEmpty() {
        return first == null;   
   }           
   
   // return the number of items on the queue
   public int size() {
       return N;
   }   
   
   // add the item
   public void enqueue(Item item) {
       if(item == null) {
           throw new java.lang.NullPointerException();
       }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;   
   }     
   
   // delete and return a random item
   public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Queue underflow");
        int num = StdRandom.uniform(N);
        int i = 0;
        Node current = first;
        Node prev = null;
        
        // special case: dequeue first item
        if(num == 0) {
            first = current.next;
        }
        
        while(i < num) {
            prev = current;
            current = current.next;
            i++;
        }
        
        if(prev != null) {
            prev.next = current.next;
        }
        
        Item item = current.item;
        current = null;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;   
   }              
   
   // return (but do not delete) a random item
   public Item sample() {
       int num = StdRandom.uniform(N);
       int i = 0;
       Node current = first;
       
       while(i < num) {
           current = current.next;
           i++;
       }
       
       return current.item;
   }               
   
   // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        private int[] arr;
        private int iteratorCount;
        private int arrNum;
        
        public ListIterator() {
            arr = new int[N];
            arrNum = N;
            for(int i = 0; i < N; i++) {
                arr[i] = i;
            }
            StdRandom.shuffle(arr);
            iteratorCount = 0;
        }
        
        public boolean hasNext() {
            return iteratorCount < arrNum - 1;
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        
        public Item next() {
            int i = 0;
            while(i < arr[iteratorCount]) {
                current = current.next;
                i++;
            }
            iteratorCount++;
            Item item = current.item;
            current = first; // reset current pointer
            return item;
        }
    }
   
   // test client
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) q.enqueue(item);
            else if (!q.isEmpty()) {
                StdOut.print(q.dequeue() + " " + q.size());
                for(String s : q) {
                    StdOut.print(s);
                }
            }
        }
        StdOut.println("(" + q.size() + " left on stack)");
    }
}