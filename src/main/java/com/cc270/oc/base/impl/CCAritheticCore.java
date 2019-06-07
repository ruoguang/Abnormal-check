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
    public Map<Integer, Double> oc(int sensitivity, List<Double> list) {
        Map<Integer, Double> map = new HashMap<>();
        List<Double> ocbase = ocbase(sensitivity, list);
        for (int i = 0; i < ocbase.size(); i++) {
            if (ocbase.get(i) > 0) {
                map.put(i, list.get(i));
            }
        }
        return map;
    }

    @Override
    public double avg(List list) {
        return sum(list) / count(list);
    }

    @Override
    public double sum(List list) {
        double result = 0;
        if (list != null) {
            String name = list.get(0).getClass().getName();
            switch (name) {
                case "java.lang.String":
                    for (Object o : list) {
                        result += Double.parseDouble(o.toString());
                    }
                    break;
                case "java.lang.Double":
                    for (Object o : list) {
                        result += Double.parseDouble(o.toString());
                    }
                    break;
                case "java.lang.Integer":
                    for (Object o : list) {
                        result += Double.parseDouble(o.toString());
                    }
                    break;
                default:
                    throw new RuntimeException("error params");
            }
        }

        return result;
    }

    @Override
    public int count(List list) {
        return list.size();
    }

    @Override
    public List<Double> re(List list) {
        ArrayList<Double> doubles = new ArrayList<>();

        double avg = avg(list);
        for (int i = 0; i < list.size(); i++) {
            doubles.add(Double.parseDouble(list.get(i).toString()) - avg);
        }
        return doubles;
    }

    @Override
    public double sd(List<Double> list) {
        double temp = 0;
        List<Double> re = re(list);
        for (Double o : re) {
            temp += Math.pow(o, 2);
        }
        return Math.sqrt(temp / (count(list) - 1));
    }

    @Override
    public List<Double> fusionToList(int sensitivity, List<Double> list) {
        List<Integer> keyList = new ArrayList<>(oc(sensitivity, list).keySet());
        for (int i = keyList.size() - 1; i >= 0; i--) {
            list.remove(keyList.get(i).intValue());
        }
        return list;
    }

    @Override
    public List<Double> ocbase(int sensitivity, List list) {
        ArrayList<Double> doubles = new ArrayList<>();
        double result = sensitivity * sd(list);
        double avg=avg(list);
        for (Object o : list) {
            double abs = Math.abs(Double.parseDouble(o.toString()) - avg);
            doubles.add(abs - result);
        }
        return doubles;
    }

    @Override
    public double getFusionValue(int sensitivity, List<Double> list) {
        return avg(list);
    }


}
