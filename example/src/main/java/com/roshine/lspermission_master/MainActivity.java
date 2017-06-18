package com.roshine.lspermission_master;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.roshine.lspermission.activity.LSPermission;
import com.roshine.lspermission.interfaces.OnPermissionListener;
import com.roshine.lspermission.interfaces.Rationale;
import com.roshine.lspermission.interfaces.ShowRationableListener;
import com.roshine.lspermission.utils.LogUtil;

/**
 * @author Roshine
 * @date 2017/6/3 16:50
 * @desc
 */
public class MainActivity extends Activity implements View.OnClickListener, OnPermissionListener {
    private static final int REQUEST_CALL_CODE = 102;
    private static final String TAG = "Roshine";
    private static final int REQUEST_CARD_CODE = 103;
    private static final int REQUEST_CAMERA_CODE = 104;
    private static final int REQUEST_MULTI_CODE = 105;
    private Button btnCall,btnSdCard,btnCamera,btnMulti,btnFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.open = true;
        initView();
        initListener();
    }

    private void initView() {
        btnCall = (Button) findViewById(R.id.btn_call);
        btnSdCard = (Button) findViewById(R.id.btn_sd_card);
        btnCamera = (Button) findViewById(R.id.btn_camera);
        btnMulti = (Button) findViewById(R.id.btn_multi);
        btnFragment = (Button) findViewById(R.id.btn_fragment);
    }

    private void initListener() {
        btnCall.setOnClickListener(this);
        btnSdCard.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_call:
                LSPermission.with(this)
                        .permissions(new String[]{Manifest.permission.CALL_PHONE})
                        .requestCode(REQUEST_CALL_CODE)
                        .callBack(this)
                        .onRationable(new ShowRationableListener() {
                            @Override
                            public void showRationablDialog(int requestCode, Rationale rationale) {
                                Toast.makeText(getApplicationContext(),"弹出需要电话权限原因",Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(getApplicationContext(),"弹出需要存储卡权限原因",Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(getApplicationContext(),"弹出需要相机权限原因",Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(getApplicationContext(),"弹出需要多个权限原因",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .start();
                break;
            case R.id.btn_fragment:
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.id_content,new FragmentPermission());
                transaction.commit();
                break;

            default:
        	    break;
        }
    }

    @Override
    public void onPermissionRequestSuc(int requestCode, String[] permissions) {
        switch (requestCode) {
            case REQUEST_CALL_CODE:
        	    Toast.makeText(getApplicationContext(),"电话权限打开成功",Toast.LENGTH_SHORT).show();
        	    break;
            case REQUEST_CARD_CODE:
                Toast.makeText(getApplicationContext(),"sd卡权限打开成功",Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_CAMERA_CODE:
                Toast.makeText(getApplicationContext(),"相机权限打开成功",Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_MULTI_CODE:
                Toast.makeText(getApplicationContext(),"多个权限打开成功",Toast.LENGTH_SHORT).show();
                break;

            default:
        	    break;
        }
    }

    @Override
    public void onPermissionRequestFail(int requestCode, String[] permissions) {
        switch (requestCode) {
            case REQUEST_CALL_CODE:
                Toast.makeText(getApplicationContext(),"电话权限打开失败",Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_CARD_CODE:
                Toast.makeText(getApplicationContext(),"sd卡权限打开失败",Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_CAMERA_CODE:
                Toast.makeText(getApplicationContext(),"相机权限打开失败",Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_MULTI_CODE:
                Toast.makeText(getApplicationContext(),"多个权限打开失败",Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}
