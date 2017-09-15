package demo.com.xiongyantest01.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import demo.com.xiongyantest01.R;

/**
 * Created by xiongyan on 2017/9/15.
 * remoteView跨进程更新UI
 * 常用于通知栏+桌面小部件
 */

public class RemoteViewActivity extends BaseActivity implements View.OnClickListener {
    private Button mNormalButton;

    @Override
    protected int setLayoutId() {
        return R.layout.remote_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        mNormalButton = (Button) findViewById(R.id.remote_normal_btn);
        addClick();
    }

    @Override
    protected void initIntent() {

    }

    private void addClick() {
        mNormalButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.remote_normal_btn:
                showNormalNotification();
                break;
            default:
                super.onClick(v);
                break;
        }

    }

    private void showNormalNotification() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_layout);
        remoteViews.setTextViewText(R.id.notification_demo, "哈哈");
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("who case").setContentText("内容").setWhen(System.currentTimeMillis()).setAutoCancel(true).setDeleteIntent(pendingIntent);
        builder.setContent(remoteViews);
        manager.notify(1, builder.build());
    }
}
