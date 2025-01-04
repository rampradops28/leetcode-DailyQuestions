/*Intuition
    The problem requires finding the number of ways to split an array into two non-empty subarrays such that the sum of the left subarray is greater than or equal to the sum of the right subarray. 
    By maintaining a running sum of elements traversed so far (add) and subtracting it from the total sum (sum), we can efficiently calculate the right subarray's sum on each iteration.

Approach
    Calculate the total sum of the array (sum).
    Use a running sum (add) to keep track of the sum of the left subarray as you iterate through the array.
    For each split point i (excluding the last index), calculate the sum of the right subarray as sums = sum - add.
    Check if the left subarray (add) is greater than or equal to the right subarray (sums).
    Increment the count if the condition is met.
    Return the count of valid splits.

Complexity
    Time Complexity:
        O(n): One pass to calculate the total sum and another to compute the splits.
    Space Complexity:
        O(1): Only a few variables are used, no additional data structures.

Code: 2270 */

//My Solution
class Solution {
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        long sum = 0;
        long count = 0;
        long add = 0;

        for(int i=0;i<n;i++){
            sum += nums[i];
        }

        for(int i=0;i<n-1;i++){
            add += (long)nums[i];
            long sums = sum - add;

            if(add >= sums){
                count++;
            }

            System.out.println(sums);
        }
        return (int)count;
    }
}

/*Test Cases:

    Example 1:
        Input: nums = [10,4,-8,7]
        Output: 2
        Explanation: 
        There are three ways of splitting nums into two non-empty parts:
        - Split nums at index 0. Then, the first part is [10], and its sum is 10. The second part is [4,-8,7], and its sum is 3. Since 10 >= 3, i = 0 is a valid split.
        - Split nums at index 1. Then, the first part is [10,4], and its sum is 14. The second part is [-8,7], and its sum is -1. Since 14 >= -1, i = 1 is a valid split.
        - Split nums at index 2. Then, the first part is [10,4,-8], and its sum is 6. The second part is [7], and its sum is 7. Since 6 < 7, i = 2 is not a valid split.
        Thus, the number of valid splits in nums is 2.

    Example 2:
        Input: nums = [2,3,1,0]
        Output: 2
        Explanation: 
        There are two valid splits in nums:
        - Split nums at index 1. Then, the first part is [2,3], and its sum is 5. The second part is [1,0], and its sum is 1. Since 5 >= 1, i = 1 is a valid split. 
        - Split nums at index 2. Then, the first part is [2,3,1], and its sum is 6. The second part is [0], and its sum is 0. Since 6 >= 0, i = 2 is a valid split.
        */