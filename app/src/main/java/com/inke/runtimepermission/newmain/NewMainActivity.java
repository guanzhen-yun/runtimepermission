package com.inke.runtimepermission.newmain;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inke.annotations.NeedsPermission;
import com.inke.annotations.OnNeverAskAgain;
import com.inke.annotations.OnPermissionDenied;
import com.inke.annotations.OnShowRetionale;
import com.inke.library2.PermissionManager;
import com.inke.library2.listener.PermissionRequest;
import com.inke.runtimepermission.R;

/**
 * 第三种 Apt javapoet
 */
public class NewMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmain);

        //如果跳转到我的XXX 界面
//        if(SharedPreferencesUtils.isLogined()) {
//            startActivity(new Intent(this, TargetActivity.class));
//        }
    }

    public void camera(View view) {
        PermissionManager.request(this, new String[]{Manifest.permission.CAMERA});
    }

    //在需要获取权限的地方注释(授权通过之后的执行代码!!!)
    @NeedsPermission()
    void showCamera() {
        Log.e("neteast >>> ","showCamera()");
    }

    //提示用户为何要开启权限
    @OnShowRetionale()
    void showRationaleForCamera(final PermissionRequest request) {
        Log.e("neteast >>> ","showRationaleForCamera()");
        new AlertDialog.Builder(this)
                .setMessage("提示用户为何要开启权限")
                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //再次执行权限请求
                        request.proceed();
                    }
                })
                .show();
    }

    //用户选择拒绝时的提示
    @OnPermissionDenied()
    void showDeniedForCamera() {
        Log.e("neteast >>> ","showDeniedForCamera()");
    }

    //用户选择不再询问后的提示
    @OnNeverAskAgain()
    void showNeverAskForCamera() {
        Log.e("neteast >>> ", "showNeverAskForCamera()");
        new AlertDialog.Builder(this)
                .setMessage("用户选择不再询问后的提示")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("neteast >>> ","showNeverAskForCamera() >>> Dialog");
                    }
                })
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("neteast >>> ","onRequestPermissionResult()");
        PermissionManager.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
