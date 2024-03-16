import com.moncoder.lingo.common.api.Result;

import java.util.Arrays;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO
 * @date 2024/3/9 18:14
 */
import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        int[][] dp = new int[n][n];
        dp[0][0] = a[0][0];
        for(int i = 1; i < n; i++){
            dp[i][0] = dp[i-1][0] + a[i][0];
        }
        for(int j = 1; j < n; j++){
            dp[0][j] = dp[0][j - 1] + a[0][j];
        }
        for (int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]) + a[i][j];
            }
        }
        System.out.println(dp[n-1][n-1]);
    }


}
