package com.cc270.oc;

import com.cc270.oc.utils.CCUtil;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author CC
 * @version v1.0
 * @date 2019/6/7 12:59
 * @description TODO
 **/
public class Test {

    @org.junit.Test
    public void test(){
        ArrayList<Double> arrayList = new ArrayList<>();
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
        System.out.println(CCUtil.ocExist(arrayList));

        System.out.println(CCUtil.ocBase(arrayList));

        System.out.println(CCUtil.cocMap(arrayList));

        System.out.println(CCUtil.ocMap(arrayList));

        System.out.println(CCUtil.fusionList(arrayList));

        System.out.println(CCUtil.fusionValue(arrayList));

    }
}
