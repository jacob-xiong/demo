package demo.com.xiongyantest01.utils.web.interfacepackage;

import android.os.Handler;

import demo.com.xiongyantest01.utils.web.JsonParser.JsonParser;

/**
 * Created by xiongyan on 2017/8/10.
 */

public interface IRequest {
    /**
     * 以Java Bean的形式提交请求
     *
     * @param jBean      : 请求参数
     * @param jsonParser : json解析对象
     * @param handler    : handler
     * @param msgId      : 消息id
     * @param methodName : 请求服务器执行的方法名
     * @return
     */
    public IReq getRequest(WebBeans jBean, final JsonParser jsonParser, final Handler handler, final int msgId, final String methodName);

    /**
     * 以Map键值对的形式提交请求
     *
     * @param param      : 请求参数
     * @param jsonParser : json解析对象
     * @param handler    : handler
     * @param msgId      : 消息id
     * @param methodName : 请求服务器执行的方法名
     * @return
     */
    public IReq getRequest(WebMaps param, final JsonParser jsonParser, final Handler handler, final int msgId, final String methodName);

    /**
     * 以Map键值对的形式提交请求
     *
     * @param param:请求参数
     * @param jsonParser:服务器响应json解析对象
     * @param methodName:服务器接口方法名
     * @param listener:服务器响应监听器
     * @return
     */
    public IReq getRequest(WebMaps param, JsonParser jsonParser, String methodName, OnRespListener listener);

    /**
     * 以Java Bean的形式提交请求
     *
     * @param param               :请求参数
     * @param jsonParser:json解析对象
     * @param methodName:服务器接口方法名
     * @param listener:服务器响应监听器
     * @return
     */
    public IReq getRequest(WebBeans param, JsonParser jsonParser, String methodName, OnRespListener listener);
}
