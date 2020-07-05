package data.struct.tree.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}
public class Test {
	/**
	 * 查找树的最大深度
	 * @param root
	 * @return
	 */
    public int maxDepth(TreeNode root) {
    	if(root == null){
    		return 0;
    	}
    	// 先求出左树最大深度，再求出右树最大深度，取最大的深度，加上自己，就是最大深度
    	int leftMaxDepth = maxDepth(root.left);
    	int rightMaxDepth = maxDepth(root.right);
    	return (leftMaxDepth > rightMaxDepth ? leftMaxDepth : rightMaxDepth) + 1;
    }
    
    /**
     * 树的最小深度
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
    	if(root == null){
    		return 0;
    	}
    	// 1，先求出左树最小深度，再求出右树最小深度，取最小的深度，加上自己，就是最小深度
    	int leftMinDepth = minDepth(root.left);
    	int rightMinDepth = minDepth(root.right);
    	
    	// ************注意点***************
    	// 2，如果当前节点有一边，没有树，那么高度应该按另一边算，而不能按没有的算
    	// 如果当前节点左右两边均没有子树，那当前节点高度就是0 + 1
    	// 如果左右子树都有，选高度低的
    	if(leftMinDepth == 0){
    		return rightMinDepth + 1;
    	}
    	if(rightMinDepth == 0){
    		return leftMinDepth + 1;
    	}
    	return (leftMinDepth < rightMinDepth ? leftMinDepth : rightMinDepth) + 1;
    }
	 
    /**
     * 交换左右树节点
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
        	return root;
        }
        
        // 如果左子树或右子树是空树，也需要交换
    	TreeNode tem = root.left;
    	root.left = root.right;
    	root.right = tem;
    	
        invertTree(root.left);
        invertTree(root.right);
        
        return root;
    }
    

    
    /**
     * 一个数组结构转为二叉树结构
     * 	借助队列实现
     * @param arr
     * @return
     */
    public static TreeNode transArrToTree(Integer[] arr){
    	if(arr.length == 0){
    		return null;
    	}
    	// 1，先设置根节点，借助队列结构，每次取出队列头结点，设置左右子树
    	TreeNode root =  new TreeNode(arr[0]);    		
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.add(root);
    	for(int i = 1; i < arr.length; i += 2){
    		//2，队列头结点为当前需要添加左右节点的当前节点
    		TreeNode curNode = queue.poll();
    		if(arr[i] != null){
    			curNode.left = new TreeNode(arr[i]);
    			// 3，新加的节点，为以后需要设置左右孩子的，也放到队列里
    			queue.add(curNode.left);
    		}
    		if(i + 1 < arr.length && arr[i + 1] != null){
    			curNode.right = new TreeNode(arr[i + 1]);
    			queue.add(curNode.right);
    		}
    	}
    	return root;
    }
    
    /**
     * 二叉树结构数组输出，广度优先输出
     * 	借助队列实现
     * @param root
     * @return
     */
    //        1
    //     2      3
    //   3   4
    // 
    //
    public static List<Integer> transTreeToArr(TreeNode root){
    	List<Integer> list = new ArrayList<>();
    	// 借助队列结构
    	Queue<TreeNode> queue = new LinkedList<>();
    	
    	if(root == null){
    		return list;
    	}
    	// 1，队列先存储根节点
    	queue.add(root);
    	
    	while(!queue.isEmpty()){
    		// 2，取出队列中头节点，作为数组下一个元素，判断左右子树，放到队列中
    		TreeNode curNode = queue.poll();
    		list.add(curNode.val);
    		if(curNode.left != null){
    			queue.add(curNode.left);
    		}
    		if(curNode.right != null){
    			queue.add(curNode.right);
    		}
    	}
    	return list;
    }
    
