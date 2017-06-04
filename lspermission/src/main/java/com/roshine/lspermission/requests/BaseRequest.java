package com.roshine.lspermission.requests;


import com.roshine.lspermission.interfaces.ShowRationableListener;

public interface BaseRequest extends Request<BaseRequest> {
    BaseRequest onRationable(ShowRationableListener listener);
}
