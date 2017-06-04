package com.roshine.lspermission.requests;


import com.roshine.lspermission.interfaces.ShowRationableListener;

/**
 * @author Roshine
 * @desc
 */
public interface BaseRequest extends Request<BaseRequest> {
    BaseRequest onRationable(ShowRationableListener listener);
}
