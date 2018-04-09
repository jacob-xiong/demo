package demo.com.xiongyantest01.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.widget.PageIndicatorView;
import demo.com.xiongyantest01.widget.PageRecyclerView;

/**
 * @author by xiongyan on 2018/2/11.
 */

public class ViewPageActivity extends BaseActivity {
    private PageRecyclerView mPageRecyclerView;
    private PageIndicatorView mPageIndicatorView;
    private List<String> dataList = null;
    private PageRecyclerView.PageAdapter myAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.view_page_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        initData();
        mPageRecyclerView = (PageRecyclerView) findViewById(R.id.cusom_swipe_view);
        // 设置指示器
        mPageRecyclerView.setIndicator((PageIndicatorView) findViewById(R.id.indicator));
        // 设置行数和列数
        mPageRecyclerView.setPageSize(2, 3);
        // 设置数据
        mPageRecyclerView.setAdapter(myAdapter = mPageRecyclerView.new PageAdapter(dataList, new PageRecyclerView.CallBack() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(ViewPageActivity.this).inflate(R.layout.view_page_item, parent, false);
                return new MyHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((MyHolder) holder).tv.setText(dataList.get(position));
            }

            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(ViewPageActivity.this, "点击："
                        + dataList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(ViewPageActivity.this, "删除："
                        + dataList.get(position), Toast.LENGTH_SHORT).show();
                myAdapter.remove(position);
            }
        }));
    }

    @Override
    protected void initIntent() {

    }

    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add(String.valueOf(i));
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView tv = null;

        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
