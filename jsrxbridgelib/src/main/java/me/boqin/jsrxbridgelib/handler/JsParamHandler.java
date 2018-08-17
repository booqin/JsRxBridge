package me.boqin.jsrxbridgelib.handler;

import android.util.Log;

import me.boqin.jsrxbridgelib.interfaces.IBToJsHandler;
import me.boqin.jsrxbridgelib.interfaces.IParamHandler;
import me.boqin.jsrxbridgelib.utils.JsonUtil;

/**
 * JsParam注释对应的数据处理
 * Created by vitozhang on 2018/7/10.
 */

public class JsParamHandler implements IParamHandler {

    private int mIndex;
    private String mCallBackId;

    public JsParamHandler(int index, String callBackId){
        mIndex = index;
        mCallBackId = callBackId;
    }

    @Override
    public void apply(IBToJsHandler IBToJsHandler, String name, Object[] params) {
        String paramsJson = JsonUtil.toJsonString(params[mIndex]);
        Log.d("BQ", "paramsJson:"+paramsJson);
        IBToJsHandler.notify(name, paramsJson, mCallBackId);
    }
}
