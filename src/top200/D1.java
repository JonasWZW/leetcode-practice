package top200;

import algo.codetop.TreeNode;
import algo.dp.DPAlgo;
import javafx.util.Pair;
import util.PrintUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @Author: jonas
 * @CreateTime: 2026-05-07  20:16
 * @Description: TODO
 * @Version: 1.0
 */
public class D1 {
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int h = 0, t = m - 1;
        while (h < t) {
            int[] tmp = matrix[h];
            matrix[h] = matrix[t];
            matrix[t] = tmp;
            h++;
            t--;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                int num = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = num;
            }
        }
        PrintUtil.print2(matrix);
    }

    public String largestNumber(int[] nums) {
        int length = nums.length;
        String[] numStr = new String[length];
        for (int i = 0; i < length; i++) {
            numStr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numStr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });
        if (numStr[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(numStr[i]);
        }
        return sb.toString();
    }

    public int widthOfBinaryTree(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> q = new LinkedList<>();
        q.offer(new Pair<>(root, 1));
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            Pair<TreeNode, Integer> first = q.getFirst();
            Pair<TreeNode, Integer> last = q.getLast();
            ans = Math.max(ans, last.getValue() - first.getValue() + 1);
            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> poll = q.poll();
                TreeNode node = poll.getKey();
                Integer index = poll.getValue();
                if (node.left != null) {
                    q.offer(new Pair<>(node.left, 2 * index));
                }
                if (node.right != null) {
                    q.offer(new Pair<>(node.right, 2 * index + 1));
                }
            }
        }
        return ans;
    }
}
