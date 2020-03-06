import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int capacity;
    private int numberOfItems;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.capacity = 2;
        this.numberOfItems = 0;

        this.queue =  (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return numberOfItems == 0;
    }

    public int size() {
        return numberOfItems;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("An item must be specified by the user.");
        }

        queue[numberOfItems++] = item;

        if (numberOfItems >= capacity) {
            capacity = 2 * capacity;
            resizeArray();
        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }

        int itemPosition = StdRandom.uniform(numberOfItems);
        Item item = queue[itemPosition];

        queue[itemPosition] = queue[numberOfItems - 1];
        queue[numberOfItems - 1] = null;

        numberOfItems--;

        if (numberOfItems <= capacity/4) {
            capacity = capacity/2;
            resizeArray();
        }

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }

        return queue[StdRandom.uniform(numberOfItems)];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private final int[] itemPositions;
        private int current;

        ArrayIterator() {
            this.itemPositions = StdRandom.permutation(numberOfItems);
            this.current = 0;
        }

        private void checkConMod() {
            if (numberOfItems != itemPositions.length) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNext() {
            checkConMod();

            return current < numberOfItems;
        }

        @Override
        public Item next() {
            checkConMod();

            if (!hasNext()) {
                throw new NoSuchElementException("There are no items to return");
            }

            return queue[itemPositions[current++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resizeArray() {
        Item[] tmp = (Item[]) new Object[capacity];

        for (int i = 0; i < numberOfItems; i++) {
            tmp[i] = queue[i];
        }

        queue = tmp;
    }

    public static void main(String[] args) {

        RandomizedQueue<String> randQueue = new RandomizedQueue<>();

        System.out.println("queue is empty:" + randQueue.isEmpty());
        System.out.println("number of items is:" + randQueue.size());

        randQueue.enqueue("to");
        randQueue.enqueue("be");
        randQueue.enqueue("or");
        randQueue.enqueue("not");
        randQueue.enqueue("to");
        randQueue.enqueue("be");

        System.out.println("number of items is:" + randQueue.size());

        Iterator<String> i = randQueue.iterator();
        while (i.hasNext()) {
            System.out.println("elements in queue:" + i.next());
        }

        for (String item:randQueue) {
            //randQueue.dequeue();
            System.out.println("elements in queue:" + item);
        }

        System.out.println("removed item:" + randQueue.dequeue());
        System.out.println("removed item:" + randQueue.dequeue());
        System.out.println("number of items is:" + randQueue.size());

        System.out.println("an item in the queue is:" + randQueue.sample());

        for (String item:randQueue) {
            System.out.println("elements in queue:" + item);
        }

    }

}