import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // 1. Build Adjacency Lists
        // 0 = Red, 1 = Blue
        List<Integer>[][] adj = new ArrayList[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) adj[i][j] = new ArrayList<>();
        }
        for (int[] edge : redEdges) adj[0][edge[0]].add(edge[1]);
        for (int[] edge : blueEdges) adj[1][edge[0]].add(edge[1]);

        int[] result = new int[n];
        Arrays.fill(result, -1);

        // 2. BFS Setup
        // Queue stores: {node, last_color, distance}
        Queue<int[]> queue = new LinkedList<>();
        // visited[color][node] to prevent infinite loops
        boolean[][] visited = new boolean[2][n];

        // Start from node 0 with both potential "previous" colors
        queue.offer(new int[]{0, 0, 0}); // Treated as arriving via Red
        queue.offer(new int[]{0, 1, 0}); // Treated as arriving via Blue
        visited[0][0] = true;
        visited[1][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int lastColor = curr[1];
            int dist = curr[2];

            // Update result if it's the first time reaching this node
            if (result[node] == -1 || dist < result[node]) {
                result[node] = dist;
            }

            // Next color must be the opposite (if last was Red/0, next is Blue/1)
            int nextColor = 1 - lastColor;

            for (int neighbor : adj[nextColor][node]) {
                if (!visited[nextColor][neighbor]) {
                    visited[nextColor][neighbor] = true;
                    queue.offer(new int[]{neighbor, nextColor, dist + 1});
                }
            }
        }

        return result;
    }
}