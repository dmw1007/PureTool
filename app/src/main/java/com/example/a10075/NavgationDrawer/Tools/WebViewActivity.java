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
 *  功能描述：在线编译的网页显示。
 *  @author：代孟伟
 * */



public class WebViewActivity extends AppCompatActivity {
    private WebView mWvMain;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == keyEvent.KEYCODE_BACK) {//监听返回键，如果可以后退就后退
            if (mWvMain.canGoBack()) {
                mWvMain.goBack();
                return true;

            }
        }
        return super.onKeyDown(keyCode, keyEvent);}


@Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actity_webview);
        mWvMain=findViewById(R.id.webv);
        mWvMain.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings =  mWvMain.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        mWvMain.loadUrl("https://c.runoob.com/");


        mWvMain.setWebViewClient(new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return shouldOverrideUrlLoading(view, request.getUrl().toString());
        }
    });

    }
}
