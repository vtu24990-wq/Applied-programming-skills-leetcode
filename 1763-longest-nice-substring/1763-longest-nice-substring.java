class Solution {
    public String longestNiceSubstring(String s) {
        if (s.length() < 2) return "";
        
        // Use a Set or a simple boolean array/bitmask to track characters
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // If the counterpart (upper/lower) is missing, this is our splitter
            if (set.contains(Character.toLowerCase(c)) && set.contains(Character.toUpperCase(c))) {
                continue;
            }
            
            // Divide: Check the left side and the right side of the splitter
            String left = longestNiceSubstring(s.substring(0, i));
            String right = longestNiceSubstring(s.substring(i + 1));
            
            // Conquer: Return the longer one (earliest occurrence is naturally handled)
            return left.length() >= right.length() ? left : right;
        }
        
        // If we checked every char and found no splitters, the whole string is nice
        return s;
    }
}