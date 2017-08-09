package demo.com.xiongyantest01.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;

import demo.com.xiongyantest01.R;

/**
 * Created by xiongyan on 2017/8/9.
 * VolleyManager 控制类
 */

public class VolleyManager {

    public static void LoadImage(Context context, ImageView imageView, String url) {
        ImageLoader imageLoader = new ImageLoader(RequestQueueUtils.getQueue(context), new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                R.drawable.bottom, R.drawable.left_bottom);
        imageLoader.get(url, listener);
    }

    public static void LoadImageOther(Context context, final ImageView imageView, String url) {
        ImageRequest imageRequest = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageView.setImageResource(R.drawable.bottom);
            }
        });
        RequestQueueUtils.getQueue(context).add(imageRequest);
    }

}
