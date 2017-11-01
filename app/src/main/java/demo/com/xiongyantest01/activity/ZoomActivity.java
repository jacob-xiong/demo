package demo.com.xiongyantest01.activity;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import demo.com.xiongyantest01.R;

/**
 * Created by xiongyan on 2017/11/1.
 * 图片缩放
 *
 * @author xiongyan
 */

public class ZoomActivity extends BaseActivity {
    private ImageView mYuanShi;
    private ImageView mZoom;

    @Override
    protected int setLayoutId() {
        return R.layout.zoom_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        mYuanShi = (ImageView) findViewById(R.id.yuan_shi);
        mZoom = (ImageView) findViewById(R.id.zoom);
        mYuanShi.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.demo));
        mZoom.setImageBitmap(decodeSampledBitmapFromResource(getResources(),R.drawable.demo,100,100));
    }

    @Override
    protected void initIntent() {

    }

    /**
     *   四个步奏 ：
     *   1、将inJustDecodeBounds设置为true预加载图片得到图片的原始宽高
        2、结合设定的像素和原始宽高计算出采样率inSampleSize
        3、将inJustDecodeBounds设为false 用计算出的采样率加载图片
        4、inSampleSize 为2的指数
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calcuateInSampleSize(options, reqHeight, reqWidth);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);

    }

    private int calcuateInSampleSize(BitmapFactory.Options options, int reqHeight, int reqWidth) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;

    }


}
