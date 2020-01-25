import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private double maxLoadFactor;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        maxLoadFactor = MAX_LF;
        this.clear();
    }


    public MyHashMap(int initialSize) {
        buckets = new ArrayMap[initialSize];
        maxLoadFactor = MAX_LF;
        this.clear();
    }

    public MyHashMap(int initialSize, double maxLoadFactor) {
        buckets = new ArrayMap[initialSize];
        this.maxLoadFactor = maxLoadFactor;
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null");
        }
        int bucketIndex = hash(key);
        return buckets[bucketIndex].containsKey(key);
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null");
        }
        int bucketIndex = hash(key);
        return buckets[bucketIndex].get(key);
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int newSize) {
        ArrayMap<K, V>[] oldBuckets = buckets;
        buckets = new ArrayMap[newSize];
        clear();
        for (ArrayMap<K, V> row: oldBuckets) {
            Iterator<K> it = row.iterator();
            while (it.hasNext()) {
                K key = it.next();
                put(key, row.get(key));
            }
        }
    }

    @Override
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value can't be null");
        }
        int bucketIndex = hash(key);
        if (!containsKey(key)) {
            size += 1;
        }
        buckets[bucketIndex].put(key, value);
        if (loadFactor() > maxLoadFactor) {
            resize(buckets.length * 2);
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();

        for (ArrayMap<K, V> row: buckets) {
            keySet.addAll(row.keySet());
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null");
        }
        int bucketIndex = hash(key);
        return  buckets[bucketIndex].remove(key);
    }

    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null");
        }
        int bucketIndex = hash(key);
        return  buckets[bucketIndex].remove(key, value);
    }


    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
