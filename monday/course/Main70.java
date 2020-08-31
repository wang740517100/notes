package cn.wangkf.monday.course;

/**
 * Created by stanley.wang on 2020/6/29.
 * 假设你正在爬楼梯。需要 _n_ 步你才能到达楼顶。

 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

 注意： 给定 _n_ 是一个正整数。

 示例 1：

 输入： 2
 输出： 2
 解释： 有两种方法可以爬到楼顶。
 1\.  1 步 + 1 步
 2\.  2 步

 示例 2：

 输入： 3
 输出： 3
 解释： 有三种方法可以爬到楼顶。
 1\.  1 步 + 1 步 + 1 步
 2\.  1 步 + 2 步
 3\.  2 步 + 1 步
 *
 *
 *
 */
public class Main70 {

    public static void main(String[] args) {

        System.out.println(climbStairs(3));
    }

    public static int climbStairs(int n) {
        if (n < 1) {
            return 0;
        } else if (n < 4) {
            return n;
        } else {
            int[] dp = new int[n + 1];
            //the number of ways to reach step n could be calculated from n-1 and n-2
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }




}
