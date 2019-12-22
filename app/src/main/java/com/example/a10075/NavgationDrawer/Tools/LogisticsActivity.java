package com.example.a10075.NavgationDrawer.Tools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import com.example.a10075.NavgationDrawer.R;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/* *
 *  功能描述：使用快递100的API接口网页实现物流查询。
 *  @author：代孟伟
 * */

public class LogisticsActivity extends AppCompatActivity {   //快递查询页
    private WebView mWblogistics;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == keyEvent.KEYCODE_BACK) {//监听返回键，如果可以后退就后退
            if (mWblogistics.canGoBack()) {
                mWblogistics.goBack();
                return true;

            }
        }
        return super.onKeyDown(keyCode, keyEvent);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);
        mWblogistics=findViewById(R.id.weblogistics);
        mWblogistics.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings = mWblogistics.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        mWblogistics.loadUrl("https://m.kuaidi100.com/app/?coname=cmb&hdisplay=web");

        mWblogistics.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return shouldOverrideUrlLoading(view, request.getUrl().toString());
            }
        });
    }
}
