package algo.codetop;


import java.util.LinkedList;

public class Day19 {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // dp[i][j] 为以 i ,j 为右下角的正方形的边的长度
        int[][] dp = new int[m][n];
        int maxEdge = 0;

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                // 边界情况，有就直接置为1
                maxEdge = 1;
            }
        }

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == '1') {
                dp[0][j] = 1;
                // 边界情况，有就直接置为1
                maxEdge = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - j][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                    maxEdge = Math.max(maxEdge, dp[i][j]);
                }
            }
        }
        return maxEdge * maxEdge;
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        LinkedList<TreeNode> s = new LinkedList<>();
        TreeNode cur = root;
        long pre = Long.MIN_VALUE;
        while (!s.isEmpty() || cur != null) {
            if (cur != null) {
                s.push(cur);
                cur = cur.left;
            } else {
                cur = s.pop();
                if (cur.val <= pre) {
                    return false;
                }
                pre = cur.val;
                cur = cur.right;
            }
        }
        return true;
    }

    int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        // 这ans计算的是路径上的节点数量，需要-1
        return ans-1;
    }

    private int dfs(TreeNode node) {
        if (node == null)
            return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        ans = Math.max(ans, left + right + 1);
        return Math.max(left, right) + 1;
    }
}
