package demo.com.xiongyantest01.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.adpater.MyTabFragmentAdapter;
import demo.com.xiongyantest01.layoutmanager.FullyLinearLayoutManager;
import demo.com.xiongyantest01.widget.WrapContentHeightViewPager;

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
    private static WrapContentHeightViewPager mTabViewPager;


    public static TabLayoutFragment newInstance(int page, List<String> data, WrapContentHeightViewPager tabViewPager) {
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
            mRecyclerView.setLayoutManager(new FullyLinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false));
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
        return view;
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
        mRecyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mRecyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                mTabViewPager.setObjectForPosition(view, mPagerPosition);
                if (mTabViewPager.getCurrentItem() == 0) {
                    mTabViewPager.resetHeight(0);
                }
                return false;
            }
        });
    }

}
