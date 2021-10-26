package com.inke.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.inke.library.R;

public class AppSettingDialog {
    public static final int SETTING_CODE = 100;

    Dialog dialog;
    public AppSettingDialog(Builder builder) {
        dialog = new Dialog(builder.context);
    }

    public void show() {
        dialog.show();
    }

    public static class Builder {
        Context context;
        DialogInterface.OnClickListener onClickListener;
        public Builder(Context context) {
            this.context = context;
        }

        public Builder setListener(DialogInterface.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }


        public AppSettingDialog build() {
            AppSettingDialog appSettingDialog = new AppSettingDialog(this);
            return appSettingDialog;
        }
    }
}
