package algo.codetop;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: jonas
 * @CreateTime: 2025-11-04  22:59
 * @Description: TODO
 * @Version: 1.0
 */
public class Day9 {
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            ans.add(q.getLast().val);
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return ans;
    }

    public int compareVersion(String version1, String version2) {
        int i1 = 0, j1 = 0;
        int i2 = 0, j2 = 0;
        while (j1 < version1.length() || j2 < version2.length()) {
            while (j1 < version1.length() && version1.charAt(j1) != '.') {
                j1++;
            }

            while (j2 < version2.length() && version2.charAt(j2) != '.') {
                j2++;
            }
            int num1 = 0;
            if (i1 < version1.length()) {
                num1 = Integer.parseInt(version1.substring(i1, j1));
            }
            i1 = j1 + 1;
            j1 = i1;
            int num2 = 0;
            if (i2 < version2.length()) {
                num2 = Integer.parseInt(version2.substring(i2, j2));
            }
            i2 = j2 + 1;
            j2 = i2;
            System.out.println(num1 + "\t" + num2);
            if (num1 > num2) {
                return 1;
            } else if (num1 < num2) {
                return -1;
            }
        }
        return 0;
    }

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) >> 1;
            int v = nums[mid];
            if (v == target) {
                return mid;
            } else if (v > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        System.out.println(cur.toString());
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    public static void main(String[] args) {
        Day9 day9 = new Day9();
//        System.out.println(day9.compareVersion("1.0", "1.0.0.0.0"));
        day9.generateParenthesis(3);
    }
}


class MyQueue {
    LinkedList<Integer> s1;
    LinkedList<Integer> s2;
    int size;

    public MyQueue() {
        s1 = new LinkedList<Integer>();
        s2 = new LinkedList<Integer>();
        size = 0;
    }

    public void push(int x) {
        s2.push(x);
        size++;
    }

    public int pop() {
        size--;
        if (!s1.isEmpty()) {
            return s1.pop();
        }
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        return s1.pop();
    }

    public int peek() {
        if (!s1.isEmpty()) {
            return s1.getFirst();
        }
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        return s1.getFirst();
    }

    public boolean empty() {
        return size == 0;
    }
}