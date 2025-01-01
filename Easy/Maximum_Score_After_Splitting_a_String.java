/*Intuition
    The goal is to split the binary string into two non-empty parts such that the sum of the number of 0s in the left part and the number of 1s in the right part is maximized. As we move the split point, we adjust the counts of 0s in the left and 1s in the right dynamically to calculate the score at each split.

Approach
    Count the total number of 1s in the string (count1).
    Traverse the string while maintaining two variables: count0 (number of 0s in the left) and count1 (remaining 1s in the right).
    For each valid split point, calculate the score as count0 + count1 and update the max score.

Complexity
    Time Complexity: O(n) (one pass to count 1s, another to compute scores).
    Space Complexity: O(1) (constant space for variables).

Code: 1422
*/
public class Maximum_Score_After_Splitting_a_String {
    public int maxScore(String s) {
        
        int n = s.length();
        int count1 = 0;
        int count0 = 0;

        for(int i=0;i<n;i++){
            if(s.charAt(i) == '1'){
                count1++;
            }
        }

        int max = 0;
        for(int i=0;i<n-1;i++){
            if(s.charAt(i) == '0'){
                count0++;
            }
            else{
                count1--;
            }
            max = Math.max(max,count1+count0);
        }
        return max;
    }
}

/*TestCases:
    Example 1:

        Input: s = "011101"
        Output: 5 
        Explanation: 
        All possible ways of splitting s into two non-empty substrings are:
        left = "0" and right = "11101", score = 1 + 4 = 5 
        left = "01" and right = "1101", score = 1 + 3 = 4 
        left = "011" and right = "101", score = 1 + 2 = 3 
        left = "0111" and right = "01", score = 1 + 1 = 2 
        left = "01110" and right = "1", score = 2 + 1 = 3
    Example 2:

        Input: s = "00111"
        Output: 5
        Explanation: When left = "00" and right = "111", we get the maximum score = 2 + 3 = 5
    Example 3:

        Input: s = "1111"
        Output: 3 
*/