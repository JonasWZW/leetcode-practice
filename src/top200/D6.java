package top200;


import util.PrintUtil;

import java.util.LinkedList;

public class D6 {
    int[] tmp;

    public int[] sortArray(int[] nums) {
        tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        for (int l = 0; l < k; l++) {
            nums[left + l] = tmp[l];
        }
    }

    public int[] dailyTemperatures(int[] temperatures) {
        // 单调递减栈
        LinkedList<Integer> q = new LinkedList<>();
        int[] ans = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!q.isEmpty() && temperatures[q.getLast()] < temperatures[i]) {
                Integer index = q.pollLast();
                ans[index] = i - index;
            }
            q.offerLast(i);
        }
        PrintUtil.print1(ans);
        return ans;
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        int size = word.length();
        boolean[][] used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, used, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, boolean[][] used, String word, int index, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || used[i][j]) {
            return false;
        }
        if (word.charAt(index) != board[i][j]) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        used[i][j] = true;
        for (int k = 0; k < direction.length; k++) {
            int nx = direction[k][0] + i;
            int ny = direction[k][1] + j;
            if (dfs(board, used, word, index + 1, nx, ny)) {
                return true;
            }
        }
        used[i][j] = false;
        return false;
    }


    public static void main(String[] args) {
        D6 d6 = new D6();
        d6.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
    }
}
