package es.datastructur.synthesizer;
import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test (expected = RuntimeException.class)
    public void dequeNullTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        try {
            arb.dequeue();
        }
        catch (RuntimeException re) {
            String message = "Ring buffer underflow";
            assertEquals(message, re.getMessage());
            throw re;
        }
        fail("Ring buffer underflow did not throw");
    }

    @Test
    public void randomInputTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        Random random = new Random();
        int[] arr = new int[5];
        int i = 0;
        while (i < 10) {
            for(int j = 0; j < 5; j++) {
                int x = random.nextInt();
                arb.enqueue(x);
                arr[j] = x;
            }
            for(int j = 0; j < 5; j++) {
                assertEquals(Integer.valueOf(arr[j]), arb.dequeue());
            }
            i++;
        }
    }

    @Test (expected = RuntimeException.class)
    public void testOverFlow() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        Random random = new Random();
        try {
            for(int i = 0; i < 6; i++) {
                arb.enqueue(random.nextInt());
            }
        }
        catch (RuntimeException re) {
            String message = "Ring buffer overflow";
            assertEquals(message, re.getMessage());
            throw re;
        }
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        Random random = new Random();
        int[] arr = new int[5];
        for(int j = 0; j < 5; j++) {
            int x = random.nextInt();
            arb.enqueue(x);
            arr[j] = x;
        }
        Iterator arbIterator = arb.iterator();
        for (int i = 0; i < 5; i++) {
            assertTrue(arbIterator.hasNext());
            assertEquals(Integer.valueOf(arr[i]), arbIterator.next());
        }
        assertFalse(arbIterator.hasNext());
    }

    @Test
    public void testEquals() {
        ArrayRingBuffer<Integer> arb1 = new ArrayRingBuffer<>(10);
        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(10);
        Random random = new Random();
        for(int j = 0; j < 5; j++) {
            int x = random.nextInt();
            arb1.enqueue(x);
            arb2.enqueue(x);
        }

        assertTrue(arb1.equals(arb2));
        assertTrue(arb2.equals(arb1));

        arb2.enqueue(5);
        assertFalse(arb1.equals(arb2));
        assertFalse(arb2.equals(arb1));

        ArrayRingBuffer<Integer> arb3 = new ArrayRingBuffer<>(10);
        ArrayRingBuffer<Integer> arb4 = new ArrayRingBuffer<>(10);
        for(int j = 0; j < 5; j++) {
            int x = random.nextInt();
            arb3.enqueue(x);
            arb4.enqueue(x);
        }
        arb3.enqueue(4);
        arb4.enqueue(5);
        arb3.enqueue(9);
        arb4.enqueue(9);
        assertFalse(arb3.equals(arb4));
        assertFalse(arb4.equals(arb3));
    }
}
