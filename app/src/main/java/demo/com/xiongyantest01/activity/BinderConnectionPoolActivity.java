package demo.com.xiongyantest01.activity;

import android.os.IBinder;
import android.os.RemoteException;

import demo.com.xiongyantest01.BinderPool.BinderPool;
import demo.com.xiongyantest01.BinderPool.ComputeImpl;
import demo.com.xiongyantest01.BinderPool.ICompute;
import demo.com.xiongyantest01.BinderPool.ISecurityCenter;
import demo.com.xiongyantest01.BinderPool.SecurityCenterImpl;
import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.utils.L;

/**
 * Created by xiongyan on 2017/8/28.
 * binder连接池
 */

public class BinderConnectionPoolActivity extends BaseActivity {
    ISecurityCenter securityCenter;
    ICompute iCompute;
    @Override
    protected int setLayoutId() {
        return R.layout.binder_connection_pool_activity;
    }

    @Override
    protected void loadData() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                doWork();
            }
        }).start();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initIntent() {

    }

    private void doWork() {
        BinderPool binderPool = BinderPool.getInsance(BinderConnectionPoolActivity.this);
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        securityCenter = SecurityCenterImpl.asInterface(securityBinder);
        String str = "你好啊";
        try {
            String pas = securityCenter.decrypt(str);
//            L.s("加密", pas);
//            L.s("解密", securityCenter.encrypt(pas));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        iCompute = ComputeImpl.asInterface(computeBinder);
//        try {
//            L.s("ICompute", "6+7=" + iCompute.add(6, 7));
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }

    }
}
