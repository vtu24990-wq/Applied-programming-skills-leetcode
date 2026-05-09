class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DSU dsu = new DSU(10001); // Max possible unique emails in constraints
        Map<String, String> emailToName = new HashMap<>();
        Map<String, Integer> emailToID = new HashMap<>();
        int id = 0;

        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++);
                }
                emailToName.put(email, name);
                dsu.union(emailToID.get(account.get(1)), emailToID.get(email));
            }
        }

        Map<Integer, List<String>> components = new HashMap<>();
        for (String email : emailToName.keySet()) {
            int root = dsu.find(emailToID.get(email));
            components.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> component : components.values()) {
            Collections.sort(component);
            List<String> mergedAccount = new ArrayList<>();
            mergedAccount.add(emailToName.get(component.get(0)));
            mergedAccount.addAll(component);
            result.add(mergedAccount);
        }

        return result;
    }
}

class DSU {
    int[] parent;
    public DSU(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }
    public int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }
    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) parent[rootI] = rootJ;
    }
}