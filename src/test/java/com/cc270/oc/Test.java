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
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(
                arrayList,
                23+"",
                26+"",
                26+"",
                23+"",
                32+"",
                22+"",
                23+"",
                23+"");

        System.out.println(CCUtil.autoOcMap(arrayList));

        System.out.println(CCUtil.autoFusionList(arrayList));

        System.out.println(CCUtil.fusionValue(arrayList));

    }
}
