package demo.com.xiongyantest01.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import demo.com.xiongyantest01.activity.AnimationActivity;
import demo.com.xiongyantest01.activity.BaseTestActivity;
import demo.com.xiongyantest01.activity.SwipeRefreshActivity;
import demo.com.xiongyantest01.activity.VolleyTestActivity;
import demo.com.xiongyantest01.activity.WheelViewActivity;
import demo.com.xiongyantest01.activity.Win8Activity;
import demo.com.xiongyantest01.bean.MainBean;

/**
 * Created by xiongyan on 2017/7/27.
 */

public class Utils {
    private static String[] title = new String[]{"Activity跳转动画", "Win8风格图片", "下拉刷新", "省市区联动WheelView", "BaseActivity测试","Volley框架测试"};
    private static Class<?>[] cls = new Class<?>[]{AnimationActivity.class, Win8Activity.class, SwipeRefreshActivity.class, WheelViewActivity.class, BaseTestActivity.class, VolleyTestActivity.class};

    public static ArrayList<MainBean> getMainData() {
        ArrayList<MainBean> list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            MainBean bean = new MainBean();
            bean.setItemTitle(title[i]);
            bean.setItemClass(cls[i]);
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
}
