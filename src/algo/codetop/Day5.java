package algo.codetop;

import com.sun.org.apache.bcel.internal.generic.LUSHR;

import java.util.*;

@SuppressWarnings("all")
public class Day5 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean f = true;
        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> dequeue = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (f) {
                    dequeue.offerLast(node.val);
                } else {
                    dequeue.offerFirst(node.val);
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            f = !f;
            ans.add(dequeue);
        }
        return ans;
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     */
    public int lengthOfLIS(int[] nums) {
        //dp[i] 以下标i结尾的子序列的最大长度
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

    /**
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        List<Integer> list = new ArrayList<>();
        int[][] direct = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int f = 0;
        int k = 0;
        int size = m * n;
        int x = 0;
        int y = 0;
        while (k < size) {
            list.add(matrix[x][y]);
            visited[x][y] = true;
            k++;
            int xx = x + direct[f][0];
            int yy = y + direct[f][1];
            if (xx < 0 || xx >= m || yy < 0 || yy >= n || visited[xx][yy]) {
                f = (f + 1) % 4;
            }
            x = x + direct[f][0];
            y = y + direct[f][1];
        }
        return list;
    }

    /**
     * 1 2 3 4
     * 4 3 2 1
     * <p>
     * 1 4 2 3
     * <p>
     * 1,2,3,4,5
     * 5,4,3,2,1
     * <p>
     * 1,5,2,4,3
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        ListNode mid = getMidOfList(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        System.out.println(mid.val);

        printList(l1);
        ListNode l3 = reverseList(l2);
        printList(l3);
        while (l1!=null && l3!=null){
            ListNode t1 = l1.next;
            ListNode t3 = l3.next;
            l1.next = l3;
            l3.next = t1;
            l1 = t1;
            l3 = t3;
        }
        printList(head);
    }

    private ListNode reverseList(ListNode l2) {
        ListNode dummy = new ListNode();

        while (l2!=null){
            ListNode t = l2.next;
            l2.next = dummy.next;
            dummy.next = l2;
            l2 = t;
        }
        return dummy.next;
    }
    public void printList(ListNode head){
        while (head!=null){
            System.out.print(head.val+"\t");
            head = head.next;
        }
        System.out.println();
    }

    private ListNode getMidOfList(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    public static void main(String[] args) {
        Day5 day5 = new Day5();
//        List<Integer> integers = day5.spiralOrder(new int[][]{
//                {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
//        });
//        System.out.println(integers);
        ListNode t = new ListNode(1);
        ListNode c = t;
        for (int i = 2; i <= 5; i++) {
            t.next = new ListNode(i);
            t =t.next;
        }
        day5.reorderList(c);
    }
}

