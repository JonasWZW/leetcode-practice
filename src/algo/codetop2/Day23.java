package algo.codetop2;


import algo.codetop.ListNode;

import java.util.*;

//24. 两两交换链表中的节点
//139. 单词拆分
//283. 移动零
//718. 最长重复子数组
//补充题6. 手撕堆排序
public class Day23 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = dummy;
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

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j,i))){
                    dp[i] = true ;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public void moveZeroes(int[] nums) {
        int l = 0;
        int r = 0;
        while (r < nums.length) {
            while (r < nums.length && nums[r] == 0) {
                r++;
            }
            if (r < nums.length) {
                nums[l++] = nums[r++];
            }
        }
        for (int i = l; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public int findLength(int[] nums1, int[] nums2) {
        // 定义dp[i]的含义
        int m = nums1.length;
        int n = nums2.length;
        int max = 0;
        //dp[i][j] 为 nums1中第i个结尾的子数组和nums2中第j个结尾的子数组，最长重复子数组的长度。
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }

    public int[] sortArray(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        quickSort(nums, 0, nums.length - 1);

        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(nums, left, right);
        quickSort(nums, left, p - 1);
        quickSort(nums, p + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        Random random = new Random();
        int index = random.nextInt(right - left) + left;
        int val = nums[index];
        swap(nums, index, right);
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (nums[j] < val) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, right);
        return i + 1;
    }

    private void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}
