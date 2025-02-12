 /*Intuition 
    The task is to find the maximum sum of two distinct numbers in an array that have the same digit sum. To achieve this efficiently, we can use a HashMap to keep track of the largest number for each digit sum encountered so far.

Approach

    Digit Sum Calculation: Create a helper function digitSum that calculates the sum of the digits of a given number.
                           Use a HashMap: Use a HashMap where the key is the digit sum and the value is the maximum number encountered with that digit sum.
    Iterate Through the Array: For each number in the array:
                                Calculate its digit sum.
                                Check if the digit sum is already in the HashMap.
                                If it is, update the maximum sum (maxi) using the current number and the value stored in the map.
                                Update the map with the maximum of the current number and the stored value.
                                If it is not, add the digit sum and the current number to the map.
    Return Result: After processing all numbers, return the maximum sum (maxi) found. If no pairs with the same digit sum were found, return -1.

Time Complexity
    Digit Sum Calculation: The digitSum function runs in (O(\log_{10}(num))) time, which simplifies to (O(1)) for fixed integer sizes.
    Main Loop: The main loop iterates through the array once, resulting in (O(n)) time complexity.
    HashMap Operations: Insertion and lookup operations in the HashMap are (O(1)) on average.
    Overall, the time complexity is (O(n)).

Space Complexity
    HashMap: The HashMap stores at most n key-value pairs, where n is the number of elements in the input array.
Auxiliary Space: The space used by the digitSum function is (O(1)).
    Overall, the space complexity is (O(n)). 

Code : 2342
*/

import java.util.HashMap;
import java.util.Map;

public class Max_Sum_of_a_Pair_With_Equal_Sum_of_Digits {
    public static void main(String[] args) { 
        int[] nums = {18, 43, 36, 13, 7};
        System.out.println(maximumSum(nums));  // Expected output: 54
    }
    public static int digitSum(int num){ 
        int rem,sum = 0;
        while(num > 0){
            rem = num%10;
            sum += rem;
            num /= 10;
        } 
        return sum;
    }
    public static int maximumSum(int[] nums) {

       int n = nums.length; 
       long maxi = -1;
       Map<Integer, Integer > hashMap = new HashMap<>();

       for(int i=0;i<n;i++){
            int currentDigitSum = digitSum(nums[i]);
            if(hashMap.containsKey(currentDigitSum)){
                int previousElement = hashMap.get(currentDigitSum);
                maxi = Math.max(maxi , nums[i] + previousElement);
                hashMap.put(currentDigitSum , Math.max(previousElement, nums[i]));
            }
            else{
                hashMap.put(currentDigitSum , nums[i]);
            }
       }  
        return (int)maxi;

    }
}


/*TestCases
    Example 1:

    Input: nums = [18,43,36,13,7]
    Output: 54
    Explanation: The pairs (i, j) that satisfy the conditions are:
    - (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
    - (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
    So the maximum sum that we can obtain is 54.
    Example 2:

    Input: nums = [10,12,19,14]
    Output: -1
    Explanation: There are no two numbers that satisfy the conditions, so we return -1. */