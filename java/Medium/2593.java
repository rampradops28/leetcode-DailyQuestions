class Solution {
    public long findScore(int[] nums) {
        int n = nums.length;
        
        // Create a priority queue (min-heap) to store [value, index] pairs.
        // The comparator ensures elements are sorted by value first.
        // If values are equal, the smaller index comes first.
        PriorityQueue<int[]> minheap = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1]; // Compare indices if values are equal
            return a[0] - b[0];                   // Otherwise, compare values
        });

        // Add each element of the array along with its index into the min-heap
        for (int i = 0; i < n; i++) {
            minheap.offer(new int[]{nums[i], i});
        }

        // Use a set to keep track of used indices and adjacent indices
        Set<Integer> res = new HashSet<>();

        // Variable to store the total score
        long sum = 0;

        // Process the elements in the min-heap
        while (!minheap.isEmpty()) {
            int cur[] = minheap.poll();  // Remove the smallest element from the heap
            int val = cur[0];           // Extract the value of the element
            int ind = cur[1];           // Extract the index of the element

            // If the index is already used, skip this element
            if (res.contains(ind)) continue;

            // Add the value to the total score
            sum += val;

            // Mark the current index, and its adjacent indices as used
            res.add(ind);
            res.add(ind + 1);
            res.add(ind - 1);
        }

        // Return the total score
        return sum;
    }
}
