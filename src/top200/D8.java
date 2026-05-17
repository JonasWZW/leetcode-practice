package top200;


import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.Arrays;
import java.util.Base64;
import java.util.BitSet;

public class D8 {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        while (left < right) {
            int w = right - left;
            ans = Math.max(ans, w * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }

    public boolean canJump(int[] nums) {
        int limit = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= limit) {
                limit = Math.max(limit, nums[i] + i);
                if (limit >= nums.length - 1) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int curStepLimit = 0;
        int time = 0;
        int limit = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + nums[i] >= nums.length - 1) {
                time++;
                return time;
            }
            limit = Math.max(limit, i + nums[i]);
            if (i == curStepLimit) {
                curStepLimit = limit;
                time++;
            }
        }
        return time;
    }

    public int threeSumClosest(int[] nums, int target) {
        int best = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int ans = nums[i] + nums[j] + nums[k];
                if (Math.abs(best - target) > Math.abs(ans - target)) {
                    best = ans;
                }
                if (target == ans) {
                    return ans;
                } else if (ans > target) {
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    k--;
                } else {
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    j++;
                }
            }
        }
        return best;
    }
}
