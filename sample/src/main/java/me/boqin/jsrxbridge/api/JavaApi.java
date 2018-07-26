package me.boqin.jsrxbridge.api;


import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import me.boqin.jsrxbridge.LoginActivity;
import me.boqin.jsrxbridgelib.interfaces.CallBackFunction;
import me.boqin.jsrxbridgelib.interfaces.IBToJavaHandler;
import me.boqin.jsrxbridgelib.utils.JsonUtil;

/**
 * 提供给JS使用到java代码，根据前端要求添加java协议代码
 * Created by Boqin on 2017/7/12.
 * Modified by Boqin
 *
 * @Version
 */
public abstract class JavaApi {

    public static IBToJavaHandler UserInfoHandler = new IBToJavaHandler() {

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

    public static IBToJavaHandler getLoginHandler(final Context context){
        return new IBToJavaHandler() {

            @Override
            public String getMethodName() {
                return "login";
            }

            @Override
            public void handler(String data, CallBackFunction function) {

                context.startActivity(new Intent(context, LoginActivity.class));

            }

        };
    }



}
