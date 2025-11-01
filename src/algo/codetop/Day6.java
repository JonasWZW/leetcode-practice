package algo.codetop;

import com.sun.org.apache.bcel.internal.generic.DUP;

import java.util.*;

/**
 * @Author: jonas
 * @CreateTime: 2025-11-01  22:12
 * @Description: TODO
 * @Version: 1.0
 */
public class Day6 {
    public String addStrings(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        StringBuilder sb = new StringBuilder();
        int i = len1 - 1;
        int j = len2 - 1;
        int c = 0;
        while (i >= 0 || j >= 0) {
            int a = i >= 0 ? Integer.parseInt(String.valueOf(num1.charAt(i))) : 0;
            int b = j >= 0 ? Integer.parseInt(String.valueOf(num2.charAt(j))) : 0;
            int k = a + b + c;
            sb.append(String.valueOf(k % 10));
            c = k / 10;
            i--;
            j--;
        }
        if (c == 1) {
            sb.append(c);
        }
        return sb.reverse().toString();
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right)
            return lists[left];
        System.out.println(left + "\t" + right);
        //lists = []（空数组）
        if (left > right)
            return null;
        int mid = (left + right) >> 1;
        ListNode leftNode = merge(lists, left, mid);
        ListNode rightNode = merge(lists, mid + 1, right);
        return mergeTwoList(leftNode, rightNode);
    }

    private ListNode mergeTwoList(ListNode leftNode, ListNode rightNode) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (leftNode != null && rightNode != null) {
            int a = leftNode.val;
            int b = rightNode.val;
            if (a < b) {
                cur.next = leftNode;
                leftNode = leftNode.next;
            } else {
                cur.next = rightNode;
                rightNode = rightNode.next;
            }
            cur = cur.next;
        }
        cur.next = leftNode == null ? rightNode : leftNode;
        return dummy.next;
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            if (list.size() == 0 || list.get(list.size() - 1)[1] < left) {
                list.add(new int[]{left, right});
            } else {
                right = Math.max(right, list.get(list.size() - 1)[1]);
                list.get(list.size() - 1)[1] = right;
            }
        }
        return list.toArray(new int[list.size()][2]);
    }

    public static void main(String[] args) {
        Day6 day6 = new Day6();
        System.out.println(day6.addStrings("456", "577"));
    }
}
