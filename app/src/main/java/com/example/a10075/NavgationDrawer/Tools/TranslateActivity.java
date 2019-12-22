package com.example.a10075.NavgationDrawer.Tools;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.a10075.NavgationDrawer.BaiduTranslate.TransApi;
import com.example.a10075.NavgationDrawer.BaiduTranslate.TranslateResult;
import com.example.a10075.NavgationDrawer.R;
import com.google.gson.Gson;
import java.util.List;

/* *
 *  功能描述：使用百度翻译API接口返回翻译结果并显示在TextView上。
 *  @author：代孟伟
 * */

public class TranslateActivity extends AppCompatActivity {


    private ImageButton imtranslate,imcopy;
    private TextView textView;
    private Handler handler = new Handler();
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        initView();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.editText);
        imtranslate = (ImageButton) findViewById(R.id.imtranslate);
        textView = (TextView) findViewById(R.id.textView);
        imcopy = (ImageButton) findViewById(R.id.imcopy);


        imtranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String query =editText.getText().toString();
                       //   final String query
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String resultJson = new TransApi().getTransResult(query, "auto", "en");
                        //拿到结果，对结果进行解析。
                        Gson gson = new Gson();
                        TranslateResult translateResult = gson.fromJson(resultJson, TranslateResult.class);
                        final List<TranslateResult.TransResultBean> trans_result = translateResult.getTrans_result();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                String dst = "";
                                for (TranslateResult.TransResultBean s : trans_result
                                        ) {
                                    dst = dst + "\n" + s.getDst();
                                }

                                textView.setText(dst);
                            }
                        });

                    }
                }).start();
            }
        });

        imcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!getApplicationContext().toString().equals("")) {
                    final ClipboardManager clipboardManager = (ClipboardManager)
                            getApplication().getSystemService(Context.CLIPBOARD_SERVICE);

                    ClipData clipData = ClipData.newPlainText("Label", getApplicationContext().toString());
                    clipboardManager.setPrimaryClip(clipData);
                    Snackbar.make(view, "成功复制到剪切板", Snackbar.LENGTH_LONG).setAction("撤销", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ClipData clipData = ClipData.newPlainText("Label", "");
                            clipboardManager.setPrimaryClip(clipData);
                        }
                    }).show();
                }
            }
        });

    }
}
