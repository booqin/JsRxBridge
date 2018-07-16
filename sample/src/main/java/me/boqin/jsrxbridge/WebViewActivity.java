package me.boqin.jsrxbridge;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import io.reactivex.functions.Consumer;
import me.boqin.jsrxbridge.api.JavaApi;
import me.boqin.jsrxbridge.api.JsApis;
import me.boqin.jsrxbridge.bean.RespBean;
import me.boqin.jsrxbridge.bean.UserInfoBean;
import me.boqin.jsrxbridgelib.JsRxBridge;

/**
 * Created by vitozhang on 2018/7/2.
 */

public class WebViewActivity extends Activity{

    private WebView mWebView;
    private JsRxBridge mJsBridge;

    private JsApis jsApiDemo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mWebView = findViewById(R.id.web_view);
        mJsBridge = new JsRxBridge.JsRxBridgeBuilder().registerToJavaHandler(JavaApi.UserInfoHandler).build(mWebView);
        jsApiDemo = mJsBridge.create(JsApis.class);
        mWebView.loadUrl("file:///android_asset/JSRxBridgeDemo.html");
        findViewById(R.id.bt_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoBean userInfoBean = new UserInfoBean();
                userInfoBean.name = "hello js";
                jsApiDemo.notifyUserInfoChange(userInfoBean).subscribe(new Consumer<RespBean>() {
                    @Override
                    public void accept(RespBean s) throws Exception {
                        Toast.makeText(WebViewActivity.this, s.getName(), Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
//                jsApiDemo.notifyUserInfoChange(userInfoBean);
            }
        });
    }
}
