package algo.codetop;


import algo.dp.DPAlgo;

import java.util.HashMap;
import java.util.Map;

public class Day12 {

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // n位数 x m位数    结果数 位<=m+n
        int[] ans = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                // 模拟乘法，暂不处理进位，对应结果数位 累加
                ans[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
//        DPAlgo.printDp1(ans);
        // 单独处理进位，左边一个 += 右边/10
        for (int i = ans.length - 2; i >= 0; i--) {
            ans[i] += ans[i + 1] / 10;
            ans[i + 1] %= 10;
        }
//        DPAlgo.printDp1(ans);
        // 判断首位是否为0
        int start = ans[0] == 0 ? 1 : 0;
        StringBuilder sb = new StringBuilder();
        while (start < ans.length) {
            sb.append(ans[start]);
            start++;
        }
        return sb.toString();
    }
    // 记录遍历的窗口
    HashMap<Character, Integer> map = new HashMap<>();
    // 记录t字符串
    HashMap<Character, Integer> tMap = new HashMap<>();

    public String minWindow(String s, String t) {
        // 长度不对，直接返回
        if (s.length() < t.length())
            return "";
        // 录入t
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }
        // l r记录位置
        int l = 0;
        int r = Integer.MAX_VALUE - 1;
        // i j 双指针，j遍历，[i,j]窗口
        int i = 0;
        int j = 0;
        // 记录是否check成功，否则返回空串
        boolean f = false;
        while (j < s.length()) {
            // 遍历j，存入map
            char c = s.charAt(j);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (check() && i <= j) {
                if (j - i + 1 < r - l + 1) {
                    f = true;
                    l = i;
                    r = j;
                }
//                System.out.println(l + "\t" + r);
                // 从map移除对应的元素  i右移
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                i++;
            }
            j++;
        }
        if (f){
            return s.substring(l, r + 1);
        }else {
            return "";
        }
    }

    private boolean check() {
        // 遍历tMap 数量少直接返回false
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            Character k = entry.getKey();
            Integer v = entry.getValue();
            Integer v1 = map.getOrDefault(k, 0);
            if (v1 < v) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Day12 day12 = new Day12();
//        System.out.println(day12.multiply("123", "456"));
        System.out.println(day12.minWindow("ADOBECODEBANC", "ABC"));
    }
}
