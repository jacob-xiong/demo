package demo.com.xiongyantest01.custom.graph;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import demo.com.xiongyantest01.R;

/**
 * Created by xiongyan on 2017/9/14.
 */

public abstract class BaseGraphView extends View {
    private String mTitleX;
    private String mTitleY;
    private int mShareX;
    private int mShareY;
    private int mColorX;
    private int mColorY;
    private Context mContext;
    private Paint mPaint;
    private Rect mRect;
    private int mWidth;
    private int mHeight;
    private int mPaddingStart;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingBottom;
    private int mPaddingTop;

    public BaseGraphView(Context context) {
        this(context, null);
    }

    public BaseGraphView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseGraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseGraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        getAttrs(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        drawXLine(canvas, mPaint);
        drawYLine(canvas, mPaint);
        deawXArrow(canvas, mPaint);
    }

    private void drawYLine(Canvas canvas, Paint mPaint) {
        mPaint.setColor(mColorX);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setTextSize(18);
        canvas.drawLine(getPaddingLeft(), getHeight() - 100, getPaddingLeft(), getMeasuredHeight() - getHeight()+100, mPaint);
        canvas.drawText(mTitleY,getPaddingLeft(), getMeasuredHeight() - getHeight()+50, mPaint);
    }

    private void deawXArrow(Canvas canvas, Paint mPaint) {
        mPaint.setColor(mColorX);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    private void drawXLine(Canvas canvas, Paint mPaint) {
        mPaint.setColor(mColorX);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setTextSize(18);
        canvas.drawLine(getPaddingLeft(), getHeight() - 100, getWidth() - 100, getHeight() - 100, mPaint);
        canvas.drawText(mTitleX, getWidth() - 50, getHeight() - 50, mPaint);

    }


    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.graph_view);
        mTitleX = ta.getString(R.styleable.graph_view_titleX);
        mColorX = ta.getColor(R.styleable.graph_view_colorX, Color.BLACK);
        mTitleY = ta.getString(R.styleable.graph_view_titleY);
        mColorY = ta.getColor(R.styleable.graph_view_colorY, Color.BLACK);
        ta.recycle();
    }
}
