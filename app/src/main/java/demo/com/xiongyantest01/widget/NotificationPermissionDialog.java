package demo.com.xiongyantest01.widget;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.utils.PermissionUtils;

/**
 * @author by xiongyan on 2018/10/19.
 */
public class NotificationPermissionDialog extends DialogFragment {
    @BindView(R.id.open_setting)
    TextView open;
    private Context mContext;

    public void setContext(Context context) {
        mContext = context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = inflater.inflate(R.layout.notification_permission_dialog, null);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.GoToSetting(mContext);
                dismiss();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            //设置布局宽高
//            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.8), (int) (dm.heightPixels * 0.4));

        }
    }
}
