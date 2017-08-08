package demo.com.xiongyantest01.activity;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.utils.BitmapCache;
import demo.com.xiongyantest01.utils.Utils;

/**
 * Created by xiongyan on 2017/8/8.
 * Volley网络框架
 */

public class VolleyTestActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout getButton;
    private ImageView requestTv;
    private RequestQueue mQueue;

    @Override
    protected int setLayoutId() {
        return R.layout.volley_test_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        mQueue = Volley.newRequestQueue(context);
        getButton = (LinearLayout) findViewById(R.id.get_button);
        requestTv = (ImageView) findViewById(R.id.request_tv);
        getButton.setOnClickListener(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_button:
                getRequest();
                getJsonRequest();
//                getImageStr();
                addImgaeLoader();
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private void getRequest() {
        StringRequest stringRequest = new StringRequest("https://www.baidu.com",
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", response);
//                        requestTv.setText(Html.fromHtml(response));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(stringRequest);
    }

    private void getJsonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://www.weather.com.cn/data/sk/101010100.html", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("------------------------------" + Utils.getUTF8XMLString(response.toString()));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);
    }

    private void getImageStr() {
        ImageRequest imageRequest = new ImageRequest(
                "http://car3.autoimg.cn/cardfs/product/g4/M06/B2/6F/800x0_1_q87_autohomecar__wKjB01g9ZMWAKLRfAAd5SpRbssA099.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        requestTv.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestTv.setImageResource(R.drawable.bottom);
            }
        });
        mQueue.add(imageRequest);
    }

    private void addImgaeLoader() {
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(requestTv,
                R.drawable.bottom, R.drawable.left_bottom);
        imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener);
    }
}
