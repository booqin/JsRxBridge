package me.boqin.jsrxbridgelib.interfaces;

/**
 * Created by vitozhang on 2018/7/3.
 */

public interface IBInterceptor {

    boolean intercept(String event, String callbackId, String params, IBToJsHandler javaCallHandler);

}
