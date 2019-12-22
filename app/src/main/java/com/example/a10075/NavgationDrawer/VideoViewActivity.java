package com.example.a10075.NavgationDrawer;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

/* *
 *  功能描述：软件启动时播放一个启动动画，使用VideoView控件
 *  @author：闫文豪
 * */

public class VideoViewActivity extends Activity {
    public static VideoViewActivity inst = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inst = this;
        // 全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_videoview);
        /*主要代码起始位置*/
        final VideoView vv = (VideoView) this.findViewById(R.id.vvsplash);
        final String uri = "android.resource://" + getPackageName() + "/" + R.raw.apure;

        vv.setVideoURI(Uri.parse(uri));
        vv.start();
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);

            }
        });

        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                vv.setVideoURI(Uri.parse(uri));
                vv.start();

            }
        });
        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2300);
                    Intent it = new Intent(VideoViewActivity.this, MainActivity.class);//启动MainActivity
                    startActivity(it);
                    finish();//关闭当前活动
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        };
        myThread.start();//启动线程

    }




    }
