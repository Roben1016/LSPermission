package com.roshine.lspermission.interfaces;

/**
 * @author DJT
 * @desc
 */
public interface OnPermissionRequestResultListener {
    void OnPermissionRequestResult(int requestCode, String[] permissions, int[] grantResults);
}
