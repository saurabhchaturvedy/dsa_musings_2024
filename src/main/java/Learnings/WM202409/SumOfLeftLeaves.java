package Learnings.WM202409;

public class SumOfLeftLeaves {


    public static int sumOfLeftLeaves(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return dfs(root, false);

    }

    public static int dfs(TreeNode root, boolean isLeft) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return isLeft ? root.data : 0;
        }

        return dfs(root.left, true) + dfs(root.right, false);

    }


    public static void main(String[] args) {


        TreeNode treeNode = new TreeNode(10);

        treeNode.left = new TreeNode(20);
        treeNode.right = new TreeNode(30);
        treeNode.left.left = new TreeNode(50);
        treeNode.right.left = new TreeNode(60);


        System.out.println(" sum of left leaves = " + sumOfLeftLeaves(treeNode));
    }
}
