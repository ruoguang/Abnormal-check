package com.cc270.oc.utils;

import com.cc270.oc.base.CCArithmetic;
import com.cc270.oc.base.impl.CCAritheticCore;

import java.util.*;

/**
 * @author CC
 * @version v2.0
 * @date 2021/5/11
 **/
public class CCUtil {

    private static CCArithmetic arithmetic = null;

    private static final int NUM_MIN = 3;

    /**
     * 敏感度系数
     */
    private static final int SENSITIVITY_ONE = 1;
    private static final int SENSITIVITY_TWO = 2;
    private static final int SENSITIVITY_THREE = 3;
    /**
     * 铭感度系数对应的数量
     */
    private static final int NUM_SENSITIVITY_TWO = 8;
    private static final int NUM_SENSITIVITY_THREE = 11;

    static {
        arithmetic = new CCAritheticCore();
    }

    /**
     * 判断数据源是否存在异常值
     *
     * @param source obj数组
     * @return
     */
    public static final boolean ocExist(Object... source) {
        double[] datas = sourceToDoubleArrays(source);
        return ocExistDetail(datas);

    }

    /**
     * 判断数据源是否存在异常值
     *
     * @param source obj数组
     * @return
     */
    public static final boolean ocExist(double... datas) {
        return ocExistDetail(datas);
    }

    /**
     * 判断数据源是否存在异常值
     *
     * @param source 集合
     * @return
     */
    public static final boolean ocExist(Collection source) {
        double[] datas = sourceToDoubleArrays(source);
        return ocExistDetail(datas);
    }

    private static boolean ocExistDetail(double[] datas) {
        int len = datas.length;
        if (len <= NUM_MIN) {
            return false;
        }
        if (len < NUM_SENSITIVITY_TWO) {
            return arithmetic.ocExist(SENSITIVITY_ONE, datas);
        }
        if (len < NUM_SENSITIVITY_THREE) {
            return arithmetic.ocExist(SENSITIVITY_TWO, datas);
        }
        return arithmetic.ocExist(SENSITIVITY_THREE, datas);
    }

    private static boolean ocExistDetail(double[] datas, Set<Integer> set) {
        int len = datas.length - set.size();
        if (len <= NUM_MIN) {
            return false;
        }
        if (len < NUM_SENSITIVITY_TWO) {
            return arithmetic.ocExist(SENSITIVITY_ONE, datas, set);
        }
        if (len < NUM_SENSITIVITY_THREE) {
            return arithmetic.ocExist(SENSITIVITY_TWO, datas, set);
        }
        return arithmetic.ocExist(SENSITIVITY_THREE, datas, set);
    }

    /**
     * 集合转double数组
     *
     * @param source
     * @return
     */
    private static double[] sourceToDoubleArrays(Collection source) {
        double[] datas = new double[source.size()];
        Iterator iterator = source.iterator();
        Object next = iterator.hasNext() ? iterator.next() : null;
        int index = 0;
        while (next != null) {
            datas[index++] = Double.parseDouble(next.toString());
            next = iterator.hasNext() ? iterator.next() : null;
        }
        return datas;
    }

    /**
     * obj数组转double数组
     *
     * @param source
     * @return
     */
    private static double[] sourceToDoubleArrays(Object[] source) {
        double[] datas = new double[source.length];
        String name = source.getClass().getName();
        switch (name) {
            case "java.lang.Double":
                for (int i = 0; i < source.length; i++) {
                    datas[i] = (double) source[i];
                }
                break;
            case "java.lang.Integer":
                for (int i = 0; i < source.length; i++) {
                    datas[i] = (double) source[i];
                }
                break;
            default:
                try {
                    for (int i = 0; i < source.length; i++) {
                        datas[i] = Double.parseDouble(source[i].toString());
                    }
                } catch (Exception e) {
                    throw new RuntimeException("source can not to narmal arrays");
                }
        }
        return datas;
    }


    public static double[] ocBase(Object... source) {
        double[] datas = sourceToDoubleArrays(source);
        return ocBaseDetail(datas);

    }

    public static double[] ocBase(Collection source) {
        double[] datas = sourceToDoubleArrays(source);
        return ocBaseDetail(datas);
    }

    private static double[] ocBaseDetail(double[] datas) {
        int len = datas.length;
        if (len <= NUM_MIN) {
            return datas;
        }
        if (len < NUM_SENSITIVITY_TWO) {
            return arithmetic.ocbase(SENSITIVITY_ONE, datas);
        } else if (len < NUM_SENSITIVITY_THREE) {
            return arithmetic.ocbase(SENSITIVITY_TWO, datas);
        } else {
            return arithmetic.ocbase(SENSITIVITY_THREE, datas);
        }
    }

    /**
     * 异常数的下标集合
     *
     * @param source
     * @return
     */
    public final static Set<Integer> ocSet(Object... source) {
        double[] datas = sourceToDoubleArrays(source);
        return ocSetDetail(datas);
    }

