package com.inke.library2.listener;

import androidx.annotation.NonNull;

public interface RequestPermission<T> {

    //请求权限值
    void requestPermission(T target, String[] permissions);

    //授权结果返回
    void onRequestPermissionsResult(T target, int requestCode, @NonNull int[] grantResults);
}
