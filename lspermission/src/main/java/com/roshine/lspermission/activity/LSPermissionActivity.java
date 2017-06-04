package com.roshine.lspermission.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.roshine.lspermission.interfaces.OnPermissionRequestResultListener;


/**
 * @author Roshine
 * @desc
 */
@TargetApi(Build.VERSION_CODES.M)
public class LSPermissionActivity extends Activity {

    public static final String KEY_PERMISSIONS = "key_permissions";
    private static final int ACTIVITY_REQUEST_CODE = 101;
    private static OnPermissionRequestResultListener resultListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String[] permissions = intent.getStringArrayExtra(KEY_PERMISSIONS);
        if(permissions == null || resultListener == null){
            finish();
        }else{
            requestPermissions(permissions,ACTIVITY_REQUEST_CODE);
        }
    }

    public static void setOnPermissionRequestResultListener(OnPermissionRequestResultListener listener){
        resultListener = listener;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (resultListener != null) {
            resultListener.OnPermissionRequestResult(requestCode,permissions,grantResults);
        }
        resultListener = null;
        finish();
    }
}
