package demo.com.xiongyantest01.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.adpater.GridAdapter;
import demo.com.xiongyantest01.adpater.ViewpageAdapter;
import demo.com.xiongyantest01.bean.GridBean;
import demo.com.xiongyantest01.decoration.SpacesItemDecoration;
import demo.com.xiongyantest01.utils.T;
import demo.com.xiongyantest01.utils.Utils;
import demo.com.xiongyantest01.widget.WrapContentHeightViewPager;

/**
 * @author by xiongyan on 2017/12/13.
 */

public class RecyclerViewGridActivity extends BaseActivity {
    private static String[] title = new String[]{"跳转动画", "风格图片", "下拉刷新", "光谷联合", "循循善诱", "大吉大利", "判定是否", "一丝不苟", "风暴之锤", "跳转动画", "风格图片", "下拉刷新", "省市区联", "循循善诱", "大吉大利", "判定是否", "一丝不苟", "风暴之锤", "跳转动画", "风格图片", "下拉刷新", "省市区联", "循循善诱", "大吉大利", "判定是否", "一丝不苟", "风暴之锤"};
    private static int[] host = new int[]{R.drawable.invoice_control_img, R.drawable.keep_list, R.drawable.invoice_control_img, R.drawable.keep_list, R.drawable.invoice_control_img, R.drawable.keep_list, R.drawable.invoice_control_img, R.drawable.keep_list, R.drawable.invoice_control_img, R.drawable.invoice_control_img, R.drawable.keep_list, R.drawable.invoice_control_img, R.drawable.keep_list, R.drawable.invoice_control_img, R.drawable.keep_list, R.drawable.invoice_control_img, R.drawable.keep_list, R.drawable.invoice_control_img, R.drawable.invoice_control_img, R.drawable.keep_list, R.drawable.invoice_control_img, R.drawable.keep_list, R.drawable.invoice_control_img, R.drawable.keep_list, R.drawable.invoice_control_img, R.drawable.keep_list, R.drawable.invoice_control_img};
    private RecyclerView mRecyclerView;
    private ArrayList<GridBean> mGridBeanList;
    private GridAdapter mGridAdapter;
    private WrapContentHeightViewPager mViewPage;

    @Override
    protected int setLayoutId() {
        return R.layout.recycler_grid_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        mViewPage = (WrapContentHeightViewPager) findViewById(R.id.view_page);
        ArrayList<View> mViewPagerList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            RecyclerView mRecyclerView = (RecyclerView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.demo_demo, mViewPage, false);
            StaggeredGridLayoutManager mManagerColor = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setNestedScrollingEnabled(false);//RecyclerView只有滑到底，NestedScrollView所影响的事件才会发生。
            mRecyclerView.setLayoutManager(mManagerColor);
            mRecyclerView.addItemDecoration(new SpacesItemDecoration(Utils.dip2px(1, RecyclerViewGridActivity.this), Utils.dip2px(1, RecyclerViewGridActivity.this), getResources().getColor(R.color.grid_line_bg)));
            initListData();
            mGridAdapter = new GridAdapter(RecyclerViewGridActivity.this, mGridBeanList);

            mRecyclerView.setAdapter(mGridAdapter);
            mGridAdapter.setOnItemClickLitener(new GridAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    T.showShort(RecyclerViewGridActivity.this, title[position]);
                }
            });

            mViewPagerList.add(mRecyclerView);
            mViewPage.setObjectForPosition(mRecyclerView, i);
        }
        mViewPage.setAdapter(new ViewpageAdapter(mViewPagerList));
    }

    private void initListData() {
        mGridBeanList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            GridBean bean = new GridBean();
            if (i != 3) {
                bean.setBgId(host[i]);
                bean.setTitle(title[i]);
                mGridBeanList.add(bean);
            }
        }
    }

    @Override
    protected void initIntent() {

    }
}
