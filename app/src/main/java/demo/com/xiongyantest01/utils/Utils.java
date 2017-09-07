package demo.com.xiongyantest01.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.bean.MainBean;
import demo.com.xiongyantest01.utils.web.interfacepackage.WebMaps;

/**
 * Created by xiongyan on 2017/7/27.
 */

public class Utils {
    private static String[] title = new String[]{"Activity跳转动画", "Win8风格图片", "下拉刷新", "省市区联动WheelView", "BaseActivity测试", "Volley框架测试", "判定是否有权限","ContentProvider测试","socket测试","BinderConnection连接池","自定义View"};
    private static int [] host = new int[]{R.string.activity_jump, R.string.activity_win8, R.string.activity_SwipeRefresh, R.string.activity_wheel_view, R.string.activity_base_test, R.string.activity_volley, R.string.activity_permission,R.string.activity_provider,R.string.activity_socket,R.string.activity_binder_pool,R.string.activity_custom};

    public static ArrayList<MainBean> getMainData() {
        ArrayList<MainBean> list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            MainBean bean = new MainBean();
            bean.setItemTitle(title[i]);
            bean.setItemHost(host[i]);
            list.add(bean);
        }
        return list;
    }

    public static String getUTF8XMLString(String xml) {
        // A StringBuffer Object
        StringBuffer sb = new StringBuffer();
        sb.append(xml);
        String xmString = "";
        String xmlUTF8 = "";
        try {
            xmString = new String(sb.toString().getBytes("UTF-8"));
            xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
            System.out.println("utf-8 编码：" + xmlUTF8);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // return to String Formed
        return xmlUTF8;
    }

    public static void setSign(WebMaps params) {
        /** try {
         String timestamp = getTimestamp();
         if(User.ecUserId > 0){
         // 在登录的情况下，传用户信息
         params.put("userid", "" + User.ecUserId);
         params.put("username", User.getUserID());
         params.put("token", User.token);
         }

         params.put("encryptversion", "2");
         params.put("timestamp", timestamp);
         params.put("os", OS);
         params.put("venderId", VENDERID);
         params.put("signmethod", AUTHTYPE);
         params.put("format", DATE_FORMAT);
         params.put("type", CLIENT_TYPE);
         params.put("channelName", channelName);
         params.put("versionName", appVersionName);
         params.put("versionCode", String.valueOf(appVersionCode));
         params.put("screensize", String.valueOf(DeviceInfo.getInstance().getScreen()));
         params.put("width", String.valueOf(DeviceInfo.getInstance().getScreenWidth()));
         params.put("height", String.valueOf(DeviceInfo.getInstance().getScreenHeight()));
         params.put("deviceid", Device.uuid);

         StringBuilder source = new StringBuilder();
         source.append("os=");
         source.append(OS);
         source.append("&timestamp=");
         source.append(timestamp);
         source.append("&userid=");
         source.append(URLDecoder.decode((String)params.getValue("userid"), "utf-8"));
         source.append("&username=");
         source.append(URLDecoder.decode((String)params.getValue("username"), "utf-8"));
         source.append("&token=");
         source.append(URLDecoder.decode((String)params.getValue("token"), "utf-8"));
         source.append("&password=");
         source.append(URLDecoder.decode((String)params.getValue("password"),"utf-8"));
         source.append("&appkey=");
         source.append(APPKEY);

         String sign = getSign(source.toString());
         params.put("sign", sign);
         params.put("signstring", source.toString().replace("&appkey=3452AB32D98C987E798E010D798E010D" , ""));
         } catch (Exception e) {
         e.printStackTrace();
         return;
         }**/
    }

    /**
     * @return 返回服务器地址
     */
    public static String getUrl() {
        return "http://10.25.32.231:80";
    }

    public static Intent getIntent(Context context, int host) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getUri(context, host)));
        return intent;
    }

    public static String getUri(Context context, int host) {
        StringBuilder builder = new StringBuilder(context.getResources().getString(R.string.scheme));
        builder.append("://");
        builder.append(context.getResources().getString(host));
        builder.append("/");
        return builder.toString();
    }
}
