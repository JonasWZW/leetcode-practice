package top200;

import algo.codetop.TreeNode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: jonas
 * @CreateTime: 2026-05-08  22:30
 * @Description:
 * @Version: 1.0
 */
public class D2 {


    int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 边数量 = node数量 - 1
        // 这个是关键，然后使用后序遍历，依次获取左右子节点的maxNode，最后比较即可。
        maxNode(root);
        return ans - 1;
    }

    public int maxNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);
        ans = Math.max(ans, left + right + 1);
        System.out.println(ans);
        return Math.max(left, right) + 1;
    }

    public int findPeakElement(int[] nums) {
        // 二分法，当mid > mid+1的时候，那么说明峰值在<=mid，否则峰值>=mid+1
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public int subarraySum(int[] nums, int k) {
        // 前缀和 + map<前缀和，数量>
        int prefixSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int time = 0;
        for (int num : nums) {
            prefixSum += num;
            if (map.containsKey(prefixSum - k)) {
                time += map.get(prefixSum - k);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return time;
    }
}
