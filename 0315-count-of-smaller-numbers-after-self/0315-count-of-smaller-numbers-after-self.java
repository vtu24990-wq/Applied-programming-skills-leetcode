class Solution {
    int[] count;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        int[] indices = new int[n];
        
        // Initialize indices: [0, 1, 2, ..., n-1]
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        mergeSort(nums, indices, 0, n - 1);

        List<Integer> result = new ArrayList<>();
        for (int c : count) {
            result.add(c);
        }
        return result;
    }

    private void mergeSort(int[] nums, int[] indices, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(nums, indices, left, mid);
        mergeSort(nums, indices, mid + 1, right);

        merge(nums, indices, left, mid, right);
    }

    private void merge(int[] nums, int[] indices, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;      // Pointer for left half
        int j = mid + 1;   // Pointer for right half
        int k = 0;         // Pointer for temp array
        int rightCount = 0; // Tracks how many elements from right side are smaller

        while (i <= mid && j <= right) {
            if (nums[indices[j]] < nums[indices[i]]) {
                // Element on right is smaller than element on left
                temp[k++] = indices[j++];
                rightCount++;
            } else {
                // Element on left is smaller or equal; 
                // add the count of all "smaller" right elements seen so far
                count[indices[i]] += rightCount;
                temp[k++] = indices[i++];
            }
        }

        // Clean up remaining elements
        while (i <= mid) {
            count[indices[i]] += rightCount;
            temp[k++] = indices[i++];
        }
        while (j <= right) {
            temp[k++] = indices[j++];
        }

        // Copy temp back to indices
        for (int p = 0; p < temp.length; p++) {
            indices[left + p] = temp[p];
        }
    }
}