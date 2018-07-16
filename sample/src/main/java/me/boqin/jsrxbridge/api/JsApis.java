package me.boqin.jsrxbridge.api;

import io.reactivex.Observable;
import me.boqin.jsrxbridge.bean.RespBean;
import me.boqin.jsrxbridge.bean.UserInfoBean;
import me.boqin.jsrxbridgelib.annotations.JsName;
import me.boqin.jsrxbridgelib.annotations.JsParam;

/**
 * Created by vitozhang on 2018/7/10.
 */

public interface JsApis {

    @JsName("userInfoChange")
    Observable<RespBean> notifyUserInfoChange(@JsParam
            UserInfoBean userInfoBean);

}
