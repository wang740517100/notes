package cn.wangkf.monday;

/**
 * Created by stanley.wang on 2020/9/17.
 */
public class LinkedDemo<E> {

    private Node<E> first;

    private Node<E> last;

    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val, Node<E> prev, Node<E> next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    public boolean add(E val) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(val, l, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        return true;
    }

    public boolean remove(Object val) {
        if (val == null) {
            for (Node<E> curr = first; curr != null; curr = curr.next) {
                if (curr.val == null) {
                    removeNode(curr);
                    return true;
                }
            }
        } else {
            for (Node<E> curr = first; curr != null; curr = curr.next) {
                if (val.equals(curr.val)) {
                    removeNode(curr);
                    return true;
                }
            }
        }
        return false;
    }

    E removeNode(Node<E> curr) {
        final E element = curr.val;
        final Node<E> next = curr.next;
        final Node<E> prev = curr.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            curr.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            curr.next = null;
        }

        curr.val = null;

        return element;
    }



}
