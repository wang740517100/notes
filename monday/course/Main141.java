package cn.wangkf.monday.course;

/**
 * Created by stanley.wang on 2020/6/28.
 *
 * 判断一个链表是不是环形链表
 */

class Node141 {
    int num;

    Node141 next;
}

public class Main141 {

    public static void main(String[] args) {

    }

    /**
     * 如果当前元素的后两个 == 头元素的后一个
     * @param head
     * @return
     */
    public static boolean hasCycle(Node141 head) {
        Node141 slow = head;
        Node141 fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

}
