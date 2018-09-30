package demo.com.xiongyantest01.activity;

import android.support.design.widget.AppBarLayout;
import android.view.View;

import butterknife.BindView;
import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.widget.AppBarStateChangeListener;
import demo.com.xiongyantest01.widget.MyToolBar;

/**
 * @author by xiongyan on 2018/9/30.
 */
public class AppBarLayoutActivity extends BaseActivity {
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.set_together_title_view)
    MyToolBar myToolBar;
    @BindView(R.id.set_together_shop_name_title_root_view)
    View mTitleRootView;
    @BindView(R.id.set_together_shop_name_title_view)
    View mTitleView;


    @Override
    protected int setLayoutId() {
        return R.layout.set_together_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {

                if (state == State.EXPANDED) {
                    /**
                     * 展开状态
                     */
                    myToolBar.setBackgroundResource(R.color.transparent);
                    mTitleRootView.setBackgroundResource(R.color.transparent);
                    mTitleView.setBackgroundResource(R.drawable.set_together_shop_name_title_bg);

                } else if (state == State.COLLAPSED) {
                    /**
                     *  折叠状态
                     */
                    myToolBar.setBackgroundResource(R.color.title_bg);
                    mTitleRootView.setBackgroundResource(R.color.white);
                    mTitleView.setBackgroundResource(R.color.transparent);
                } else {
                    /**
                     * 中间状态
                     */
                    myToolBar.setBackgroundResource(R.color.transparent);
                    mTitleRootView.setBackgroundResource(R.color.transparent);
                    mTitleView.setBackgroundResource(R.drawable.set_together_shop_name_title_bg);
                }
            }
        });
    }

    @Override
    protected void initIntent() {

    }
}
