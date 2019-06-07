package com.cc270.oc.base;

import java.util.List;

public interface ICCArithmetic {
    List<Double> oc(int times,List<?> list);
    double avg(List<?> list);
    double sum(List<?> list);
    int count(List<?> list);
    List<Double> re(List<?> list);
    double sd(List<?> list);
    List<Double> ocbase(int times,List<?> list);
    List<Double> fusionToList(int times,List<?> list);

}
