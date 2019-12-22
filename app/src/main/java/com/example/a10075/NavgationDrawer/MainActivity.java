package com.example.a10075.NavgationDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.a10075.NavgationDrawer.Binary.BinaryActivity;
import com.example.a10075.NavgationDrawer.Tools.ToolsActivity;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/* *
 *  功能描述：软件主启动Activity，显示主界面的WebView，给软件设置Toolbar，侧滑栏，fab按钮组件，控制侧滑栏菜单item的页面跳转。
 *  @author：代孟伟
 * */


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private WebView mWvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);   //启用toolbar

        mWvMain = findViewById(R.id.webv);
        mWvMain.getSettings().setJavaScriptEnabled(true);   // 开启对JavaScript的支持
        WebSettings webSettings =  mWvMain.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);   //  缩放至屏幕大小
        webSettings.setUseWideViewPort(true);   //自动调整页面大小
        mWvMain.loadUrl("https://dmw1007.gitee.io/c_home_page/");  // 首界面网页码云地址

        mWvMain.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return shouldOverrideUrlLoading(view, request.getUrl().toString());
            }
        });   /*  创建WebViewClient通知，请求事件  */



        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        /*
           悬浮fab按钮（暂未设置功能）
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_about:
                        Intent intent=new Intent(MainActivity.this,AboutActivity.class);
                        startActivity(intent);
                        break;//跳转关于界面
                }
                switch (menuItem.getItemId()){
                    case R.id.nav_tool:
                        Intent intent=new Intent(MainActivity.this,ToolsActivity.class);
                        startActivity(intent);
                        break;//跳转工具箱界面
                }
                switch (menuItem.getItemId()){
                    case R.id.nav_book:
                        Intent intent=new Intent(MainActivity.this,BookActivity.class);
                        startActivity(intent);
                        break;//跳转资料库界面
                }
                switch (menuItem.getItemId()){
                    case R.id.nav_color:
                        Intent intent=new Intent(MainActivity.this, BinaryActivity.class);
                        startActivity(intent);
                        break;//跳转进制转换界面
                }
                switch (menuItem.getItemId()){
                    case R.id.nav_share:
                        Intent textInter=new Intent(Intent.ACTION_SEND);
                        textInter.setType("text/plain");
                        textInter.putExtra(Intent.EXTRA_TEXT,"分享一个APP给你，下载地址：http://dmw1007.cn/?/Ctool/");
                        startActivity(Intent.createChooser(textInter,"好东西要记得分享给朋友!"));
                        break;//分享按钮
                }
                switch (menuItem.getItemId()){
                    case R.id.nav_close:
                      finish();
                        break;//跳转进制转换界面
                }

                return false;
            }
        });
        }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_setting) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_tool) {


        } else if (id == R.id.nav_color) {

        } else if (id == R.id.nav_book) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_close) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == keyEvent.KEYCODE_BACK) {//监听返回键，如果可以后退就后退
            if (mWvMain.canGoBack()) {
                mWvMain.goBack();
                return true;

            }
        }
        return super.onKeyDown(keyCode, keyEvent);
    }
}
