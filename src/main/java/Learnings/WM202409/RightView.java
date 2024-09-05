package Learnings.WM202409;

import java.util.LinkedList;
import java.util.Queue;

public class RightView {


    public static void rightView(TreeNode root) {

        if (root == null) return;


        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);


        while (!queue.isEmpty()) {

            int size = queue.size();
            int i = 0;


            while (i++ < size) {


                TreeNode temp = queue.poll();


                if (i == size) {

                    System.out.print(temp.data + " ");
                }


                if (temp.left != null) {

                    queue.offer(temp.left);
                }


                if (temp.right != null) {

                    queue.offer(temp.right);
                }
            }
        }
    }


    public static void main(String[] args) {


        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);


        rightView(root);
    }
}
