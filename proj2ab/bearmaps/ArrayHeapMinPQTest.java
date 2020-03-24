package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Random;

public class ArrayHeapMinPQTest {

    @Test
    public void testGetSmallest1() {
        ArrayHeapMinPQ<Integer> a1 = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> aN = new NaiveMinPQ<>();

        for (int i = 0; i < 1000; i++) {
            a1.add(i, i);
            aN.add(i, i);
            assertEquals(aN.getSmallest(), a1.getSmallest());
            assertEquals(aN.size(), a1.size());
        }
        for (int i = 0; i < 1000; i++) {
            assertTrue(a1.contains(i));
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(aN.removeSmallest(), a1.removeSmallest());
            assertEquals(aN.size(), a1.size());
        }
    }

    @Test
    public void testGetSmallest2() {
        ArrayHeapMinPQ<Integer> a1 = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> aN = new NaiveMinPQ<>();

        for (int i = 1000; i >= 0; i--) {
            a1.add(i, i);
            aN.add(i, i);
            assertEquals(aN.getSmallest(), a1.getSmallest());
            assertEquals(aN.size(), a1.size());
        }
        for (int i = 1000; i >= 0; i--) {
            assertTrue(a1.contains(i));
        }
        for (int i = 1000; i >= 0; i--) {
            assertEquals(aN.removeSmallest(), a1.removeSmallest());
            assertEquals(aN.size(), a1.size());
        }
    }

    @Test
    public void randomizeTestGetSmallest() {
        for (int i = 0; i < 100; i++) {
            Random rand = new Random(i);
            ArrayHeapMinPQ<Integer> a1 = new ArrayHeapMinPQ<>();
            NaiveMinPQ<Integer> aN = new NaiveMinPQ<>();
            rand.nextDouble();
            int size = 0;
            for (int j = 0; j < 300; j++) {
                double p = rand.nextDouble();
                if (size == 0 || p > 0.4) {
                    int insertItem = rand.nextInt();
                    double insertPriority = rand.nextDouble();
                    a1.add(insertItem, insertPriority);
                    aN.add(insertItem, insertPriority);
                    assertEquals(aN.getSmallest(), a1.getSmallest());
                    assertTrue(a1.size() == aN.size());
                } else {
                    a1.removeSmallest();
                    aN.removeSmallest();
                    assertEquals(aN.getSmallest(), a1.getSmallest());
                    assertTrue(a1.size() == aN.size());
                }
            }
        }
    }

    @Test
    public void randomChangePriorityTest() {
        for (int i = 0; i < 500; i++) {
            Random rand = new Random(i);
            ArrayHeapMinPQ<Integer> a1 = new ArrayHeapMinPQ<>();
            NaiveMinPQ<Integer> aN = new NaiveMinPQ<>();
            int size = rand.nextInt(300) + 100;
            for (int j = 0; j < size; j++) {
                double addPriority = rand.nextDouble();
                a1.add(j, addPriority);
                aN.add(j, addPriority);
            }
            int changeItem = rand.nextInt(size);
            double changePriority = rand.nextDouble();
            a1.changePriority(changeItem, changePriority);
            aN.changePriority(changeItem, changePriority);
            for (int j = 0; j < size; j++) {
                assertEquals(a1.removeSmallest(), aN.removeSmallest());
                assertEquals(a1.size(), aN.size());
            }
        }
    }
}
