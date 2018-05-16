package demo.com.xiongyantest01.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.adpater.MyTabFragmentAdapter;

/**
 * @author by xiongyan on 2018/4/10.
 */
public class TabLayoutFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String DATA = "DATA";
    private int mPagerPosition;
    private List<String> mData;
    @BindView(R.id.tab_recycler_view)
    RecyclerView mRecyclerView;
    private MyTabFragmentAdapter mAdapter;
    View view;
    private static ViewPager mTabViewPager;
    private int mRequestPageId = 1;


    public static TabLayoutFragment newInstance(int page, List<String> data, ViewPager tabViewPager) {
        TabLayoutFragment fragment = new TabLayoutFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putSerializable(DATA, (Serializable) data);
        fragment.setArguments(args);
        mTabViewPager = tabViewPager;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.tab_fragment, null);
            ButterKnife.bind(this, view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false));
            mRecyclerView.setHasFixedSize(true);
            if (mData == null) {
                mData = new ArrayList<>();
            }
            mAdapter = new MyTabFragmentAdapter(getContext(), mData);
            mRecyclerView.addItemDecoration(new TabLayoutFragment.MyItemDecoration());
            mRecyclerView.setAdapter(mAdapter);
        }

        ViewGroup parentView = (ViewGroup) view.getParent();
        if (parentView != null) {
            parentView.removeView(view);
        }
        measureHeight();
        mRecyclerView.addOnScrollListener(new OnScrollListener() {
            private int lastViewPosistion;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE && lastViewPosistion + 1 == recyclerView.getLayoutManager().getItemCount()
                        && mRequestPageId <= 8) {
                    loadData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastViewPosistion = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }
        });
        return view;
    }

    private void loadData() {
        for (int i = 0; i < 10; i++) {
            String str = "第" + mRequestPageId + "次请求" + i + "号商品";
            mData.add(str);
        }
        mRequestPageId++;
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPagerPosition = getArguments().getInt(ARG_PAGE);
        mData = (List<String>) getArguments().getSerializable(DATA);
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
            outRect.set(0, 0, 0, 20);
        }
    }

    private void measureHeight() {
//        mRecyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                mRecyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
//                mTabViewPager.setObjectForPosition(view, mPagerPosition);
//                if (mTabViewPager.getCurrentItem() == 0) {
//                    mTabViewPager.resetHeight(0);
//                }
//                return false;
//            }
//        });
    }

}
