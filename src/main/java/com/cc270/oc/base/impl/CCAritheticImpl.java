package com.cc270.oc.base.impl;

import com.cc270.oc.base.ICCArithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CC
 * @version v1.0
 * @date 2019/6/7 12:15
 **/
public class CCAritheticImpl implements ICCArithmetic {


    @Override
    public List<Double> oc(int times,List list) {
        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<Double> doubles = new ArrayList<>();
        List ocbase = ocbase(times, list);
        for (int i = 0; i < ocbase.size(); i++) {
            if(Double.parseDouble(ocbase.get(i).toString())>0){
                temp.add(i);
            }
        }
        for (Integer integer : temp) {
            doubles.add(Double.parseDouble(list.get(integer).toString()));
        }

        return doubles;
    }

    @Override
    public double avg(List list) {
        return sum(list)/count(list);
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
        return list==null?0:list.size();
    }

    @Override
    public List<Double> re(List list) {
        ArrayList<Double> doubles = new ArrayList<>();
        double avg=avg(list);
        for (Object o : list) {
            doubles.add(Double.parseDouble(o.toString())-avg);
        }
        return doubles;
    }

    @Override
    public double sd(List list) {
        double sum=0;
        List re = re(list);
        for (Object o : re) {
            sum+=Math.pow(Double.parseDouble(o.toString()),2);
        }
        int n=count(list)-1;
        return Math.sqrt(sum/n);
    }

    @Override
    public List<Double> fusionToList(int times, List<?> list) {
        List newList=list;
        List oc = ocbase(times, list);
        for (int i = oc.size() - 1; i >= 0; i--) {
            if(Double.parseDouble(oc.get(i).toString())>0){
                newList.remove(i);
            }
        }
        return newList;
    }

    @Override
    public List<Double> ocbase(int times, List list) {
        ArrayList<Double> doubles = new ArrayList<>();
        double avg=avg(list);
        double timessd=times*sd(list);
        for (Object o : list) {
            double abs = Math.abs(Double.parseDouble(o.toString()) - avg);
            doubles.add(abs-timessd);
        }
        return doubles;
    }
}
