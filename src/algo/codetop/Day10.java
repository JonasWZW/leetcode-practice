package algo.codetop;

import algo.dp.DPAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @Author: jonas
 * @CreateTime: 2025-11-05  19:45
 * @Description: TODO
 * @Version: 1.0
 */
public class Day10 {
    //输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
    //输出：[3,3,5,5,6,7]
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < k - 1; i++) {
            while (!q.isEmpty() && nums[q.getLast()] < nums[i]) {
                q.removeLast();
            }
            q.offer(i);
        }
        for (int i = k - 1; i < nums.length; i++) {
            while (!q.isEmpty() && nums[q.getLast()] < nums[i]) {
                q.removeLast();
            }
            while (!q.isEmpty() && i - q.getFirst() >= k) {
                q.removeFirst();
            }
            q.offer(i);
            ans[i - k + 1] = nums[q.getFirst()];
        }

        return ans;
    }

    //输入：s = "(()"
    //输出：2
    //解释：最长有效括号子串是 "()"
    //
    //输入：s = ")()())"
    //输出：4
    //解释：最长有效括号子串是 "()()"
    // ()(()())
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        char[] array = s.toCharArray();
        int ans = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == ')') {
                if (array[i - 1] == '(') {
                    dp[i] = (i > 2 ? dp[i - 2] : 0) + 2;
                } else if (array[i - 1] == ')') {
                    if (i - dp[i - 1] - 1 >= 0 && array[i - dp[i - 1] - 1] == '(') {
                        dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                    }
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        DPAlgo.printDp1(dp);
        return ans;
    }

    public static void main(String[] args) {
        Day10 day10 = new Day10();
//        int[] ints = day10.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
//        for (int i = 0; i < ints.length; i++) {
//            System.out.print(ints[i] + "\t");
//        }
        System.out.println(day10.longestValidParentheses("()(()())"));
    }
}


