package com.example.a10075.NavgationDrawer.Tools.OCR;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.example.a10075.NavgationDrawer.R;
import com.google.gson.Gson;
import static org.litepal.LitePalApplication.getContext;

/* *
 *  功能描述：使用百度AI的文字识别API来实现的OCR功能，将识别结果放在CardView上的一个TextView中。
 *  @author：代孟伟
 * */

public class OcrActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_GENERAL = 105;
    private static final int REQUEST_CODE_GENERAL_BASIC = 106;
    private static final int REQUEST_CODE_ACCURATE_BASIC = 107;
    private static final int REQUEST_CODE_ACCURATE = 108;
    private static final int REQUEST_CODE_GENERAL_ENHANCED = 109;
    private static final int REQUEST_CODE_GENERAL_WEBIMAGE = 110;
    private static final int REQUEST_CODE_BANKCARD = 111;
    private static final int REQUEST_CODE_VEHICLE_LICENSE = 120;
    private static final int REQUEST_CODE_DRIVING_LICENSE = 121;
    private static final int REQUEST_CODE_LICENSE_PLATE = 122;
    private static final int REQUEST_CODE_BUSINESS_LICENSE = 123;
    private static final int REQUEST_CODE_RECEIPT = 124;

    private static final int REQUEST_CODE_PASSPORT = 125;
    private static final int REQUEST_CODE_NUMBERS = 126;
    private static final int REQUEST_CODE_QRCODE = 127;
    private static final int REQUEST_CODE_BUSINESSCARD = 128;
    private static final int REQUEST_CODE_HANDWRITING = 129;
    private static final int REQUEST_CODE_LOTTERY = 130;
    private static final int REQUEST_CODE_VATINVOICE = 131;
    private static final int REQUEST_CODE_CUSTOM = 132;

    private boolean hasGotToken = false;

    private AlertDialog.Builder alertDialog;
    private AutoCompleteTextView ocr_result;
    private CardView cv_result;
    private ImageButton imcopy;
    private Activity mActivity;
    private Button ocr;
    private Boolean isOcr = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);

        ocr = findViewById(R.id.ocr);
        imcopy=findViewById(R.id.imcopy);
        cv_result = findViewById(R.id.cv_result);
        ocr_result = findViewById(R.id.TV_output);

        ocr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkTokenStatus()) {
                    return;
                }
                Intent intent = new Intent(OcrActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                        CameraActivity.CONTENT_TYPE_GENERAL);
                startActivityForResult(intent, REQUEST_CODE_GENERAL_BASIC);
            }
        });
        initAccessToken();
    }




    private boolean checkTokenStatus() {
        if (!hasGotToken) {
            Toast.makeText(getApplicationContext(), "token还未成功获取", Toast.LENGTH_LONG).show();
        }
        return hasGotToken;

    }
    /**
     * 以license文件方式初始化
     */
    private void initAccessToken() {
        OCR.getInstance(this).initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                alertText("licence方式获取token失败", error.getMessage());
            }
        }, getApplicationContext());
    }
    private void alertText(final String title, final String message) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("确定", null)
                        .show();
            }
        });
    }
    private void infoPopText(final String result) {
        alertText("", result);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initAccessToken();
        } else {
            Toast.makeText(getContext(), "需要android.permission.READ_PHONE_STATE", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 识别成功回调，通用文字识别
        if (requestCode == REQUEST_CODE_GENERAL_BASIC && resultCode == Activity.RESULT_OK) {
            RecognizeService.recGeneral(this, FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            //infoPopText(result);
                            cv_result.setVisibility(View.VISIBLE);
                            if (isOcr == true) {
                                isOcr = false;
                                ocr_result.setText("");
                            }
                            if (isOcr == false) {
                                Gson gson = new Gson();
                                ocrBean ob = gson.fromJson(result, ocrBean.class);
                                for (ocrBean.WordsResultBean wordsResultBean : ob.getWords_result()) {
                                    ocr_result.append(wordsResultBean.getWords() + "\n");
                                }
                                isOcr = true;
                            }
                            //ocr_result.setText(result);
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
}
