package com.cc270.oc.base.impl;

import com.cc270.oc.base.CCArithmetic;

import java.util.*;


/**
 * cc算法的核心类
 *
 * @author CC
 * @version v1.0
 * @date 2019/6/7 12:15
 **/
public class CCAritheticCore implements CCArithmetic {

    @Override
    public Set<Integer> oc(int sensitivity, double[] datas) {
        Set<Integer> set = new HashSet<>();
        double[] ocbase = ocbase(sensitivity, datas);
        for (int i = 0; i < ocbase.length; i++) {
            if (ocbase[i] > 0) {
                set.add(i);
            }
        }
        return set;
    }

    @Override
    public void coc(int sensitivity, double[] datas, Set<Integer> res) {
        double[] ocbase = ocbase(sensitivity, datas, res);
        for (int i = 0; i < ocbase.length; i++) {
            if (!res.contains(i) && ocbase[i] > 0) {
                res.add(i);
            }
        }
    }

    @Override
    public double avg(double[] datas, Set<Integer> set) {
        return sum(datas, set) / (datas.length - set.size());
    }

    @Override
    public double avg(double[] datas) {
        return sum(datas) / (datas.length);
    }

    @Override
    public double sum(double[] datas, Set<Integer> set) {
        double result = 0D;
        int index = 0;
        for (double data : datas) {
            if (!set.contains(index)) {
                result += data;
            }
            index++;
        }
        return result;
    }

    @Override
    public double sum(double[] datas) {
        double result = 0D;
        for (double data : datas) {
            result += data;
        }
        return result;
    }

    @Override
    public int count(List list) {
        return list.size();
    }

    @Override
    public double[] re(double[] datas, Set<Integer> set) {
        double[] doubles = new double[datas.length];
        double avg = avg(datas, set);
        for (int i = 0; i < datas.length; i++) {
            if (!set.contains(i)) {
                doubles[i] = datas[i] - avg;
            }
        }
        return doubles;
    }

    @Override
    public double sd(double[] datas, Set<Integer> set) {
        double temp = 0D;
        double[] re = re(datas, set);
        int index = 0;
        for (double o : re) {
            if (!set.contains(index)) {
                temp += Math.pow(o, 2);
            }
            index++;
        }
        return Math.sqrt(temp / (datas.length - set.size() - 1));
    }


    @Override
    public double sd(double[] datas) {
        Set<Integer> set = new HashSet<>();
        return sd(datas, set);
    }

    @Override
    public double[] fusionToList(int sensitivity, double[] datas) {
        Set<Integer> set = oc(sensitivity, datas);
        double[] doubles = new double[datas.length - set.size()];
        Iterator<Integer> iterator = set.iterator();
        Integer next = iterator.hasNext() ? iterator.next() : null;
        int index = 0;
        for (int i = 0; i < datas.length; i++) {
            if (next != null && next.equals(i)) {
                next = iterator.hasNext() ? iterator.next() : null;
                continue;
            }
            doubles[index++] = datas[i];
        }
        return doubles;
    }

    @Override
    public double[] ocbase(int sensitivity, double[] datas) {
        double[] doubles = new double[datas.length];
        double result = sensitivity * sd(datas);
        double avg = avg(datas);
        int index = 0;
        for (double o : datas) {
            doubles[index++] = Math.abs(o - avg) - result;
        }
        return doubles;
    }


    @Override
    public double[] ocbase(int sensitivity, double[] datas, Set<Integer> set) {
        double[] doubles = new double[datas.length];
        double result = sensitivity * sd(datas, set);
        double avg = avg(datas, set);
        int index = 0;
        for (double o : datas) {
            if (!set.contains(index)) {
                doubles[index] = Math.abs(o - avg) - result;
            }
            index++;
        }
        return doubles;

    }

    @Override
    public double getFusionValue(int sensitivity, double[] datas) {
        return avg(fusionToList(sensitivity, datas));
    }

    @Override
    public boolean ocExist(int sensitivity, double[] datas) {
        double result = sensitivity * sd(datas);
        double avg = avg(datas);
        for (double o : datas) {
            if (Math.abs(o - avg) - result > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean ocExist(int sensitivity, double[] datas, Set<Integer> set) {
        double result = sensitivity * sd(datas, set);
        double avg = avg(datas, set);
        int index = 0;
        for (double o : datas) {
            if (!set.contains(index)) {
                if (Math.abs(o - avg) - result > 0) {
                    return true;
                }
            }
            index++;
        }
        return false;
    }
}
