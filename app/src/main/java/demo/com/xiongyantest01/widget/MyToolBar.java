package demo.com.xiongyantest01.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import demo.com.xiongyantest01.R;

/**
 * @author by xiongyan on 2018/9/30.
 */
public class MyToolBar extends Toolbar {
    private ImageView mLeftImageView;
    private TextView mCenterTextView;
    private ImageView mRightImageView;
    private TextView mRightTextView;
    private View mRootView;

    public MyToolBar(Context context) {
        super(context);
        initView();
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mRootView = LayoutInflater.from(getContext()).inflate(R.layout.my_tool_bar, null);
        mLeftImageView = (ImageView) mRootView.findViewById(R.id.set_together_back_bg);
        mCenterTextView = (TextView) mRootView.findViewById(R.id.set_together_title_tv);
        mRightImageView = (ImageView) mRootView.findViewById(R.id.set_together_title_car);
        mRightTextView = (TextView) mRootView.findViewById(R.id.set_together_title_product_number);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER_HORIZONTAL);
        addView(mRootView, lp);
    }

}
