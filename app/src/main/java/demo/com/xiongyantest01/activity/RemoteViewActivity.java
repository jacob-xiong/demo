package demo.com.xiongyantest01.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RemoteViews;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.IntEvaluator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

import demo.com.xiongyantest01.R;

/**
 * Created by xiongyan on 2017/9/15.
 * remoteView跨进程更新UI
 * 常用于通知栏+桌面小部件
 */

public class RemoteViewActivity extends BaseActivity implements View.OnClickListener{
    private Button mNormalButton;
    private int width = 500;
    private int normalWidth;
    private Button mNewButton;
    private WindowManager.LayoutParams mLayoutParams;
    WindowManager mWindowManager;

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
        addAnimation();
//        addNewButton();
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
                performAnimate();
//                performAnimate(mNormalButton, mNormalButton.getWidth(), mNormalButton.getWidth() - width);
//                width = -width;

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

    private void addAnimation() {
        Animator set = AnimatorInflater.loadAnimator(this, R.anim.property_animator);
        set.setTarget(mNormalButton);
        set.start();
    }

    private void performAnimate() {
        ViewWrapper wrapper = new ViewWrapper(mNormalButton);

        if (normalWidth == 0) {
            normalWidth = mNormalButton.getWidth();
            ObjectAnimator.ofInt(wrapper, "width", width).setDuration(5000).start();
        } else {
            ObjectAnimator.ofInt(wrapper, "width", width > 0 ? width : normalWidth).setDuration(5000).start();

        }
        width = -width;

    }


    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }


    private void performAnimate(final View target, final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currentValue = (int) valueAnimator.getAnimatedValue();
                float fraction = valueAnimator.getAnimatedFraction();
                target.getLayoutParams().width = mEvaluator.evaluate(fraction, start, end);
                target.requestLayout();
            }
        });
        valueAnimator.setDuration(2000).start();
    }

    private void addNewButton() {
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mNewButton = new Button(this);
        mNewButton.setText("测试");
        mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT);
//        mLayoutParams.flags =   WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        mLayoutParams.gravity = Gravity.CENTER;
        mLayoutParams.x = 200;
        mLayoutParams.y = 400;
        mWindowManager.addView(mNewButton, mLayoutParams);
        mNewButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int rawX = (int) event.getX();
                int rawY = (int) event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        mLayoutParams.x = rawX;
                        mLayoutParams.y = rawY;
                        mWindowManager.updateViewLayout(mNewButton, mLayoutParams);
                        break;

                }
                return false;
            }
        });

    }



}
