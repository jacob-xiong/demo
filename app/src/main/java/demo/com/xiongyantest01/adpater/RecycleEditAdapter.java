package demo.com.xiongyantest01.adpater;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.com.xiongyantest01.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * @author by xiongyan on 2018/10/31.
 */
public class RecycleEditAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> mDataList;
    private RecycleEditListener mListener;
    private LayoutInflater mInflater;
    private int index;


    public RecycleEditAdapter(Context context, List<String> list, RecycleEditListener listener) {
        mContext = context;
        mDataList = list;
        mListener = listener;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_and_edit_item, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        final int dex = position;
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.reduceTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.clickReduce(dex);
                }
            }
        });
        itemViewHolder.addTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.clickAdd(dex);
                }
            }
        });
        initEditText(itemViewHolder, dex, mDataList.get(dex));
    }

    private void initEditText(final ItemViewHolder itemViewHolder, final int position, String s) {

        if (itemViewHolder.itemEdit.getTag() instanceof TextWatcher) {
            itemViewHolder.itemEdit.removeTextChangedListener((TextWatcher) itemViewHolder.itemEdit.getTag());
        }

        // 第2步：移除TextWatcher之后，设置EditText的Text。
        itemViewHolder.itemEdit.setText(s);
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable.toString())) {
                    mDataList.set(position, "");
                } else {
                    mDataList.set(position, editable.toString());
                }
            }
        };
        itemViewHolder.itemEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    itemViewHolder.itemEdit.clearFocus();
                    if (mContext instanceof Activity) {
                        // 先隐藏键盘
                        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    return true;
                }
                return false;
            }
        });

        itemViewHolder.itemEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    itemViewHolder.itemEdit.clearFocus();
                    if (mContext instanceof Activity) {
                        // 先隐藏键盘
                        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    return true;
                }
                return false;
            }
        });
        itemViewHolder.itemEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    index = position;
                }
            }
        });

        itemViewHolder.itemEdit.clearFocus();
        itemViewHolder.itemEdit.addTextChangedListener(watcher);
        itemViewHolder.itemEdit.setTag(watcher);
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.edit_item_reduce)
        TextView reduceTv;
        @BindView(R.id.edit_item_edit)
        EditText itemEdit;
        @BindView(R.id.edit_item_add)
        TextView addTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface RecycleEditListener {
        void clickAdd(int position);

        void clickReduce(int position);
    }

}
