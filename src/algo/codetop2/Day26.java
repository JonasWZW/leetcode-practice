package algo.codetop2;

import algo.codetop.ListNode;
import algo.codetop.TreeNode;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.*;

public class Day26 {
    // 153. 寻找旋转排序数组中的最小值
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }

    //47. 全排列 II
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        travelPermuteUnique(nums, used);

        return ans;
    }

    private void travelPermuteUnique(int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                path.add(nums[i]);
                used[i] = true;
                travelPermuteUnique(nums, used);
                used[i] = false;
                path.removeLast();
            }
        }
    }

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;  // 当前计算结果
        int num = 0;     // 当前数字
        int sign = 1;    // 当前符号：1 表示正，-1 表示负

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // 构建完整的数字
                num = num * 10 + (c - '0');
            } else if (c == '+') {
                // 遇到加号，将当前数字加到结果中
                result += sign * num;
                num = 0;        // 重置数字
                sign = 1;       // 设置下一个数字的符号为正
            } else if (c == '-') {
                // 遇到减号，将当前数字加到结果中
                result += sign * num;
                num = 0;        // 重置数字
                sign = -1;      // 设置下一个数字的符号为负
            } else if (c == '(') {
                // 遇到左括号，将当前结果和符号压栈
                stack.push(result);
                stack.push(sign);
                // 重置，开始计算括号内的表达式
                result = 0;
                sign = 1;
            } else if (c == ')') {
                // 遇到右括号，完成当前括号内的计算
                result += sign * num;  // 加上最后一个数字
                num = 0;

                // 括号前的符号
                int prevSign = stack.pop();
                // 括号前的结果
                int prevResult = stack.pop();

                // 合并结果：括号内的结果 * 括号前的符号 + 括号前的结果
                result = prevResult + prevSign * result;
                sign = 1;  // 重置符号（可选）
            }
            // 空格直接跳过
        }

        // 处理最后一个数字
        result += sign * num;

        return result;
    }

    //40. 组合总和 II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        boolean[] used = new boolean[candidates.length];
        Arrays.sort(candidates);
        travelSum2(candidates, target, 0, 0, used);
        return ans;
    }

    private void travelSum2(int[] candidates, int target, int sum, int startIndex, boolean[] used) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (!used[i]) {
                if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                    continue;
                }
                sum += candidates[i];
                used[i] = true;
                path.add(candidates[i]);
                travelSum2(candidates, target, sum, i + 1, used);
                path.removeLast();
                sum -= candidates[i];
                used[i] = false;
            }
        }
    }

    //55. 跳跃游戏
    public boolean canJump(int[] nums) {
        int limit = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i <= limit) {
                limit = Math.max(limit, i + nums[i]);
                if (limit >= n - 1) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    //11. 盛最多水的容器
    public int maxArea(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            int h = Math.min(height[i], height[j]);
            max = Math.max(max, h * (j - i));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }

    // 手撕归并排序
    int[] tmp;

    public int[] sortArray(int[] nums) {
        tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        int k = 0;
        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
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
    }

    //136. 只出现一次的数字
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }

    public ListNode rotateRight(ListNode head, int k) {
        //输入：head = [1,2,3,4,5], k = 2
        //输出：[4,5,1,2,3]
        if (head == null) {
            return null;
        }
        int len = 0;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        k = k % len;
        if (k == 0) {
            return dummy.next;
        }
        ListNode fast = dummy;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        ListNode slow = dummy;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        cur = slow.next;
        slow.next = null;
        fast.next = dummy.next;
        dummy.next = cur;
        return dummy.next;
    }

    public static void main(String[] args) {
        Day26 day26 = new Day26();
        int[] nums = {1, 3, 2, 5, 4, 7, 0};
        day26.sortArray(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }
}