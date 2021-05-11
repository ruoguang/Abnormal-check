package com.cc270.oc.base;

import java.util.List;
import java.util.Set;

/**
 * cc算法的核心接口
 *
 * @author CC
 * @version v1.0
 * @date 2019/6/7 12:15
 */
public interface CCArithmetic {


    /**
     * 求一个集合的算数平均值
     *
     * @param list 原集合
     * @return 集合的算数平均数
     */
    double avg(double[] datas, Set<Integer> set);


    double avg(double[] datas);

    /**
     * 求一个集合的总和
     *
     * @param list 集合
     * @return 集合的总和值
     */
    double sum(double[] datas, Set<Integer> set);

    double sum(double[] datas);

    /**
     * 求集合元素的数量
     *
     * @param list 集合
     * @return 元素的数量
     */
    int count(List list);

    /**
     * 剩余误差的集合
     *
     * @param list 原集合
     * @return 剩余误差的集合
     */
    double[] re(double[] datas, Set<Integer> set);

    /**
     * 标准差的计算
     *
     * @param list 原集合
     * @return 集合的标准差
     */
    double sd(double[] datas, Set<Integer> set);

    double sd(double[] datas);

    /**
     * 求集合在nσ准则公式判断的值，值=|当前值-算数平均值|-敏感度*标准差
     *
     * @param sensitivity 敏感度
     * @param datas       原集合
     * @return 值是否在nσ准则内，大于0是范围外，等于0是范围临界值，小于0是正常范围值。
     */
    double[] ocbase(int sensitivity, double[] datas);


    double[] ocbase(int sensitivity, double[] datas, Set<Integer> set);

    /**
     * 集合的异常值结果集，返回结果是异常值<index,data>的结果
     *
     * @param list        原集合
     * @param sensitivity 敏感度
     * @param res
     * @return 返回在nσ准则过滤后的异常值集合，包括下标和异常值数据
     */
    Set<Integer> oc(int sensitivity, double[] datas);


    void coc(int sensitivity, double[] datas, Set<Integer> res);

    /**
     * 集合的融合结果
     *
     * @param sensitivity 敏感度
     * @param list        原集合
     * @return 在nσ准则后，剔除异常值后的正常数据。
     */
    double[] fusionToList(int sensitivity, double[] datas);

    /**
     * 集合的融合值
     *
     * @param sensitivity 敏感度
     * @param list        原集合
     * @return 在nσ准则后的融合平均值，也叫融合值。
     */
    double getFusionValue(int sensitivity, double[] datas);

    boolean ocExist(int sensitivity, double[] datas, Set<Integer> set);

    boolean ocExist(int sensitivity, double[] datas);


}
