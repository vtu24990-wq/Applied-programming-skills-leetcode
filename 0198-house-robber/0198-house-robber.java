class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int prev2 = 0; // Max profit 2 houses ago
        int prev1 = 0; // Max profit 1 house ago

        for (int money : nums) {
            // Decision: Max of (skip current house) OR (rob current house + profit from 2 houses ago)
            int current = Math.max(prev1, money + prev2);
            
            // Shift variables forward for the next house
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}