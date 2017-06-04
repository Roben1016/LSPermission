package com.roshine.lspermission.interfaces;

/**
 * @author DJT
 * @desc
 */
public interface OnPermissionListener {
    void onPermissionRequestSuc(int requestCode, String[] permissions);
    void onPermissionRequestFail(int requestCode, String[] permissions);
}
