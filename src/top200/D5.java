package top200;


import algo.codetop.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class D5 {

    public TreeNode invertTree(TreeNode root) {
        // 后续遍历，空节点返回null。然后交换左右子节点，此时就会完全全部的翻转
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode l = invertTree(root.left);
        TreeNode r = invertTree(root.right);
        root.left = r;
        root.right = l;
        return root;
    }

    public int findLength(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        // dp[i][j] 表示nums1前i个组成的数组 和 nums2前j个组成的数组，的最长公共子数组的长度。
        int ans = 0;
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    public void moveZeroes(int[] nums) {
        // 双指针，找到第一个不为0的，覆盖给i，此时i j后移一位。
        // 完成后，若i为越界，i后面的都置为0
        int i = 0, j = 0;
        while (j < nums.length) {
            while (j < nums.length && nums[j] == 0) {
                j++;
            }
            if (j >= nums.length) {
                break;
            }
            nums[i++] = nums[j++];
        }
        while (i < nums.length) {
            nums[i++] = 0;
        }
    }

    public int majorityElement(int[] nums) {
        // 数字可以分为两批，一批数量一半以上，且一样。
        int ans = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                ans = nums[i];
            }
            if (ans == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return ans;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int sum = 0;
        //adjoinList[i][j] 表示i是j的前置课程
        LinkedList<List<Integer>> adjoinList = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            adjoinList.add(new ArrayList<>());
        }

        int[] needCourses = new int[numCourses];
        // 可直接学习的课程编号
        LinkedList<Integer> q = new LinkedList<>();
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preCourse = prerequisite[1];
            List<Integer> list = adjoinList.get(preCourse);
            list.add(course);
            needCourses[course]++;
        }
        // 将不需要前置课的课程，加入q中
        for (int i = 0; i < needCourses.length; i++) {
            if (needCourses[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            // 遍历可以直接学习的课程。
            Integer noNeedPreCourse = q.poll();
            sum++;
            // 获取前置课程为noNeedPreCourse的课程list
            List<Integer> list = adjoinList.get(noNeedPreCourse);
            for (Integer c : list) {
                // 减少需要学习的课程数量。当为0时，那么可以直接学习，则加入q
                needCourses[c]--;
                if (needCourses[c] == 0) {
                    q.offer(c);
                }
            }
        }

        return sum == numCourses;
    }

}
