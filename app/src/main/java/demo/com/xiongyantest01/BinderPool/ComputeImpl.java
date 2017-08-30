package demo.com.xiongyantest01.BinderPool;

import android.os.RemoteException;


/**
 * Created by xiongyan on 2017/8/28.
 */

public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
