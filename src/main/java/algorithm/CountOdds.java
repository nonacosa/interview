package algorithm;

/**
 * https://leetcode-cn.com/problems/count-odd-numbers-in-an-interval-range/
 */
public class CountOdds {


    // 1 5      1 1 3 3
    // 2 6      2 2 3 2
    // 2 4      2 2 1 1
    // 2 5      2 1 2 2
    // 2 7      2 1 4 3
    public int countOdds(int low, int high) {
        if (low % 2 == 0 && high % 2 == 0) {
            return (high - low) / 2;
        } else {
            return (high - low) / 2 + 1;
        }
    }


    public static void main(String[] args) {
        System.out.println(new CountOdds().countOdds(8,10));
    }
}
