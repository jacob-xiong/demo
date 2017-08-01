package demo.com.xiongyantest01.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import demo.com.xiongyantest01.R;

/**
 * Created by xiongyan on 2017/7/27.
 * 利用Theme自定义Activity间的切换动画
 * blog http://blog.csdn.NET/lmj623565791/article/details/22990643
 */

public class AnimationActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_activity);
        TextView demo = (TextView) findViewById(R.id.demo);
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIntent();
            }
        });
    }


    private void sendIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
