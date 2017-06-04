package com.roshine.lspermission.requests;


import com.roshine.lspermission.interfaces.OnPermissionListener;

public interface Request<T extends Request> {

    T permissions(String[] permissions);
    T requestCode(int requestCode);
    T callBack(OnPermissionListener callBack);
    void start();
}
