package com.inke.runtimepermission.old.sample;

import java.util.List;

public interface PermissionListener {
    void onGranted();

    void onDenied(List<String> deniedPermission);
}
