package com.hdu.tx.aschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pualgo on 2015/8/25.
 */
public class PartyIntrodue extends BaseActivity {
    @Bind(R.id.bt_introdue)
    Button btIntrodue;
    @Bind(R.id.edit_introdue)
    LinearLayout editIntrodue;
    String str=null;
    @Bind(R.id.edit_Text)
    EditText editText;
   public final static String MSG="MSG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.party_introdue);
        ButterKnife.bind(this);
        Intent intent= getIntent();
       Bundle bundle = intent.getExtras();
        String str = bundle.getString(MSG);
        editText.setText(str);

    }
    public void CancelIntrodue(View view)
    {
        str = editText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(MSG,str);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void FinishIntrodue(View view) {
        str = editText.getText().toString();
       Intent intent = new Intent();
        intent.putExtra(MSG,str);
        setResult(RESULT_OK, intent);
        finish();

    }
}

