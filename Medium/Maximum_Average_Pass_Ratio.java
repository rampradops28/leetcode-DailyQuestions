/*Intuition:
    The goal is to maximize the average pass ratio by distributing extraStudents optimally. To do so:
        # Assign each extra student to the class that gains the most improvement in the pass ratio.
        # Use a max-heap to always pick the class with the highest potential improvement.

Approach:
    Heap Initialization:
        For each class, calculate the potential improvement in pass ratio if an extra student is added.
        Store these improvements along with the class index in a max-heap.

    Distribute Extra Students:
        While there are extra students, repeatedly:
        Pop the class with the highest improvement from the heap.
        Add a passing student to that class.
        Recalculate the new improvement for that class and push it back into the heap.

    Final Calculation:
        Once all extra students are assigned, compute the final average pass ratio by summing up the pass ratios of all classes and dividing by the number of classes.

Time Complexity:
    Heap Initialization:
        For each class, calculating the initial improvement takes O(1).  With n classes, this is O(n).

    Heap Operations:
        Adding or removing from the heap takes O(logn).Doing this for extraStudents operations results in O(extraStudents . logn).

    Final Calculation:
        Summing pass ratios takes O(n)

    Total Time Complexity:
        O(n + extraStudents.logn)
 
Code: 1792 */

// My Approach
/*class Solution {
    public static double avg(int ind, int n, int m,int[][] classes){

        double sum = 0;
        double avg = 0;
        double res = 0;

        for(int j=0;j<m;j++){
            classes[ind][j] += 2;
        }

        for(int i=0;i<n;i++){
           sum += (float)classes[i][0] / classes[i][1]; 
           System.out.println("sum:" +sum);
        }
        avg = sum / n;

        System.out.println(avg);
        res = Math.round(avg * 100000.0)/100000.0; 
        System.out.println(res);

        for(int j=0;j<m;j++){
            classes[ind][j] -= 2;
        }
        return res;

    }
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        
        int n = classes.length;
        int m = classes[0].length;

        double maxAvg = 0;
        for(int i=0;i<n;i++){
            double ans = avg(i,n,m,classes);
            maxAvg = Math.max(ans,maxAvg);

        }

        return maxAvg;
    }
}*/

// optimal

import java.util.PriorityQueue;

class Maximum_Average_Pass_Ratio {
    public double maxAverageRatio(int[][] classes, int extraStudents) {

        int n = classes.length; 
        // Max-heap to store the potential improvement for each class
        PriorityQueue<double[]> max = new PriorityQueue<>((a, b) -> 
            Double.compare(b[0], a[0]) // Sort by max improvement (descending)
        );

        // Calculate the initial improvement if an extra student is added to each class
        for (int i = 0; i < n; i++) {
            double curr = (double) classes[i][0] / classes[i][1]; // Current pass ratio
            double neww = (double) (classes[i][0] + 1) / (classes[i][1] + 1); // Pass ratio after adding 1 student
            double tot = neww - curr; // Improvement in pass ratio
            max.offer(new double[]{tot, i}); // Store improvement and class index in the heap
        }

        // Assign extra students to maximize the average pass ratio
        while (extraStudents-- > 0) {
            double[] top = max.poll(); // Get class with the highest improvement
            int ind = (int) top[1]; // Extract class index
            classes[ind][0]++; // Add a passing student
            classes[ind][1]++; // Increase total students

            // Recalculate the improvement for the updated class
            double curr = (double) classes[ind][0] / classes[ind][1];
            double neww = (double) (classes[ind][0] + 1) / (classes[ind][1] + 1);
            double tot = neww - curr;
            max.offer(new double[]{tot, ind}); // Push updated improvement back to the heap
        }

        // Calculate the final average pass ratio
        double avg = 0.0;
        for (int i = 0; i < n; i++) {
            avg += (double) classes[i][0] / classes[i][1];
        }

        return avg / n; // Return the average pass ratio
    }
}

/*TestCases:

    Example 1:
    Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
    Output: 0.78333
    Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
    
    Example 2:
    Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
    Output: 0.53485 */
