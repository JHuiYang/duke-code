package com.example.demo.vo;

/**
 * @author yangjianhui
 * @version $Id DataVO.java, v 0.1 2018-06-27 17:02 yangjianhui Exp $$
 */
public class DataVO {

    //水洗硅石
    private int shuiXiGuiShiToTal;
    //木炭
    private int muTanTotal;

    //油焦/兰炭
    private int youJiaoAndLanTanTotal;

    //水洗煤
    private int shuiXiMeiTotal;

    //木块
    private int muKuaiTotal;

    //料批
    private String batch;

    //配方
    private String formula;

    //系数
    private String coeffcient;

    //配料人
    private String batchingPerson;

    public int getShuiXiGuiShiToTal() {
        return shuiXiGuiShiToTal;
    }

    public void setShuiXiGuiShiToTal(int shuiXiGuiShiToTal) {
        this.shuiXiGuiShiToTal = shuiXiGuiShiToTal;
    }

    public int getMuTanTotal() {
        return muTanTotal;
    }

    public void setMuTanTotal(int muTanTotal) {
        this.muTanTotal = muTanTotal;
    }

    public int getYouJiaoAndLanTanTotal() {
        return youJiaoAndLanTanTotal;
    }

    public void setYouJiaoAndLanTanTotal(int youJiaoAndLanTanTotal) {
        this.youJiaoAndLanTanTotal = youJiaoAndLanTanTotal;
    }

    public int getShuiXiMeiTotal() {
        return shuiXiMeiTotal;
    }

    public void setShuiXiMeiTotal(int shuiXiMeiTotal) {
        this.shuiXiMeiTotal = shuiXiMeiTotal;
    }

    public int getMuKuaiTotal() {
        return muKuaiTotal;
    }

    public void setMuKuaiTotal(int muKuaiTotal) {
        this.muKuaiTotal = muKuaiTotal;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getCoeffcient() {
        return coeffcient;
    }

    public void setCoeffcient(String coeffcient) {
        this.coeffcient = coeffcient;
    }

    public String getBatchingPerson() {
        return batchingPerson;
    }

    public void setBatchingPerson(String batchingPerson) {
        this.batchingPerson = batchingPerson;
    }
}