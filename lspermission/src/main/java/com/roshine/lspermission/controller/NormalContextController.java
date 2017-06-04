package com.roshine.lspermission.controller;

import android.content.Context;
import android.content.Intent;

/**
 * @author Roshine
 * @desc
 */
public class NormalContextController implements Controller {
    private Context mContext;

    public NormalContextController(Context context) {
        if (context != null) {
            this.mContext = context;
        }else{
            throw new IllegalArgumentException("context is null!");
        }
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public boolean getShouldShowRationalePermissions(String[] deniedPermissions) {
        return false;
    }

    @Override
    public void startActivity(Intent intent) {
        if (mContext != null) {
            mContext.startActivity(intent);
        }else{
            throw new IllegalArgumentException("context is null!");
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (mContext != null) {
            mContext.startActivity(intent);
        }else{
            throw new IllegalArgumentException("context is null!");
        }
    }
}
