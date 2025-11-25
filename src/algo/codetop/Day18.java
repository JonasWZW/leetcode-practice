package algo.codetop;

public class Day18 {

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;

        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, travel(grid, i, j));
            }
        }
        return max;
    }

    private int travel(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || grid[i][j] == 0)
            return 0;
        grid[i][j] = 0;
        int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int ans = 1;
        for (int k = 0; k < direction.length; k++) {
            int ni = direction[k][0] + i;
            int nj = direction[k][1] + j;
            ans += travel(grid, ni, nj);
        }
        return ans;
    }

    boolean f = false;

    // dfs超时了
    public boolean searchMatrix(int[][] matrix, int target) {
        travelMatrix(matrix, 0, 0, target);
        return f;
    }

    private void travelMatrix(int[][] matrix, int i, int j, int target) {
        if (f || matrix[i][j] == target) {
            f = true;
            return;
        }
        if (matrix[i][j] > target) {
            return;
        }
        if (i >= matrix.length || j >= matrix[0].length) {
            return;
        }
        travelMatrix(matrix, i + 1, j, target);
        travelMatrix(matrix, i, j + 1, target);
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        // 向右旋转45°  酷似一颗二叉搜索树
        // 右上角为根节点，往左变小，往右变大
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                --y;
            } else {
                ++x;
            }
        }
        return false;
    }

}
