package me.boqin.jsrxbridgelib;

import static me.boqin.jsrxbridgelib.interfaces.IJavascriptInterface.CALL_BACK_FROM_JS;

import java.util.Map;

import me.boqin.jsrxbridgelib.interfaces.CallBackFunction;
import me.boqin.jsrxbridgelib.interfaces.IBInterceptor;
import me.boqin.jsrxbridgelib.interfaces.IBToJsHandler;

/**
 * 默认的拦截器，拦截回调方法
 * Created by vitozhang on 2018/7/12.
 */

public class CallBackInterceptor implements IBInterceptor {

    private Map<String, CallBackFunction> mCallBacks;

    public CallBackInterceptor(Map<String, CallBackFunction> callBackMap){

        mCallBacks = callBackMap;
    }

    @Override
    public boolean intercept(String event, String callbackId, String params, IBToJsHandler javaCallHandler) {
        if (CALL_BACK_FROM_JS.equals(event)) {
            //调用Js方法后的回调
            if (mCallBacks.get(callbackId)!=null) {
                mCallBacks.get(callbackId).onCallBack(params);
            }
            return true;
        }
        return false;
    }
}
