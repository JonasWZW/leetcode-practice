package algo.codetop2;

import algo.codetop.TreeNode;

import java.util.HashMap;
import java.util.Map;


//62. 不同路径
//198. 打家劫舍
//152. 乘积最大子数组
//560. 和为K的子数组
//112. 路径总和
@SuppressWarnings("all")
public class Day21 {
    public int uniquePaths(int m, int n) {
        // dp[i][j] 为到达i,j处的最多方案
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    public int maxProduct(int[] nums) {
        // dp[i] i下标结尾的子数组的最大乘积
        int[] maxDp = new int[nums.length];
        int[] mimDp = new int[nums.length];
        maxDp[0] = nums[0];
        mimDp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxDp[i] = Math.max(Math.max(maxDp[i - 1] * nums[i], mimDp[i - 1] * nums[i]), nums[i]);
            mimDp[i] = Math.min(Math.min(maxDp[i - 1] * nums[i], mimDp[i - 1] * nums[i]), nums[i]);
            max = Math.max(maxDp[i], max);
        }
        return max;
    }

    //    public int subarraySum(int[] nums, int k) {
//        int sum = 0;
//        HashMap<Integer, Integer> map = new HashMap<>();
//        map.put(0, 1);
//        int[] preSum = new int[nums.length];
//        preSum[0] = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            preSum[i] = preSum[i - 1] + nums[i];
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (map.containsKey(preSum[i] - k)) {
//                sum += map.get(preSum[i] - k);
//            }
//            map.put(nums[i], map.getOrDefault(nums[i], 0));
//        }
//        return sum;
//    }
    public int subarraySum(int[] nums, int k) {
        // 哈希表：key-前缀和, value-该前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();

        // 初始化：前缀和为0出现1次（空数组的情况）
        prefixSumCount.put(0, 1);

        int count = 0;    // 满足条件的子数组个数
        int prefixSum = 0; // 当前前缀和

        for (int num : nums) {
            prefixSum += num; // 更新前缀和

            // 如果存在前缀和 = 当前前缀和 - k，说明找到了满足条件的子数组
            if (prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }

            // 更新当前前缀和的出现次数
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
