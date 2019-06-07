package com.cc270.oc.utils;

import com.cc270.oc.base.ICCArithmetic;
import com.cc270.oc.base.impl.CCAritheticImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CC
 * @version v1.0
 * @date 2019/6/7 16:27
 * @description TODO
 **/
public class CCUtil {

    private static ICCArithmetic arithmetic = null;

    static {
        arithmetic = new CCAritheticImpl();
    }

    public static List ocBaseList(int times, List list) {
        return arithmetic.ocbase(times, list);
    }

    public static List ocList(int times, List list) {
        return arithmetic.oc(times, list);
    }

    public static List fusionList(int times, List list) {
        return arithmetic.fusionToList(times, list);
    }

    public static Map autoOcMap(List list) {
        HashMap<Integer, Object> map = new HashMap<>();
        if (list == null) {
            return null;
        } else {
            int size = list.size();
            if (size < 3) {
                return null;
            }
            if (size < 8) {
                List<Double> index = arithmetic.ocbase(1, list);
                for (int i = 0; i < index.size(); i++) {
                    if (index.get(i) > 0) {
                        map.put(i, list.get(i));
                    }
                }
            }
            if (size < 11) {
                List<Double> index = arithmetic.ocbase(2, list);
                for (int i = 0; i < index.size(); i++) {
                    if (index.get(i) > 0) {
                        map.put(i, list.get(i));
                    }
                }
            }
            if (size >= 11) {
                List<Double> index = arithmetic.ocbase(3, list);
                for (int i = 0; i < index.size(); i++) {
                    if (index.get(i) > 0) {
                        map.put(i, list.get(i));
                    }
                }
            }
            return map;
        }

    }

    public static List<Double> autoFusionList(List list) {
        List<Double> doubles = new ArrayList<>();
        if (list == null) {
            return null;
        } else {
            int size = list.size();
            if (size < 3) {
                return list;
            }
            if (size < 8) {
                doubles = arithmetic.fusionToList(1, list);
            }
            if (size < 11) {
                doubles = arithmetic.fusionToList(2, list);
            }
            if (size >= 11) {
                doubles = arithmetic.fusionToList(3, list);
            }
            return doubles;
        }
    }

    public static double fusionValue(List list) {
        return arithmetic.avg(list);
    }
}
