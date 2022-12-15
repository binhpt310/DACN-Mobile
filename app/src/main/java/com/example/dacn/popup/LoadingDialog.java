package com.example.dacn.popup;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

import com.example.dacn.R;

public class LoadingDialog {
    Context context;
    Dialog dialog;

    public LoadingDialog(Context context){
        this.context = context;
    }

    public  void ShowDialog(){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loading_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.create();
        dialog.show();
        dialog.setCancelable(false);
    }

    public void DismissDialog(){
        dialog.dismiss();
    }

}
