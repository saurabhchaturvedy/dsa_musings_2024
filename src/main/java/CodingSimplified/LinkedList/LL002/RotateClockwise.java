package CodingSimplified.LinkedList.LL002;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class RotateClockwise {



    public static ListNode rotateRight(ListNode head, int k) {

        if(head==null || k<0)
        {
            return head;
        }

        int size = ListSizeRecursive.size(head);

        k = k%size;

        if(k==0)
        {
            return head;
        }


        ListNode previousToRotationStart = head;
        int i=1;

        while(i<size-k)
        {
            previousToRotationStart = previousToRotationStart.next;
            i++;

        }

        ListNode rotationHead = previousToRotationStart.next;
        ListNode headNode = rotationHead;
        previousToRotationStart.next = null;


        while(rotationHead.next!=null)
        {

            rotationHead = rotationHead.next;
        }


        rotationHead.next = head;

        return headNode;

    }


    public static void main(String[] args) {

        ListNode root = null;
        root= LinkedListUtils.insert(1,root);
        root=LinkedListUtils.insert(2,root);
        root=LinkedListUtils.insert(3,root);
        root=LinkedListUtils.insert(4,root);
        root=LinkedListUtils.insert(5,root);

        LinkedListUtils.print(root);

        root = rotateRight(root,2);

        LinkedListUtils.print(root);
    }
}
