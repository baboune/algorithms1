import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int N; // size of the stack
    private Node first;
    
    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }
    
    public Deque() {                     // construct an empty deque
        first = null;
        N = 0;
    }
    
    public boolean isEmpty() {           // is the deque empty?
        return first == null;
    }
    
    public int size() {                  // return the number of items on the deque
        return N;
    }
    
    public void addFirst(Item item) {    // insert the item at the front
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    
    public void addLast(Item item) {     // insert the item at the end
        Node current = first;
        
        if (current == null) {
            addFirst(item);
            return;
        }
        
        while(current.next != null) {
            current = current.next;
        }
        
        Node newlast = new Node();
        newlast.item = item;
        newlast.next = current.next;
        current.next = newlast;
        N++;
    }
    
    public Item removeFirst() {          // delete and return the item at the front
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    
    public Item removeLast() {           // delete and return the item at the end
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Node last = null;
        Node current = first;
        
        // special case for list containing single element
        if (current.next == null) {
            Item item = current.item;
            first = null;
            N--;
            return item;
        }
        
        while(current.next != null) {
            last = current;
            current = current.next;
        }
        
        Item item = current.item;
        
        last.next = null;
        N--;
        return item;
    }
    
    public Iterator<Item> iterator() {   // return an iterator over items in order from front to end
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public void remove() {
            throw new java.util.NoSuchElementException();
        }
        
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
        
    // test client
    public static void main(String[] args) {
        Deque<String> d = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) d.addLast(item);
            else if (!d.isEmpty()) StdOut.print(d.removeLast() + " " + d.size());
        }
        StdOut.println("(" + d.size() + " left on stack)");
    }
}