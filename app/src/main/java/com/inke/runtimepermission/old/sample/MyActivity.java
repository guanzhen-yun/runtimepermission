package com.inke.runtimepermission.old.sample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

//第二种方式
//@ContentView(R.layout.activity_more) //ioc
public class MyActivity extends MyBaseActivity implements PermissionListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListener(this);
    }

//    @OnClick(R.id.camera)
//    public void openCamera(View btn) {
//         requestRuntimePermission(new String[] {Manifest.permission.CAMERA});
//    }
//
//    @OnClick(R.id.write)
//    public void saveFiles(View btn) {
//        requestRuntimePermission(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE});
//    }
//
//    @OnClick(R.id.more)
//    public void more(View btn) {
//        requestRuntimePermission(new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE});
//    }

    @Override
    public void onGranted() {
        Toast.makeText(this, "用户允许了权限", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDenied(List<String> deniedPermission) {
        Toast.makeText(this, "用户拒绝了权限", Toast.LENGTH_SHORT).show();
        // 用户点击"不再询问"
    }
}
