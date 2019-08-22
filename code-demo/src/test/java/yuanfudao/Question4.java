package yuanfudao;

/**
 * @author: ulei
 * @date: 2019-08-22
 */
public class Question4 {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        new Solution().ReverseList(a);
        while (e != null) {
            System.out.println(e.val);
            e = e.next;
        }
    }

    public static class Solution {
        public ListNode ReverseList(ListNode head) {
            ListNode pre = null;
            ListNode next = null;
            while (head != null) {
                // 保存要反转到头的那个节点
                next = head.next;
                // 要反转的那个节点指向已经反转的上一个节点(备注:第一次反转的时候会指向null)
                head.next = pre;
                // 上一个已经反转到头部的节点
                pre = head;
                // 一直向链表尾走
                head = next;
            }
            return null;
        }

    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
