public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[4];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[4];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
        for(int i = 0; i < other.size(); i++) {
            addLast((T)other.get(i));
        }
    }

    public T get(int index) {
        if(index < 0 || index >= size) {
            return null;
        }
        int virtualIndex = index + 1 + nextFirst;
        if (virtualIndex > items.length-1) {
            int trueIndex = virtualIndex - items.length;
            return items[trueIndex];
        } else {
            return items[virtualIndex];
        }
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        size += 1;

        if (nextFirst != 0) {
            nextFirst = nextFirst - 1;
        } else {
            nextFirst = items.length - 1;
        }

        if(size == items.length) {
            resize(size * 2);
        }
    }

    public void addLast(T item) {
        items[nextLast] = item;
        size += 1;

        if (nextLast + 1 != items.length) {
            nextLast += 1;
        } else {
            nextLast = 0;
        }

        if(size == items.length) {
            resize(size * 2);
        }
    }

    public void removeFirst() {
        if(size == 0){
            return;
        }
        if(nextFirst != items.length-1){
            nextFirst += 1;
        } else {
            nextFirst = 0;
        }
        size -= 1;
        if(size < items.length*0.25) {
            resize(size * 2);
        }
    }

    public void removeLast() {
        if(size ==0){
            return;
        }
        if(nextLast != 0){
            nextLast -= 1;
        } else {
            nextLast = items.length - 1;
        }
        size -= 1;
        if(size < items.length*0.25) {
            resize(size * 2);
        }
    }

    private void resize(int targetSize) {
        T[] newItems = (T[]) new Object[targetSize];

        if(nextLast - nextFirst == size + 1) {
            System.arraycopy(items, nextFirst+1, newItems, 0, size);
        } else {
            int rightSize = items.length - nextFirst - 1;
            if(rightSize != 0){
                System.arraycopy(items, nextFirst+1, newItems, 0, rightSize);
            }
            System.arraycopy(items, 0, newItems, rightSize, size - rightSize);
        }
        nextFirst = newItems.length - 1;
        nextLast = size;
        items = newItems;
    }

    public int getItemsLength() {
        return items.length;
    }

}
