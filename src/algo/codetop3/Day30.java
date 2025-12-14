package algo.codetop3;

import algo.codetop.TreeNode;
import algo.dp.DPAlgo;

import java.rmi.MarshalException;

public class Day30 {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null)
            return false;
        //先从根节点判断B是不是A的子结构，如果不是在分别从左右两个子树判断，
        //只要有一个为true，就说明B是A的子结构
        return isSub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    boolean isSub(TreeNode A, TreeNode B) {
        //这里如果B为空，说明B已经访问完了，确定是A的子结构
        if (B == null)
            return true;
        //如果B不为空A为空，或者这两个节点值不同，说明B树不是
        //A的子结构，直接返回false
        if (A == null)
            return false;
        //当前节点比较完之后还要继续判断左右子节点
        return A.val == B.val && isSub(A.left, B.left) && isSub(A.right, B.right);
    }


    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // 子树和子结构不同，
        // 子树必须要求，从某个节点开始以下的所有节点和结构都相同
        // 子结构，从某个节点开始以下的结构相同即可，可以小于等于子树。所以他们的isSub的写法不同
        if (root == null) {
            return subRoot == null;
        }
        return isSub1(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSub1(TreeNode p, TreeNode q) {
        // 子树要求完全相等，所以都为null是，返回true
        if (p == null && q == null) {
            return true;
        }
        // 当仅有一个为null时 false
        if (p == null || q == null) {
            return false;
        }
        //递归下去，判断当前值，然后p的左对应q的左，知道满足终止条件。
        return p.val == q.val && isSub1(p.left, q.left) && isSub1(p.right, q.right);
    }

    //59. 螺旋矩阵 II
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int k = 1;
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directIndex = 0;
        int r = 0, c = 0;
        while (k <= n * n) {
            matrix[r][c] = k;
            k++;
            int nr = r + direction[directIndex][0];
            int nc = c + direction[directIndex][1];
            if (nr < 0 || nr >= n || nc < 0 || nc >= n || matrix[nr][nc] != 0) {
                directIndex = (directIndex + 1) % 4;
                nr = r + direction[directIndex][0];
                nc = c + direction[directIndex][1];
            }
            r = nr;
            c = nc;
        }
        return matrix;
    }

    public static void main(String[] args) {
        Day30 day30 = new Day30();
        int[][] nums = day30.generateMatrix(4);
        DPAlgo.printDp(nums);
    }
}
