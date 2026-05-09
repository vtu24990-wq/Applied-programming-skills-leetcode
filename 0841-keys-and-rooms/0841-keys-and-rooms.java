class Solution {
    // time : O(n), n = number of elements to loop through
    // space: O(n), n = number of elements store in an Array 

    // src       0     1          2     3
    // rooms  = [1,3] [3, 0, 1]  [2]   [0]

    // visited = false  false    false  false 
    // visited = true   true     false  true

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];   // can also use HashSet instead of Array
        dfs(0, rooms, visited);

        for(boolean v : visited) {
            if(v == false) return false;
        }
        return true;
        }

        public void dfs(int src, List<List<Integer>> graph, boolean[] visited) {
            if(visited[src] == true) return;
            visited[src] = true;

            List<Integer> neighbours = graph.get(src);
            for(int neighbour : neighbours) {
                dfs(neighbour, graph, visited);
            }
        }
}