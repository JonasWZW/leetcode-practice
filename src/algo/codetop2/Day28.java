package algo.codetop2;


import jdk.nashorn.internal.ir.IdentNode;

import java.security.KeyStore;
import java.util.Stack;

public class Day28 {
    // 498. 对角线遍历
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] ans = new int[m * n];
        int k = 0;
        for (int i = 0; i < m + n - 1; i++) {
            if (i % 2 == 0) {
                int x = i < m ? i : m - 1;
                int y = i < m ? 0 : i - m + 1;
                while (x >= 0 && y < n) {
                    ans[k++] = mat[x][y];
                    x--;
                    y++;
                }
            } else {
                int x = i < n ? 0 : i - n + 1;
                int y = i < n ? i : n - 1;
                while (x < m && y >= 0) {
                    ans[k++] = mat[x][y];
                    x++;
                    y--;
                }
            }
        }
        return ans;
    }

    // 518. 零钱兑换 II
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];

        for (int j = 0; j <= amount; j++) {
            if (j % coins[0] == 0) {
                dp[0][j] = 1;
            }
        }
        for (int i = 0; i < coins.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length - 1][amount];
    }

    // LCR 155. 将二叉搜索树转化为排序的双向链表
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    Node head;
    Node pre;
    // LCR 155. 将二叉搜索树转化为排序的双向链表
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        inorder(root);

        head.left = pre;
        pre.right = head;

        return head;
    }

    private void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);

        if (pre == null) {
            head = root;
        } else {
            pre.right = root;
        }
        root.left = pre;

        pre = root;

        inorder(root.right);
    }

    public Node treeToDoublyList1(Node root) {
        if (root == null) {
            return null;
        }
        Stack<Node> s = new Stack<>();
        Node pre = null;
        Node head = null;
        Node cur = root;
        while (cur != null || !s.isEmpty()) {
            if (cur != null) {
                s.push(cur);
                cur = cur.left;
            } else {
                cur = s.pop();

                if (pre == null) {
                    head = cur;
                } else {
                    pre.right = cur;
                }
                cur.left = pre;
                pre = cur;

                cur = cur.right;
            }
        }
        head.left = pre;
        pre.right = head;
        return head;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int left = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int x = mid / n;
            int y = mid % n;
            if (target == matrix[x][y]) {
                return true;
            } else if (target < matrix[x][y]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    // 7. 整数反转
    public int reverse(int x) {
        int sum = 0;
        while (x != 0) {
            if (sum < Integer.MIN_VALUE / 10 || sum > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int k = x % 10;
            sum = 10 * sum + k;
            x /= 10;
        }
        return sum;
    }
}
