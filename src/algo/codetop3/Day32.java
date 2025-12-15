package algo.codetop3;

import algo.codetop.ListNode;

import java.util.List;
import java.util.Stack;

public class Day32 {
    //445. 两数相加 II
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 两个栈，可以先进后出，从最低位开始做加法
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        ListNode c = l1;
        while (c != null) {
            s1.push(c.val);
            c = c.next;
        }
        c = l2;
        while (c != null) {
            s2.push(c.val);
            c = c.next;
        }
        int n = 0;
        ListNode ans = null;
        // 两个栈都为空，且没有进位的时候结束
        while (!s1.isEmpty() || !s2.isEmpty() || n != 0) {
            // 当栈为空的时候，就设置加数为0
            int a = s1.isEmpty() ? 0 : s1.pop();
            int b = s2.isEmpty() ? 0 : s2.pop();
            int k = a + b + n;
            int val = k % 10;
            n = k / 10;
            // 头插法，依次创建节点，然后让头部插入
            ListNode node = new ListNode(val);
            node.next = ans;
            ans = node;
        }

        return ans;
    }

    //45. 跳跃游戏 II
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int maxRange = 0;
        int end = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + nums[i] >= nums.length - 1) {
                return ++ans;
            }
            maxRange = Math.max(maxRange, i + nums[i]);
            if (i == end) {
                end = maxRange;
                ans++;
            }
        }
        return ans;
    }
}

class Trie {
    Trie[] child;
    boolean isNode;

    public Trie() {
        child = new Trie[26];
        isNode = false;
    }

    public void insert(String word) {
        Trie t = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (t.child[index] == null) {
                t.child[index] = new Trie();
            }
            t = t.child[index];
        }
        t.isNode = true;
    }

    public boolean search(String word) {
        Trie n = searchPrefix(word);
        return n != null && n.isNode;
    }

    private Trie searchPrefix(String word) {
        Trie t = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (t.child[index] == null) {
                return null;
            }
            t = t.child[index];
        }
        return t;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }
}