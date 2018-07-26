package me.boqin.jsrxbridgelib.api;

import me.boqin.jsrxbridgelib.interfaces.CallBackFunction;
import me.boqin.jsrxbridgelib.interfaces.IBToJavaHandler;

import static me.boqin.jsrxbridgelib.interfaces.IJavascriptInterface.CALL_BACK_FROM_JS;

/**
 * 用于调用Js后的回调
 * Created by vitozhang on 2018/7/11.
 */

public class DefaultJavaApi {


    public static IBToJavaHandler CallBack = new IBToJavaHandler() {
        @Override
        public String getMethodName() {
            return CALL_BACK_FROM_JS;
        }

        @Override
        public void handler(String data, CallBackFunction function) {
            //直接将参数返回给回调接口
            function.onCallBack(data);
        }
    };

}