    /**
     * 判断两个树是否相同
     * 	通过递归判断, 如果两棵树相同, 左子树也会相同, 右子树也会相同
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
    	// 1,相同，肯定到了空节点了，确定是相同的，不用再判断孩子了
    	if(p == q){
    		return true;
    	}
    	// 2，如果当前节点不同，比较值
    	if(p != null && q != null && p.val == q.val){
    		// 2.1当前值相同，比较左右孩子
        	if(!isSameTree(p.left, q.left)){
        		return false;
        	}
        	if(!isSameTree(p.right, q.right)){
        		return false;
        	}
        	// 如果左右孩子都相同，是相同的树
        	return true;
    	} else{
    		// 2.2当前值不同，不是相同的树
        	return false;
    	}
    }
    
    /**
     * 判断树上有没有和为sum的路径
     * 	递归判断, 从root判断路径和为sum
     * 			从root.left, root.right判断路径和为sum-root.val
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
    	// 1,如果root为空，表示没有找到符合的叶子节点
    	if(root == null){
    		return false;
    	}
    	
    	// 2,终止的一定是一个叶子节点；且叶子节点值为当前需要的sum值
        if(root.left == null && root.right == null){
        	return root.val == sum;
        }
        
        //3，判断左右子树
        if(hasPathSum(root.left, sum - root.val)){
        	return true;
        }
        if(hasPathSum(root.right, sum - root.val)){
        	return true;
        }
        
        return false;
    }
    
    /**
     * 查找所有二叉树路径
     * 	递归方式实现
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
    	List<String> list = new ArrayList<>();
    	
    	// 1,如果到了空节点，返回空路径集合
    	if(root == null){
    		return list;
    	}
    	
    	// 2,如果是根节点，终止
        if(root.left == null && root.right == null){
        	list.add(String.valueOf(root.val));
        	return list;
        }
        
        // 3,如果不是根节点，求出左边的路径，加上当前节点，求出右边的路径，加上当前节点
        List<String> lefts = binaryTreePaths(root.left);
        for(String leftPath : lefts){
        	list.add(root.val + "->" + leftPath);
        }
        List<String> rights = binaryTreePaths(root.right);
        for(String rightPath : rights){
        	list.add(root.val + "->" + rightPath);
        }
        return list;
    }
    
    //=================================================================
    /**
     * 查找所有和为sum的路径(需要从每个节点作为初始节点, 都判断一下路径, 而不是仅从头结点判断)
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
    	// 如果root为null，肯定没有符合的路径
        if(root == null){
        	return 0;
        }
        
        // 1,查找当前节点为根节点，所有符合的记录
        int res = findPath(root, sum);
        
        // 2,查找不包含当前节点，符合的记录
        //（实际上就是遍历所有节点，对每个节点做findPath操作，最后求出符合的记录数）
        res += pathSum(root.left, sum);
        res += pathSum(root.right, sum);
        
        return res;
    }
    /**
     * 在以node为根节点的路径中（包含Node）；和为sum
     * @param node
     * @param num
     * @return
     */
    private int findPath(TreeNode node, int num){
    	// 找到了nude为空，就是没有符合，且不用继续找了，返回0
    	if(node == null){
    		return 0;
    	}
    	int res = 0;
    	// 1,找到一条；但是如果后边存在和为0的节点，仍属于路径，需要继续找
    	if(node.val == num){
    		res += 1;
    	}
    	
    	// 2,继续寻找左子树和右子树，看有没有符合的
    	res += findPath(node.left, num - node.val);
    	res += findPath(node.right, num - node.val);
    	
    	return res;
    }
    //=============================================================
    
    /**
     * 假设给定p，q都在树中，且不为空, 找他们的最小公共祖先
     * 这是二分查找树
     * 
     * p, q的祖先肯定大于p的值, 小于q的值 
     *
     * 
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
        	return null;
        }
        
        // root比p和q都大，在左子树找公共祖先
        if(root.val > p.val && root.val > q.val){
        	return lowestCommonAncestor(root.left, p, q);
        }
        // root比p和q都小，在右子树找公共祖先
        if(root.val < p.val && root.val < q.val){
        	return lowestCommonAncestor(root.right, p, q);
        }
        
        // q，p在root两边，后q，p存在一个元素值等于root
        return root;
    }
    
    public static void main(String[] args) {
//		TreeNode node = new Test().invertTree(transArrToTree(new Integer[]{4,2,7,1,3,6,9}));
//		System.out.println(transTreeToArr(node));
    	
//    	new Test().isSameTree(p, q);
	}
}
