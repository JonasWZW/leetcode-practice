package algo.codetop;


public class SortAlgo {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }


    private int quickSelect(int[] nums, int left, int right, int kk) {
        int partition = partition(nums, left, right);
        if (partition == kk) {
            return nums[partition];
        } else if (partition > kk) {
            return quickSelect(nums, left, partition - 1, kk);
        } else {
            return quickSelect(nums, partition + 1, right, kk);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int mid = left + (right - left) / 2;
        int val = nums[mid];
        swap(nums, mid, right);
        int i = left - 1;
        for (int j = left; j <= right; j++) {
            if (nums[j] < val) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, right);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public int findKthLargest0(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }
        System.out.println("\n-------------------");
        return nums[nums.length - k];
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right)
            return;
        int p = partition(nums, left, right);
        quickSort(nums, left, p - 1);
        quickSort(nums, p + 1, right);
    }



    public static void main(String[] args) {
        SortAlgo sortAlgo = new SortAlgo();
        System.out.println(sortAlgo.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 1));
    }
}
