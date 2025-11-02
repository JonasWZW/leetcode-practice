package algo.codetop;

import java.util.*;

/**
 * @Author: jonas
 * @CreateTime: 2025-11-02  21:38
 * @Description: TODO
 * @Version: 1.0
 */
public class Day7 {
    //    输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//    输出：[[1,6],[8,10],[15,18]]
//    解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        LinkedList<int[]> list = new LinkedList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (i == 0) {
                list.add(interval);
            } else {
                int[] last = list.get(list.size() - 1);
                if (interval[0] > last[1]) {
                    list.add(interval);
                } else {
                    int left = Math.min(last[0], interval[0]);
                    int right = Math.max(last[1], interval[1]);
                    list.removeLast();
                    list.add(new int[]{left, right});
                }
            }
        }
        return list.toArray(new int[list.size()][2]);
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode currA = headA;
        ListNode currB = headB;
        while (currA != currB) {
            currA = currA == null ? headB : currA.next;
            currB = currB == null ? headA : currB.next;
        }
        return currA;
    }

    public int trap(int[] height) {

        int[] left = new int[height.length];
        left[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        int[] right = new int[height.length];
        right[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            int h = Math.min(left[i],right[i]);
            sum += h - height[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Day7 day7 = new Day7();
        System.out.println(day7.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
