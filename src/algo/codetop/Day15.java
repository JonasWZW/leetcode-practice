package algo.codetop;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.zip.CRC32;

public class Day15 {
    public String decodeString(String s) {
        //"3[a]2[bc]"

        // 存放[ 左边的数字
        Stack<Integer> ns = new Stack<>();
        // 存放[ 左边的以及进行过解码的字符串
        Stack<String> ss = new Stack<>();
        // 当前数字
        int cur = 0;
        // 当前字符串
        String curString = "";
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                // 数字直接累加
                cur = 10 * cur + c - '0';
            } else if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                // 字符直接加
                curString += c;
            } else if (c == '[') {
                // 把数字和字符串直接压入栈
                ns.push(cur);
                ss.push(curString);
                // 重置
                cur = 0;
                curString = "";
            } else if (c == ']') {

                Integer multi = ns.pop();
                String str = ss.pop();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < multi; i++) {
                    sb.append(curString);
                }
                //拼接
                ss.push(str + sb.toString());
                curString = str + sb.toString();
            }
        }

        return curString;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric2(root.left, root.right);
    }

    private boolean isSymmetric2(TreeNode left, TreeNode right) {

        if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null && right == null) {
            return true;
        }

        return left.val == right.val && isSymmetric2(left.left, right.right) && isSymmetric2(left.right, right.left);
    }

    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        travelCombinationSum(candidates, target, 0, 0);
        return ans;
    }

    private void travelCombinationSum(int[] candidates, int target, int sum, int startIndex) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            path.add(candidates[i]);
            travelCombinationSum(candidates, target, sum + candidates[i], i);
            path.removeLast();
        }

    }

    public static void main(String[] args) {
        Day15 day15 = new Day15();
        System.out.println(day15.decodeString("2[abc]3[cd]ef").equals("abcabccdcdcdef"));
    }
}
