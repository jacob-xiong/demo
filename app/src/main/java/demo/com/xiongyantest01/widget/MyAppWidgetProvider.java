package demo.com.xiongyantest01.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.widget.RemoteViews;

import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.utils.L;
import demo.com.xiongyantest01.utils.T;

/**
 * Created by xiongyan on 2017/9/18.
 */

public class MyAppWidgetProvider extends android.appwidget.AppWidgetProvider {
    private static final String TAG = "AppWidgetProvider";
    public static final String CLICK_ACTION = "demo";

    public MyAppWidgetProvider() {
        super();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        L.i(TAG, "onReceive:action=" + intent.getAction());
        if (intent.getAction().equals(CLICK_ACTION)) {
            T.showShort(context, "CLICK");
            new Thread() {
                @Override
                public void run() {
                    Bitmap srcBT = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    for (int i = 0; i < 37; i++) {
                        float degree = (i * 10) % 360;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
                        remoteViews.setImageViewBitmap(R.id.widget_image_view, rotateBitMap(context, srcBT, degree));
                        Intent intent = new Intent();
                        intent.setAction(CLICK_ACTION);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                        remoteViews.setOnClickPendingIntent(R.id.widget_image_view, pendingIntent);
                        appWidgetManager.updateAppWidget(new ComponentName(context, MyAppWidgetProvider.class), remoteViews);
                        SystemClock.sleep(30);

                    }
                }
            }.start();
        }
    }

    private Bitmap rotateBitMap(Context context, Bitmap srcBT, float degree) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap bitMap = Bitmap.createBitmap(srcBT, 0, 0, srcBT.getWidth(), srcBT.getHeight(), matrix, true);
        return bitMap;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        L.i(TAG, "update");
        final int counter = appWidgetIds.length;
        for (int i = 0; i < counter; i++) {
            int appWiggetId = appWidgetIds[i];
            onWidgetUpdate(context, appWidgetManager, appWiggetId);
        }
    }

    private void onWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int appWiggetId) {
        L.i(TAG, "onWidgetUpdate");
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent intent = new Intent();
        intent.setAction(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.widget_image_view, pendingIntent);
        appWidgetManager.updateAppWidget(appWiggetId, remoteViews);


    }
}
