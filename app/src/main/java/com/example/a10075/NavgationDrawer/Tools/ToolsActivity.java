package com.example.a10075.NavgationDrawer.Tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import com.example.a10075.NavgationDrawer.R;
import com.example.a10075.NavgationDrawer.Tools.Compass.CompassActivity;
import com.example.a10075.NavgationDrawer.Tools.OCR.OcrActivity;

/* *
 *  功能描述：控制工具箱界面内各小工具的界面跳转。
 *  @author：代孟伟
 * */

public class ToolsActivity extends AppCompatActivity {
    private ImageButton mBtt1;
    private ImageButton mBtt2;
    private ImageButton mBtt3;
    private ImageButton mBtt4;
    private ImageButton mBtt5;
    private ImageButton mBtt6;
    private ImageButton mBtt7;
    private ImageButton mBtt8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        mBtt1=findViewById(R.id.bttool1);
        mBtt2=findViewById(R.id.bttool2);
        mBtt3=findViewById(R.id.bttool3);
        mBtt4=findViewById(R.id.bttool4);
        mBtt5=findViewById(R.id.bttool5);
        mBtt6=findViewById(R.id.bttool6);
        mBtt7=findViewById(R.id.bttool7);
        mBtt8=findViewById(R.id.bttool8);

        mBtt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ToolsActivity.this,WebViewActivity.class);
                startActivity(intent);
            }
        });
        mBtt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ToolsActivity.this,TranslateActivity.class);
                startActivity(intent);//跳转翻译界面
            }
        });
        mBtt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ToolsActivity.this, ColorWallActivity.class);
                startActivity(intent);//跳转配色墙界面
            }
        });
        mBtt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ToolsActivity.this, CalculatorActivity.class);
                startActivity(intent);//跳转计算器界面
            }
        });
        mBtt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ToolsActivity.this, LogisticsActivity.class);
                startActivity(intent);//跳转物流查询界面
            }
        });
        mBtt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ToolsActivity.this,NumberActivity.class);
                startActivity(intent);//跳转随机数界面
            }
        });
        mBtt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ToolsActivity.this, CompassActivity.class);
                startActivity(intent);//跳转指南针界面
            }
        });
        mBtt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ToolsActivity.this, OcrActivity.class);
                startActivity(intent);//跳转指南针界面
            }
        });







    }
}
