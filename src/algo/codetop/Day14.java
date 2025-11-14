package algo.codetop;

import java.awt.image.renderable.RenderableImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Day14 {
    // 78. 子集
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        travel(ans, path, nums, 0);
        return ans;
    }

    private void travel(List<List<Integer>> ans, LinkedList<Integer> path, int[] nums, int index) {
        if (path.size() > nums.length) {
            return;
        }
        ans.add(new ArrayList<>(path));

        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            travel(ans, path, nums, i + 1);
            path.removeLast();
        }
    }

    //129. 求根节点到叶节点数字之和
    public int sumNumbers(TreeNode root) {
        int sum = 0;
        LinkedList<Integer> list = new LinkedList<>();
        travel(list, root, 0);
        for (Integer integer : list) {
            sum += integer;
        }
        return sum;
    }

    private void travel(LinkedList<Integer> list, TreeNode root, int current) {
        if (root == null) {
            return;
        }
        int newCurrent = 10 * current + root.val;

        if (root.left == null && root.right == null) {
            list.add(newCurrent);
            return;
        }
        if (root.left != null) {
            travel(list, root.left, newCurrent);
        }
        if (root.right != null) {
            travel(list, root.right, newCurrent);
        }
    }
    //34. 在排序数组中查找元素的第一个和最后一个位置
    //给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }

        // 查找左边界
        int left = findLeftBound(nums, target);
        // 如果左边界没找到，说明目标值不存在
        if (left == -1) {
            return result;
        }

        // 查找右边界
        int right = findRightBound(nums, target);

        result[0] = left;
        result[1] = right;
        return result;
    }

    // 查找左边界：第一个等于target的位置
    private int findLeftBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= target) {
                right = mid;  // 向左收缩
            } else {
                left = mid + 1;  // 向右收缩
            }
        }

        // 检查是否找到目标值
        return nums[left] == target ? left : -1;
    }

    // 查找右边界：最后一个等于target的位置
    private int findRightBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;  // 注意这里要+1，避免死循环

            if (nums[mid] <= target) {
                left = mid;  // 向右收缩
            } else {
                right = mid - 1;  // 向左收缩
            }
        }

        return nums[left] == target ? left : -1;
    }
}

//155. 最小栈
class MinStack {
    // s1 正常存储
    LinkedList<Integer> s1;
    // s2 为单调栈，栈顶为最小值
    LinkedList<Integer> s2;

    public MinStack() {
        s1 = new LinkedList<Integer>();
        s2 = new LinkedList<Integer>();
    }

    public void push(int val) {
        s1.push(val);
        if (s2.isEmpty()) {
            s2.push(val);
        } else {
            Integer peek = s2.peek();
            if (peek >= val) {
                s2.push(val);
            } else {
                s2.push(peek);
            }
        }
    }

    public void pop() {
        s1.pop();
        s2.pop();
    }

    public int top() {
        return s1.peek();
    }

    public int getMin() {
        return s2.peek();
    }
}
