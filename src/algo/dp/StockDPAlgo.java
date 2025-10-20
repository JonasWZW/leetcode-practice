package algo.dp;


public class StockDPAlgo {
    /**
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     */
    public int maxProfit(int[] prices) {
        // dp[i][0/1] 表示第i天，0持股，1不持股，身上的金钱数量
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], 0 - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        DPAlgo.printDp(dp);

        return dp[prices.length - 1][1];
    }

    public int maxProfit2(int[] prices) {
        // dp[i][0/1] 表示第i天，0持股，1不持股，身上的金钱数量
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        DPAlgo.printDp(dp);

        return dp[prices.length - 1][1];
    }

    public int maxProfit3(int[] prices) {
        // 第i天，0第一次持有，1第一次卖出，2第二次持有，3第二次卖出
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = -prices[0];
        dp[0][3] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = -prices[i];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
        }
        return dp[prices.length - 1][3];
    }

    public int maxProfit4(int k, int[] prices) {
        // 第i天，0第一次持有，1第一次卖出，2第二次持有，3第二次卖出
        int[][] dp = new int[prices.length][2 * k];
        for (int j = 0; j < 2 * k; j++) {
            if (j % 2 == 0) {
                dp[0][j] = -prices[0];
            }
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < 2 * k; j = j + 2) {
                if (j == 0)
                    dp[i][j] = Math.max(dp[i - 1][j], -prices[i]);
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] + prices[i]);
            }
        }

        return dp[prices.length - 1][2 * k - 1];
    }

    public static void main(String[] args) {
        StockDPAlgo stockDPAlgo = new StockDPAlgo();
        System.out.println(stockDPAlgo.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(stockDPAlgo.maxProfit3(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(stockDPAlgo.maxProfit4(2,new int[]{7, 1, 5, 3, 6, 4}));
    }

}
