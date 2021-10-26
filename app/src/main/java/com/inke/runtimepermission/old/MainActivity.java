package com.inke.runtimepermission.old;

import android.Manifest;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.ContentView;

import com.inke.library.PermissionManager;
import com.inke.library.annotation.IPermission;
import com.inke.runtimepermission.R;
import com.inke.runtimepermission.old.base.PermissionActivity;

//第三种方式--反射

@ContentView(R.layout.activity_main)
public class MainActivity extends PermissionActivity {

    private static final int CAMERA_REQUEST_CODE = 111; //拍照权限请求标识码
    private static final int LOCATION_CONTACTS_CODE = 222; //位置、联系人权限请求标识码

    @OnClick(R.id.singlePermission)
    public void singlePermission(View btn) {
        cameraTask();
    }

    @OnClick(R.id.multiPermission)
    public void multiPermission(View btn) {
        locationContactsTask();
    }

    @IPermission(CAMERA_REQUEST_CODE)
    private void cameraTask() { // private
        if(PermissionManager.hasPermissions(this, Manifest.permission.CAMERA)) {// 授权通过
            Toast.makeText(this, "授权通过! ", Toast.LENGTH_SHORT).show();
        } else { // 请求授权
            PermissionManager.requestPermissions(this, CAMERA_REQUEST_CODE, Manifest.permission.CAMERA);
        }
    }

    @IPermission(LOCATION_CONTACTS_CODE)
    private void locationContactsTask() { // private
        if(PermissionManager.hasPermissions(this, Manifest.permission.CAMERA)) {// 授权通过
            Toast.makeText(this, "授权通过! ", Toast.LENGTH_SHORT).show();
        } else { // 请求授权
            PermissionManager.requestPermissions(this, CAMERA_REQUEST_CODE, Manifest.permission.CAMERA);
        }
    }
}
