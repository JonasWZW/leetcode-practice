package top200;


import algo.codetop.ListNode;
import algo.codetop.TreeNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class D4 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    public int minSubArrayLen(int target, int[] nums) {
        // 双指针，sum>=target就移动left
        int left = 0, right = 0;
        int sum = 0;
        int ans = nums.length + 1;
        while (right < nums.length) {
            sum += nums[right];
            right++;
            while (sum >= target && left < right) {
                ans = Math.min(ans, right - left);
                sum -= nums[left];
                left++;
            }
        }
        return ans == nums.length + 1 ? 0 : ans;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        cur.next = head;
        while (cur.next != null && cur.next.next != null) {
            ListNode p = cur.next;
            ListNode q = cur.next.next;
            p.next = q.next;
            q.next = p;
            cur.next = q;
            cur = p;
        }
        return dummy.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        while (head != null) {
            ListNode cur = head.next;
            int v = head.val;
            while (cur != null && v == cur.val) {
                cur = cur.next;
            }
            head.next = cur;
            head = cur;
        }
        return dummy.next;
    }

    // 单词拆分
    public boolean wordBreak(String s, List<String> wordDict) {
        // dp[i] 为 字符串s的前i个字符子串，是否能够由wordDict组成
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        // 前0个表示空串，为true
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
