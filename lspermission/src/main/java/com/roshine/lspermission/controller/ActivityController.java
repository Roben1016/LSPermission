package com.roshine.lspermission.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * @author Roshine
 * @desc
 */
public class ActivityController implements Controller {
    private Activity mActivity;
    public ActivityController(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public Context getContext() {
        if (mActivity != null) {
            return mActivity;
        }else{
            throw new IllegalArgumentException("activity is null");
        }
    }

    @Override
    public boolean getShouldShowRationalePermissions(String[] deniedPermissions) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return false;
        }else{
            if (deniedPermissions != null) {
                for (String deniedPermission : deniedPermissions) {
                    if(mActivity.shouldShowRequestPermissionRationale(deniedPermission)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void startActivity(Intent intent) {
        if (mActivity != null) {
            mActivity.startActivity(intent);
        }else{
            throw new IllegalArgumentException("activity is null!");
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (mActivity != null) {
            mActivity.startActivityForResult(intent,requestCode);
        }else{
            throw new IllegalArgumentException("activity is null!");
        }
    }
}
