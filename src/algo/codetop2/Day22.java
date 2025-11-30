package algo.codetop2;

import algo.codetop.ListNode;
import algo.codetop.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

//227. 基本计算器 II
//83. 删除排序链表中的重复元素
//226. 翻转二叉树
//209. 长度最小的子数组
//169. 多数元素
public class Day22 {
    //输入：s = "3+2*2"
    //输出：7
    //-2-2*3+5-3/2
    // -1 5 -6 2
    public int calculate(String s) {
        int ans = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        int num = 0;  // 当前正在解析的数字
        char preSign = '+';  // 当前数字前的运算符，初始为'+'

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 如果是数字，构建完整的数字
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');  // 处理多位数
            }

            // 如果不是数字或者是最后一个字符，进行运算
            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                // 根据前一个运算符决定操作
                if (preSign == '+') {
                    stack.push(num);  // 正数直接入栈
                } else if (preSign == '-') {
                    stack.push(-num); // 负数入栈（用负数表示）
                } else if (preSign == '*') {
                    // 乘法：弹出栈顶，计算后重新入栈
                    stack.push(stack.pop() * num);
                } else if (preSign == '/') {
                    // 除法：弹出栈顶，计算后重新入栈
                    stack.push(stack.pop() / num);
                }

                // 更新运算符，重置数字
                preSign = c;
                num = 0;
            }
        }

        // 将栈中所有数字相加得到最终结果
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                int v = cur.val;
                ListNode t = cur;
                while (t != null && t.val == v) {
                    t = t.next;
                }
                cur.next = t;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode l = invertTree(root.left);
        TreeNode r = invertTree(root.right);
        root.right = l;
        root.left = r;
        return root;
    }

    public int minSubArrayLen(int target, int[] nums) {
        int min = nums.length + 1;
        int left = 0;
        int right = 0;
        int sum = 0;
        while (right < nums.length) {
            sum += nums[right];
            while (sum >= target) {
                sum -= nums[left];
                min = Math.min(min, right - left + 1);
                left++;
            }
            right++;
        }
        return min == nums.length + 1 ? 0 : min;
    }

    public int majorityElement(int[] nums) {
        int ans = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count==0){
                ans = nums[i];
            }
            if (ans==nums[i]){
                count++;
            }else {
                count--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Day22 day22 = new Day22();
        System.out.println(day22.calculate("42"));

    }
}
