package demo.com.xiongyantest01.widget;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;



import java.util.List;

import demo.com.xiongyantest01.R;


/**
 * @author by xiongyan on 2018/2/8.
 */

public class MarqueeView extends LinearLayout  {
    private Context mContext;
    private ViewFlipper viewFlipper;
    private View marqueeTextView;
    private List<String> textArrays;
    private MarqueeTextViewClickListener marqueeTextViewClickListener;

    public MarqueeView(Context context) {
        super(context);
        mContext = context;
        initBasicView();
    }


    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initBasicView();
    }

    public void setTextArraysAndClickListener(List<String> textArrays, MarqueeTextViewClickListener marqueeTextViewClickListener) {//1.设置数据源；2.设置监听回调（将textView点击事件传递到目标界面进行操作）
        this.textArrays = textArrays;
        this.marqueeTextViewClickListener = marqueeTextViewClickListener;
        initMarqueeTextView(textArrays, marqueeTextViewClickListener);
    }

    public void initBasicView() {//加载布局，初始化ViewFlipper组件及效果
        marqueeTextView = LayoutInflater.from(mContext).inflate(R.layout.marquee_textview_layout, null);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(marqueeTextView, layoutParams);
        viewFlipper = (ViewFlipper) marqueeTextView.findViewById(R.id.viewFlipper);
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_top_in));//设置上下的动画效果（自定义动画，所以改左右也很简单）
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_bottom_out));
        viewFlipper.startFlipping();
    }

    public void initMarqueeTextView(List<String> textArrays, MarqueeTextViewClickListener marqueeTextViewClickListener) {
        if (textArrays.size() == 0) {
            return;
        }

        int i = 0;
        viewFlipper.removeAllViews();
        while (i < textArrays.size()) {
            TextView textView = new TextView(mContext);
            textView.setText(textArrays.get(i));
            textView.setOnClickListener(marqueeTextViewClickListener);
            LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            viewFlipper.addView(textView, lp);
            i++;
        }
    }

    public void releaseResources() {
        if (marqueeTextView != null) {
            if (viewFlipper != null) {
                viewFlipper.stopFlipping();
                viewFlipper.removeAllViews();
                viewFlipper = null;
            }
            marqueeTextView = null;
        }
    }

    public interface MarqueeTextViewClickListener extends View.OnClickListener {
        @Override
        void onClick(View view);
    }
}
