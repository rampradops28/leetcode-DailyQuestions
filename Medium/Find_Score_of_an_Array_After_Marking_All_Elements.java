/*Intuition:
    The problem is about efficiently processing elements in ascending order of their value while marking indices (and adjacent ones) as "used" to avoid reusing them. Using a min-heap (PriorityQueue) ensures that the smallest element is always processed first, making the implementation both simple and efficient.

Approach:
    Min-Heap Construction:
    Store each element of the array along with its index in a PriorityQueue. Use a comparator to prioritize by value, and in case of ties, by index.

    Processing Elements:
    Extract the smallest value from the heap.
    If its index or adjacent indices have already been "used," skip it.
    Otherwise, add the value to the score and mark the current, previous, and next indices as "used."

    Return Result:
    Once the heap is empty, return the accumulated score.

Complexity:
    Time Complexity: 
        # Heap Initialization: O(nlogn) for adding all elements to the heap.
        # Processing Elements: O(nlogn) for n heap poll operations.
        # Total: O(nlogn)
    Space Complexity:
        # Heap Storage: O(n) for the heap.
        # Set Storage: O(n) for tracking used indices.
        # Total: O(n)

Code: 2593

*/

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


class Find_Score_of_an_Array_After_Marking_All_Elements {

    public static long findScore(int[] nums) {
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

    public static void main(String[] args) {
        
        int arr[] = {2,3,5,1,3,2};

        System.out.println(findScore(arr));
    }
}
/* 
TestCases:
    Example 1:

    Input: nums = [2,1,3,4,5,2]
    Output: 7
    Explanation: We mark the elements as follows:
    - 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
    - 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
    - 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
    Our score is 1 + 2 + 4 = 7.
    
    Example 2:

    Input: nums = [2,3,5,1,3,2]
    Output: 5
    Explanation: We mark the elements as follows:
    - 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
    - 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
    - 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
    Our score is 1 + 2 + 2 = 5.
*/