package demo.com.xiongyantest01.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by xiongyan on 2017/8/9.
 */

public class RequestQueueUtils {
    private static RequestQueue mQueue;

    public static RequestQueue getQueue(Context context) {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(context);
        }
        return mQueue;
    }
}
