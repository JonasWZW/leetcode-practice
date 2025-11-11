package algo.codetop;

import javax.sound.midi.Soundbank;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: jonas
 * @CreateTime: 2025-11-11  22:07
 * @Description: TODO
 * @Version: 1.0
 */
public class Day11 {
    /**
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。
     * <p>
     * 函数 myAtoi(string s) 的算法如下：
     * <p>
     * 空格：读入字符串并丢弃无用的前导空格（" "）
     * 符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
     * 转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
     * 舍入：如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被舍入为 −231 ，大于 231 − 1 的整数应该被舍入为 231 − 1 。
     * 返回整数作为最终结果。
     */
    public int myAtoi(String s) {
        int i = 0;
        int ans = 0;
        int sign = 1;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        int startIndex = i;
        int limit1 = Integer.MAX_VALUE / 10;
        int y1 = Integer.MAX_VALUE % 10;
        int limit2 = Integer.MIN_VALUE / 10;
        int y2 = Integer.MIN_VALUE % 10;
        while (i < s.length()) {
            int n = s.charAt(i) - '0';
            if (i == startIndex && s.charAt(i) == '-') {
                sign = -1;
            } else if (i == startIndex && s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                if (ans > limit1 || ans == limit1 && sign * n > y1) {
                    return Integer.MAX_VALUE;
                }
                if (ans < limit2 || ans == limit2 && sign * n < y2) {
                    return Integer.MIN_VALUE;
                }

                ans = 10 * ans + sign * n;
            } else {
                break;
            }
            i++;
        }
        return ans;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode n1 = l1, n2 = l2;
        int c = 0;
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (n1 != null || n2 != null) {
            int v1 = n1==null?0:n1.val;
            int v2 = n2==null?0:n2.val;
            int sum = v1 + v2 + c;
            cur.next = new ListNode(sum %10);
            c = sum / 10;
            if (n1!=null)
                n1 = n1.next;
            if (n2!=null)
                n2 = n2.next;
            cur = cur.next;
        }
        if (c!=0){
            cur.next = new ListNode(c);
        }
        return dummy.next;
    }
}
