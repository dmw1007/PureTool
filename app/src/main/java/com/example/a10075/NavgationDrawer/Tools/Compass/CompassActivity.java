package com.example.a10075.NavgationDrawer.Tools.Compass;
import android.support.v7.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.example.a10075.NavgationDrawer.R;

/* *
 *  功能描述：使用开源代码Compass-ChaoS实现的仿小米指南针。
 *  @author：代孟伟
 * */


public class CompassActivity extends AppCompatActivity {

    public static CompassActivity inst = null;

    private SensorManager mSensorManager;
    private SensorEventListener mSensorEventListener;
    private CompassView CompassView;
    private float val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inst = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    // 全屏显示

        setContentView(R.layout.activity_compass);
        CompassView = (CompassView) findViewById(R.id.ccv);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                val = event.values[0];
                CompassView.setVal(val);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        mSensorManager.registerListener(mSensorEventListener,mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(mSensorEventListener);
    }

}