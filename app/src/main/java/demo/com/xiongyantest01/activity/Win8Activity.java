package demo.com.xiongyantest01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import demo.com.xiongyantest01.R;

/**
 * Created by xiongyan on 2017/7/27.
 */

public class Win8Activity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 禁止录屏
         */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.win8_activity);
    }


}
