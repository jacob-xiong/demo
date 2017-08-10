package demo.com.xiongyantest01.bean.ReturnTemple;

import java.io.Serializable;

/**
 * Created by xiongyan on 2017/8/10.
 */

public class ReturnTemple implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 是否返回成功
     */
    public boolean issuccessful;
    /**
     * 状态
     */
    public int statuscode = 0;
    public int result;
    public String description="测试";
    /**
     * 返回的數據
     */
    public Object data;
    public String getResultDescription() {
        switch (result) {
            case 1:
                return "成功";
            default:
                return "成功";
        }
    }
}
