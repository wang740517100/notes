package cn.wangkf.monday;

import java.util.*;

public class Demo {


    public static void main(String[] args)  {


        Integer a = new Integer(56);

        List<Integer> numArr = new ArrayList<>();
        numArr.add(1);
        numArr.add(2);
        numArr.add(3);
        numArr.add(4);
        Iterator<Integer> iterator = numArr.iterator();
        while (iterator.hasNext()) {
//            numArr.removeIf(num -> num == 3);
            Integer num = iterator.next();
            if (num == 3) {
                iterator.remove();
            }
        }
    }
}