    /**
     * 异常数的下标集合
     *
     * @param source
     * @return
     */
    public final static Set<Integer> ocSet(Collection source) {
        double[] datas = sourceToDoubleArrays(source);
        return ocSetDetail(datas);
    }

    private static Set<Integer> ocSetDetail(double[] datas) {
        int len = datas.length;
        if (len <= NUM_MIN) {
            return new HashSet<>();
        }
        if (len < NUM_SENSITIVITY_TWO) {
            return arithmetic.oc(SENSITIVITY_ONE, datas);
        }
        if (len < NUM_SENSITIVITY_THREE) {
            return arithmetic.oc(SENSITIVITY_TWO, datas);
        }
        return arithmetic.oc(SENSITIVITY_THREE, datas);
    }

    /**
     * 融合后的数组 去掉异常值得数组
     *
     * @param source
     * @return
     */
    public final static double[] fusionList(Object... source) {
        double[] datas = sourceToDoubleArrays(source);
        return fusionListDetail(datas);
    }

    /**
     * 融合后的数组 去掉异常值得数组
     *
     * @param source
     * @return
     */
    public final static double[] fusionList(Collection source) {
        double[] datas = sourceToDoubleArrays(source);
        return fusionListDetail(datas);
    }

    private static double[] fusionListDetail(double[] datas) {
        int len = datas.length;
        if (len <= NUM_MIN) {
            return datas;
        }
        if (len < NUM_SENSITIVITY_TWO) {
            return arithmetic.fusionToList(SENSITIVITY_ONE, datas);
        }
        if (len < NUM_SENSITIVITY_THREE) {
            return arithmetic.fusionToList(SENSITIVITY_TWO, datas);
        }
        return arithmetic.fusionToList(SENSITIVITY_THREE, datas);
    }

    /**
     * 融合值
     *
     * @param source
     * @return
     */
    public static double fusionValue(Object... source) {
        double[] datas = sourceToDoubleArrays(source);
        return fusionValueDetail(datas);
    }

    /**
     * 融合值
     *
     * @param source
     * @return
     */
    public static double fusionValue(Collection source) {
        double[] datas = sourceToDoubleArrays(source);
        return fusionValueDetail(datas);
    }

    private static double fusionValueDetail(double[] datas) {
        int len = datas.length;
        if (len <= NUM_MIN) {
            return arithmetic.avg(datas);
        }
        if (len < NUM_SENSITIVITY_TWO) {
            return arithmetic.getFusionValue(SENSITIVITY_ONE, datas);
        }
        if (len < NUM_SENSITIVITY_THREE) {
            return arithmetic.getFusionValue(SENSITIVITY_TWO, datas);
        }
        return arithmetic.getFusionValue(SENSITIVITY_THREE, datas);
    }

    @Deprecated
    public static double finalFusionValue(Object... source) {
        double[] datas = sourceToDoubleArrays(source);
        return finalFusionValueDetail(datas);

    }

    @Deprecated
    private static double finalFusionValueDetail(double[] datas) {
        int len = datas.length;
        if (!ocExist(datas)) {
            return arithmetic.avg(datas);
        }
        if (len < NUM_SENSITIVITY_TWO) {
            datas = arithmetic.fusionToList(SENSITIVITY_ONE, datas);
            return finalFusionValueDetail(datas);
        }
        if (len < NUM_SENSITIVITY_THREE) {
            datas = arithmetic.fusionToList(SENSITIVITY_TWO, datas);
            return finalFusionValueDetail(datas);
        }
        datas = arithmetic.fusionToList(SENSITIVITY_THREE, datas);
        return finalFusionValueDetail(datas);
    }

    @Deprecated
    public static double finalFusionValue(Collection source) {
        double[] datas = sourceToDoubleArrays(source);

        return finalFusionValueDetail(datas);
    }

    @Deprecated
    public static final Set<Integer> finalSet(Object... source) {
        double[] datas = sourceToDoubleArrays(source);
        Set<Integer> res = new HashSet<>();
        return cocListDetail(datas, res);
    }

    @Deprecated
    public static final Set<Integer> finalSet(Collection source) {
        double[] datas = sourceToDoubleArrays(source);
        Set<Integer> res = new HashSet<>();
        return cocListDetail(datas, res);
    }

    @Deprecated
    private static Set<Integer> cocListDetail(double[] datas, Set<Integer> res) {
        int len = datas.length - res.size();
        if (!ocExistDetail(datas, res)) {
            return res;
        }
        if (len < NUM_SENSITIVITY_TWO) {
            arithmetic.coc(SENSITIVITY_ONE, datas, res);
            return cocListDetail(datas, res);
        }
        if (len < NUM_SENSITIVITY_THREE) {
            arithmetic.coc(SENSITIVITY_TWO, datas, res);
            return cocListDetail(datas, res);
        }
        arithmetic.coc(SENSITIVITY_THREE, datas, res);
        return cocListDetail(datas, res);
    }


}
