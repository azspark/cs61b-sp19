public interface Deque<T> {
    public void addFirst(T x);
    public void addLast(T y);
    public T removeLast();
    public T removeFirst();
    public T get(int i);
    public int size();

    default public boolean isEmpty() {
        return size() == 0;
    }
}
