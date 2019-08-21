package algorithm;

/**
 * @author: ulei
 * @date: 2019-08-14
 */
public class SinglyLinkedList<E> {
    private int size = 0;
    private Node<E> first;
    private Node<E> last;


    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    private void linkLast(E e) {
        final Node<E> f = this.first;
        final Node<E> l = last;
        Node<E> newNode = new Node<>(l, e);
        if (f == null) {
            this.first = newNode;
        } else if (l == null) {
            last = newNode;
            first.next = newNode;
        } else {
            last = newNode;
            l.next = newNode;
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    public E getFirst() {
        return first.item;
    }

    public E getLast() {
        return last.item;
    }


    private static class Node<E> {
        E item;
        Node<E> next;

        private Node(Node<E> next, E item) {
            this.next = next;
            this.item = item;
        }
    }


    public static void main(String[] args) {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        linkedList.add(8);
        System.out.println(linkedList.toString());


    }
}
