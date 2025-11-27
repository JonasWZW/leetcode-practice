package algo.codetop;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class Day20 {
    //162. 寻找峰值
    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    //14. 最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        String str = strs[0];
        int k = 0;
        while (k < str.length()) {
            char c = str.charAt(k);
            for (int i = 1; i < strs.length; i++) {
                if (k >= strs[i].length()) {
                    return str.substring(0, k);
                }
                char ci = strs[i].charAt(k);
                if (c != ci) {
                    return str.substring(0, k);
                }
            }
            k++;
        }
        return str;
    }

    //179. 最大数
    public String largestNumber(int[] nums) {
        String s = Arrays.stream(nums).mapToObj(String::valueOf).sorted((v1, v2) -> (v2 + v1).compareTo(v1 + v2)).collect(Collectors.joining());
        return s.charAt(0) == '0' ? "0" : s;
    }

    //113. 路径总和 II
    ArrayList<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        travelPathSum(root, targetSum, 0);
        return ans;
    }

    private void travelPathSum(TreeNode root, int targetSum, int sum) {
        if (root == null) {
            return;
        }
        // 收割节点
        sum += root.val;
        path.add(root.val);
        // 满足条件，直接加进去
        if (root.left == null && root.right == null && sum == targetSum) {
            ans.add(new ArrayList<>(path));
        }

        //遍历左右
        if (root.left != null) {
            travelPathSum(root.left, targetSum, sum);
        }
        if (root.right != null) {
            travelPathSum(root.right, targetSum, sum);
        }
        // 回溯
        sum -= root.val;
        path.removeLast();
    }

    //662. 二叉树最大宽度
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        // 使用下标法，根为x 左子节点为2*x 右子节点为2*x+1
        LinkedList<Pair<TreeNode, Integer>> q = new LinkedList<>();
        q.offer(new Pair<>(root, 1));

        while (!q.isEmpty()) {
            int size = q.size();
            Pair<TreeNode, Integer> first = q.getFirst();
            Pair<TreeNode, Integer> last = q.getLast();
            ans = Math.max(ans, last.getValue() - first.getValue() + 1);
            while (size > 0) {
                Pair<TreeNode, Integer> poll = q.poll();
                TreeNode node = poll.getKey();
                Integer index = poll.getValue();
                if (node.left != null) {
                    q.offer(new Pair<>(node.left, index * 2));
                }
                if (node.right != null) {
                    q.offer(new Pair<>(node.right, index * 2 + 1));
                }
                size--;
            }
        }
        return ans;
    }
}
