package demo.com.xiongyantest01.activity;

import demo.com.xiongyantest01.R;

/**
 * Created by xiongyan on 2017/8/2.
 */

public class BaseTestActivity extends BaseActivity {
    @Override
    protected int setLayoutId() {
        return R.layout.base_test_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected boolean isShowActionBarTitle() {
        return true;
    }

    @Override
    protected int getActionTitle() {
        return R.string.app_name;
    }

    @Override
    protected int getRightBtnRes() {
        return R.drawable.base_action_back;
    }
}
