package algo.codetop2;


import algo.codetop.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day25 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 记录学了多少节课程
        int sum = 0;

        //学了index课程后，可以学什么课程
        List<List<Integer>> adjoinList = new ArrayList<>();
        //初始化 邻接表
        for (int i = 0; i < numCourses; i++) {
            adjoinList.add(new ArrayList<>());
        }
        //可以直接直接学的课程
        LinkedList<Integer> canStudyQueue = new LinkedList<>();
        int[] need = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int target = prerequisite[0];
            int preTarget = prerequisite[1];
            adjoinList.get(preTarget).add(target);
            // 学习target的课程，需要的前置课程数量
            need[target]++;
        }
        for (int i = 0; i < numCourses; i++) {
            // 当前置课程数量为0的时候，就可以直接学习
            if (need[i] == 0) {
                canStudyQueue.offer(i);
            }
        }
        while (!canStudyQueue.isEmpty()) {
            //从可以直接学的课程里面，直接进行学习，sum++
            Integer index = canStudyQueue.poll();
            sum++;
            // 获取学了当前课程，后续可学的课程。
            List<Integer> list = adjoinList.get(index);
            // 注意：这个时候不能直接学，因为可能存在一门课程，需要多个前置课程。
            for (Integer course : list) {
                // 当前课程的前置课程数量-1
                // 当前置课程数量为0的时候，就可以直接学习
                need[course]--;
                if (need[course] == 0) {
                    canStudyQueue.offer(course);
                }
            }
        }

        return sum == numCourses;
    }

    // 序列化（BFS）
    public String serialize(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null,");
            } else {
                sb.append(node.val).append(",");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return sb.toString();
    }

    // 反序列化（BFS）
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] nodes = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        queue.offer(root);

        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // 处理左子节点
            if (!nodes[index].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(nodes[index]));
                queue.offer(node.left);
            }
            index++;

            // 处理右子节点
            if (!nodes[index].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(nodes[index]));
                queue.offer(node.right);
            }
            index++;
        }
        return root;
    }

    public static void main(String[] args) {
        Day25 day25 = new Day25();
        System.out.println(day25.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }
}
