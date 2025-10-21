package algo.dp;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author: jonas
 * @CreateTime: 2025-10-21  21:29
 * @Description: TODO
 * @Version: 1.0
 */
public class SubArrayDPAlgo {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) return 1;
        int ans = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                ans = Math.max(dp[i], ans);
            }
        }
//        DPAlgo.printDp1(nums);
//        DPAlgo.printDp1(dp);
        return ans;
    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 1) return 1;
        int ans = 1;
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                ans++;
            } else {
                max = Math.max(max, ans);
                ans = 1;
            }
        }
        return Math.max(max, ans);
    }

    /**
     * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
     * 输出：3
     * 解释：长度最长的公共子数组是 [3,2,1]
     */
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int max = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        DPAlgo.printDp(dp);
        return max;
    }

    /**
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        int ans = 0;

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        SubArrayDPAlgo subArrayDPAlgo = new SubArrayDPAlgo();
//        System.out.println(subArrayDPAlgo.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
//        System.out.println(subArrayDPAlgo.findLengthOfLCIS(new int[]{1, 3, 5, 7}));
        System.out.println(subArrayDPAlgo.findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7}));
    }
}
