class Solution {
    public int climbStairs(int n) {
        // Handle base cases
        if (n <= 2) return n;

        int first = 1;  // Ways to reach 1st step
        int second = 2; // Ways to reach 2nd step

        for (int i = 3; i <= n; i++) {
            int current = first + second;
            first = second;
            second = current;
        }

        return second;
    }
}