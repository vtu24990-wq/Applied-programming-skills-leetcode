class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Max-Heap: stores points based on distance squared (descending)
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
        );

        for (int[] point : points) {
            maxHeap.add(point);
            // If heap exceeds size k, remove the farthest point
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // Convert heap to the required result format
        int[][] result = new int[k][2];
        while (k > 0) {
            result[--k] = maxHeap.poll();
        }
        
        return result;
    }
}