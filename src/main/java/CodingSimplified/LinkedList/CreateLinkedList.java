package CodingSimplified.LinkedList;

public class CreateLinkedList {


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(15, root);
        root = LinkedListUtils.insert(22, root);
        root = LinkedListUtils.insert(35, root);

        LinkedListUtils.print(root);
    }
}
