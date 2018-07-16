package me.boqin.jsrxbridge.api;


import java.util.HashMap;

import me.boqin.jsrxbridgelib.interfaces.CallBackFunction;
import me.boqin.jsrxbridgelib.interfaces.IXGToJavaHandler;
import me.boqin.jsrxbridgelib.utils.JsonUtil;

/**
 * 提供给JS使用到java代码，根据前端要求添加java协议代码
 * Created by Boqin on 2017/7/12.
 * Modified by Boqin
 *
 * @Version
 */
public abstract class JavaApi {

    public static IXGToJavaHandler UserInfoHandler = new IXGToJavaHandler() {

        @Override
        public String getMethodName() {
            return "getUserInfo";
        }

        @Override
        public void handler(String data, CallBackFunction function) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", "BQ");
            function.onCallBack(JsonUtil.toJsonString(map));
        }
    };

}
