package com.roshine.lspermission.requests;


import com.roshine.lspermission.interfaces.OnPermissionListener;

/**
 * @author Roshine
 * @desc
 */
public interface Request<T extends Request> {

    T permissions(String[] permissions);
    T requestCode(int requestCode);
    T callBack(OnPermissionListener callBack);
    void start();
}
