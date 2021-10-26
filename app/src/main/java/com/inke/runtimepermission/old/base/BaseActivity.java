package com.inke.runtimepermission.old.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inke.library.PermissionManager;
import com.inke.library.listener.PermissionCallback;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity implements PermissionCallback {

    private static final String TAG = "PermissionCallback";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectManager.inject(this);
    }

    @Override
    public void onPermissionGranted(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionGranted:   requestCode=" + requestCode + "/perms.size()=" + perms.size());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.onRequestPermissionResult(requestCode, permissions, grantResults, this);
    }
}
