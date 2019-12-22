package com.example.a10075.NavgationDrawer;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/* *
 *  功能描述：about界面的显示和跳转功能
 *  @author：代孟伟
 * */


public class AboutActivity extends AppCompatActivity {

    private String version;

    private TextView versionText;

    private LinearLayout openSourceCode, githubFollow, coolapkFollow, email, translateSource,
            ocrSource,  versionLayout, author, compass, colorpicker ,Gson, cardview, binary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actity_about);

        versionLayout = findViewById(R.id.verisonLayout);
        author = findViewById(R.id.author);
        openSourceCode = findViewById(R.id.openSourceCode);
        githubFollow = findViewById(R.id.github_follow);
        coolapkFollow = findViewById(R.id.coolapk_follow);
        translateSource = findViewById(R.id.tranlateSource);
        ocrSource = findViewById(R.id.ocrSource);
        email = findViewById(R.id.email);
        versionText = findViewById(R.id.version_id);
        compass = findViewById(R.id.compass);
        colorpicker = findViewById(R.id.colorpicker);
        binary= findViewById(R.id.binary);
        Gson = findViewById(R.id.gson);
        cardview = findViewById(R.id.cardview);

        PackageManager manager = getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            versionText.setText("Version: " + info.versionName);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        openSourceCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"暂未开源，抱歉！",Toast.LENGTH_SHORT).show();
            }
        });

        githubFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/dmw1007"));
                startActivity(intent);
            }
        });

        coolapkFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.coolapk.com/u/1104810"));
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:1007507251@qq.com"));
                startActivity(intent);
            }
        });

        ocrSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://ai.baidu.com/"));
                startActivity(intent);
            }
        });

        translateSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.fanyi.baidu.com/api/trans/product/index"));
                startActivity(intent);
            }
        });


        versionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"当前版本号为"+version,Toast.LENGTH_SHORT).show();
            }
        });

        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"你很靓仔哦！",Toast.LENGTH_SHORT).show();
            }
        });
        compass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/ChaosOctopus/ChaosCompass"));
                startActivity(intent);
            }
        });
        colorpicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/QuadFlask/colorpicker"));
                startActivity(intent);
            }
        });
        binary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/rtugeek/HexConverter"));
                startActivity(intent);
            }
        });
        Gson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/google/gson"));
                startActivity(intent);
            }
        });
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/googlesamples/android-CardView"));
                startActivity(intent);
            }
        });





    }
}
