package me.boqin.jsrxbridge;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import io.reactivex.disposables.Disposable;
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

    private Disposable mDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mWebView = findViewById(R.id.web_view);
        initJsBridge(mWebView);
        mWebView.loadUrl("file:///android_asset/JSRxBridgeDemo.html");
        findViewById(R.id.bt_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoBean userInfoBean = new UserInfoBean();
                userInfoBean.name = "hello js";
                if (mDisposable!=null) {
                    mDisposable.dispose();
                }
                mDisposable = jsApiDemo.notifyUserInfoChange(userInfoBean).subscribe(new Consumer<RespBean>() {
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
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mJsBridge.onDestroy();
        if (mDisposable!=null) {
            mDisposable.dispose();
        }
    }

    private void initJsBridge(WebView webView) {
        mJsBridge = new JsRxBridge.JsRxBridgeBuilder()
                .registerToJavaHandler(JavaApi.UserInfoHandler)
                .registerToJavaHandler(JavaApi.getLoginHandler(this))
                .build(webView);
        jsApiDemo = mJsBridge.create(JsApis.class);
    }
}
