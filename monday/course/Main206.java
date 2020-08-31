package cn.wangkf.monday.course;

/**
 * Created by stanley.wang on 2020/6/28.
 *
 *
 *
 */
class Node206 {
    int num;

    Node206 next;
}
public class Main206 {



    public  Node206 reverseList(Node206 head) {
        Node206 newHead = null;
        return reverse(head, newHead);
    }


    Node206 reverse(Node206 head, Node206 newHead) {
        if (head == null) {
            return newHead;
        }
        Node206 next = head.next;
        head.next = newHead;
        newHead = head;
        head = next;
        return reverse(head, newHead);
    }



}
