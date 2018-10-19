package demo.com.xiongyantest01.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.utils.AllCapTransformationMethod;
import demo.com.xiongyantest01.widget.MarqueeView;

/**
 * Created by xiongyan on 2017/7/27.
 * 利用Theme自定义Activity间的切换动画
 * blog http://blog.csdn.NET/lmj623565791/article/details/22990643
 */

public class AnimationActivity extends Activity {
    TextView demo, demo1, demo2, demo3, demo4, demo5, pay;
    EditText mEditText, mEditText1;
    Button buttonDemo;
    MarqueeView marqueeView;
    ViewFlipper mViewFlipper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_activity);
        demo = (TextView) findViewById(R.id.demo);
        demo1 = (TextView) findViewById(R.id.demo1);
        demo2 = (TextView) findViewById(R.id.demo2);
        demo3 = (TextView) findViewById(R.id.demo3);
        demo4 = (TextView) findViewById(R.id.demo4);
        demo5 = (TextView) findViewById(R.id.loan_ad_tv);
        pay = (TextView) findViewById(R.id.pay_for_other);
        demo4.setText(Html.fromHtml(getTitleText()));
        mEditText = (EditText) findViewById(R.id.demo_text);
        mEditText1 = (EditText) findViewById(R.id.demo_text1);
        mEditText.setTransformationMethod(new AllCapTransformationMethod());
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIntent();

            }
        });
        setText();
        buttonDemo = (Button) findViewById(R.id.demo_button);
        buttonDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("---------------------" + mEditText.getText().toString().toUpperCase());
                System.out.println("---------------------" + mEditText1.getText());
                mEditText.setText(mEditText1.getText());
            }
        });

//        changeDate();
//        setmarqueeView();
        demo5.setSelected(true);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private String getTitleText() {
        String ss = "你所搜索的艾泰为是处方药，武汉有30家店铺为你提供佩服";
        String s1 = "艾泰为";
        String s2 = "武汉";
        String s3 = "30家店铺";
        ss = ss.replace(s1, "<b>" + s1 + "</b>").replace(s2, "<b>" + s2 + "</b>").replace(s3, "<b>" + s3 + "</b>");
//        ss = ss.replace(s2, "<b>" + s2 + "</b>");
//        ss = ss.replace(s3, "<b>" + s3 + "</b>");
        return ss;

    }


    private void sendIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    private void setText() {
        SpannableStringBuilder spanBuilder = new SpannableStringBuilder(getResources().getString(R.string.vip_un_login_esc));
        spanBuilder.setSpan(new TextAppearanceSpan(null, 0, 30, null, null), 4, spanBuilder.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        demo.setText(spanBuilder);

        SpannableStringBuilder spanBuilder1 = new SpannableStringBuilder(getResources().getString(R.string.vip_not));
        spanBuilder1.setSpan(new TextAppearanceSpan(null, 0, 30, null, null), 4, spanBuilder1.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        demo1.setText(spanBuilder1);


        SpannableStringBuilder spanBuilder2 = new SpannableStringBuilder(getResources().getString(R.string.vip_is_esc));
        spanBuilder2.setSpan(new TextAppearanceSpan(null, 0, 30, null, null), 4, spanBuilder2.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        demo2.setText(spanBuilder2);

        SpannableStringBuilder spanBuilder3 = new SpannableStringBuilder(getResources().getString(R.string.vip_is_expire));
        spanBuilder3.setSpan(new TextAppearanceSpan(null, 0, 30, null, null), 4, spanBuilder3.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        demo3.setText(spanBuilder3);

    }

    private String changeDate(String time) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy.MM.dd");
        String strFormat = "";
        try {
            Date date = myFormat.parse(time);
            strFormat = myFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return strFormat.replaceAll("-", ".");
    }

    private void setmarqueeView() {
        List<String> list = new ArrayList<>();
        list.add("不知道你");
        list.add("是否");
        list.add("热门");
        list.add("热门放大放大发");
        list.add("放大放大昂达个");
        marqueeView = (MarqueeView) findViewById(R.id.demo_demo);
        marqueeView.setTextArraysAndClickListener(list, new MarqueeView.MarqueeTextViewClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
