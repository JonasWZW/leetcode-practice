package algo.codetop;

import algo.dp.DPAlgo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day3 {
    public static void main(String[] args) {
        Day3 day3 = new Day3();
//        System.out.println(day3.longestPalindrome("babad"));
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            int v1 = list1.val;
            int v2 = list2.val;
            if (v1 <= v2) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) {
            cur.next = list1;
        }
        if (list2 != null) {
            cur.next = list2;
        }
        return dummy.next;
    }

    public String longestPalindrome(String s) {
        int len = s.length();
        // dp[i][j] 表示[i,j]的子串是否为回文串，true表示是
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int ans = 0;
        String ansStr = "";
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                    if (j - i + 1 > ans) {
                        ans = j - i + 1;
                        ansStr = s.substring(i, j + 1);
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        DPAlgo.printDp(dp);
        System.out.println(ans);
        return ansStr;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = q.poll();
                list.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            ans.add(list);
        }
        return ans;
    }

    public int numIslands(char[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int ans = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    isLandDFS(grid, i, j);
                }
            }
        }
        return ans;
    }

    private void isLandDFS(char[][] grid, int i, int j) {
        grid[i][j] = '0';

        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            isLandDFS(grid, i - 1, j);
        }
        if (i + 1 < grid.length && grid[i + 1][j] == '1') {
            isLandDFS(grid, i + 1, j);
        }
        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            isLandDFS(grid, i, j - 1);
        }
        if (j + 1 < grid[0].length && grid[i][j + 1] == '1') {
            isLandDFS(grid, i, j + 1);
        }
    }

    public int search(int[] nums, int target) {
        // 二分的时候，如果一边有序，且target在有序的范围内，那么取之，否则取另外一边
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            if (nums[mid] <= nums[right]) {
                if (nums[right] >= target && target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
