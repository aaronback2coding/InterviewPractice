package aaron.javaramp;


public class Main {

    public static void main(String[] args) {
        MyLinkedList<String> ll = new MyLinkedList<>();
        ll.add("a");
        ll.add("b");
        ll.add("c");
        ll.print();

        ll.printBackward4();


    }
}
