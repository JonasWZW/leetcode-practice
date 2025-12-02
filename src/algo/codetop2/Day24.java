package algo.codetop2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;



public class Day24 {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        LinkedList<Integer> s = new LinkedList<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!s.isEmpty() && temperatures[s.peek()] < temperatures[i]) {
                Integer index = s.pop();
                ans[index] = i - index;
            }
            s.push(i);
        }
        return ans;
    }

    public String validIPAddress(String queryIP) {
        if (queryIP.contains(".") && queryIP.contains(":")) {
            return "Neither";
        }
        if (queryIP.contains(".")) {
            if (checkIPV4(queryIP)) {
                return "IPv4";
            } else {
                return "Neither";
            }
        }

        if (queryIP.contains(":")) {
            if (checkIPV6(queryIP)) {
                return "IPv6";
            } else {
                return "Neither";
            }
        }
        return "Neither";
    }

    private boolean checkIPV6(String queryIP) {
        String[] split = queryIP.split("\\:", -1);
        if (split.length != 8) {
            return false;
        }
        for (String s : split) {
            if (s.length() < 1 || s.length() > 4) {
                return false;
            }
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!(Character.isDigit(c) || c >= 'a' && c <= 'f' || c >= 'A' && c <= 'F')) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkIPV4(String queryIP) {
        String[] split = queryIP.split("\\.", -1);
        if (split.length != 4) {
            return false;
        }
        for (String s : split) {
            if (s.length() < 1 || s.length() > 3) {
                return false;
            }
            if (s.length() > 1 && s.charAt(0) == '0') {
                return false;
            }
            int num = 0;
            try {
                num = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;
            }
            if (num < 0 || num > 255) {
                return false;
            }
        }
        return true;
    }

    public Node copyRandomList(Node head) {
        //这种“复制带额外指针的链表”是一个经典面试题，属于图复制的特例。一旦你见过几次，大脑就会自动分类：
        //这是“带随机指针的图”问题
        //核心挑战：处理循环引用/前向引用
        //标准解法：映射关系（哈希表）
        if (head == null) return null;
        // 旧节点 -> 新节点
        Map<Node, Node> map = new HashMap<>();
        Node curr = head;

        // 第一次遍历：创建所有新节点
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        // 第二次遍历：连接指针
        curr = head;
        while (curr != null) {
            Node copy = map.get(curr);
            copy.next = map.get(curr.next);
            copy.random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head);
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (travelExist(board, used, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean travelExist(char[][] board, boolean[][] used, int x, int y, String word, int index) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || used[x][y]) {
            return false;
        }
        if (board[x][y] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        if (index >= word.length()) {
            return false;
        }
        used[x][y] = true;
        int[][] direct = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < direct.length; i++) {
            int nx = direct[i][0] + x;
            int ny = direct[i][1] + y;
            if (travelExist(board, used, nx, ny, word, index + 1)) {
                return true;
            }
        }
        used[x][y] = false;
        return false;
    }

    public static void main(String[] args) {
        Day24 day24 = new Day24();
        System.out.println(day24.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
