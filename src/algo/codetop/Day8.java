package algo.codetop;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: jonas
 * @CreateTime: 2025-11-03  19:14
 * @Description: TODO
 * @Version: 1.0
 */
public class Day8 {
    /**
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     */
    public int minDistance(String word1, String word2) {
        //dp[i][j] 为 以word1的第i个字符，word2第j个字符结尾的单词，这俩个单子一致，需要进行增删改操作的最小数量
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }

        }
        return dp[word1.length()][word2.length()];
    }


    public int longestCommonSubsequence(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        int ans = 0;
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                ans = Math.max(ans, dp[i][j]);
            }

        }
        return ans;
    }

    int ans = 0;

    public int maxPathSum(TreeNode root) {
        travelMaxPath(root);
        return ans;
    }

    private int travelMaxPath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = travelMaxPath(root.left);
        int r = travelMaxPath(root.right);
        ans = Math.max(l, 0) + Math.max(r, 0) + root.val;

        return root.val + Math.max(Math.max(l, 0), Math.max(r, 0));
    }

    ArrayList<String> list = new ArrayList<String>();
    LinkedList<String> path = new LinkedList<>();

    public List<String> restoreIpAddresses(String s) {
        travelIpAddresses(s, 0);
        return list;
    }

    private void travelIpAddresses(String s, int index) {
        if (path.size() == 4 && index >= s.length()) {
            String join = String.join(".", path);
            list.add(join);
            return;
        }
        if (path.size() > 4) {
            return;
        }

        for (int i = index; i < s.length(); i++) {
            String numStr = s.substring(index, i + 1);
            if (isIPV4(numStr)) {
                path.add(numStr);
                travelIpAddresses(s, i + 1);
                path.removeLast();
            }
        }
    }

    private boolean isIPV4(String numStr) {
        if (numStr.charAt(0) == '0' && numStr.length() > 1) {
            return false;
        }
        if (numStr.length() > 3) {
            return false;
        }
        return Integer.parseInt(numStr) <= 255;
    }

    // 重复的保留一个
/*    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        while (head!=null && head.next!=null){
            if (head.val != head.next.val){
                head = head.next;
            }else {
                int v = head.val;
                ListNode cur = head;
                while (cur.next!=null){
                    if (cur.next.val == v){
                        cur = cur.next;
                    }
                }
                head.next = cur;
            }
        }
        return dummy.next;
    }*/
    // 重复的一个不留
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        head = dummy;
        while (head.next != null && head.next.next != null) {
            if (head.next.val != head.next.next.val) {
                head = head.next;
            } else {
                int v = head.next.val;
                ListNode cur = head.next;
                while (cur != null && cur.val == v) {
                    cur = cur.next;
                }
                head.next = cur;
            }
        }
        return dummy.next;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                ListNode cur = head;
                while (cur != slow) {
                    cur = cur.next;
                    slow = slow.next;
                }
                return cur;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Day8 day8 = new Day8();
        System.out.println(day8.minDistance("intention", "execution"));
    }
}

