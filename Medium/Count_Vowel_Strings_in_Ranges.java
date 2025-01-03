/*Intuition:
    The problem involves checking if words in a specific range start and end with vowels. To optimize the brute-force approach, 
    we use a prefix sum to precompute cumulative counts of valid words (starting and ending with vowels). This allows quick 
    computation for any query by subtracting prefix sums.

Approach:
    Create a prefix array to store cumulative counts of words starting and ending with vowels.
    For each query [left, right], calculate the count using:

Complexity:
    Time: O(n*L+q), where n is the number of words, L is average word length, and q is the number of queries.
    Space: O(n)

code: 2559 */

// My Approach

class Solution {
    public static int range(String[] words,int left,int right){

        String vowel = "aeiou";
        int count = 0;

        for(int i=left;i<=right;i++){
            String word = words[i];

            char first = word.charAt(0);
            char last = word.charAt(word.length()-1);

            if(vowel.indexOf(first) != -1 && vowel.indexOf(last) != -1){
                    count++;
            }
        }
        return count;
    }

    public int[] vowelStrings(String[] words, int[][] queries) {
        
        int n = words.length;
        int r = queries.length;
        int c = queries[0].length;
        int[] result = new int[r];

        for(int i=0;i<r;i++){
            result[i] = range(words,queries[i][0],queries[i][1]);
        }

        return result;
    }
}

//optimal code

class Solutions {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        String vowels = "aeiou";
        int[] prefix = new int[n + 1]; // Prefix sum array
        int[] result = new int[queries.length];

        // Step 1: Build the prefix sum array
        for (int i = 0; i < n; i++) {
            char first = words[i].charAt(0);
            char last = words[i].charAt(words[i].length() - 1);
            prefix[i + 1] = prefix[i] + (vowels.indexOf(first) != -1 && vowels.indexOf(last) != -1 ? 1 : 0);
        }

        // Step 2: Process each query using the prefix sum array
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            result[i] = prefix[right + 1] - prefix[left];
        }

        return result;
    }
}

// TestCases:

//     Example 1:
//         Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
//         Output: [2,3,0]
//         Explanation: The strings starting and ending with a vowel are "aba", "ece", "aa" and "e".
//         The answer to the query [0,2] is 2 (strings "aba" and "ece").
//         to query [1,4] is 3 (strings "ece", "aa", "e").
//         to query [1,1] is 0.
//         We return [2,3,0].

//     Example 2:
//         Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
//         Output: [3,2,1]
//         Explanation: Every string satisfies the conditions, so we return [3,2,1].