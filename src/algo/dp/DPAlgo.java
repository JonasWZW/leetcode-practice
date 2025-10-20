package algo.dp;

public class DPAlgo {

    /**
     * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
     * 返回 你可以获得的最大乘积 。
     * <p>
     * 示例 1:
     * 输入: n = 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1。
     * <p>
     * 示例 2:
     * 输入: n = 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
     */
    public int integerBreak(int n) {
        // dp[i] 代表 为i时，拆分出来的整数乘积的最大值。
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max(i * (i - j), i * dp[i - j]));
            }
        }
        return dp[n];
    }

    /**
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     * <p>
     * 输入：n = 3
     * 输出：5
     * <p>
     * 输入：n = 1
     * 输出：1
     */
    public int numTrees(int n) {
        // dp[i] 代表 i个节点，组成的二叉搜索树数量
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // 不用关系搜索二叉树的数字和其他的，
                // dp[j]代表左子树， dp[i-j-1]代表右子树
                // 不用关心他们怎么排序，数字怎么弄
                // dp[3] = dp[0]*dp[2] + dp[1]*dp[1] + dp[2]*dp[0]
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    public static void printDp(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

    }
}
