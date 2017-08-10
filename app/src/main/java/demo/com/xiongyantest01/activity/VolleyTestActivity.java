package demo.com.xiongyantest01.activity;

import android.os.Build;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import demo.com.xiongyantest01.JsonRequest.JsonRequestWithAuth;
import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.bean.OrmliteBean.User;
import demo.com.xiongyantest01.bean.ReturnTemple.ReturnTemple;
import demo.com.xiongyantest01.utils.RequestQueueUtils;
import demo.com.xiongyantest01.utils.Utils;
import demo.com.xiongyantest01.utils.VolleyManager;
import demo.com.xiongyantest01.utils.web.JsonParser.DemoParser;
import demo.com.xiongyantest01.utils.web.Web;
import demo.com.xiongyantest01.utils.web.interfacepackage.IRequest;
import demo.com.xiongyantest01.utils.web.interfacepackage.WebMaps;

/**
 * Created by xiongyan on 2017/8/8.
 * Volley网络框架
 */

public class VolleyTestActivity extends BaseActivity implements View.OnClickListener {
    private static String IMG_URL = "http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";
    private static String IMG_URL_OTHER = "http://car3.autoimg.cn/cardfs/product/g4/M06/B2/6F/800x0_1_q87_autohomecar__wKjB01g9ZMWAKLRfAAd5SpRbssA099.jpg";
    private LinearLayout getButton;
    private ImageView requestTv;
    private RequestQueue mQueue;
    private static final int DEMO_MES = 1024;

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
                getImageStr();
//                addImgaeLoader();
                setTest1();
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
        VolleyManager.LoadImageOther(context, requestTv, IMG_URL_OTHER);
//        ImageRequest imageRequest = new ImageRequest(
//                "http://car3.autoimg.cn/cardfs/product/g4/M06/B2/6F/800x0_1_q87_autohomecar__wKjB01g9ZMWAKLRfAAd5SpRbssA099.jpg",
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap response) {
//                        requestTv.setImageBitmap(response);
//                    }
//                }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                requestTv.setImageResource(R.drawable.bottom);
//            }
//        });
//        mQueue.add(imageRequest);
    }

    private void addImgaeLoader() {
        VolleyManager.LoadImage(context, requestTv, IMG_URL);
    }


    private void setTest() {
        Map<String, String> appendHeader = new HashMap<String, String>();
        appendHeader.put("username", "jacob");
        appendHeader.put("password", "123");

        String url = "http://10.25.32.231:80/demo/TestServlet";
        JsonRequestWithAuth<User> userRequest = new JsonRequestWithAuth<User>(url, User.class, new Response.Listener<User>() {


            @Override
            public void onResponse(User response) {
                System.out.println("==========================" + response.getName());
                System.out.println("==========================" + response.getId());
            }
        }, appendHeader, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("==========================" + volleyError.toString());
            }
        });

        RequestQueueUtils.getQueue(context).add(userRequest);
    }

    private void setTest1() {
        IRequest iRequest = Web.getIRequest();
        WebMaps map = Web.getWebMap();
        map.put("username", "jacob");
        map.put("password", "123");
        iRequest.getRequest(map, new DemoParser(), handler, DEMO_MES, "TestServlet");
    }

    @Override
    protected void dealWithMessage(Message msg) {
        super.dealWithMessage(msg);
        switch (msg.what) {
            case DEMO_MES:
                if (msg.obj != null) {
                    ReturnTemple temple = (ReturnTemple) msg.obj;
                    User user = (User) temple.data;
                    TextView requestTV = (TextView) findViewById(R.id.request_demo);
                    System.out.println("==========================" + user.getName());
                    System.out.println("==========================" + user.getId());
                    requestTV.setText(user.getName() + user.getId());
                }
                break;
        }
    }
}
