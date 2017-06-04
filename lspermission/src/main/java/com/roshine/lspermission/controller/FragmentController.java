package com.roshine.lspermission.controller;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * @author Roshine
 * @desc
 */
public class FragmentController implements Controller {
    private Fragment mFragment;
    public FragmentController(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Override
    public Context getContext() {
        if (mFragment != null) {
            return mFragment.getActivity();
        }else{
            throw new IllegalArgumentException("fragment is null!");
        }
    }

    @Override
    public boolean getShouldShowRationalePermissions(String[] deniedPermissions) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return false;
        }else{
            if (deniedPermissions != null) {
                for (String deniedPermission : deniedPermissions) {
                    if(mFragment.shouldShowRequestPermissionRationale(deniedPermission)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void startActivity(Intent intent) {
        if (mFragment != null) {
            mFragment.startActivity(intent);
        }else{
            throw new IllegalArgumentException("fragment is null!");
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (mFragment != null) {
            mFragment.startActivityForResult(intent,requestCode);
        }else{
            throw new IllegalArgumentException("fragment is null!");
        }

    }
}
