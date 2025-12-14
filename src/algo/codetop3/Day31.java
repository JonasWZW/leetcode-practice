package algo.codetop3;

import algo.codetop.TreeNode;
import sun.security.provider.Sun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day31 {
    public int candy(int[] ratings) {
        int[] scores = new int[ratings.length];
        int sum = 0;
        Arrays.fill(scores, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                scores[i] = scores[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                scores[i] = Math.max(scores[i], (scores[i + 1] + 1));
            }
        }
        for (int i = 0; i < scores.length; i++) {
            System.out.println(scores[i]);
            sum += scores[i];
        }
        return sum;
    }


    public int longestIncreasingPath(int[][] matrix) {
        int ans = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(matrix, m, n, nums, i, j));
            }
        }
        return ans;
    }

    int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private int dfs(int[][] matrix, int m, int n, int[][] nums, int i, int j) {
        // 获取在matrix数组中 下标为i,j的，以他为结尾的，最长长度
        if (nums[i][j] != 0) {
            return nums[i][j];
        }
        nums[i][j]++;
        for (int[] direct : direction) {
            int ni = i + direct[0];
            int nj = j + direct[1];
            if (ni >= 0 && nj >= 0 && ni < m && nj < n && matrix[ni][nj] > matrix[i][j]) {
                nums[i][j] = Math.max(nums[i][j], dfs(matrix, m, n, nums, ni, nj) + 1);
            }
        }

        return nums[i][j];
    }

    public List<Integer> findDuplicates1(int[] nums) {
        int[] n1 = new int[nums.length];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int k = nums[i];
            if (n1[k - 1] == k) {
                list.add(k);
            } else {
                n1[k - 1] = k;
            }
        }
        return list;
    }

    public List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 依次遍历数组，且取绝对值，将他和应该放入的位置比较
            // 如果该放入的位置 以为负数，则说明已经出现了，此值是重复的
            // 否则，就将该位置对应的值置为相反数
            // 使用正负作为标记为，正数代表没有使用过，负数代表已经有了。且取值的时候做绝对值。
            int k = Math.abs(nums[i]);
            if (nums[k - 1] < 0) {
                list.add(k);
            } else {
                nums[k - 1] = -nums[k - 1];
            }

        }
        return list;
    }

    int ans;
    int count;

    public int findTargetNode(TreeNode root, int cnt) {
        dfsTree(root, cnt);
        return ans;
    }

    private void dfsTree(TreeNode root, int cnt) {
        if (root == null) {
            return;
        }
        dfsTree(root.right, cnt);
        count++;
        if (count == cnt) {
            ans = root.val;
        }
        dfsTree(root.left, cnt);
    }

    public static void main(String[] args) {
        Day31 day31 = new Day31();
        System.out.println(day31.candy(new int[]{1, 2, 2}));
    }
}
