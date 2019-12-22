package com.example.a10075.NavgationDrawer.Tools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.a10075.NavgationDrawer.R;
import java.util.Random;

/* *
 *  功能描述：通过rnd函数来生成随机数
 *  @author：闫文豪
 * */

public class NumberActivity extends AppCompatActivity {

    private final static int MIN_DEFAULT_VALUE = 1;  //设置初始最小值
    private final static int MAX_DEFAULT_VALUE = 10;   //最大值
    private final Random rnd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        // Setup the default values
        resetDefaultValues(true);
    }

    private void resetHistoryBox() {
        EditText pastNumbersTextBox = (EditText) findViewById(R.id.txtPastHistory);
        pastNumbersTextBox.setText("历史数字: ");        //记录历史记录
    }

    private void resetDefaultValues(boolean resetPastBox) {
        final EditText minTextBox = (EditText) findViewById(R.id.minimum_text_value);
        minTextBox.setText(String.valueOf(MIN_DEFAULT_VALUE));

        final EditText maxTextBox = (EditText) findViewById(R.id.maximum_text_value);
        maxTextBox.setText(String.valueOf(MAX_DEFAULT_VALUE));

        final TextView curTextBox = (TextView) findViewById(R.id.current);
        curTextBox.setText(String.valueOf(MIN_DEFAULT_VALUE));

        if (resetPastBox) {
            resetHistoryBox();
        }
    }

    public void onClickNextNumber(View view) {

        final EditText minTextBox = (EditText) findViewById(R.id.minimum_text_value);
        final EditText maxTextBox = (EditText) findViewById(R.id.maximum_text_value);

        int minValue = Integer.parseInt(minTextBox.getText().toString());
        int maxValue = Integer.parseInt(maxTextBox.getText().toString());

        int nextValue = rnd.nextInt(maxValue) + minValue;              //主要算法，调用rnd函数生成随机数

        ((TextView) findViewById(R.id.current)).setText(String.valueOf(nextValue));  //数字进入EditText

        final EditText history = (EditText) findViewById(R.id.txtPastHistory);

        String historyVal = history.getText().toString();
        historyVal = historyVal + " " +     String.valueOf(nextValue);

        history.setText(historyVal);




    }

    public void onClickReset(View view) {
        resetDefaultValues(true);
    }

    public void onClickClearHistory(View view) {
        resetHistoryBox();
    }
}