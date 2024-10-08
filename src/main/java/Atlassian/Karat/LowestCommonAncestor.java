package Atlassian.Karat;

public class LowestCommonAncestor {


    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) {

            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        TreeNode leftN = lowestCommonAncestor(root.left, p, q);
        TreeNode rightN = lowestCommonAncestor(root.right, p, q);

        if (leftN != null && rightN != null) {

            return root;
        }

        if (leftN != null) {

            return leftN;
        }

        return rightN;
    }


    public static void main(String[] args) {


        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);

        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(4);
        root.left.right.left = new TreeNode(7);

        root.right = new TreeNode(1);
        root.right.right = new TreeNode(8);
        root.right.left = new TreeNode(0);


        System.out.println(" lowest common ancestor is = "+lowestCommonAncestor(root,root.left,root.left.right.right).data);
    }
}
