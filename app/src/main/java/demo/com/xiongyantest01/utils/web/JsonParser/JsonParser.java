package demo.com.xiongyantest01.utils.web.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.SimpleDateFormat;

import demo.com.xiongyantest01.bean.ReturnTemple.ReturnTemple;

/**
 * Created by xiongyan on 2017/8/10.
 */

public abstract class JsonParser {
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 返回的數據
     */
    public ReturnTemple temple = new ReturnTemple();

    /**
     * 解析公共信息
     *
     * @param json
     */
    public void json2Obj(String json) {
        if (json != null && json.startsWith("{")) {
            json = json.replaceAll("NaN", "0");
            json = json.replaceAll("Infinity", "0");
            JSONTokener jsonToken = new JSONTokener(json);
            JSONObject jsonObject;
            try {
                jsonObject = (JSONObject) jsonToken.nextValue();
//                temple.issuccessful = jsonObject.optBoolean("issuccessful");
//                temple.statuscode = jsonObject.optInt("statuscode");
//                temple.description = jsonObject.optString("description") + "";
//                temple.userid = jsonObject.optString("userid");
                parseData(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            temple.description = "服务器返回错误，返回的不是Json格式的数据";
        }
    }

    /**
     * @param result 1成功/2未登錄/3token不正確/4系統繁忙
     */
    protected int getResult(int result) {
        if (1 == result) {
            return 1;
        } else if (2 == result || 3 == result) {
            return -1;
        } else {
            return 2;
        }
    }

    /**
     * 成功则进入具体解析
     *
     * @param jsonObject
     */
    public abstract void parseData(JSONObject jsonObject) throws JSONException;

}
