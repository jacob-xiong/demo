package demo.com.xiongyantest01.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.adpater.RecycleEditAdapter;
import demo.com.xiongyantest01.utils.T;

/**
 * @author by xiongyan on 2018/10/31.
 */
public class RecycleViewAndEditActivity extends BaseActivity implements RecycleEditAdapter.RecycleEditListener, View.OnLayoutChangeListener {
    @BindView(R.id.recycler_and_edit)
    RecyclerView mRecyclerView;
    RecycleEditAdapter adapter;
    private List<String> list;
    @BindView(R.id.root_layout)
    View activityRootView;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;


    @Override
    protected int setLayoutId() {
        return R.layout.recycle_view_and_edit_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String str = String.valueOf(i);
            list.add(str);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleEditAdapter(this, list, this);
        mRecyclerView.setAdapter(adapter);
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
        activityRootView.addOnLayoutChangeListener(this);
    }

    @Override
    protected void initIntent() {

    }


    @Override
    public void clickAdd(int position) {

    }

    @Override
    public void clickReduce(int position) {

    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            T.showShort(this, "监听到软键盘弹起...");
        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
            T.showShort(this, "监听到软件盘关闭...");
//            activityRootView.findFocus().clearFocus();
        }

    }
}
