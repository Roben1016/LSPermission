package com.roshine.lspermission.controller;

import android.content.Context;
import android.content.Intent;

/**
 * @author Roshine
 * @desc
 */
public interface Controller {
    Context getContext();

    boolean getShouldShowRationalePermissions(String[] mDeniedPermissions);

    void startActivity(Intent intent);
    void startActivityForResult(Intent intent, int requestCode);
}
