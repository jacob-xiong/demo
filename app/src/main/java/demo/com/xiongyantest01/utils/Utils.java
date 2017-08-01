package demo.com.xiongyantest01.utils;

import java.util.ArrayList;

import demo.com.xiongyantest01.activity.AnimationActivity;
import demo.com.xiongyantest01.activity.SwipeRefreshActivity;
import demo.com.xiongyantest01.activity.WheelViewActivity;
import demo.com.xiongyantest01.activity.Win8Activity;
import demo.com.xiongyantest01.bean.MainBean;

/**
 * Created by xiongyan on 2017/7/27.
 */

public class Utils {
    private static String[] title = new String[]{"Activity跳转动画", "Win8风格图片", "下拉刷新", "省市区联动WheelView"};
    private static Class<?>[] cls = new Class<?>[]{AnimationActivity.class, Win8Activity.class, SwipeRefreshActivity.class, WheelViewActivity.class};

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
}
