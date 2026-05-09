import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // 1. Assign unique groups to items that don't belong to any group (-1)
        int groupCount = m;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = groupCount++;
            }
        }

        // 2. Initialize graphs and in-degrees
        List<Integer>[] itemGraph = new ArrayList[n];
        List<Integer>[] groupGraph = new ArrayList[groupCount];
        for (int i = 0; i < n; i++) itemGraph[i] = new ArrayList<>();
        for (int i = 0; i < groupCount; i++) groupGraph[i] = new ArrayList<>();

        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[groupCount];

        // 3. Build the graphs
        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                // Item-level dependency
                itemGraph[prev].add(i);
                itemIndegree[i]++;

                // Group-level dependency (if items are in different groups)
                if (group[i] != group[prev]) {
                    groupGraph[group[prev]].add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        // 4. Perform Topological Sort for both items and groups
        List<Integer> itemOrder = topologicalSort(itemGraph, itemIndegree, n);
        List<Integer> groupOrder = topologicalSort(groupGraph, groupIndegree, groupCount);

        // If a cycle is detected in either, return empty array
        if (itemOrder.isEmpty() || groupOrder.isEmpty()) {
            return new int[0];
        }

        // 5. Organize sorted items by their group IDs
        Map<Integer, List<Integer>> groupToItems = new HashMap<>();
        for (int item : itemOrder) {
            groupToItems.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        // 6. Build the final result based on the sorted group order
        int[] result = new int[n];
        int idx = 0;
        for (int groupId : groupOrder) {
            List<Integer> items = groupToItems.getOrDefault(groupId, new ArrayList<>());
            for (int item : items) {
                result[idx++] = item;
            }
        }

        return result;
    }

    private List<Integer> topologicalSort(List<Integer>[] graph, int[] indegree, int count) {
        List<Integer> order = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < count; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order.add(curr);
            for (int neighbor : graph[curr]) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Return empty list if there's a cycle (order size != total nodes)
        return order.size() == count ? order : new ArrayList<>();
    }
}