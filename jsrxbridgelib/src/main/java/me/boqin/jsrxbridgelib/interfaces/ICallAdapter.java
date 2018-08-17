package me.boqin.jsrxbridgelib.interfaces;

import me.boqin.jsrxbridgelib.ServiceMethod;

/**
 * Created by vitozhang on 2018/7/11.
 */

public interface ICallAdapter<T> {

    T adapt(ServiceMethod serviceMethod);

}
