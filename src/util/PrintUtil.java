package util;

/**
 * @Author: jonas
 * @CreateTime: 2026-05-07  20:21
 * @Description: TODO
 * @Version: 1.0
 */
public class PrintUtil {
    public static void print1(int[] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + "\t");
        }
        System.out.println();
    }

    public static void print2(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
