package com.hdu.tx.aschool.ui.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.ui.widget.progressbutton.CircularProgressButton;


public class UploadButtonDemoActivity extends Activity {
	
	private CircularProgressButton mButton1;
	private CircularProgressButton mButton2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uploadbuttondemo);
		
		
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
