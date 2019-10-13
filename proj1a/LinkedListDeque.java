public class LinkedListDeque<T> {

    private class TNode {
        public TNode prev;
        public T item;
        public TNode next;

        public TNode(T item, TNode prev, TNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        for(int i=0; i < other.size(); i++) {
            addLast((T)other.get(i));
        }
    }

    public void addFirst(T item) {
        TNode temp = sentinel.next;
        sentinel.next = new TNode(item, sentinel, sentinel.next);
        temp.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        TNode temp = sentinel.prev;
        sentinel.prev = new TNode(item, sentinel.prev, sentinel);
        temp.next = sentinel.prev;
        size += 1;
    }

    public T removeFirst() {
        TNode removed = sentinel.next;
        T first = get(0);
        sentinel.next = removed.next;
        removed.next.prev = sentinel;
        if(size > 0)
            size -= 1;
        return first;
    }

    public T removeLast() {
        TNode removed = sentinel.prev;
        T last = get(size - 1);
        sentinel.prev = removed.prev;
        removed.prev.next = sentinel;
        if(size > 0)
            size -= 1;
        return last;
    }

    public T get(int index) {
        if(index < 0 || index >= size) {
            return null;
        }
        TNode outNode = sentinel.next;
        while(index > 0) {
            outNode = outNode.next;
            index--;
        }
        return outNode.item;
    }

    public T getRecursive(int index) {
        if(index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int count, TNode recurNode) {
        if(count == 0){
            return recurNode.item;
        } else {
            return getRecursiveHelper(count-1, recurNode.next);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        TNode iter  = sentinel;
        while(iter.next != sentinel) {
            System.out.print(iter.next.item + " ");
            iter = iter.next;
        }
        System.out.println();
    }

    public int size() {
        return size;
    }
}
