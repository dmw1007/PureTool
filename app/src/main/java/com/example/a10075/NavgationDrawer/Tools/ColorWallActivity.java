package com.example.a10075.NavgationDrawer.Tools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import com.example.a10075.NavgationDrawer.R;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;
import com.flask.colorpicker.OnColorSelectedListener;

/* *
 *  功能描述：使用ColorPicker开源组件实现的颜色选择器。
 *  @author：代孟伟
 * */

public class ColorWallActivity extends AppCompatActivity {

    private AutoCompleteTextView color_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorwall);
        color_result = findViewById(R.id.color_output);
        ColorPickerView colorPickerView = findViewById(R.id.color_picker_view);
        colorPickerView.addOnColorChangedListener(new OnColorChangedListener() {
            @Override public void onColorChanged(int selectedColor) {
                // Handle on color change
                color_result.setText( "" + Integer.toHexString(selectedColor));
            }
        });
        colorPickerView.addOnColorSelectedListener(new OnColorSelectedListener() {
            @Override
            public void onColorSelected(int selectedColor) {

                color_result.setText("" + Integer.toHexString(selectedColor).toUpperCase());
            }
        });
    }
}
