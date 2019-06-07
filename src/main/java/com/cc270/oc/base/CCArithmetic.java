package com.cc270.oc.base;

import java.util.List;
import java.util.Map;

/**
 * cc算法的核心接口
 * @author CC
 * @version v1.0
 * @date 2019/6/7 12:15
 *
 */
public interface CCArithmetic {


    /**
     * 求一个集合的算数平均值
     * @param list 原集合
     * @return 集合的算数平均数
     */
    double avg(List list);

    /**
     * 求一个集合的总和
     * @param list 集合
     * @return 集合的总和值
     */
    double sum(List list);

    /**
     * 求集合元素的数量
     * @param list 集合
     * @return 元素的数量
     */
    int count(List list);

    /**
     * 剩余误差的集合
     * @param list 原集合
     * @return 剩余误差的集合
     */
    List<Double> re(List<Double> list);

    /**
     * 标准差的计算
     * @param list 原集合
     * @return 集合的标准差
     */
    double sd(List<Double> list);

    /**
     * 求集合在nσ准则公式判断的值，值=|当前值-算数平均值|-敏感度*标准差
     * @param sensitivity 敏感度
     * @param list 原集合
     * @return 值是否在nσ准则内，大于0是范围外，等于0是范围临界值，小于0是正常范围值。
     */
    List<Double> ocbase(int sensitivity,List<Double> list);

    /**
     * 集合的异常值结果集，返回结果是异常值<index,data>的结果
     * @param sensitivity 敏感度
     * @param list 原集合
     * @return 返回在nσ准则过滤后的异常值集合，包括下标和异常值数据
     */
    Map<Integer,Double> oc(int sensitivity, List<Double> list);

    /**
     * 集合的融合结果
     * @param sensitivity 敏感度
     * @param list 原集合
     * @return 在nσ准则后，剔除异常值后的正常数据。
     */
    List<Double> fusionToList(int sensitivity,List<Double> list);

    /**
     * 集合的融合值
     * @param sensitivity 敏感度
     * @param list 原集合
     * @return 在nσ准则后的融合平均值，也叫融合值。
     */
    public double getFusionValue(int sensitivity,List<Double> list);

}
