package aaron.javaramp;


public class Node<T> {
    T val;
    Node<T> next = null;
    Node<T> prev = null;

    public Node(T val) {
        this.val = val;
    }
}
