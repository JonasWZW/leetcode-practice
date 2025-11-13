package algo.codetop;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Day13 {
    public int firstMissingPositive(int[] nums) {
        // 从理想的结果，反推出思路和答案
        // 期望nums[i] 的值为 i+i
        // 1,2,3,4,.....n-2,n-1
        int n = nums.length;

        // 第一遍遍历：将每个数字放到正确的位置
        for (int i = 0; i < n; i++) {
            // 当 nums[i] 在 [1, n] 范围内，且不在正确位置上时，进行交换
            // 由于可能需要交换很多次，才能换到正确的值，也可能不满足[1,n]条件从而推出循环
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        // 第二遍遍历：找到第一个位置不匹配的数字
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // 如果所有位置都匹配，返回 n+1
        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    //输出: [3,9,20,null,null,15,7]
    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return travel(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode travel(int[] preorder, int pl, int pr, int[] inorder, int il, int ir) {
        if (pl > pr) return null;
        int val = preorder[pl];
        TreeNode node = new TreeNode(val);
        Integer index = map.get(val);
        int range = index - il;
        node.left = travel(preorder, pl + 1, pl + range, inorder, il, index - 1);
        node.right = travel(preorder, pl + range + 1, pr, inorder, index + 1, ir);
        return node;
    }

    public String reverseWords(String s) {
        // 双指针 + stack
        LinkedList<String> stack = new LinkedList<>();
        int i = 0;
        int j = 0;
        // j遍历字符串
        while (j < s.length()) {
            // 找到第一个不为' '的
            while (j < s.length() && s.charAt(j) == ' ') {
                j++;
            }
            i = j;
            //找到第一个为' '的或者j出界
            while (j < s.length() && s.charAt(j) != ' ') {
                j++;
            }
            //此时，需要 i在界内，且 i下标不为' '
            if (i < s.length() && s.charAt(i) != ' ')
                stack.push(s.substring(i, j));
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    public static void main(String[] args) {
        Day13 day13 = new Day13();
        System.out.println(day13.reverseWords(" a good   example "));
    }
}
