package top200;

import algo.codetop.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: jonas
 * @CreateTime: 2026-05-09  20:31
 * @Description: TODO
 * @Version: 1.0
 */
public class D3 {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum, 0);
        return ans;
    }

    private void dfs(TreeNode root, int targetSum, int sum) {
        if (root == null || targetSum < sum) {
            return;
        }
        path.add(root.val);
        sum += root.val;
        if (root.left == null && root.right == null && targetSum == sum) {
            ans.add(new ArrayList<>(path));
        }

        if (root.left != null) {
            dfs(root.left, targetSum, sum);
        }
        if (root.right != null) {
            dfs(root.right, targetSum, sum);
        }
        path.removeLast();
    }

    public int uniquePaths(int m, int n) {
        // dp[i][j]表示，在下标位i，j的节点，可以有多少种方式到达。
        int[][] dp = new int[m][n];
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    public int rob(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        if (len <= 1) {
            return nums[len - 1];
        }
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[len - 1];
    }
}
