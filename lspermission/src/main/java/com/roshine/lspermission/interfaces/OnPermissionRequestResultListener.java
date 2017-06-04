package com.roshine.lspermission.interfaces;

public interface OnPermissionRequestResultListener {
    void OnPermissionRequestResult(int requestCode, String[] permissions, int[] grantResults);
}
