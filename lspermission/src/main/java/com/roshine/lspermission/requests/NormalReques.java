package com.roshine.lspermission.requests;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;


import com.roshine.lspermission.activity.LSPermission;
import com.roshine.lspermission.activity.LSPermissionActivity;
import com.roshine.lspermission.controller.Controller;
import com.roshine.lspermission.interfaces.OnPermissionListener;
import com.roshine.lspermission.interfaces.OnPermissionRequestResultListener;
import com.roshine.lspermission.interfaces.Rationale;
import com.roshine.lspermission.interfaces.ShowRationableListener;
import com.roshine.lspermission.utils.Constants;
import com.roshine.lspermission.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class NormalReques implements BaseRequest, Rationale, OnPermissionRequestResultListener {
    private Controller mController;
    private String[] mPermissions;
    private int mRequesCode = -100;
    private OnPermissionListener mCallBack;
    private String[] mDeniedPermissions;
    private ShowRationableListener mShowRationableListener;

    public NormalReques(Controller controller) {
        if (controller != null) {
            this.mController = controller;
        } else {
            throw new IllegalArgumentException("controlle is null!");
        }
    }

    @Override
    public BaseRequest permissions(String[] permissions) {
        if (permissions != null) {
            this.mPermissions = permissions;
        } else {
            throw new IllegalArgumentException("permissions is null!");
        }
        return this;
    }

    @Override
    public BaseRequest requestCode(int requestCode) {
        this.mRequesCode = requestCode;
        return this;
    }

    @Override
    public BaseRequest callBack(OnPermissionListener callBack) {
        if (callBack != null) {
            this.mCallBack = callBack;
        } else {
            throw new IllegalArgumentException("callBack is null!");
        }
        return this;
    }

    @Override
    public BaseRequest onRationable(ShowRationableListener listener) {
        if (listener != null) {
            this.mShowRationableListener = listener;
        } else {
            throw new IllegalArgumentException("ShowRationableListener is null!");
        }
        return this;
    }

    @Override
    public void start() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {//api23 以下
            callBackSuccess();
        } else {
            if(mPermissions != null && mRequesCode != -100 && mCallBack != null ){
                mDeniedPermissions = getPermissionForDenied(mController.getContext(), mPermissions);
                if (mDeniedPermissions.length > 0) {
                    boolean shouldShow = mController.getShouldShowRationalePermissions(mDeniedPermissions);
                    if (shouldShow && mShowRationableListener != null) {
                        mShowRationableListener.showRationablDialog(mRequesCode, this);
                    } else {
                        goPermissionActivity();
                    }
                } else {
                    callBackSuccess();
                }
            }else{
                LogUtil.showD(Constants.GLOBAL_TAG,"please check if you have added permissions or request code " +
                        "or request callbake or onRationable callback!");
            }
        }
    }

    private String[] getPermissionForDenied(Context context, String[] mPermissions) {
        if (mPermissions == null) {
            throw new IllegalArgumentException("permissions is null,please add permissions!");
        }
        List<String> denyList = new ArrayList<>();
        for (String permission : mPermissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                denyList.add(permission);
            }
        }
        return denyList.toArray(new String[denyList.size()]);
    }

    private void callBackSuccess() {
        if (mCallBack != null && mRequesCode != -100 && mPermissions != null) {
            mCallBack.onPermissionRequestSuc(mRequesCode, mPermissions);
        } else {
            LogUtil.showD(Constants.GLOBAL_TAG, "please check if you have added a callback " +
                    "or request code or permissions!");
        }
    }

    private void callBackFailed(String[] permissions) {
        if (mCallBack != null && mRequesCode != -100 && permissions != null) {
            mCallBack.onPermissionRequestFail(mRequesCode, permissions);
        } else {
            LogUtil.showD(Constants.GLOBAL_TAG, "please check if you have added a callback " +
                    "or request code or permissions!");
        }
    }

    @Override
    public void goPermissionActivity() {
        LSPermissionActivity.setOnPermissionRequestResultListener(this);
        Intent intent = new Intent(mController.getContext(), LSPermissionActivity.class);
        intent.putExtra(LSPermissionActivity.KEY_PERMISSIONS, mDeniedPermissions);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mController.startActivity(intent);
    }

    @Override
    public void cancel() {
        int[] results = new int[mPermissions.length];
        for (int i = 0; i < mPermissions.length; i++) {
            results[i] = ContextCompat.checkSelfPermission(mController.getContext(), mPermissions[i]);
        }
        OnPermissionRequestResult(mRequesCode, mPermissions, results);
    }

    @Override
    public void OnPermissionRequestResult(int requestCode, String[] permissions, int[] grantResults) {
        if (LSPermission.checkPermissions(mController.getContext(), permissions)) {
            callBackSuccess();
        } else {
            callBackFailed(permissions);
        }
    }

}
