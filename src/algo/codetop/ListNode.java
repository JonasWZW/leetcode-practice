package algo.codetop;

import javax.swing.*;
import javax.swing.text.rtf.RTFEditorKit;
import java.util.HashMap;
import java.util.logging.Handler;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

