package algo.codetop3;


import algo.dp.DPAlgo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day35 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        if (l1 + l2 != l3) {
            return false;
        }
        //dp[i][j]表示，s1的第i个结尾和s2的第j个结尾组合的字符串，能否组成前i+j个的s3
        boolean[][] dp = new boolean[l1 + 1][l2 + 1];
        dp[0][0] = true;
        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                int t = i + j - 1;
                if (i > 0 && s1.charAt(i - 1) == s3.charAt(t)) {
                    dp[i][j] = dp[i - 1][j];
                }

                if (j > 0 && s2.charAt(j - 1) == s3.charAt(t)) {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[l1][l2];
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 邻接表 [i][j] 表示学了课程i 才可能可以去学课程j。即是必要条件
        ArrayList<LinkedList<Integer>> adjoinList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjoinList.add(new LinkedList<>());
        }
        //入度表，需要前置学习x门课，才可以学习课程i
        int[] inEdge = new int[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int[] n = prerequisites[i];
            inEdge[n[0]] = inEdge[n[0]] + 1;
            adjoinList.get(n[1]).add(n[0]);
        }
        // 将入度为0的课程，加入队列，这个队列里面记载可以直接学习的课程
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < inEdge.length; i++) {
            if (inEdge[i] == 0) {
                list.offer(i);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        int cnt = 0;
        while (!list.isEmpty()) {
            // 直接可以学习的课程，加入ans
            Integer index = list.poll();
            cnt++;
            ans.add(index);
            // 获取学习了该课程后，就可能可以学的课程表。
            // 把他们的入度-1，如果为0，就加入0入度表
            LinkedList<Integer> courses = adjoinList.get(index);
            for (Integer course : courses) {
                inEdge[course]--;
                if (inEdge[course] == 0) {
                    list.offer(course);
                }
            }
        }
        // 当不相等的时候，说明存在环。有环，则返回空
        return cnt == numCourses ? ans.stream().mapToInt(i -> i).toArray() : new int[0];
    }

    public static void main(String[] args) {
        Day35 day35 = new Day35();
        //输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
        int[] order = day35.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        DPAlgo.printDp1(order);
    }
}
