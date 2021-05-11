package com.cc270.oc.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.cc270.oc.utils.CCUtil;

public class Test {
    public static void main(String[] args) {
        List<Double> arrayList = new ArrayList<>();
        Collections.addAll(
                arrayList,
                58.994,
                58.976,
                58.983,
                58.992,
                59.004,
                59.024,
                59.024,
                59.034,
                59.014,
                58.044,
                59.054,
                59.064,
                59.074
        );
        System.out.println("*****源数据*****");
        for (Double aDouble : arrayList) {
            System.out.println(aDouble);
        }
        System.out.println("*****是否存在异常值*****");
        System.out.println(CCUtil.ocExist(arrayList));
        System.out.println("*****融合后的数组数据*****");
        for (double v : CCUtil.fusionList(arrayList)) {
            System.out.println(v);
        }
        System.out.println("*****异常数组下标集合*****");
        System.out.println(CCUtil.ocSet(arrayList));
        System.out.println("*****融合结果*****");
        System.out.println(CCUtil.fusionValue(arrayList));
        System.out.println("*****最终异常数组下标*****");
        System.out.println(CCUtil.finalSet(arrayList));
        System.out.println("*****最终融合值*****");
        System.out.println(CCUtil.finalFusionValue(arrayList));
    }
}
