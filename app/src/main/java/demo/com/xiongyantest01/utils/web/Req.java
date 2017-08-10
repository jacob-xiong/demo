package demo.com.xiongyantest01.utils.web;

import demo.com.xiongyantest01.utils.web.interfacepackage.IReq;

/**
 * Created by xiongyan on 2017/8/10.
 */

public class Req implements IReq {

    private CustomRequest req;
    protected void bindCustomRequest(CustomRequest req) {
        this.req = req;
    }
    public void cancel() {
        req.cancel();
    }
}
