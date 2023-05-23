package com.pgyer.pgyerupdateversiondemo.pgyerutils;


/**
 * Created by liuqiang 2021-01-21 .
 */
public interface PgyCheckoutCallBack {
    //有新版本回调
    void onNewVersionExist(PgyCheckSoftModel model);
    //没有新版本回调
    void onNonentityVersionExist(String error);
    //获取失败回调
    void onFail(String error);

}
