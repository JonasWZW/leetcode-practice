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

    /**
     * babgbag  bag
     * 5个
     *
     * @param s
     * @param t
     * @return b   a   g
     * 1   0   0   0
     * b    1
     * a    1
     * b    1
     * g    1
     * b    1
     * a    1
     * g    1
     */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        DPAlgo.printDp(dp);
        return dp[s.length()][t.length()];
    }

    /**
     * abc
     * bbc
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        // dp[i][j] 表示，word1已第i个字符结尾的字符串和word2以第j个字符结尾的字符串达到相等的所需进行删除的次数。

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
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        DPAlgo.printDp(dp);

        return dp[word1.length()][word2.length()];
    }


    public int minDistance1(String word1, String word2) {
        // dp[i][j] 表示，word1已第i个字符结尾的字符串和word2以第j个字符结尾的字符串达到相等的所需进行删除的次数。

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
        DPAlgo.printDp(dp);

        return dp[word1.length()][word2.length()];
    }

    /**
     * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
     * 回文字符串 是正着读和倒过来读一样的字符串。
     * 子字符串 是字符串中的由连续字符组成的一个序列。
     * <p>
     * 示例 1：
     * 输入：s = "abc"
     * 输出：3
     * 解释：三个回文子串: "a", "b", "c"
     */
    public int countSubstrings(String s) {
        // dp[i][j] 表示从[i,j]的是否为回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    if (j - i <= 1) {
                        sum++;
                        dp[i][j] = true;
                    } else {
                        if (dp[i + 1][j - 1]) {
                            sum++;
                            dp[i][j] = true;
                        }
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        DPAlgo.printDp(dp);
        return sum;
    }

    /**
     * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
     * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     * 示例 1：
     * 输入：s = "bbbab"
     * 输出：4
     * 解释：一个可能的最长回文子序列为 "bbbb" 。
     */
    public int longestPalindromeSubseq(String s) {
        // dp[i][j]表示[i,j]取自s，最长回文子序列的长度

        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        DPAlgo.printDp(dp);
        return dp[0][s.length() - 1];
    }

    public static void main(String[] args) {
        SubArrayDPAlgo subArrayDPAlgo = new SubArrayDPAlgo();
//        System.out.println(subArrayDPAlgo.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
//        System.out.println(subArrayDPAlgo.findLengthOfLCIS(new int[]{1, 3, 5, 7}));
//        System.out.println(subArrayDPAlgo.findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7}));
//        System.out.println(subArrayDPAlgo.numDistinct("babgbag", "bag"));
//        System.out.println(subArrayDPAlgo.minDistance("leetcode", "etco"));

//        System.out.println(subArrayDPAlgo.countSubstrings("abcb"));
        System.out.println(subArrayDPAlgo.longestPalindromeSubseq("bbbab"));
    }
}
