package demo.com.xiongyantest01.utils.web;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import demo.com.xiongyantest01.utils.web.interfacepackage.IRequest;
import demo.com.xiongyantest01.utils.web.interfacepackage.WebMap;

/**
 * Created by xiongyan on 2017/8/10.
 */

public class Web {
    private static RequestQueue mRequestQueue;

    /**
     * 初始化WEB
     * @return
     */
    public static boolean init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        return true;
    }

    public static IRequest getIRequest() {
        return new GetRequest(mRequestQueue);
    }

    public static WebMap getWebMap() {
        return new WebMap();
    }
}
