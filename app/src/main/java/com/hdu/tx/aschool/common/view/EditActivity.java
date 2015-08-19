package com.hdu.tx.aschool.common.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/8/18.
 */
public class EditActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.iv_clear)
    ImageView ivClear;
    @Bind(R.id.decript)
    TextView decript;
    @Bind(R.id.btn_submit)
    ActionProcessButton btnSubmit;
    @Bind(R.id.title_tv)
    TextView titleTv;
    private int progress;
    private Timer timer;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (progress != 100) {
                progress += 2;
                btnSubmit.setProgress(progress);
            } else {
                timer.cancel();
                btnSubmit.setEnabled(true);
                //btnSubmit.setProgress(0);
            }

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_editactivity);
        ButterKnife.bind(this);


        Bundle bundle = getIntent().getExtras();

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (bundle != null) {
            titleTv.setText(bundle.getString("title"));
            decript.setText(bundle.getString("descript"));
            etUsername.setHint(bundle.getString("hint"));


//            toolbar.setTitle("title");
//            decript.setText("descript");
//            textinput.setHint("hint");
        }

    //    final EditText editText = textinput.getEditText();
        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUsername.setText("");
            }
        });
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    ivClear.setVisibility(View.VISIBLE);
                } else {
                    ivClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress = 0;
                timer = new Timer();
                btnSubmit.setEnabled(false);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0);
                    }
                }, 0, 100);
            }
        });
    }
}
