package me.boqin.jsrxbridgelib.WebClient;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import me.boqin.jsrxbridgelib.utils.BridgeUtil;


/**
 * Created by vitozhang on 2018/7/3.
 */

public class JSWebViewClient extends WebViewClient{

    private static final String TO_LOAD_JS = "JSRxBridge.js";

    private String mToLoadJs;

    public JSWebViewClient(String toLoadJs){
        super();
        mToLoadJs = toLoadJs;
    }

    public JSWebViewClient(){
        this(TO_LOAD_JS);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        BridgeUtil.webViewLoadLocalJs(view, mToLoadJs);
    }


}
