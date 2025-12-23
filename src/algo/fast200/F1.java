package algo.fast200;


import algo.codetop.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class F1 {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        HashSet<Character> set = new HashSet<>();
        int i =0,j=0;
        while (j<s.length()){
            char c = s.charAt(j);
            if (!set.contains(c)){
                set.add(c);
                j++;
            }else {
                set.remove(s.charAt(i));
                i++;
            }
            ans = Math.max(ans,j-i);
        }

        return ans;
    }

    public ListNode reverseList(ListNode head) {
        ListNode dummy = null;
        while (head!=null){
            ListNode n = head.next;
            head.next = dummy;
            dummy = head;
            head = n;
        }
        return dummy;
    }
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums,0,nums.length-1,nums.length-k);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left>=right){
            return -1;
        }
        int p = partition(nums,left,right);
        if (p==k){
            return nums[p];
        }else if (p>k){
            return quickSelect(nums,left,p-1,k);
        }else {
            return quickSelect(nums,p+1,right,k);
        }
    }

    private int partition(int[] nums, int left, int right) {
        if (left>=right){
            return left;
        }
        int mid = left + (right-left)/2;
        int val = nums[mid];
        swap(nums,mid,right);
        int i =  left-1;
        for (int j = left; j <= right; j++) {
            if (nums[j]<val){
                i++;
                swap(nums,i,j);
            }
        }
        swap(nums,i+1,right);
        return i+1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k==1){
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        while (head!=null){
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail= tail.next;
                if (tail==null){
                    return dummy.next;
                }
            }
            ListNode next = tail.next;
            reverse(head,tail);
            pre.next = tail;
            head.next = next;
            pre = head;
            head = next;
        }
        return dummy.next;
    }

    private void reverse(ListNode head, ListNode tail) {
        ListNode dummy = new ListNode();
        ListNode curr = head;
        ListNode end = tail.next;
        while (curr != end) {
            ListNode p = curr.next;
            curr.next = dummy.next;
            dummy.next = curr;
            curr = p;
        }
    }
}


class LRUCache {
    class Node{
        int key;
        int val;
        Node pre;
        Node next;
        public Node() {
        }
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    Node head;
    Node tail;
    HashMap<Integer, Node> map;
    int capacity;
    int cnt;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        map = new HashMap<>();
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node==null){
            return -1;
        }else {
            moveToHead(node);
            return node.val;
        }
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node==null){
            if (this.capacity==this.cnt){
                removeTail();
            }
            Node n = new Node(key,value);
            addToHead(n);
        }else {
            node.val = value;
            moveToHead(node);
        }

    }

    private void addToHead(Node n) {
        Node t = head.next;
        n.pre= head;
        n.next = t;
        t.pre=n;
        head.next =n;
        map.put(n.key,n);
        this.cnt++;
    }

    private void removeTail() {
        Node pre = tail.pre;
        map.remove(pre.key);
        Node t = tail.pre.pre;
        t.next = tail;
        tail.pre = t;
        pre.pre = null;
        pre.next = null;
        this.cnt--;
    }

    private void moveToHead(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        Node t = head.next;
        node.pre = head;
        node.next = t;
        t.pre = node;
        head.next = node;
    }
}