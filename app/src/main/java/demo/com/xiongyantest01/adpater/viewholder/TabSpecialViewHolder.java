package demo.com.xiongyantest01.adpater.viewholder;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.activity.MyTabActivity;
import demo.com.xiongyantest01.bean.TabActivityItemBean;
import demo.com.xiongyantest01.widget.WrapContentHeightViewPager;

/**
 * @author by xiongyan on 2018/4/9.
 */
public class TabSpecialViewHolder extends TabFloorBaseViewHolder {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mTabViewPager;
    private TabLayoutAdapter mAdapter;
    private Context mContext;

    public TabSpecialViewHolder(Context context, final ViewGroup parrent, final View itemView) {
        super(context, itemView);
        this.mContext = context;
        ButterKnife.bind(this, itemView);
//        final ViewTreeObserver viewTreeObserver = parrent.getViewTreeObserver();
//        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                if (viewTreeObserver.isAlive()) {
//                    viewTreeObserver.removeOnPreDrawListener(this);
//                }
////                mTabViewPager.setMaxHeight((int) (parrent.getHeight() - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, itemView.getContext().getResources().getDisplayMetrics()) - 20));
//                ViewGroup.LayoutParams layoutParams=mTabViewPager.getLayoutParams();
//                layoutParams.height=parrent.getHeight() - mTabLayout.getHeight() - 20;
//                mTabViewPager.setLayoutParams(layoutParams);
//                return false;
//            }
//
//        });

    }

    @Override
    public void createView(int width) {

    }

    @Override
    public void bind(TabActivityItemBean data) {
        mAdapter = new TabLayoutAdapter(((MyTabActivity) mContext).getSupportFragmentManager(), data.getTabList(), data.getValueMap(), mTabViewPager);
        mTabViewPager.setAdapter(mAdapter);
        mTabViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mTabViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                mTabViewPager.resetHeight(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
