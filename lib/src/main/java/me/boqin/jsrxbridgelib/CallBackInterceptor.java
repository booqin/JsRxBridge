package me.boqin.jsrxbridgelib;

import android.util.Log;

import java.util.Map;

import me.boqin.jsrxbridgelib.interfaces.CallBackFunction;
import me.boqin.jsrxbridgelib.interfaces.IXGInterceptor;
import me.boqin.jsrxbridgelib.interfaces.IXGToJsHandler;

import static me.boqin.jsrxbridgelib.interfaces.IJavascriptInterface.CALL_BACK_FROM_JS;

/**
 * Created by vitozhang on 2018/7/12.
 */

public class CallBackInterceptor implements IXGInterceptor{

    private Map<String, CallBackFunction> mCallBacks;

    public CallBackInterceptor(Map<String, CallBackFunction> callBackMap){

        mCallBacks = callBackMap;
    }

    @Override
    public boolean intercept(String event, String callbackId, String params, IXGToJsHandler javaCallHandler) {
        Log.d("BQ", "event:"+event);
        Log.d("BQ", "event:"+CALL_BACK_FROM_JS);
        Log.d("BQ", "event:"+CALL_BACK_FROM_JS.equals(event));
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
