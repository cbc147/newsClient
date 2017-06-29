package com.example.newsclient.activity;

import android.animation.Animator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.newsclient.R;



public class GuideActivity extends BaseActivity {
    private Button btn_go;
    private ImageView iv_guide;
    private int index=0;

    private int[] imagesArray = new int[] {
            R.drawable.ad_new_version1_img1,
            R.drawable.ad_new_version1_img2,
            R.drawable.ad_new_version1_img3,
            R.drawable.ad_new_version1_img4,
            R.drawable.ad_new_version1_img5,
            R.drawable.ad_new_version1_img6,
            R.drawable.ad_new_version1_img7,
    };



    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    startAnimation();
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopMusic();
    }

    @Override
    public void initData() {
        startAnimation();
        playMusic();

    }


    private void startAnimation() {
        iv_guide.setBackgroundResource(imagesArray[index % imagesArray.length]);
        index++;

        iv_guide.setScaleX(1f);
        iv_guide.setScaleY(1f);

        iv_guide.animate().scaleX(1.2f)
                .scaleY(1.2f).setDuration(3000)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        // 延迟1秒后发消息，发消息后，会调用mHandler的handleMessage方法， 此处what为0，handleMessage会根据0作判断。
                        mHandler.sendEmptyMessageDelayed(0, 1000);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                }).start();
    }

    @Override
    public void initView() {
        iv_guide = (ImageView) findViewById(R.id.iv_guide);
        btn_go = (Button) findViewById(R.id.btn_go);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_guide;
    }



    @Override
    public void initListener() {
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterMainActivity();
            }
        });
    }

    private void enterMainActivity() {
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
        finish();
    }

private MediaPlayer mMediaPlayer;

    public void playMusic(){
        try{
            mMediaPlayer = MediaPlayer.create(this , R.raw.new_version);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.setVolume(1f , 1f);
            mMediaPlayer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stopMusic(){
        if (mMediaPlayer != null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

}
