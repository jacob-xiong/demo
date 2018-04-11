package demo.com.xiongyantest01.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.bean.ReturnTemple.ReturnTemple;
import demo.com.xiongyantest01.utils.T;
import demo.com.xiongyantest01.utils.web.Web;

/**
 * Created by xiongyan on 2017/8/1.
 * 基类Activity
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener, Handler.Callback {
    protected LayoutInflater mInflater;
    protected Context context;
    private View mActionView;
    private ImageView mActionBarBack;
    private TextView mActionBarTitle;
    private ImageView mActionBarRightImg;
    private TextView mActionBarRightTv;
    public Handler handler = new Handler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        mInflater = LayoutInflater.from(this);
        context = getApplicationContext();
        Web.init(context);
        setContentView(R.layout.base_activity);
        initIntent();
        initLayout();
        ButterKnife.bind(this);
        initView();
        initActionBarTitle();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected abstract int setLayoutId();

    protected abstract void loadData();

    protected abstract void initView();

    protected abstract void initIntent();

    private void initActionBarTitle() {
        if (isShowActionBarTitle()) {
            initTitleView();
            mActionView.setVisibility(View.VISIBLE);
            mActionBarTitle.setText(getActionTitle());
            if (getRightBtnRes() != -1) {
                String sourceType = this.getResources().getResourceTypeName(getRightBtnRes());
                if (sourceType.contains("drawable")) {
                    mActionBarRightImg.setVisibility(View.VISIBLE);
                    mActionBarRightImg.setImageResource(getRightBtnRes());
                } else if (sourceType.contains("string")) {
                    mActionBarRightTv.setVisibility(View.VISIBLE);
                    mActionBarRightTv.setText(getRightBtnRes());
                }
            }
            mActionBarRightImg.setOnClickListener(this);
            mActionBarBack.setOnClickListener(this);
        }
    }


    protected boolean isShowActionBarTitle() {
        return false;
    }


    protected int getActionTitle() {
        return -1;
    }

    private void initTitleView() {
        mActionView = findViewById(R.id.action_bar_view);
        mActionBarBack = (ImageView) findViewById(R.id.action_back);
        mActionBarTitle = (TextView) findViewById(R.id.action_title);
        mActionBarRightImg = (ImageView) findViewById(R.id.action_right_img);
        mActionBarRightTv = (TextView) findViewById(R.id.action_right_tv);
    }

    protected int getRightBtnRes() {
        return -1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_back:
                if (isShowSaveTipWhenExit()) {
                    T.showShort(this, "测试");
                } else {
                    this.finish();
                }
                break;
            case R.id.action_right_tv:
                break;
            case R.id.action_right_img:
                break;
            default:
                break;
        }
    }

    protected boolean isShowSaveTipWhenExit() {
        return false;
    }

    private void initLayout() {
        ViewStub viewStub = (ViewStub) this.findViewById(R.id.content_view);
        viewStub.setLayoutResource(setLayoutId());
        viewStub.inflate();
    }

    @Override
    public final boolean handleMessage(Message msg) {
        boolean result = true;
        if (msg != null && msg.obj instanceof ReturnTemple) {
            ReturnTemple temple = (ReturnTemple) msg.obj;
            if (temple.result == -10) {

            } else if (temple.result == -100) {

            }
            result = true;
        }
        dealWithMessage(msg);

        return result;
    }

    protected void dealWithMessage(Message msg) {
    }
}
