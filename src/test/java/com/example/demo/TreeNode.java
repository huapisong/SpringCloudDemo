package com.example.demo;

public class TreeNode {
    public int data;
    public TreeNode left, right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // 中序遍历（LDR）是二叉树遍历的一种，也叫做中根遍历、中序周游。在二叉树中，中序遍历首先遍历左子树，然后访问根结点，最后遍历右子树。
    public static void inOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        } else {
            inOrderTraversal(node.left);
            System.out.println(node.data);
            inOrderTraversal(node.right);
        }
    }

    /**
     * 方法名称：delete()
     * 方法描述：删除结点
     *
     * @param采用递归的方式进行删除
     * @returnString
     * @Exception
     */
    private void deleteNode(TreeNode p) {
        //TODOAuto-generatedmethodstub
        if (p != null) {
            //如果结点有左子树
        /*1。若p有左子树，找到其左子树的最右边的叶子结点r，用该叶子结点r来替代p，把r的左孩子
        作为r的父亲的右孩子。
        2。若p没有左子树，直接用p的右孩子取代它。
        */
            if (p.right != null) {
                TreeNode r = p.right;
                TreeNode prev = p.right;
                while (r.right != null) {
                    prev = r;
                    r = r.right;
                }
                p.data = r.data;
                //若r不是p的左子树,p的左子树不变，r的左子树作为r的父结点的右孩子结点
                if (prev != r) {
                    prev.right = r.right;
                } else {
                    //若r是p的左子树，则p的左子树指向r的左子树
                    p.right = r.right;
                }
            } else {
                p = p.right;
            }
        }
    }
}
