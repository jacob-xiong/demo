package demo.com.xiongyantest01.activity;

import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import demo.com.xiongyantest01.R;


/**
 * Created by xiongyan on 2017/8/31.
 * 自定义View
 */

public class CustomViewActivity extends BaseActivity {
    private WebView webView;
    private static final String LOAD_URL = "http://www.111.com.c n/items/showDesc.action?itemId=971870";

    @Override
    protected int setLayoutId() {
        return R.layout.custom_view_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {

        initWebView();
    }


    @Override
    protected void initIntent() {

    }

    private void initWebView() {
        webView = (WebView) findViewById(R.id.webview_demo);
        WebSettings settings = webView.getSettings();
//        WebSettings settings = webview.getSettings();
//
//        //webView  加载视频，下面五行必须
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }
//        settings.setJavaScriptEnabled(true);//支持js
//        settings.setPluginState(WebSettings.PluginState.ON);// 支持插件
//        settings.setLoadsImagesAutomatically(true);  //支持自动加载图片
//
//
//        settings.setUseWideViewPort(true);  //将图片调整到适合webview的大小  无效
//        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//
//        webview.setWebChromeClient(new WebChromeClient() );v
//        webview.loadUrl(contextLink);// 加载链接
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //webview的缓存模式
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //自动加载图片
        settings.setLoadsImagesAutomatically(true);
        settings.setBuiltInZoomControls(true);
        //自动缩放
        settings.setSupportZoom(true);
        //设置webview的插件转状态
        settings.setPluginState(WebSettings.PluginState.ON);
        //允许与js交互
        settings.setJavaScriptEnabled(true);
        //设置默认的字符编码
        settings.setDefaultTextEncodingName("utf-8");
        webView.loadUrl(LOAD_URL);

        //为了防止和过滤掉一些其他的网页地址我们可以重写shouldOverrideUrlLoading
        //来覆盖掉之前的url加载路径
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return  true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            /** 你可以在出话之前加载一些资源*/
            @Override
            public void onLoadResource(WebView view, String url) {

            }
        });
    }


    @Override
    protected void onPause() {
        try {
            webView.getClass().getMethod("onPause").invoke(webView, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        try {
            webView.getClass().getMethod("onResume").invoke(webView, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onResume();
    }
}
