package me.boqin.jsrxbridgelib.interfaces;

/**
 * 参数处理接口
 * Created by vitozhang on 2018/7/10.
 */

public interface IParamHandler {

    void apply(IBToJsHandler IBToJsHandler, String name, Object[] params);
}
