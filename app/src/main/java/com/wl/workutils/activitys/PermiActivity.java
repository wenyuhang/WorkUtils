package com.wl.workutils.activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wl.workutils.R;
import com.wl.workutils.config.Action;
import com.wl.workutils.utils.SystemUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 权限管理页面
 */

@RuntimePermissions
public class PermiActivity extends AppCompatActivity {


    @Bind(R.id.btn_open_camera)
    Button btnOpenCamera;
    @Bind(R.id.btn_open_location)
    Button btnOpenLocation;
    @Bind(R.id.btn_open_call)
    Button btnOpenCall;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permi);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_open_camera, R.id.btn_open_location, R.id.btn_open_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_open_camera:
                PermiActivityPermissionsDispatcher.cameraWithPermissionCheck(PermiActivity.this);
                break;
            case R.id.btn_open_location:
                break;
            case R.id.btn_open_call:
                PermiActivityPermissionsDispatcher.callWithPermissionCheck(PermiActivity.this);
                break;
        }
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void camera(){
        SystemUtils.getInstance().openCamera(this, Action.TYPE_CAMERA);
    }
    //OnShowRationale提示用户为何要开启权限
    @OnShowRationale(Manifest.permission.CAMERA)
    //提示用户为何要开启权限
    void showMsg(final PermissionRequest request) {
        new AlertDialog.Builder(this).setMessage("请开启相机的权限").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                request.proceed();//再次请求权限
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();//取消执行请求
            }
        }).show();
    }
    //OnPermissionDenied 用户选择拒绝时的提示
    @OnPermissionDenied({Manifest.permission.CAMERA})
    void showCDenied() {
        Toast.makeText(this, "您拒绝了此权限", Toast.LENGTH_SHORT).show();
    }

    //NeedsPermission 用来获取权限
    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void call() {
        intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //OnShowRationale提示用户为何要开启权限
    @OnShowRationale(Manifest.permission.CALL_PHONE)
    //提示用户为何要开启权限
    void showWhy(final PermissionRequest request) {
        new AlertDialog.Builder(this).setMessage("请开启打电话的权限").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                request.proceed();//再次请求权限
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();//取消执行请求
            }
        }).show();
    }

    //OnPermissionDenied 用户选择拒绝时的提示
    @OnPermissionDenied({Manifest.permission.CALL_PHONE})
    void showDenied() {
        Toast.makeText(this, "您拒绝了此权限", Toast.LENGTH_SHORT).show();
    }

    //OnNeverAskAgain 用户选择不再询问后的提示
    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void showNotAsk() {
        new AlertDialog.Builder(this).setMessage("需要访问电话的权限，不开启将无法使用！").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                //设置去向意图
                Uri uri = Uri.fromParts("package", PermiActivity.this.getPackageName(), null);
                intent.setData(uri);
                //发起跳转
                startActivity(intent);
            }
        }).setNegativeButton("取消", null).show();
    }


    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermiActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }


}
