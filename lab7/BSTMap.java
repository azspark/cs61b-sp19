import java.security.Key;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private class Node {
        private K key;
        private V value;
        private int size;
        private Node left;
        private Node right;
        private Node (K k, V v, int s) {
            key = k;
            value = v;
            size = s;
        }
    }

    private Node root;

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null");
        }
        return getHelper(key, root);
    }

    private V getHelper(K key, Node node) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return getHelper(key, node.left);
        } else {
            return getHelper(key, node.right);
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null");
        }
        root = putHelper(key, value, root);
    }

    private Node putHelper(K key, V value, Node node) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = putHelper(key, value, node.left);
        } else if (cmp > 0) {
            node.right = putHelper(key, value, node.right);
        } else {
            node.value = value;
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public Set<K> keySet() {
        Set keySet = new HashSet<K>();

        for (Iterator<K> it = iterator(); it.hasNext(); ) {
            K k = it.next();
            keySet.add(k);
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        V value = get(key);
        if (value != null) {
            root = removeHelper(root, key);
        }
        return value;
    }

//    @Override
//    public V remove(K key) {
//        if (key == null) {
//            throw new IllegalArgumentException("Key can't be null");
//        }
//        if (root!= null && root.key.compareTo(key) == 0) {
//            Node toBeRemovedNode = root;
//            root = candidateNode(toBeRemovedNode);
//            if (toBeRemovedNode.left == null || toBeRemovedNode.right == null) {
//                return toBeRemovedNode.value;
//            }
//            root.left = toBeRemovedNode.left;
//            root.right = toBeRemovedNode.right;
//            return toBeRemovedNode.value;
//        }
//        return removeHelper(key, root);
//    }

    @Override
    public V remove(K key, V value) {
        V valueGet = get(key);
        if (value.equals(valueGet)) {
            root = removeHelper(root, key);
        }
        return valueGet;
    }

    private Node removeHelper(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeHelper(node.left, key);
        } else if (cmp > 0) {
            node.right = removeHelper(node.right, key);
        } else{
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node removed = node;
            node = candidateNode(removed); // find predecessor
            node.left = removeHelper(node.left, removed.key); // new left subtree will be without removed node
            node.right = removed.right;
        }

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

//    private V removeHelper(K key, Node node) {
//        if (node == null) {
//            return null;
//        }
//
//        if (node.left != null && node.left.key.compareTo(key) == 0) {
//            Node toBeRemovedNode = node.left;
//            node.left = candidateNode(toBeRemovedNode);
//            if (toBeRemovedNode.left == null || toBeRemovedNode.right == null) {
//                return toBeRemovedNode.value;
//            }
//            node.left.left = toBeRemovedNode.left;
//            node.left.right = toBeRemovedNode.right;
//            return toBeRemovedNode.value;
//        } else if (node.right != null && node.right.key.compareTo(key) == 0) {
//            Node toBeRemovedNode = node.right;
//            node.right = candidateNode(toBeRemovedNode);
//            if (toBeRemovedNode.right == null || toBeRemovedNode.left == null) {
//                return toBeRemovedNode.value;
//            }
//            node.right.left = toBeRemovedNode.left;
//            node.right.right = toBeRemovedNode.right;
//            return toBeRemovedNode.value;
//        }
//
//        int cmp = key.compareTo(node.key);
//        if (cmp < 0) {
//            return removeHelper(key, node.left);
//        } else {
//            return removeHelper(key, node.right);
//        }
//    }

//    private void removeProcess

    /**
     * Return predecessor of given node
     * Used when deleting a node with two child
     * @param node
     * @return
     */
    private Node candidateNode(Node node) {
        //In my last version of implementation, I try to
        // use this method to deal with all situation,
        // that algorithm works but not elegant and
        // did not not handle size update.
//        if (node.left == null && node.right == null) {
//            return null;
//        }
//        if (node.left == null) {
//            return node.right;
//        } else if (node.right == null) {
//            return node.left;
//        }
        Node curNode = node.left;
//        Node lastNode = node;
        while (curNode.right != null) {
            curNode = curNode.right;
        }
//        remove(curNode.key);
        return curNode;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {

        private Stack<Node> stack;
        public BSTMapIterator() {
            stack = new Stack<Node>();
            Node curNode = root;
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public K next() {
            Node tempNode = stack.pop();
            K returnKey = tempNode.key;
            if (tempNode.right != null) {
                tempNode = tempNode.right;
                stack.push(tempNode);
                tempNode = tempNode.left;
                while (tempNode != null) {
                    stack.push(tempNode);
                    tempNode = tempNode.left;
                }
            }
            return returnKey;
        }
    }

    public void printInOrder() {
        for (Iterator<K> it = iterator(); it.hasNext(); ) {
            K k = it.next();
            System.out.print(k + " ");
        }
        System.out.print("\n");
    }
}
