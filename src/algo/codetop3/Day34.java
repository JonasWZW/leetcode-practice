package algo.codetop3;

import algo.codetop.ListNode;
import algo.codetop.TreeNode;
import com.sun.org.apache.bcel.internal.generic.POP;

import java.util.HashMap;

public class Day34 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode l1 = head;
        ListNode l2 = head.next;
        ListNode h2 = l2;
        while (l2 != null && l2.next != null) {
            l1.next = l2.next;
            l1 = l1.next;
            l2.next = l1.next;
            l2 = l2.next;
        }
        l1.next = h2;
        return head;
    }

    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return travelTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode travelTree(int[] inorder, int i, int i1, int[] postorder, int j, int j1) {
        if (i > i1) {
            return null;
        }
        int val = postorder[j1];
        Integer index = map.get(val);
        int len = index - i;
        TreeNode node = new TreeNode(val);
        node.left = travelTree(inorder, i, index - 1, postorder, j, j + len - 1);
        node.right = travelTree(inorder, index + 1, i1, postorder, j + len, j1-1);
        return node;
    }
}
