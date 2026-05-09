import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        // Create a DP array to store the min coins for every value up to 'amount'
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        
        // Initialize the array with a value larger than any possible answer
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // If dp[amount] is still 'max', it means the amount cannot be formed
        return dp[amount] > amount ? -1 : dp[amount];
    }
}