package com.roshine.lspermission_master;

import android.Manifest;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.roshine.lspermission.activity.LSPermission;
import com.roshine.lspermission.interfaces.OnPermissionListener;
import com.roshine.lspermission.interfaces.Rationale;
import com.roshine.lspermission.interfaces.ShowRationableListener;

/**
 * @author Roshine
 * @date 2017/6/4 13:54
 * @desc
 */
public class FragmentPermission extends Fragment implements View.OnClickListener ,OnPermissionListener {
    private static final int REQUEST_CALL_CODE = 102;
    private static final String TAG = "Roshine";
    private static final int REQUEST_CARD_CODE = 103;
    private static final int REQUEST_CAMERA_CODE = 104;
    private static final int REQUEST_MULTI_CODE = 105;
    private Button btnCall,btnSdCard,btnCamera,btnMulti;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_permission,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initListener();
    }

    private void initView(View view) {
        btnCall = (Button) view.findViewById(R.id.btn_call);
        btnSdCard = (Button) view.findViewById(R.id.btn_sd_card);
        btnCamera = (Button) view.findViewById(R.id.btn_camera);
        btnMulti = (Button) view.findViewById(R.id.btn_multi);
    }
    private void initListener() {
        btnCall.setOnClickListener(this);
        btnSdCard.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call:
                LSPermission.with(this)
                        .permissions(new String[]{Manifest.permission.CALL_PHONE})
                        .requestCode(REQUEST_CALL_CODE)
                        .callBack(this)
                        .onRationable(new ShowRationableListener() {
                            @Override
                            public void showRationablDialog(int requestCode, Rationale rationale) {
                                Toast.makeText(getActivity(),"弹出需要电话权限原因",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .start();
                break;
            case R.id.btn_sd_card:
                LSPermission.with(this)
                        .permissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})
                        .requestCode(REQUEST_CARD_CODE)
                        .callBack(this)
                        .onRationable(new ShowRationableListener() {
                            @Override
                            public void showRationablDialog(int requestCode, Rationale rationale) {
                                Toast.makeText(getActivity(),"弹出需要存储卡权限原因",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .start();
                break;
            case R.id.btn_camera:
                LSPermission.with(this)
                        .permissions(new String[]{Manifest.permission.CAMERA})
                        .requestCode(REQUEST_CAMERA_CODE)
                        .callBack(this)
                        .onRationable(new ShowRationableListener() {
                            @Override
                            public void showRationablDialog(int requestCode, Rationale rationale) {
                                Toast.makeText(getActivity(),"弹出需要相机权限原因",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .start();
                break;
            case R.id.btn_multi:
                LSPermission.with(this)
                        .permissions(new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE})
                        .requestCode(REQUEST_MULTI_CODE)
                        .callBack(this)
                        .onRationable(new ShowRationableListener() {
                            @Override
                            public void showRationablDialog(int requestCode, Rationale rationale) {
                                Toast.makeText(getActivity(),"弹出需要多个权限原因",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .start();
                break;

            default:
                break;
        }
    }
    @Override
    public void onPermissionRequestSuc(int requestCode, String[] permissions) {
        switch (requestCode) {
            case REQUEST_CALL_CODE:
                Toast.makeText(getActivity(),"电话权限打开成功",Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_CARD_CODE:
                Toast.makeText(getActivity(),"sd卡权限打开成功",Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_CAMERA_CODE:
                Toast.makeText(getActivity(),"相机权限打开成功",Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_MULTI_CODE:
                Toast.makeText(getActivity(),"多个权限打开成功",Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    @Override
    public void onPermissionRequestFail(int requestCode, String[] permissions) {
        switch (requestCode) {
            case REQUEST_CALL_CODE:
                Toast.makeText(getActivity(),"电话权限打开失败",Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_CARD_CODE:
                Toast.makeText(getActivity(),"sd卡权限打开失败",Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_CAMERA_CODE:
                Toast.makeText(getActivity(),"相机权限打开失败",Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_MULTI_CODE:
                Toast.makeText(getActivity(),"多个权限打开失败",Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}
