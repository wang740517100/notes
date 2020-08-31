package cn.wangkf.monday.course;

/**
 * Created by stanley.wang on 2020/6/28.
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。

  数组中的每个元素代表你在该位置可以跳跃的最大长度。

  你的目标是使用最少的跳跃次数到达数组的最后一个位置。

     示例:

     输入: [2,3,1,1,4]
     输出: 2
     解释: 跳到最后一个位置的最小跳跃数是 `2`。
          从下标为 0 跳到下标为 1 的位置，跳 `1` 步，然后跳 `3` 步到达数组的最后一个位置。

     说明:

     假设你总是可以到达数组的最后一个位置。
 */
public class Main23 {

    public static void main(String[] args) {
        int[] tar = {2, 3, 1, 1, 4};

        int res = jump(tar);

        System.out.println(res);
    }

    public static int jump(int[] nums) {
        int stepCount = 0; // 1 2 3
        int lastJumpMax = 0; // 1 4
        int currentJumpMax = 0; // 1 4 4 4 8
        for (int i = 0; i < nums.length - 1; i++) {
            currentJumpMax = Math.max(currentJumpMax, i + nums[i]);
            if (i == lastJumpMax) {
                stepCount ++;
                lastJumpMax = currentJumpMax;
            }
        }
        return stepCount;
    }

}
