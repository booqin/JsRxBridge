package me.boqin.jsrxbridgelib;

import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.boqin.jsrxbridgelib.interfaces.CallBackFunction;
import me.boqin.jsrxbridgelib.interfaces.IJavascriptInterface;
import me.boqin.jsrxbridgelib.interfaces.IXGInterceptor;
import me.boqin.jsrxbridgelib.interfaces.IXGToJavaHandler;
import me.boqin.jsrxbridgelib.interfaces.IXGToJsHandler;


/**
 * Created by vitozhang on 2018/6/29.
 */

public class JavascriptInterfaceImpl implements IJavascriptInterface {

    private Map<String, IXGToJavaHandler> mNativeCallMap;
    private List<IXGInterceptor> mInterceptorList;

    private IXGToJsHandler mJavaCallHandler;
    private IJavascriptInterface mDefaultHandler;
    private Handler mMainHandler;
    private Runnable mMainRunnable;

    public JavascriptInterfaceImpl(IXGToJsHandler javaCallHandler){
        mNativeCallMap = new HashMap<>();
        mInterceptorList = new ArrayList<>();
        mJavaCallHandler = javaCallHandler;
        mMainHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * add interceptor
     */
    public void addInterceptor(IXGInterceptor interceptor){
        mInterceptorList.add(interceptor);
    }

    /**
     * 添加JS端调用的java的接口
     */
    public void addCall(IXGToJavaHandler ixgBridgeHandler){
        if (ixgBridgeHandler == null) {
            return;
        }
        mNativeCallMap.put(ixgBridgeHandler.getMethodName(), ixgBridgeHandler);
    }

    public void setDefaultCall(IJavascriptInterface handler){
        mDefaultHandler = handler;
    }

    @JavascriptInterface
    @Override
    public void invokeHandler(final String event, final String paramsString, final String callbackId){
        mMainRunnable = new Runnable() {
            @Override
            public void run() {
                boolean isFilter = false; //是否过滤
                for (IXGInterceptor javaApiInterceptor : mInterceptorList) {
                    isFilter |= javaApiInterceptor.intercept(event, callbackId, paramsString, mJavaCallHandler);
                }
                if (!isFilter) { //是否过滤
                    if (mNativeCallMap.get(event) != null) { //执行注册的接口
                        mNativeCallMap.get(event).handler(paramsString, new CallBackFunction() {
                            @Override
                            public void onCallBack(String data) {
                                if (mJavaCallHandler != null) {
                                    mJavaCallHandler.callBack(callbackId, data);
                                }
                            }
                        });

                    }else { //执行默认的调用
                        if (mDefaultHandler!=null) {
                            mDefaultHandler.invokeHandler(event, paramsString,callbackId);
                        }
                    }
                }
            }
        };
        mMainHandler.post(mMainRunnable);

    }


    public void onDestroy(){
        mMainHandler.removeCallbacks(mMainRunnable);
        mNativeCallMap.clear();
        mInterceptorList.clear();
        mJavaCallHandler = null;
    }

}
