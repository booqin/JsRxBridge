package me.boqin.jsrxbridgelib;

import java.util.Map;

import me.boqin.jsrxbridgelib.interfaces.CallBackFunction;
import me.boqin.jsrxbridgelib.interfaces.ICallAdapter;

/**
 * Created by vitozhang on 2018/7/11.
 */

public class RxJavaAdapter implements ICallAdapter {

    public Map<?, ? extends CallBackFunction> map;

    public RxJavaAdapter(Map<?, ? extends CallBackFunction> callbacks){
        map = callbacks;
    }

    @Override
    public Object adapt(final ServiceMethod serviceMethod) {
        //创建Observable
        if (serviceMethod.getResultType() == void.class) {
            serviceMethod.apply();
            return void.class;
        }else {
            return new JsObservable(serviceMethod);
        }

    }
}
