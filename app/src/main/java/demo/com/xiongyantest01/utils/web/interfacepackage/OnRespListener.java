package demo.com.xiongyantest01.utils.web.interfacepackage;

import demo.com.xiongyantest01.bean.ReturnTemple.ReturnTemple;

/**
 * Created by xiongyan on 2017/8/10.
 */

public interface OnRespListener {
    /**
     * 服务器响应回调
     *
     * @param temple : 服务器返回数据被封装。
     */
    public void onResp(ReturnTemple temple);
}
