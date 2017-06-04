package com.roshine.lspermission.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;


import com.roshine.lspermission.controller.ActivityController;
import com.roshine.lspermission.controller.FragmentController;
import com.roshine.lspermission.controller.FragmentV4Controller;
import com.roshine.lspermission.controller.NormalContextController;
import com.roshine.lspermission.requests.BaseRequest;
import com.roshine.lspermission.requests.NormalReques;

import java.util.Arrays;
import java.util.List;

public class LSPermission {

    public static boolean checkPermissions(Context context,String[] permissions) {
        return checkPermissions(context, Arrays.asList(permissions));
    }

    public static boolean checkPermissions(Context context,List<String> permissions){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }else {
            for (String permission : permissions) {
                String op = AppOpsManagerCompat.permissionToOp(permission);
                if (TextUtils.isEmpty(op)) continue;
                int result = AppOpsManagerCompat.noteProxyOp(context, op, context.getPackageName());
                if (result == AppOpsManagerCompat.MODE_IGNORED) return false;
                result = ContextCompat.checkSelfPermission(context, permission);
                if (result != PackageManager.PERMISSION_GRANTED) return false;
            }
            return true;
        }
    }
    public static BaseRequest with(Activity activity){
        return new NormalReques(new ActivityController(activity));
    }
    public static BaseRequest with(Fragment fragment){
        return new NormalReques(new FragmentController(fragment));
    }
    public static BaseRequest with(android.support.v4.app.Fragment fragment){
        return new NormalReques(new FragmentV4Controller(fragment));
    }
    public static BaseRequest with(Context context){
        return new NormalReques(new NormalContextController(context));
    }
}
