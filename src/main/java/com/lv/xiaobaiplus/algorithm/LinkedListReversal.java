package com.lv.xiaobaiplus.algorithm;

/**
 * @program: xiaobaiPlus
 * @ClassName LinkedListReversal
 * @description: 反转单链表
 * @author: gxjh
 * @create: 2024-12-07 17:51
 * @Version 1.0
 **/
public class LinkedListReversal {

    /**
     * 链表
     */


    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        printListNode(head);

        ListNode reverseHead = reverseList(head);

        printListNode(reverseHead);
    }

    //反转ListNode
    public static ListNode reverseList(ListNode head){

        ListNode prev = null;
        ListNode current = head;

        while(current != null) {
            //临时存储下一个节点
            ListNode nextTemp = current.next;
            //当前节点指向前一个节点
            current.next = prev;
            //将前一个节点移动到当前节点
            prev = current;
            //将当前节点移动到下一个节点
            current = nextTemp;

        }
        return prev;
    }

    //打印ListNode
    public static void printListNode(ListNode head) {
        ListNode current = head;
        while(current != null) {
            System.out.println(current.val + "");
            current = current.next;
        }

    }





}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

