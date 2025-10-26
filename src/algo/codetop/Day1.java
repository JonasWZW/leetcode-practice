package algo.codetop;

import java.util.HashMap;


public class Day1 {
    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
     * <p>
     * 示例 1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();
        int l = 0;
        int r = 0;
        int ans = 0;
        char[] arr = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        while (r < arr.length) {
            if (map.getOrDefault(arr[r], 0) == 0) {
                map.put(arr[r], 1);
                r++;
            } else {
                map.remove(arr[l]);
                l++;
            }
            ans = Math.max(ans, map.size());
        }
        return ans;
    }

    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode p = head;
        while (p != null) {
            ListNode post = p.next;
            p.next = dummy.next;
            dummy.next = p;
            p = post;
        }
        return dummy.next;
    }

    public int[] sortArray(int[] nums) {
        if (nums.length <= 1) return nums;
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int p = partition(nums, left, right);
        quickSort(nums, left, p - 1);
        quickSort(nums, p + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int mid = left + (right - left) / 2;
        int val = nums[mid];
        swap(nums, mid, right);
        int i = left;
        for (int j = left; j <= right; j++) {
            if (nums[j] < val) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        Day1 d = new Day1();
        System.out.println(d.lengthOfLongestSubstring("au"));
    }

}
