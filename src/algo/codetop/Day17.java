package algo.codetop;


import algo.dp.DPAlgo;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import javax.xml.stream.FactoryConfigurationError;
import java.io.File;
import java.util.*;

public class Day17 {

    boolean flag = true;

    // 110 平衡二叉树
    public boolean isBalanced(TreeNode root) {
        travelHigh(root);
        return flag;
    }

    private int travelHigh(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = travelHigh(root.left);
        int right = travelHigh(root.right);
        if (Math.abs(right - left) > 1)
            flag = false;
        return Math.max(left, right) + 1;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<TreeNode> s = new LinkedList<>();
        s.add(root);
        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            list.add(node.val);
            if (node.right != null) {
                s.push(node.right);
            }
            if (node.left != null) {
                s.push(node.left);
            }
        }
        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<TreeNode> s = new LinkedList<>();
        s.add(root);
        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            list.add(node.val);

            if (node.left != null) {
                s.push(node.left);
            }
            if (node.right != null) {
                s.push(node.right);
            }
        }
        Collections.reverse(list);
        return list;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<TreeNode> s = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !s.isEmpty()) {
            if (cur != null) {
                s.push(cur);
                cur = cur.left;
            } else {
                cur = s.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }

    //输入：nums = [100,4,200,1,3,2]
    //输出：4
    //解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
    public int longestConsecutive(int[] nums) {
        //128. 最长连续序列
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;

        for (int num : set) {// 遍历set而不是数组，避免重复元素
            if (!set.contains(num - 1)) {
                int k = 1;
                int n = num + 1;
                while (set.contains(n)) {
                    k++;
                    n++;
                }
                ans = Math.max(ans, k);
            }
        }

        return ans;
    }
    //48. 旋转图像
    public void rotate(int[][] matrix) {
        int l1 = 0;
        int l2 = matrix.length - 1;
        while (l1 < l2) {
            int[] matrix1 = matrix[l1];
            matrix[l1] = matrix[l2];
            matrix[l2] = matrix1;
            l1++;
            l2--;
        }
        DPAlgo.printDp(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                int k = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = k;
            }
        }
        System.out.println("===============");
        DPAlgo.printDp(matrix);
    }

    public static void main(String[] args) {
        Day17 day17 = new Day17();
        int[][] ans = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        day17.rotate(ans);
    }

}
