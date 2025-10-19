package algo.dp;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TransferQueue;

// 背包问题
// 0 1 背包
// 完全背包
public class BagDPALgo {
    /**
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * 示例 1：
     * 输入：nums = [1,5,11,5]
     * 输出：true
     * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
     * 示例 2：
     * 输入：nums = [1,2,3,5]
     * 输出：false
     * 解释：数组不能分割成两个元素和相等的子集。
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        // dp[i][j] 代表，从0-i中任取元素，装进容量为j的背包，最后的背包里面物品的价值的最大值
        int[][] dp = new int[len][target + 1];
        for (int i = nums[0]; i <= target; i++) {
            dp[0][i] = nums[0];
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len - 1][target] == target;
    }


    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }
        System.out.println(sum);
        int target = sum / 2;
        int[][] dp = new int[stones.length][target + 1];

        for (int i = stones[0]; i <= target; i++) {
            dp[0][i] = stones[0];
        }

        for (int i = 1; i < stones.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= stones[i])
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        for (int i = 0; i < stones.length; i++) {
            for (int j = 0; j <= target; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }

        return Math.abs(sum - 2 * dp[stones.length - 1][target]);
    }

    public int findTargetSumWays(int[] nums, int target) {
        // n1 + n2 = sum
        // n1 - n2 = target
        // n1 = (sum + target) / 2
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if ((sum + target) % 2 == 1) {
            return 0;
        }
        int k = (sum + target) / 2;
        // 从nums中0-i任取，装满j背包的方案
        int[][] dp = new int[nums.length][k + 1];
//        for (int j = 0; j <= k; j++) {
//            if (j == nums[0])
//                dp[0][j] = 1;
//        }
        if (nums[0] <= k) {
            dp[0][nums[0]] = 1;
        }
        int zero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zero++;
            }
            dp[i][0] = (int) Math.pow(2, zero);
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= k; j++) {
                if (j >= nums[0]) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[nums.length - 1][k];
    }

    public int findMaxForm(String[] strs, int m, int n) {
        //dp[i][j][k] 表示 任取取0-i字符串，0的数量不超过j，1的数量不超过k的最大数量
        int[][][] dp = new int[strs.length][m + 1][n + 1];
        int zeroNum0 = getZeroNum(strs[0]);
        int OneNum0 = strs[0].length() - zeroNum0;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i >= zeroNum0 && j >= OneNum0) {
                    dp[0][i][j] = 1;
                }
            }
        }
        for (int i = 1; i < strs.length; i++) {
            int zeroNum = getZeroNum(strs[i]);
            int OneNum = strs[i].length() - zeroNum;
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= zeroNum && k >= OneNum) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeroNum][k - OneNum] + 1);
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }

        return dp[strs.length - 1][m][n];
    }

    public int getZeroNum(String s) {
        char[] chars = s.toCharArray();
        int zero = 0;
        for (char aChar : chars) {
            if (aChar == '0') {
                zero++;
            }
        }
        return zero;
    }

    public static void main(String[] args) {

    }
}
