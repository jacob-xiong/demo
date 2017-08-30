package demo.com.xiongyantest01.BinderPool;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import demo.com.xiongyantest01.utils.L;

/**
 * Created by xiongyan on 2017/8/28.
 */

public class BinderPoolService extends Service {
    private static final String TAG = "BinderPoolService";
    private Binder mBinderPool = new BinderPool.BinderPoolImpl();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        L.v(TAG, "onBind");
        return mBinderPool;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
