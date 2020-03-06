import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int numberOfItems;

    private class Node {
        Item item;
        Node previousNode;
        Node nextNode;
    }

    public Deque() {
        this.first = null;
        this.last = null;

        this.numberOfItems = 0;
    }

    public boolean isEmpty() {
        return numberOfItems == 0;
    }

    public int size() {
        return numberOfItems;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("An item must be specified by the user.");
        }

        Node oldFirst = first;

        first = new Node();
        first.item = item;
        first.nextNode = oldFirst;

        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.previousNode = first;
        }

        numberOfItems++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("An item must be specified by the user.");
        }

        Node oldLast = last;

        last = new Node();
        last.item = item;
        last.previousNode = oldLast;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.nextNode = last;
        }

        numberOfItems++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }

        Item item = first.item;

        numberOfItems--;

        if (isEmpty()) {
            first = null;
        } else {
            first = first.nextNode;
            first.previousNode = null;
        }

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }

        Item item = last.item;

        numberOfItems--;

        if (isEmpty()) {
            last = null;
        } else {
            last = last.previousNode;
            last.nextNode = null;
        }

        return item;
    }

    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {

        private Node current;

        private LinkedListIterator() {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
           if (!hasNext()) {
               throw new NoSuchElementException("There are no more items.");
           }
           Item item = current.item;
           current = current.nextNode;

           return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public static void main(String[] args) {

        Deque<String> deque = new Deque<>();

        System.out.println("deque is empty:" + deque.isEmpty());
        System.out.println("number of items is:" + deque.numberOfItems);

        deque.addFirst("be");
        deque.addFirst("to");

        deque.addLast("or");
        deque.addLast("not");

        System.out.println("deque is empty:" + deque.isEmpty());
        System.out.println("number of items is:" + deque.numberOfItems);

        for (String item:deque) {
            System.out.println("elements in queue:" + item);
        }

        System.out.println("removed item from the front:" + deque.removeFirst());
        System.out.println("number of items is:" + deque.numberOfItems);

        System.out.println("removed item from the end:" + deque.removeLast());
        System.out.println("number of items is:" + deque.numberOfItems);

        for (String item:deque) {
            System.out.println("elements in queue:" + item);
        }

    }

}