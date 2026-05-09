import java.util.HashSet;
import java.util.Set;

class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> normalizedEmails = new HashSet<>();
        
        for (String email : emails) {
            // Split into local and domain parts
            String[] parts = email.split("@");
            String local = parts[0];
            String domain = parts[1];
            
            // 1. Handle the '+' rule: remove everything after '+'
            if (local.contains("+")) {
                local = local.substring(0, local.indexOf("+"));
            }
            
            // 2. Handle the '.' rule: remove all dots
            local = local.replace(".", "");
            
            // Combine them back and add to the set
            normalizedEmails.add(local + "@" + domain);
        }
        
        return normalizedEmails.size();
    }
}