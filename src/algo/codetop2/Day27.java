package algo.codetop2;


import algo.codetop.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

public class Day27 {
    //16. 最接近的三数之和
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
                int sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum == target) {
                    return target;
                } else if (sum > target) {
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

    //958. 二叉树的完全性检验
    public boolean isCompleteTree(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean f = false;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                f = true;
            } else {
                if (f) {
                    return false;
                }
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        return true;
    }


    //LCR 170. 交易逆序对的总数
    int[] tmp;
    public int reversePairs(int[] nums) {
        tmp = new int[nums.length];
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int count = 0;
        count += mergeSort(nums, left, mid);
        count += mergeSort(nums, mid + 1, right);
        int k = 0;
        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
                count += mid - i + 1;
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        for (int l = 0; l < k; l++) {
            nums[l + left] = tmp[l];
        }
        return count;
    }
}
