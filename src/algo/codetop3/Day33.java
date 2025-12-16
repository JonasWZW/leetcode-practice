package algo.codetop3;


import java.util.*;

public class Day33 {
    //圆环上有10个点，编号为0~9。从0点出发，每次可以逆时针和顺时针走一步，问走n步回到0点共有多少种走法。
    //输入: 2
    //输出: 2
    //解释：有2种方案。分别是0->1->0和0->9->0
    public int backToOrigin(int n) {
        int len = 10;
        //dp[i][j]表示从0点出发 走i步，到达j下标的方案数量
        int[][] dp = new int[n + 1][len];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j] = dp[i - 1][(j - 1 + len) % 10] + dp[i - 1][(j + 1) % 10];
            }
        }

        return dp[n][0];
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        ArrayList<int[]> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer num = entry.getKey();
            Integer time = entry.getValue();
            if (heap.size() == k) {
                if (heap.peek()[1] < time) {
                    heap.poll();
                    heap.offer(new int[]{num, time});
                }
            } else {
                heap.offer(new int[]{num, time});
            }

        }
        int[] ans = new int[heap.size()];
        for (int i = 0; i < k; i++) {
            ans[i] = heap.poll()[0];
        }
        return ans;
    }

    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        if (plants.length == 0) {
            return false;
        }
        int x = 0;
        int y = plants[0].length - 1;

        while (x < plants.length && y >= 0) {
            int num = plants[x][y];
            if (num == target) {
                return true;
            } else if (num > target) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }

    public boolean checkValidString(String s) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack1.push(i);
            } else if (c == '*') {
                stack2.push(i);
            } else {
                if(stack1.isEmpty() && stack2.isEmpty()){
                    // 没有（或*匹配）时，直接返回false
                    //其他情况，直接pop出对应的即可。当然得先pop左括号，应*可以匹配其他的
                    return false;
                }else if (!stack1.isEmpty()){
                    stack1.pop();
                }else if (!stack2.isEmpty()){
                    stack2.pop();
                }
            }
        }
        while (!stack1.isEmpty() && !stack2.isEmpty()){
            Integer leftIndex = stack1.pop();
            Integer index = stack2.pop();
            // * ( 这种是false
            if (leftIndex>index){
                return false;
            }
        }
        return stack1.isEmpty();
    }

    public static void main(String[] args) {
        Day33 day33 = new Day33();
        day33.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
    }
}
