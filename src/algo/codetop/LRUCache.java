package algo.codetop;

import java.util.HashMap;

public class LRUCache {
    class Node {
        int key;
        int val;
        Node pre;
        Node next;

        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    int capacity;
    HashMap<Integer, Node> map;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        } else {
            moveNodeToHead(node);
            return node.val;
        }
    }

    private void moveNodeToHead(Node node) {
        Node nodePre = node.pre;
        Node nodeNext = node.next;
        nodePre.next = nodeNext;
        nodeNext.pre = nodePre;
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, value);
            addNodeToHead(node);
            map.put(key, node);
            if (map.size() > capacity) {
                removeLastNode();
            }
        } else {
            node.val = value;
            moveNodeToHead(node);
        }
    }

    private void removeLastNode() {
        Node lastNode = tail.pre;
        Node preLastNode = tail.pre.pre;
        preLastNode.next = tail;
        tail.pre = preLastNode;
        map.remove(lastNode.key);
        lastNode.pre = null;
        lastNode.next = null;
    }

    private void addNodeToHead(Node node) {
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

}

