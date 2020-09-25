package cn.wangkf.monday.sort;

import java.util.Arrays;

/**
 * 快速排序 nlogn logn 不稳定的
 * 1、把数组的第一个数值作为基准数
 * 2、定义左哨兵和右哨兵，左哨兵向右找到比基准数大的数，右哨兵向左找到比基准数小的数，交换位置
 * 3、循环交换知道两个哨兵相遇，交换基准数和相遇位置的数
 * 4、以基准数为准把左右边分为两个数组递归执行上面步骤
 *
 * Created by stanley.wang on 2020/6/17.
 */
public class QuickSort {

    public static void main(String[] args) {
        Integer[] array = Arrays.asList(2, 8, 6, 4, 5).toArray(new Integer[5]);
        quickSort(array);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    public static void quickSort(Integer[] array) {
        int len;
        if(array == null || (len = array.length) == 0 || len == 1) {
            return ;
        }
        sort(array, 0, len - 1);
    }

    public static void sort(Integer[] array, int left, int right) {
        if(left > right) {
            return;
        }
        // base中存放基准数
        int base = array[left];
        int i = left, j = right;
        while(i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while(array[j] >= base && i < j) {
                j--;
            }
            // 再从左往右边找，直到找到比base值大的数
            while(array[i] <= base && i < j) {
                i++;
            }
            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if(i < j) {
                array[i] = array[i] ^ array[j];
                array[j] = array[i] ^ array[j];
                array[i] = array[i] ^ array[j];
            }
        }
        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;
        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }

}
