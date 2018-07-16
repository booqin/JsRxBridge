package me.boqin.jsrxbridge.api;

import java.util.HashMap;

import me.boqin.jsrxbridgelib.interfaces.IXGToJsHandler;
import me.boqin.jsrxbridgelib.utils.JsonUtil;

/**
 * 用于Native调用的JS方法。
 * Created by Boqin on 2017/7/12.
 * Modified by Boqin
 * @Version
 */
@Deprecated
public class JSApi {

    private IXGToJsHandler mJavaCallHandler;

    public JSApi(IXGToJsHandler javaCallHandler){
        mJavaCallHandler = javaCallHandler;
    }

    public void notifyTokenChange(){
        mJavaCallHandler.notify("userInfoChange", JsonUtil.toJsonString(getUserInfo()));
    }

    private Object getUserInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "error");
        return map;
    }

}
