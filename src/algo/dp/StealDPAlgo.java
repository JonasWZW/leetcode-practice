package algo.dp;

import javax.sound.midi.Soundbank;
import java.util.Arrays;

/**
 * @Author: jonas
 * @CreateTime: 2025-10-20  21:48
 * @Description: TODO
 * @Version: 1.0
 */
public class StealDPAlgo {
    /**
     * 输入：[1,2,3,1]
     * 输出：4
     */
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0],nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    public int rob2(int[] nums) {
        if (nums.length == 1) return nums[0];
        int[] n1 = new int[nums.length-1];
        int[] n2 = new int[nums.length-1];

        for (int i = 0; i <nums.length; i++) {
            if (i<nums.length-1)
                n1[i] = nums[i];
            if (i>0)
                n2[i-1] = nums[i];
        }
        for (int i = 0; i < n1.length; i++) {
            System.out.print(n1[i]+ "\t");
        }
        System.out.println();
        for (int i = 0; i < n2.length; i++) {
            System.out.print(n1[2]+ "\t");
        }
        System.out.println();
        int a1 = rob(n1);
        int a2 = rob(n2);
        return Math.max(a1,a2);
    }

    public static void main(String[] args) {
        StealDPAlgo stealDPAlgo = new StealDPAlgo();
        System.out.println(stealDPAlgo.rob2(new int[]{1, 2, 3, 1}));
    }
}
