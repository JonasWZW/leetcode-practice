package algo.stack;

import java.util.*;

/**
 * @Author: jonas
 * @CreateTime: 2025-10-12  00:07
 * @Description: TODO
 * @Version: 1.0
 */
public class MonotonousStackAlgo {
    /**
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
     * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * 示例 1:
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                if (temperatures[stack.peek()] >= temperatures[i]) {
                    stack.push(i);
                } else {
                    while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                        int index = stack.pop();
                        ans[index] = i - index;
                    }
                    stack.push(i);
                }
            }

        }
        return ans;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];

        HashMap<Integer, Integer> map = new HashMap<>();

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            if (stack.isEmpty()) {
                stack.push(nums2[i]);
            } else {
                if (stack.peek() >= nums2[i]) {
                    stack.push(nums2[i]);
                } else {
                    while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                        int num = stack.pop();
                        map.put(num, nums2[i]);
                    }
                    stack.push(nums2[i]);
                }
            }
        }
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.getOrDefault(nums1[i], -1);
        }

        return ans;
    }

    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < len * 2; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                if (nums[stack.peek() % len] >= nums[i % len]) {
                    stack.push(i);
                } else {
                    while (!stack.isEmpty() && nums[stack.peek() % len] < nums[i % len]) {
                        int index = stack.pop();
                        if (ans[index % len] == -1) {
                            ans[index % len] = nums[i % len];
                        }
                    }
                    stack.push(i);
                }
            }
        }
        return ans;
    }

    public int trap(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {

        }

        return sum;
    }

    public int trap1(int[] height) {
        int sum = 0;
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        left[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        right[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }
        for (int i = 0; i < height.length; i++) {
            sum += Math.min(left[i], right[i]) - height[i];
        }

        return sum;
    }

    public int trapRainWater(int[][] heightMap) {
        class Node {
            int x;
            int y;
            int z;

            Node(int a, int b, int c) {
                x = a;
                y = b;
                z = c;
            }
        }
        int sum = 0;
        int m = heightMap.length;
        int n = heightMap[0].length;
        // 木桶原理，能装多少水取决于最短的木桶边
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> {
            return n1.z - n2.z;
        });
        boolean[][] used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 最外圈的无法装水，直接加入最小堆，且标记为已访问
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    used[i][j] = true;
                    queue.add(new Node(i, j, heightMap[i][j]));
                }
            }
        }
        int[][] directs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            // 依次从最小堆，获取最小的元素，然后遍历他的合法的上下左右四个位置
            Node minNode = queue.poll();
            int x = minNode.x;
            int y = minNode.y;
            int z = minNode.z;
            for (int i = 0; i < directs.length; i++) {
                int[] direct = directs[i];
                int nx = x + direct[0];
                int ny = y + direct[1];
                // 只处理合法的位置
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !used[nx][ny]) {
                    if (z > heightMap[nx][ny]){
                        sum += z - heightMap[nx][ny];
                    }
                    used[nx][ny] = true;
                    queue.offer(new Node(nx, ny, Math.max(z,heightMap[nx][ny])));
                }
            }
        }
        return sum;
    }


    public static void main(String[] args) {

    }
}
