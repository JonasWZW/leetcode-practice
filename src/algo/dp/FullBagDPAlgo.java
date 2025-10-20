package algo.dp;

import java.io.File;

/**
 * @Author: jonas
 * @CreateTime: 2025-10-19  14:07
 * @Description: TODO
 * @Version: 1.0
 */
public class FullBagDPAlgo {
    /**
     * 输入：amount = 5, coins = [1, 2, 5]
     * 输出：4
     * 解释：有四种方式可以凑成总金额：
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     */
    public int change(int amount, int[] coins) {
        // 0-i任取硬币，凑成j金额的最多方式。
        int[][] dp = new int[coins.length][amount + 1];

        for (int j = 1; j <= amount; j++) {
            if (j % coins[0] == 0) {
                dp[0][j] = 1;
            }
        }
//        for (int i = 0; i < coins.length; i++) {
//            dp[i][0] = 1;
//        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
//        DPAlgo.printDp(dp);

        return dp[coins.length - 1][amount];
    }


    public static void main(String[] args) {
        FullBagDPAlgo fullBagDPAlgo = new FullBagDPAlgo();
        fullBagDPAlgo.change(5, new int[]{1, 2, 5});
    }
}
