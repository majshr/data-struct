package data.struct.linkedlist;

/**
 * 单向链表
 * @author bhz（maj）
 * @since 2019年7月8日
 */
public class SingleLinkedListQuestion {
	
	/**
	 * 链表Node节点结构
	 * @since 2019年7月8日
	 */
	static class Node {
		int val;
		Node next;
		public Node(int val, Node next){
			this.val = val;
			this.next = next;
		}
		
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			Node cur = this;
			while(cur != null){
				sb.append(cur.val + "    ");
				cur = cur.next;
			}
			
			return sb.toString();
		}
	}
	
	private Node head;
	
	@Override
	public String toString() {
		return head == null ? "" : head.toString();
	}
	
	/**
	 * ************************通过遍历链表获取链表长度***********************************
	 */
	public int getLenByTraversal() {
		int len = 0;
		if(head != null) {
			Node node = head;
			while(node != null) {
				len++;
				node = node.next;
			}
		}
		return len;
	}
	
	/**
	 * 翻转链表，遍历一遍链表，
	 * 	头插法：每遍历到一个元素，用头插方式，指向前边的节点
	 */
	public void reverseListHeadInsert(){
		if(head == null){
			return;
		}
		
		Node newHead = null;
		Node cur = head;
		while(cur != null){
			// 保留下一个访问节点
			Node next = cur.next;
			
			// 当前节点指向之前的节点，并设置新头部
			cur.next = newHead;
			newHead = cur;
			
			// 访问下一个
			cur = next;
		}
		
		head = newHead;
	}
	
	/**
	 * 打印链表
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 */
	public void printList() {
		if(head != null) {
			Node node = head;
			while(node != null) {
				System.out.print(node.val + "    ");
				node = node.next;
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		SingleLinkedListQuestion list = new SingleLinkedListQuestion();
		
		Node node1 = new Node(1, null);
		Node node2 = new Node(2, node1);
		Node node3 = new Node(3, node2);
		Node node4 = new Node(4, node3);
		
		//链表为空
//		System.out.println(list.getLenByTraversal());
		
		//链表有值了
		list.head = node4;
		list.printList();
//		System.out.println(list.getLenByTraversal());
		list.reverseListHeadInsert();
		list.printList();
	}
	
	/**
	 ************************求单链表的倒数第k个节点*************************
	 * 	使用两个指针, 第一个指针正向先循环到第k个节点, 开始第二个指针, 此时两个指针距离为k, 
	 * 	两个指针同时向后走, 第一个指针到达结尾时, 第二个指针处节点为倒数第k个节点
	 * 
	 ************************求单链表中间的节点*****************************
	 * 使用两个指针, 一个每次走两个节点, 一个一次走一个节点; 第一个走到结尾节点时, 第二个就走到了中间节点
	 * 
	 ************************判断一个单链表中是否有环*****************************
	 * 	使用两个指针, 一个指针每次走两步, 一个指针每次走一步, 
	 * 	如果是没有环的, 快的指针先走到终点, 两个指针也不会相遇
	 * 	如果有环, 快指针走到环中, 与慢指针距离k, 快指针与慢指针每次距离缩小1, 最终会与慢指针相遇
	 * 
	 ************************判断两个链表是否相交*****************************
	 * 	如果两个单链表相交, 则相交点后续的节点都是相同的, 所以两个链表最后一个节点一定是相同的
	 * 	遍历第一个链表, 记录最后一个节点, 然后遍历第二个链表, 记录最后一个节点, 判断与第一个节点的是否相同
	 * 
	 ************************判断两个链表相交的第一个节点*****************************
	 * 	先遍历两个链表获取lenA和lenB, 并判断最后一个节点是否相同, 是否相交, 不相交, 直接返回
	 * 	假设长的为lenA, 不可能在lenA-lenB之前的节点相交, 那么之后的节点都相同, lenB不够长
	 * 	在lenA长度减去lenB长度之后, 两个链表长度就相同了, 使用两个指针, 分别遍历, 当指针相同时, 就是相交的节点
	 * 
	 * 1 2 3
	 *         4 5 6
	 *   2 3
	 ************************已知一个单链表存在环, 求进入环的第一个节点*****************************
	 * 	在环中的一个节点处断开（当然函数结束时不能破坏原链表），这样就形成了两个相交的单链表，
	 * 	求进入环中的第一个节点也就转换成了求两个单链表相交的第一个节点
	 * 	
	 */
	
	/**
	 * ************************倒着打印链表******************************
	 * (1)递归遍历链表, 访问到节点后在进行打印
	 * (2)遍历链表, 将链表节点放到栈中, 在打印栈
	 */
	
	/**
	 *************给出一单链表头指针pHead和一节点指针pToBeDeleted，O(1)时间复杂度删除节点pToBeDeleted*********************
	 * 	链表中每个节点结构一样, 可以把该节点下个节点的数据复制到该节点, 删除下一个节点即可
	 * 	但如果该节点是最后一个节点(pToBeDeleted == null), 还是需要遍历到倒数第二个, 设置为空, 但总的说时间复杂度还是O(1)
	 */
	

}
