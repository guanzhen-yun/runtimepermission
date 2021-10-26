package com.inke.runtimepermission.newmain;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.inke.library2.listener.PermissionRequest;
import com.inke.library2.listener.RequestPermission;
import com.inke.library2.utils.PermissionUtils;

import java.lang.ref.WeakReference;

//public class NewMainActivity$Permissions implements RequestPermission<NewMainActivity> {
//
//    private static final int REQUEST_SHOWCAMERA = 666;
//    private static String[] PERMISSION_SHOWCAMERA;
//
//    public NewMainActivity$Permissions() {
//    }
//
//    public void requestPermission(NewMainActivity target, String[] permissions) {
//        PERMISSION_SHOWCAMERA = permissions;
//        if(PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCAMERA)) {
//            target.showCamera();
//        } else if(PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWCAMERA)) {
//            target.showRationaleForCamera(new PermissionRequestImpl(target));
//        } else {
//            ActivityCompat.requestPermissions(target, PERMISSION_SHOWCAMERA, 666);
//        }
//    }
//
//    public void onRequestPermissionsResult(NewMainActivity target, int requestCode, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case 666:
//                if(PermissionUtils.verifyPermissions(grantResults)) {
//                    target.showCamera();
//                } else if(!PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWCAMERA)) {
//                    target.showNeverAskForCamera();
//                } else {
//                    target.showDeniedForCamera();
//                }
//            default:
//        }
//    }
//
//    private static final class PermissionRequestImpl implements PermissionRequest {
//        private final WeakReference<NewMainActivity> weakTarget;
//
//        private PermissionRequestImpl(NewMainActivity target) {
//            this.weakTarget = new WeakReference(target);
//        }
//
//        public void proceed() {
//            NewMainActivity target = (NewMainActivity) this.weakTarget.get();
//            if(target != null) {
//                ActivityCompat.requestPermissions(target, PERMISSION_SHOWCAMERA, REQUEST_SHOWCAMERA);
//            }
//        }
//    }
//}
