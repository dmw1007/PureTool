package com.example.a10075.NavgationDrawer.Binary;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.a10075.NavgationDrawer.Binary.view.RadixEditText;
import com.example.a10075.NavgationDrawer.R;
import com.example.a10075.NavgationDrawer.Binary.view.Keyboard;

/* *
 *  功能描述：主要监听键盘和设置部分控件属性，将监听结果传给tools来计算结果。
 *  @author：张明明
 * */

public class BinaryActivity extends AppCompatActivity implements View.OnClickListener {

    private AlertDialog mRadixPickerDialog;
    private TextView mTvLabelMisc;
    private RadixEditText mCurrentEditText;
    private Keyboard mKeyboard;
    private ClipboardManager mClipboardManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_main);
        initView();

    }


    private void initView() {
        mTvLabelMisc = findViewById(R.id.tv_label_misc);
        mKeyboard = findViewById(R.id.keyboard);
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        final String radix[] = {"3", "4", "5", "6", "7", "8", "9", "11", "12", "13", "14", "15"};
        mRadixPickerDialog = new AlertDialog.Builder(this).setItems(radix, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mTvLabelMisc.setText(radix[i]);
                ((RadixEditText) findViewById(R.id.et_misc))
                        .setRadix(Integer.parseInt(radix[i]));
            }
        }).setNegativeButton(R.string.cancel, null).create();

        mTvLabelMisc.setOnClickListener(this);
        mKeyboard.setOnKeyboardListener(new Keyboard.OnKeyboardListener() {
            @Override
            public void onKeyboardClickListener(Button button, String value) {
                if("DEL".equalsIgnoreCase(value)){
                    String data =  mCurrentEditText.getText().toString();
                    if(data.trim().length() != 0){
                        mCurrentEditText.setText(data.substring(0, data.length() - 1));
                        mCurrentEditText.setSelection(mCurrentEditText.getText().length());
                    }
                }else if("COPY".equalsIgnoreCase(value)){
                    String data = mCurrentEditText.getText().toString().replaceAll(" ", "");
                    ClipData clipData = ClipData.newPlainText("Data",data);
                    mClipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(BinaryActivity.this, getString(R.string.done), Toast.LENGTH_SHORT).show();
                }else if(".".equals(value)){
                    if(TextUtils.isEmpty(mCurrentEditText.getText())){
                        mCurrentEditText.append("0.");
                    }else if(!mCurrentEditText.getText().toString().contains(".")){
                        mCurrentEditText.append(value);
                    }
                }else{
                    mCurrentEditText.append(value);
                }

            }

            @Override
            public void onKeyboardLongClickListener(Button button, String value) {
                if("DEL".equalsIgnoreCase(value)){
                    mCurrentEditText.setText("");
                }
            }
        });

        for (RadixEditText radixEditText : RadixEditText.getRadixEditTexts()) {
            radixEditText.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_UP:
                            RadixEditText radixEditText = (RadixEditText) v;
                            mKeyboard.setRadix(radixEditText.getRadix());
                            mKeyboard.show();
                            mCurrentEditText = radixEditText;
                            updateColor(radixEditText.getRadix());
                            break;
                        default:
                            break;
                    }
                    return false;
                }
            });
        }

        mCurrentEditText =  RadixEditText.getRadixEditTexts().get(0);

        //hide the keyboard
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mKeyboard.hide();
                handler.removeCallbacks(this);
            }
        },500);
    }


    private void updateColor(int radix){
        int colorHint = getResources().getColor(R.color.textColorHint);
        int white = getResources().getColor(R.color.white);
        int colorPrimary = getResources().getColor(R.color.colorPrimary);
        for (RadixEditText radixEditText : RadixEditText.getRadixEditTexts()) {
            if(radixEditText.getRadix() == radix){
                int color = radix == 10 ? white : colorPrimary;
                radixEditText.setTextColor(color);
                radixEditText.setHintTextColor(color);
                continue;
            }
            if(radixEditText.getRadix() == 10){
                radixEditText.setHintTextColor(colorHint);
                radixEditText.setTextColor(colorHint);
                continue;
            }
            radixEditText.setHintTextColor(0xFFD4D4D4);
            radixEditText.setTextColor(0xFF616161);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_label_misc:
                mRadixPickerDialog.show();
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == 4 && mKeyboard.isShowing()){
            mKeyboard.hide();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

