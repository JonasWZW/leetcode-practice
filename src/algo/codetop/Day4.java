package algo.codetop;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Day4 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length == 0) {
            return ans;
        }
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        travelPermute(nums, ans, path, used);
        return ans;
    }

    private void travelPermute(int[] nums, List<List<Integer>> ans, LinkedList<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            travelPermute(nums, ans, path, used);
            used[i] = false;
            path.removeLast();
        }
    }

    public int maxProfit1(int[] prices) {
        //dp[i][j]表示 第i天 0持有 1不持有 股票的时候的财产总和
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], 0 - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        return dp[prices.length - 1][1];
    }

    public int maxProfit2(int[] prices) {
        //dp[i][j]表示 第i天 0持有 1不持有 股票的时候的财产总和
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        return dp[prices.length - 1][1];
    }

    public int maxProfit3(int[] prices) {
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = -prices[0];
        dp[0][3] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], 0 - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
        }
        return dp[prices.length - 1][3];
    }

    public int maxProfit4(int[] prices, int k) {
        // 第i天，0第一次持有，1第一次卖出，2第二次持有，3第二次卖出
        int[][] dp = new int[prices.length][2 * k];
        for (int j = 0; j < 2 * k; j = j + 2) {
            dp[0][j] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < 2 * k; j = j + 2) {
                if (j == 0)
                    dp[i][j] = Math.max(dp[i - 1][j], -prices[i]);
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] + prices[i]);
            }
        }

        return dp[prices.length - 1][2 * k - 1];
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null || root==p || root==q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left,p ,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (left!=null && right!=null){
            return root;
        }
        return left==null?right:left;
    }
    // 遍历后，把当前节点->父节点，然后使用map从p开始往上走，以此把p和p的父节点们加入set，在从q开始往上走，第一个重复的就是父节点。
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {

        HashMap<Integer, TreeNode> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        dfs(root,map);
        TreeNode t = p;
        while (t!=null){
            set.add(t.val);
            t = map.get(t.val);
        }
        while (q != null){
            if (set.contains(q.val)){
                return q;
            }else {
                q = map.get(q.val);
            }
        }
        return null;
    }

    private void dfs(TreeNode root, HashMap<Integer, TreeNode> map) {
        if (root==null){
            return;
        }
        if (root.left!=null){
            map.put(root.left.val, root);
            dfs(root.left,map);
        }
        if (root.right!=null){
            map.put(root.right.val, root);
            dfs(root.right,map);
        }
    }

    public static void main(String[] args) {
        Day4 day4 = new Day4();
        List<List<Integer>> permute = day4.permute(new int[]{1, 2, 3});
        System.out.println(permute);
    }
}
