package algo.backtrack;


import java.util.*;

public class CombinationAlgo {

    /**
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案。
     */
    public List<List<Integer>> combine(int n, int k) {
        if (n == 0) return ans;
        travel1(n, k, 1);
        return ans;
    }

    public void travel1(int n, int k, int startIndex) {
        if (k == path.size()) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
//            i++;
            travel1(n, k, i + 1);
//            i--;
            path.removeLast();
        }
    }

    /**
     * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
     * <p>
     * 只使用数字1到9
     * 每个数字 最多使用一次
     * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        travel3(k, n, 1);
        return ans;
    }

    private void travel3(int k, int sum, int startIndex) {
        if (sum == 0 && path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (sum < 0 || path.size() > k) {
            return;
        }
        for (int i = startIndex; i <= 9; i++) {
            sum -= i;
            path.add(i);
            travel3(k, sum, i + 1);
            path.removeLast();
            sum += i;
        }
    }


    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)
            return ansLetter;
        //"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        phoneLetterTable.put('2', "abc");
        phoneLetterTable.put('3', "def");
        phoneLetterTable.put('4', "ghi");
        phoneLetterTable.put('5', "jkl");
        phoneLetterTable.put('6', "mno");
        phoneLetterTable.put('7', "pqrs");
        phoneLetterTable.put('8', "tuv");
        phoneLetterTable.put('9', "wxyz");
        travelLetter(digits, phoneLetterTable, 0);
        return ansLetter;
    }

    public void travelLetter(String digits, Map<Character, String> table, int startIndex) {
        if (sb.length() == digits.length()) {
            ansLetter.add(sb.toString());
            return;
        }
        String letters = table.get(digits.charAt(startIndex));
        for (int i = 0; i < letters.length(); i++) {
            sb.append(String.valueOf(letters.charAt(i)));
            travelLetter(digits, table, startIndex + 1);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        travelSum(candidates, target, 0, 0);
        return ans;
    }

    private void travelSum(int[] candidates, int target, int sum, int startIndex) {
        if (target == sum) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (target < sum)
            return;

        for (int i = startIndex; i < candidates.length; i++) {
            path.add(candidates[i]);
            travelSum(candidates, target, sum + candidates[i], i);
            path.removeLast();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        boolean[] used = new boolean[candidates.length];
        Arrays.sort(candidates);
        travelSum2(candidates, target, 0, 0, used);
        return ans;
    }

    private void travelSum2(int[] candidates, int target, int sum, int startIndex, boolean[] used) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (sum > target)
            return;

        for (int i = startIndex; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            path.add(candidates[i]);
            sum += candidates[i];
            travelSum2(candidates, target, sum, i + 1, used);
            sum -= candidates[i];
            path.removeLast();
            used[i] = false;
        }
    }


    /**
     * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     * 示例 1：
     * <p>
     * 输入：s = "aab"
     * 输出：[["a","a","b"],["aa","b"]]
     * 示例 2：
     * <p>
     * 输入：s = "a"
     * 输出：[["a"]]
     */
    public List<List<String>> partition(String s) {
        travelPartition(s, 0);

        return partitionList;
    }

    public void travelPartition(String s, int startIndex) {
        if (startIndex >= s.length()) {
            partitionList.add(new ArrayList<>(partitionPath));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (!isPalindromic(s, startIndex, i)) {
                continue;
            }
            partitionPath.add(s.substring(startIndex, i + 1));
            travelPartition(s, i + 1);
            partitionPath.removeLast();
        }
    }

    public boolean isPalindromic(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4) return ansLetter;
        travelIp(s, 0);
        return ansLetter;
    }

    private void travelIp(String s, int startIndex) {
        if (path.size() == 4 && startIndex >= s.length()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i)).append(".");
            }
            sb.deleteCharAt(sb.length() - 1);
            ansLetter.add(sb.toString());
        }
        if (path.size() > 4 || startIndex > s.length()) {
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            //例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，
            //但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址

//            if (i == 0 && s.charAt(i) == '0') {
//                continue;
//            }

            if (!isIpv4Dot(s, startIndex, i)) {
                continue;
            }
            path.add(Integer.valueOf(s.substring(startIndex, i + 1)));
            travelIp(s, i + 1);
            path.removeLast();
        }
    }

    public boolean isIpv4Dot(String s, int left, int right) {
        if (s.charAt(left) == '0' && right != left) {
            return false;
        }
        if (right - left >= 3) {
            return false;
        }
        int num = Integer.parseInt(s.substring(left, right + 1));
        if (num > 255) {
            return false;
        }
        return true;
    }

    /**
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * <p>
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * <p>
     * 示例 2：
     * 输入：nums = [0]
     * 输出：[[],[0]]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        travelSubset(nums, 0);
        return ans;
    }

    private void travelSubset(int[] nums, int startIndex) {
        if (startIndex > nums.length) {
            return;
        }
        // 以前都是收割叶子节点，这次是收割所有的节点，包括根节点和叶子节点，以及中间的节点。
        ans.add(new ArrayList<>(path));
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            travelSubset(nums, i + 1);
            path.removeLast();
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        travelSubset2(nums, used, 0);
        return ans;
    }

    private void travelSubset2(int[] nums, boolean[] used, int startIndex) {
        ans.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            travelSubset2(nums, used, i + 1);
            used[i] = false;
            path.removeLast();
        }
    }

    /**
     * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
     * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
     * <p>
     * 示例 1：
     * 输入：nums = [4,6,7,7]
     * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
     * <p>
     * 示例 2：
     * 输入：nums = [4,4,3,2,1]
     * 输出：[[4,4]]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        // 区别于之前的，不能对nums进行排序了，不然就直接是递增序列了
        travelSubsequences(nums, 0);
        return ans;
    }

    private void travelSubsequences(int[] nums, int startIndex) {
        if (path.size() >= 2) {
            ans.add(new ArrayList<>(path));
        }
        if (startIndex >= nums.length) {
            return;
        }
        // 直接在每一层进行层维度的剪枝，去掉重复的即可。
        // 以前是组合场景，排完序，当且仅当，相邻的两个元素相等，且前一个元素未使用，遍历到后一个元素的时候，continue
        HashSet<Integer> set = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            if (!path.isEmpty() && path.peekLast() > nums[i]) {
                continue;
            }
            if (set.contains(nums[i])) {
                continue;
            }
            if (path.size() + nums.length - i < 2) {
                continue;
            }
            set.add(nums[i]);
            path.add(nums[i]);
            travelSubsequences(nums, i + 1);
            path.removeLast();

        }

    }


    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        travelPermute(nums, used);
        return ans;
    }

    private void travelPermute(int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue;
            path.add(nums[i]);
            used[i] = true;
            travelPermute(nums, used);
            used[i] = false;
            path.removeLast();
        }
    }

    /**
     * 输入：nums = [1,1,2]
     * 输出：
     * [[1,1,2],
     * [1,2,1],
     * [2,1,1]]
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        travelPermuteUnique(nums, used);
        return ans;
    }

    private void travelPermuteUnique(int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            travelPermuteUnique(nums, used);
            used[i] = false;
            path.removeLast();
        }
    }


    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    LinkedList<Integer> path = new LinkedList<>();

    List<String> ansLetter = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    HashMap<Character, String> phoneLetterTable = new HashMap<>();

    List<List<String>> partitionList = new ArrayList<>();
    LinkedList<String> partitionPath = new LinkedList<>();
}
