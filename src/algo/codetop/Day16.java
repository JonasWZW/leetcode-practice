package algo.codetop;

import algo.dp.DPAlgo;

public class Day16 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < grid[0].length; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        DPAlgo.printDp(dp);
        return dp[grid.length-1][grid[0].length-1];
    }

    public static void main(String[] args) {
        Day16 day16 = new Day16();
        int[][] ints = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(day16.minPathSum(ints));
    }
}
