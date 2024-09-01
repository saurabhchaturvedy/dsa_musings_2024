package Learnings.WM202409;

import java.util.*;

public class AllNodesDistanceKBinaryTree {


    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Map<TreeNode, TreeNode> childParentMap = new HashMap<>();
        createChildParentMap(root, childParentMap);

        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(target);

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                TreeNode curr = queue.poll();

                visited.add(curr);

                if (k == 0) {
                    result.add(curr.data);
                }

                if (childParentMap.get(curr) != null && !visited.contains(childParentMap.get(curr))) {

                    queue.offer(childParentMap.get(curr));
                }

                if (curr.left != null && !visited.contains(curr.left)) {

                    queue.offer(curr.left);
                }

                if (curr.right != null && !visited.contains(curr.right)) {

                    queue.offer(curr.right);
                }
            }
            k--;
            if (k < 0)
                break;
        }

        return result;
    }

    public static void createChildParentMap(TreeNode root, Map<TreeNode, TreeNode> childParentMap) {

        if (root == null) {
            return;
        }

        if (root.left != null) {
            childParentMap.put(root.left, root);

        }

        if (root.right != null) {
            childParentMap.put(root.right, root);

        }

        createChildParentMap(root.left, childParentMap);
        createChildParentMap(root.right, childParentMap);
    }


    public static void main(String[] args) {


        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);


        System.out.println(" all nodes at distance K for node 5 = " + distanceK(root, root.left, 2));
    }
}
