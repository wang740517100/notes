package cn.wangkf.monday.sort;

import java.util.Arrays;

/**
 * Created by stanley.wang on 2020/6/17.
 */
public class BubbleSort {

    public static void main(String[] args) {
        Integer[] array = Arrays.asList(2, 8, 6, 4, 5).toArray(new Integer[5]);
        sort(array);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    public static void sort(Integer[] array) {
        // int len = array.length;  assign 和 store
        // if (array == null || len =  == 0 || len == 1)  use

        int len;
        // 这样写应该是因为执行引擎少从栈里面取(use)一次操作  assign 和 store
        if (array == null || (len = array.length) == 0 || len == 1) {
            return ;
        }
        for (int i=0; i<array.length-1; i++) {
            for (int j=0; j<array.length-1-i; j++) {
                if (array[j] <= array[j+1]) {
                    continue;
                }
                array[j] = array[j] ^ array[j+1];
                array[j+1] = array[j] ^ array[j+1];
                array[j] = array[j] ^ array[j+1];
            }
        }
    }

}
