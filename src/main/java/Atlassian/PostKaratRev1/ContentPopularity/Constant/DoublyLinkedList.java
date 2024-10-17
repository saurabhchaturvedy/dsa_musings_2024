package Atlassian.PostKaratRev1.ContentPopularity.Constant;

public class DoublyLinkedList {


    Node head;
    Node tail;


    DoublyLinkedList() {

        this.head = new Node(-1);
        this.tail = new Node(-1);

        this.head.next = tail;
        this.tail.prev = head;
    }


    public Node addToEnd(int contentId) {

        Node newNode = new Node(contentId);
        addNodeBefore(tail, newNode);

        return newNode;
    }


    public void moveToEnd(Node node) {

        remove(node);
        addNodeBefore(tail, node);

    }


    public void remove(Node node) {

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    public Node addNodeBefore(Node beforeNode, Node newNode) {


        newNode.prev = beforeNode.prev;
        newNode.next = beforeNode;
        beforeNode.prev.next = newNode;
        beforeNode.prev = newNode;

        return newNode;
    }
}
