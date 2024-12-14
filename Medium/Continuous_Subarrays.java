/*Intuition:
    The task is to find all continuous subarrays where the difference between the maximum and minimum values is at most 2. Using a sliding window with deques helps efficiently maintain the maximum and minimum values in the current window.
   
Approach:
    Use two deques (maxDeque and minDeque) to track the indices of the maximum and minimum values in the current sliding window.
    Expand the window by moving the right pointer and update the deques.
    If the condition (max - min ≤ 2) is violated, shrink the window from the left.
    Count the valid subarrays for each window.

Complexity:
    Time Complexity:  O(n)
        Each element is added to and removed from the deques at most once.
    Space Complexity:  O(n)
        Space is used for the deques to store indices. 

Code: 2762 */

import java.util.ArrayDeque;
import java.util.Deque;

public class Continuous_Subarrays {
    public long continuousSubarrays(int[] nums) {
        int n = nums.length;
        long count = 0; // To store the total number of valid subarrays
        int left = 0;   // Left pointer of the sliding window

        // Deques to maintain the indices of max and min values in the window
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        // Iterate through the array with the right pointer
        for (int right = 0; right < n; right++) {
            // Maintain maxDeque: remove smaller elements from the back
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] < nums[right])
                maxDeque.pollLast();
            maxDeque.offerLast(right);

            // Maintain minDeque: remove larger elements from the back
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] > nums[right])
                minDeque.pollLast();
            minDeque.offerLast(right);

            // Shrink the window if the condition is violated
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > 2) {
                // If the max or min is at the left boundary, remove it
                if (maxDeque.peekFirst() == left) maxDeque.pollFirst();
                if (minDeque.peekFirst() == left) minDeque.pollFirst();
                left++; // Move the left pointer to shrink the window
            }

            // Add the number of valid subarrays ending at the current 'right'
            count += (right - left + 1);
        }

        return count; // Return the total count of valid subarrays
    }
}

/*TestCases:
    Example 1:

    Input: nums = [5,4,2,4]
    Output: 8
    Explanation: 
    Continuous subarray of size 1: [5], [4], [2], [4].
    Continuous subarray of size 2: [5,4], [4,2], [2,4].
    Continuous subarray of size 3: [4,2,4].
    Thereare no subarrys of size 4.
    Total continuous subarrays = 4 + 3 + 1 = 8.
    It can be shown that there are no more continuous subarrays.
 

    Example 2:

    Input: nums = [1,2,3]
    Output: 6
    Explanation: 
    Continuous subarray of size 1: [1], [2], [3].
    Continuous subarray of size 2: [1,2], [2,3].
    Continuous subarray of size 3: [1,2,3].
    Total continuous subarrays = 3 + 2 + 1 = 6.*/
 
