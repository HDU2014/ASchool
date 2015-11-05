package com.hdu.tx.aschool.common.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.ui.activity.MainActivity;

/**
 * Created by Administrator on 2015/8/15.
 */
public class MyNiftyDialog {

    public  MyNiftyDialog(Activity context){
        final NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(context);
        dialogBuilder
                .withTitle("Modal Dialog")                                  //.withTitle(null)  no title
                .withTitleColor("#ffffffff")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessage("This is a modal Dialog.\nThis is a modal Dialog.")                     //.withMessage(null)  no Msg
                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                .withDialogColor(context.getResources().getColor(R.color.colorPrimary))                               //def  | withDialogColor(int resid)
                .withIcon(context.getResources().getDrawable(R.drawable.ic_plus))
                .withDuration(300)                                          //def
                .withEffect(Effectstype.SlideBottom)                                         //def Effectstype.Slidetop
                .withButton1Text("OK")                                      //def gone
                .withButton2Text("Cancel")                                  //def gone
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "i'm btn1", Toast.LENGTH_SHORT).show();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                    }
                })
                .show();
    }

}
