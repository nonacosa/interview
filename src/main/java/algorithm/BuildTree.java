package algorithm;

import java.util.Arrays;

/**
 * @author wenda.zhuang
 * @Date 2020/5/22 2:43 PM
 * @Description 105. 从前序与中序遍历序列构造二叉树
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * @E-mail sis.nonacosa@gmail.com
 */
public class BuildTree {

	public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }


    private static TreeNode treeNode ;
	public TreeNode buildTree(int[] preorder, int[] inorder) {

		if(preorder == null || inorder == null) return null;

		treeNode = new TreeNode(preorder[0]);
		int preRootIndex = 0;
		for (int i = 0; i < preorder.length; i++) {
			if(preorder[0] == inorder[i]) {
				preRootIndex = i;
			} else {

			}
		}
		int preRoot = preorder[preorder[0]];
		// pre 左子树
		int[] preLeft = Arrays.copyOf(preorder,preRootIndex );
		// pre 右子树
		int[] preRight = Arrays.copyOfRange(preorder,preRootIndex + 1,preorder.length);
		return null;
	}

	public static void main(String[] args) {
		new BuildTree().buildTree(new int[] {3,9,20,5,7,},new int[] {9,3,5,20,7,});
	}
}
