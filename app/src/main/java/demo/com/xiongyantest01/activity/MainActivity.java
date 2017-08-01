package demo.com.xiongyantest01.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.adpater.MyAdapter;
import demo.com.xiongyantest01.bean.MainBean;
import demo.com.xiongyantest01.utils.Utils;

/**
 *
 */

public class MainActivity extends Activity {
    private RecyclerView mRecyClerView;
    private MyAdapter myAdapter;
    private ArrayList<MainBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void sendIntent(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    private void initView() {
        list = Utils.getMainData();
        mRecyClerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyClerView.setLayoutManager(linearLayoutManager);
        mRecyClerView.addItemDecoration(new MyItemDecoration());
        myAdapter = new MyAdapter(this, list);
        myAdapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                sendIntent(list.get(position).getItemClass());
            }
        });
        mRecyClerView.setAdapter(myAdapter);
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
