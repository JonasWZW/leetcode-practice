package algo.codetop;

import javax.management.DynamicMBean;
import java.awt.font.NumericShaper;
import java.beans.beancontext.BeanContext;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: jonas
 * @CreateTime: 2025-10-28  21:15
 * @Description: TODO
 * @Version: 1.0
 */
public class Day2 {
    /**
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[2,1,4,3,5]
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dummy.next;
                }
            }
            ListNode next = tail.next;
            reverse(head, tail);
            pre.next = tail;
            head.next = next;

            pre = head;
            head = pre.next;
        }
        return dummy.next;
    }

    private void reverse(ListNode head, ListNode tail) {
        ListNode dummy = new ListNode();
        ListNode curr = head;
        ListNode end = tail.next;
        while (curr != end) {
            ListNode p = curr.next;
            curr.next = dummy.next;
            dummy.next = curr;
            curr = p;
        }
    }

    /**
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 解释：
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
     * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
     * 注意，输出的顺序和三元组的顺序并不重要。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // "泛型看外层，容器看内层"
        // 外层容器的继承关系可以传递，但内层泛型类型必须完全匹配。
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i <= nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> path = new ArrayList<>();
                    path.add(nums[i]);
                    path.add(nums[left]);
                    path.add(nums[right]);
                    ans.add(path);
                    while (right>left&&nums[left]== nums[left+1]){
                        left++;
                    }
                    while (right>left&&nums[right]== nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }


    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ans = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right)
            return;

        int p = partition(nums, left, right);
        quickSort(nums, left, p - 1);
        quickSort(nums, p + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int mid = left + (right - left) / 2;
        int val = nums[mid];
        swap(nums, mid, right);
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (nums[j] < val) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, right);
        return i + 1;
    }

    private void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}
