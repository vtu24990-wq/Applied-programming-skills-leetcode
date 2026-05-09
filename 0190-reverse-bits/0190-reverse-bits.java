public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int rev = 0;
        for (int i = 0; i < 32; i++) {
            // Shift result left to make room for the next bit
            rev <<= 1;
            // Extract the last bit of n and add it to rev
            rev |= (n & 1);
            // Unsigned shift n to the right to move to the next bit
            n >>>= 1;
        }
        return rev;
    }
}