package demo.com.xiongyantest01.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import demo.com.xiongyantest01.R;

/**
 * Created by xiongyan on 2017/8/11.
 * 判断App是否有该权限
 */

public class PermissionActivity extends BaseActivity {
    private TextView demo;
    private Button demoButton;
    private String PERMISSION_URL = "android.permission.USE_CREDENTIALS";

    @Override
    protected int setLayoutId() {
        return R.layout.permission_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        demo = (TextView) findViewById(R.id.permisson_tv);
        demoButton = (Button) findViewById(R.id.get_permisson);
        demoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getNotification()) {
                    demo.setText("有");
                } else {
                    startSetting();
                }
            }
        });
    }

    @Override
    protected void initIntent() {

    }

    private boolean isGetPermission(String permissionUrl) {
        PackageManager pm = getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED == pm.checkPermission(permissionUrl, "packageName"));
        return permission;
    }

    private boolean getNotification() {
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        boolean isOpened = manager.areNotificationsEnabled();
        return isOpened;
    }

    private void startSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getApplication().getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }
}
