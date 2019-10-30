package es.datastructur.synthesizer;
import jdk.jshell.spi.ExecutionControl;
import org.junit.Test;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.function.DoubleToIntFunction;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        fillCount = 0;
        first = 0;
        last = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (fillCount() == capacity()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        if (last != capacity() - 1) {
            last += 1;
        } else {
            last = 0;
        }
        fillCount += 1;

        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (fillCount() == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T returned = rb[first];
        if (first != capacity() - 1) {
            first +=1;
        } else {
            first = 0;
        }
        fillCount -= 1;
        return returned;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (fillCount() == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    @Override
    public boolean equals(Object o) {
        Iterator origin = iterator();
        Iterator compared = ((ArrayRingBuffer) o).iterator();
        while (origin.hasNext()) {
            if (!compared.hasNext()) {
                return false;
            }
            if (!origin.next().equals(compared.next())) {
                return false;
            }
        }
        if (compared.hasNext()) {
            return false;
        }
        return true;
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int usedSize;
        private int trueIndex;
        public ArrayRingBufferIterator() {
            trueIndex = first;
            usedSize = 0;
        }

        @Override
        public boolean hasNext() {
            return usedSize < fillCount;
        }

        @Override
        public T next() {
            T out = rb[trueIndex];
            if (trueIndex < capacity() - 1) {
                trueIndex += 1;
            } else{
                trueIndex = 0;
            }
            usedSize += 1;
            return out;
        }

    }
}
    // TODO: Remove all comments that say TODO when you're done.
