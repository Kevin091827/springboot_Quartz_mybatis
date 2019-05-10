package com.kevin.demo.util;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:    封装ajax请求
 * @Author:         Kevin
 * @CreateDate:     2019/5/10 1:43
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/10 1:43
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public class AjaxResult implements Serializable{
    private int status;//状态码 ，200为正常，500为异常
    private String sData;//包装String类型的数据
    private Object oData;//包装Object类型的数据

    public AjaxResult(){ }

    /*包装sData*/
    public AjaxResult(int status, String sData){
        this.status = status;
        this.sData = sData;
    }

    /*包装oData*/
    public AjaxResult(int status, Object oData){
        this.status = status;
        this.oData = oData;
    }


    /**
     * @auther: Arong
     * @description: 默认的成功结果
     * @param
     * @return: com.iteason.anbaoli_vote_system.utils.AjaxResult
     * @date: 2019/1/15 14:04
     */
    public AjaxResult ok(){
        return new AjaxResult(200,"成功！");
    }

    /**
     * @auther: Arong
     * @description: 请求成功返回字符串
     * @param oData
     * @return: com.iteason.anbaoli_vote_system.utils.AjaxResult
     * @date: 2019/1/15 14:06
     */
    public AjaxResult ok(String oData){
        return new AjaxResult(200,oData);
    }

    /**
     * @auther: Arong
     * @description: 请求成功返回对象
     * @param oData
     * @return: com.iteason.anbaoli_vote_system.utils.AjaxResult
     * @date: 2019/1/15 14:08
     */
    public AjaxResult ok(Object oData){
        return new AjaxResult(200,oData);
    }

    /**
     * @auther: Arong
     * @description: 请求成功返回对象集合
     * @param listData
     * @return: com.iteason.anbaoli_vote_system.utils.AjaxResult
     * @date: 2019/1/15 14:09
     */
    public AjaxResult ok(List<Object> listData){
        return  new AjaxResult(200,listData);
    }

    /**
     * @auther: Arong
     * @description: 请求失败，服务器出现异常
     * @param
     * @return: com.iteason.anbaoli_vote_system.utils.AjaxResult
     * @date: 2019/1/15 14:10
     */
    public AjaxResult error(){
       return new AjaxResult(500,"服务器异常");
    }

    /**
     * @auther: Arong
     * @description: 请求失败，服务器出现异常,发送异常信息
     * @param
     * @return: com.iteason.anbaoli_vote_system.utils.AjaxResult
     * @date: 2019/1/15 14:10
     */
    public AjaxResult error(String sData){
        return new AjaxResult(500,sData);
    }

    public AjaxResult error(Object oData){
        return new AjaxResult(500,oData);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getsData() {
        return sData;
    }

    public void setsData(String sData) {
        this.sData = sData;
    }

    public Object getoData() {
        return oData;
    }

    public void setoData(Object oData) {
        this.oData = oData;
    }

}
