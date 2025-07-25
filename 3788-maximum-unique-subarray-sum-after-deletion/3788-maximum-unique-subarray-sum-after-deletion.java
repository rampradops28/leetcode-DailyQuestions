class Solution {

    // All values are negative → best to choose the largest single number.

    // All elements are same → pick one of them.

    // All elements are unique → take the entire array.

    public int maxSum(int[] nums) {

        // Step 1: Initialize max as the smallest integer possible
        int max = Integer.MIN_VALUE;

        // Flag to check if all numbers are negative
        boolean isNegative = true;

        // Loop through all numbers
        for (int n : nums) {

            // If we find any positive number, it's not all negative
            if (n >= 0) {
                isNegative = false;
            }

            // Track the largest number found
            if (max < n) {
                max = n;
            }
        }

        // Special Case: If all numbers are negative, return the highest one
        if (isNegative) {
            return max;
        }

        // Step 2: Use a HashSet to store only unique numbers
        Set<Integer> s = new HashSet<>();

        for (int i : nums) {
            s.add(i); // Add each number once (removes duplicates automatically)
        }

        // Step 3: Sum only the positive unique values
        int sum = 0;
        for (int j : s) {
            if (j > 0) {
                sum += j;
            }
        }

        // Step 4: Return the final sum
        return sum;
    }
}
