package algorithm;

import java.util.ArrayList;
import java.util.List;

import apple.laf.JRSUIUtils;

/**
 * @author wenda.zhuang
 * @Date 2021/4/17 下午4:42
 * @Description https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * @E-mail sis.nonacosa@gmail.com
 */
public class LevelOrder {

	  // Definition for a binary tree node.
	  public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode() {}
	      TreeNode(int val) { this.val = val; }
	      TreeNode(int val, TreeNode left, TreeNode right) {
	          this.val = val;
	          this.left = left;
	          this.right = right;
	      }
	  }

	  List<List<Integer>> levels = new ArrayList<>();

	  public void helper(TreeNode treeNode,int level) {
	  	 if(levels.size() == level) levels.add(new ArrayList<>());
	  	 levels.get(level).add(treeNode.val);

	  	 if(treeNode.left != null) helper(treeNode.left,level + 1);
	  	 if(treeNode.right != null) helper(treeNode.right,level + 1);
	  }

	  //递归
	  public List<List<Integer>> levelOrder(TreeNode root) {
		if(root == null) return levels;
		helper(root,0);
		return levels;

      }

	public static void main(String[] args) {
	}

}
