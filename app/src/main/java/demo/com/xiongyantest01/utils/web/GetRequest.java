package demo.com.xiongyantest01.utils.web;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.android.volley.RequestQueue;
import demo.com.xiongyantest01.utils.web.JsonParser.JsonParser;

import demo.com.xiongyantest01.bean.ReturnTemple.ReturnTemple;
import demo.com.xiongyantest01.utils.web.interfacepackage.IReq;
import demo.com.xiongyantest01.utils.web.interfacepackage.IRequest;
import demo.com.xiongyantest01.utils.web.interfacepackage.OnRespListener;
import demo.com.xiongyantest01.utils.web.interfacepackage.WebBeans;
import demo.com.xiongyantest01.utils.web.interfacepackage.WebMaps;
import demo.com.xiongyantest01.utils.Utils;

/**
 * Created by xiongyan on 2017/8/10.
 */

public class GetRequest implements IRequest{

    public static final String COMMON_ERROR_MSG = "连接超时，请稍后重试...";
    public static final int COMMON_ERROR_CODE = 2;

    private RequestQueue queue;

    public GetRequest(RequestQueue queue){
        this.queue = queue;
    }

    @Override
    public IReq getRequest(WebMaps params, final JsonParser jsonParser, final Handler handler, final int msgId, String methodName) {
        Utils.setSign(params);
        String url = Utils.getUrl();
        if (!TextUtils.isEmpty(methodName)) {
            url = url + "/demo/" + methodName;
        }

        CustomRequest.CustomListener customListener = new CustomRequest.CustomListener() {
            @Override
            public void onResponse(String response) {
                if(response == null || response.equals("")){
                    Object result;
                    if (jsonParser != null) {
                        ReturnTemple temple = new ReturnTemple();
                        temple.issuccessful = false;
                        temple.description = COMMON_ERROR_MSG;
                        temple.result = COMMON_ERROR_CODE;
                        result = temple;
                    } else {
                        result = "请求失败，请稍后重试";
                    }
                    if (handler != null) {
                        Message message = handler.obtainMessage();
                        message.what = msgId;
                        message.obj = result;
                        handler.sendMessage(message);
                    }
                }else{
                    /**
                     * 有些请求默认是没有parser传过来的，出参只求String，譬如联合登录等
                     * 所以加了一个else if
                     */
                    Object result;


                    if (jsonParser != null) {
                        jsonParser.json2Obj(response);
                        if (TextUtils.isEmpty(jsonParser.temple.description)) {
                            jsonParser.temple.description = jsonParser.temple.getResultDescription();
                        }

                        result = jsonParser.temple;
                    } else if (response != null) {
                        result = response;
                    } else {
                        ReturnTemple temple = new ReturnTemple();
                        temple.issuccessful = false;
                        temple.description = "";
                        temple.result = -100;
                        result = temple;
                    }
                    if (handler != null) {
                        Message message = handler.obtainMessage();
                        message.what = msgId;
                        message.obj = result;
                        handler.sendMessage(message);
                    }
                }
            }
        };

        CustomRequest request = new CustomRequestMap(url, params.getMap(), customListener);
        Req req = new Req();
        req.bindCustomRequest(request);
        queue.add(request);
        return req;
    }

    @Override
    public IReq getRequest(WebMaps params, final JsonParser jsonParser,String methodName, final OnRespListener listener) {
        Utils.setSign(params);
        String url = Utils.getUrl();
        if (!TextUtils.isEmpty(methodName)) {
            url = url + "?method=" + methodName;
        }

        CustomRequest.CustomListener customListener = new CustomRequest.CustomListener() {
            @Override
            public void onResponse(String response) {
                if(response == null || response.equals("")){//请求失败
                    ReturnTemple temple = new ReturnTemple();
                    temple.issuccessful = false;
                    if (jsonParser != null) {
                        temple.description = COMMON_ERROR_MSG;
                        temple.result = COMMON_ERROR_CODE;
                    } else {
                        temple.data = "请求失败，请稍后重试";
                    }

                    listener.onResp(temple);
                }else{
                    /**
                     * 有些请求默认是没有parser传过来的，出参只求String，譬如联合登录等
                     * 所以加了一个else if
                     */


                    if (jsonParser != null) {
                        jsonParser.json2Obj(response);
                        if (TextUtils.isEmpty(jsonParser.temple.description)) {
                            jsonParser.temple.description = jsonParser.temple.getResultDescription();
                        }

                    } else if (response != null) {
                        jsonParser.temple.data = response;
                    } else {
                        ReturnTemple temple = new ReturnTemple();
                        temple.issuccessful = false;
                        temple.description = "";
                        temple.result = -100;
                    }

                    listener.onResp(jsonParser.temple);
                }
            }
        };

        CustomRequest request = new CustomRequestMap(url, params.getMap(), customListener);
        Req req = new Req();
        req.bindCustomRequest(request);
        queue.add(request);
        return req;
    }

    @Override
    public IReq getRequest(WebBeans param, JsonParser jsonParser, String methodName, OnRespListener listener) {
        return null;
    }

    @Override
    public IReq getRequest(WebBeans jBean, JsonParser jsonParser, Handler handler, int msgId, String methodName) {
        return null;
    }
}
