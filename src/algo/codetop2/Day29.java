package algo.codetop2;

import java.util.Arrays;

public class Day29 {
    //75. 颜色分类
    public void sortColors(int[] nums) {
        int[] ns = Arrays.copyOf(nums, nums.length);
        Arrays.fill(nums, 1);
        int i = 0;
        int j = nums.length - 1;
        for (int k = 0; k < ns.length; k++) {
            if (ns[k] == 0) {
                nums[i++] = ns[k];
            } else if (ns[k] == 2) {
                nums[j--] = ns[k];
            }
        }
    }

    //91. 解码方法
    public int numDecodings(String s) {
        int n = s.length();
        // dp[i]表示，第i个结尾的字符串的合法的解码个数
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            if (i > 1 && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26) && s.charAt(i - 2) != '0') {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    //50. Pow(x, n)
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        if (n < 0) {
            x = 1.0 / x;
            n = -n;
        }
        return quickPow(x, n);
    }

    private double quickPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double y = quickPow(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }

    public double myPow1(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        if (n < 0) {
            x = 1.0 / x;
            // int n 可能会溢出
            n = -n;
        }
        double ans = 1.0;
        double k = x;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans *= k;
            }
            k *= k;
            n = n >> 1;
        }
        return ans;
    }
}
