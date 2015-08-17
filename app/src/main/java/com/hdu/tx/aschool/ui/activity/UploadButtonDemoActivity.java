package com.hdu.tx.aschool.ui.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.dd.processbutton.FlatButton;
import com.dd.processbutton.iml.ActionProcessButton;
import com.dd.processbutton.iml.GenerateProcessButton;
import com.dd.processbutton.iml.SubmitProcessButton;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.ui.widget.progressbutton.CircularProgressButton;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.hoang8f.widget.FButton;


public class UploadButtonDemoActivity extends Activity {

    @Bind(R.id.btnWithIcons1)
    CircularProgressButton btnWithIcons1;
    @Bind(R.id.btnWithIcons2)
    CircularProgressButton btnWithIcons2;
    @Bind(R.id.btn3)
    FButton btn3;
    @Bind(R.id.bt4)
    ActionProcessButton bt4;
    @Bind(R.id.bt5)
    SubmitProcessButton bt5;
    @Bind(R.id.bt6)
    ActionProcessButton bt6;
    private CircularProgressButton mButton1;
    private CircularProgressButton mButton2;
    private boolean endless_mode;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    if (progress != 100) {
                        progress += 2;
                        bt4.setProgress(progress);
                    } else {
                        timer.cancel();
                        bt4.setEnabled(true);
                        bt4.setProgress(0);
                    }
                    break;
                case 1:
                    if (progress != 100) {
                        progress += 2;
                        bt5.setProgress(progress);
                    } else {
                        timer.cancel();
                        bt5.setEnabled(true);
                        bt5.setProgress(0);
                    }
                    break;
                case 2:
                    if (progress != 100) {
                        progress += 2;
                        bt6.setProgress(progress);
                    } else {
                        timer.cancel();
                        bt6.setEnabled(true);
                        bt6.setProgress(0);
                    }
                    break;
            }
        }
    };
    private Timer timer;
    private int progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadbuttondemo);
        ButterKnife.bind(this);


        mButton1 = (CircularProgressButton) findViewById(R.id.btnWithIcons1);
        mButton2 = (CircularProgressButton) findViewById(R.id.btnWithIcons2);


        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButton1.getProgress() == 0) {
                    simulateSuccessProgress(mButton1);
                } else {
                    mButton1.setProgress(0);
                }
            }
        });


        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButton2.getProgress() == 0) {
                    simulateErrorProgress(mButton2);
                } else {
                    mButton2.setProgress(0);
                }
            }
        });

        if (endless_mode) {

        }


        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress = 0;
                timer = new Timer();
                bt4.setEnabled(false);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0);
                    }
                }, 0, 100);
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress = 0;
                timer = new Timer();
                bt5.setEnabled(false);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                    }
                }, 0, 100);
            }
        });

        bt6.setMode(ActionProcessButton.Mode.PROGRESS);
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress = 0;
                timer = new Timer();
                bt6.setEnabled(false);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(2);
                    }
                }, 0, 100);
            }
        });
    }

    /**
     * �ɹ�
     */
    private void simulateSuccessProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
            }
        });
        widthAnimation.start();
    }

    /**
     * ʧ��
     */
    private void simulateErrorProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 99);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                if (value == 99) {
                    button.setProgress(-1);
                }
            }
        });
        widthAnimation.start();
    }

}
