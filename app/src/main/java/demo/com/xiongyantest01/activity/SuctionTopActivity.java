package demo.com.xiongyantest01.activity;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.widget.SuctionTopParentChlidLayout;
import demo.com.xiongyantest01.widget.SuctionTopParentLayout;

import static demo.com.xiongyantest01.utils.DimensionConvert.dip2px;

/**
 * @author by xiongyan on 2018/5/7.
 * 吸顶Activity
 */
public class SuctionTopActivity extends BaseActivity {
    @BindView(R.id.biaoti)
    TextView biaoti;
    @BindView(R.id.fenleibiaoti)
    TextView fenleibiaoti;
    @BindView(R.id.child)
    SuctionTopParentChlidLayout chlidLayout;
    @BindView(R.id.parent)
    SuctionTopParentLayout parentLayout;
    @BindView(R.id.bg_view)
    View bgView;
    @BindView(R.id.dibuwenmiao)
    TextView dibuwenmiao;

    @Override
    protected int setLayoutId() {

        return R.layout.suction_top_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        setFullActivity();
        parentLayout.setNsc(chlidLayout);
        fenleibiaoti.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                fenleibiaoti.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                parentLayout.setImgHeight(bgView.getMeasuredHeight() - biaoti.getMeasuredHeight() + dibuwenmiao.getMeasuredHeight()-getStatusBarHeight());
            }
        });
    }

    @Override
    protected void initIntent() {

    }

    private void setFullActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ((LinearLayout) findViewById(R.id.bg_view)).setPadding(0, getStatusBarHeight() + dip2px(context, 44), 0, 0);
            setMargins(findViewById(R.id.biaoti), 0, getStatusBarHeight(), 0, 0);
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
