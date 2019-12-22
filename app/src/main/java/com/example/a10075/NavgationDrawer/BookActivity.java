package com.example.a10075.NavgationDrawer;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/* *
 *  功能描述：使用WebView控件来显示资料库的网站，并设置文件下载调用外部浏览器。
 *  @author：代孟伟
 * */

public class BookActivity extends AppCompatActivity {
    private WebView mWbook;

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
            if (keyCode == keyEvent.KEYCODE_BACK) {//监听返回键，如果可以后退就后退
                if (mWbook.canGoBack()) {
                    mWbook.goBack();
                    return true;

                }
            }
            return super.onKeyDown(keyCode, keyEvent);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_book);
            mWbook = findViewById(R.id.webbook);
            mWbook.getSettings().setJavaScriptEnabled(true);
            WebSettings webSettings = mWbook.getSettings();
            webSettings.setDomStorageEnabled(true);
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);
            mWbook.loadUrl("http://dmw1007.cn/");   //资料库的个人网盘网址

            mWbook.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return shouldOverrideUrlLoading(view, request.getUrl().toString());
                }
            });

            mWbook.setDownloadListener(new DownloadListener() {
                @Override
                public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });//调用外置浏览器下载


        }
    }
