package demo.com.xiongyantest01.activity;

import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.adpater.MyTabActivityAdapter;
import demo.com.xiongyantest01.bean.TabActivityItemBean;
import demo.com.xiongyantest01.widget.MyRecylerView;
import demo.com.xiongyantest01.widget.MySwipeRefreshLayout;

/**
 * @author by xiongyan on 2018/4/9.
 * RecyleView多布局，ItemView含有tableLayout+Viewpager+fragment+RecyleView 其中TabLayout吸顶，Viewpager高度自适应，Fragment和Activity多种交互方式
 */
public class MyTabActivity extends BaseActivity {
    public static final int NORMAL_TYPE = 1;
    public static final int SPECIAL_TYPE = 2;
    private static final int REFRESH_COMPLETE = 0X110;

    @BindView(R.id.tab_title)
    TextView mTabTitle;
    @BindView(R.id.tab_SwipeRefreshLayout)
    MySwipeRefreshLayout mTabSwipeRefreshLayout;
    @BindView(R.id.tab_main_recyler_view)
    MyRecylerView mTabMainRecyclerView;
    ArrayList<TabActivityItemBean> mData;
    private MyTabActivityAdapter myAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    mTabSwipeRefreshLayout.setRefreshing(false);
                    break;

            }
        }

    };


    @Override
    protected int setLayoutId() {
        return R.layout.tab_activity;
    }

    @Override
    protected void loadData() {
    }

    @Override
    protected void initView() {
        initData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mTabMainRecyclerView.setLayoutManager(linearLayoutManager);
        mTabMainRecyclerView.addItemDecoration(new MyTabActivity.MyItemDecoration());
        myAdapter = new MyTabActivityAdapter(this, mData, mTabMainRecyclerView);
        mTabMainRecyclerView.setAdapter(myAdapter);
        mTabSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
            }
        });
    }

    @Override
    protected void initIntent() {

    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            TabActivityItemBean bean = new TabActivityItemBean();
            bean.setItemValue("第" + i + "条");
            bean.setItemType(NORMAL_TYPE);
            mData.add(bean);
        }

        ArrayList<String> tabList = new ArrayList<>();
        Map<Integer, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < 15; i++) {
            tabList.add("类目" + i);
            ArrayList<String> valueList = new ArrayList<>();
            for (int j = 0; j < 15; j++) {
                String str = "第" + i + "类目" + "第" + j + "列";
                valueList.add(str);
            }
            map.put(i, valueList);

        }
        TabActivityItemBean bean = new TabActivityItemBean();
        bean.setItemType(SPECIAL_TYPE);
        bean.setTabList(tabList);
        bean.setValueMap(map);
        mData.add(bean);
    }

    class MyItemDecoration extends RecyclerView.ItemDecoration {
        /**
         * @param outRect 边界
         * @param view    recyclerView ItemView
         * @param parent  recyclerView
         * @param state   recycler 内部数据管理
         */
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0, 0, 0, 25);
        }
    }
}
