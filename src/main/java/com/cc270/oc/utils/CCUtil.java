package com.cc270.oc.utils;

import com.cc270.oc.base.CCArithmetic;
import com.cc270.oc.base.impl.CCAritheticCore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author CC
 * @version v1.0
 * @date 2019/6/7 16:27
 **/
public class CCUtil {

    private static CCArithmetic arithmetic = null;

    static {
        arithmetic = new CCAritheticCore();
    }

    public static boolean ocExist(List list) {
        if (list == null) {
            return false;
        } else {
            int size = list.size();
            if (size <= 3) {
                return false;
            }
            if (size < 8) {
                for (Object o : arithmetic.ocbase(1, list)) {
                    if (Double.parseDouble(o.toString()) > 0) {
                        return true;
                    }
                }
            }
            if (size < 11) {
                for (Object o : arithmetic.ocbase(2, list)) {
                    if (Double.parseDouble(o.toString()) > 0) {
                        return true;
                    }
                }
            }
            if (size >= 11) {
                for (Object o : arithmetic.ocbase(3, list)) {
                    if (Double.parseDouble(o.toString()) > 0) {
                        return true;
                    }
                }
            }
            throw new RuntimeException("Judgment of failure！");
        }
    }

    public static List< Double> ocBase(List list) {
        List<Double> doubles = new ArrayList<>();
        if (list == null) {
            doubles = null;
        } else {
            int size = list.size();
            if (size <= 3) {
                doubles = null;
            }
            if (size < 8) {
                doubles = arithmetic.ocbase(1, list);
            }
            if (size < 11) {
                doubles = arithmetic.ocbase(2, list);
            }
            if (size >= 11) {
                doubles = arithmetic.ocbase(3, list);
            }
        }
        return doubles;
    }

    public static Map<Integer, Double> ocMap(List list) {
        Map<Integer, Double> map = new HashMap<>();
        if (list == null) {
            map = null;
        } else {
            int size = list.size();
            if (size <= 3) {
                map = null;
            }
            if (size < 8) {
                map = arithmetic.oc(1, list);
            }
            if (size < 11) {
                map = arithmetic.oc(2, list);
            }
            if (size >= 11) {
                map = arithmetic.oc(3, list);
            }
        }
        return map;
    }

    public static List<Double> fusionList(List list) {
        List<Double> doubles = new ArrayList<>();
        if (list == null) {
            doubles = null;
        } else {
            int size = list.size();
            if (size <= 3) {
                doubles = null;
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
        }
        return doubles;
    }

    public static double fusionValue(List list) {
        double result = 0;
        if (list == null) {
            result = 0;
        } else {
            int size = list.size();
            if (size <= 3) {
                result = 0;
            }
            if (size < 8) {
                result = arithmetic.getFusionValue(1, list);
            }
            if (size < 11) {
                result = arithmetic.getFusionValue(2, list);
            }
            if (size >= 11) {
                result = arithmetic.getFusionValue(3, list);
            }
        }
        return result;
    }

    private static  int cocMapIndex = 0;
    private static Map<Integer, List<Double>> resultMap = new HashMap<>();

    public static synchronized Map<Integer, List<Double>> cocMap(List list) {
        if (!ocExist(list)) {
            Map<Integer, List<Double>> coctMap = resultMap;
            resultMap=null;
            cocMapIndex = 0;
            return coctMap;
        }
        Map<Integer, Double> temp = ocMap(list);
        resultMap.put(cocMapIndex++,temp.values().stream().collect(Collectors.toList()));
        return cocMap(new ArrayList<>(resultMap.values()));
    }
}
