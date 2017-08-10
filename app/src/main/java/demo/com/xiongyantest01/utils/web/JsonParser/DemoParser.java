package demo.com.xiongyantest01.utils.web.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import demo.com.xiongyantest01.bean.OrmliteBean.User;


/**
 * Created by xiongyan on 2017/8/10.
 */

public class DemoParser extends JsonParser {
    @Override
    public void parseData(JSONObject jsonObject) throws JSONException {
//        JSONObject data = jsonObject.optJSONObject("data");
        if (jsonObject != null) {
            User user = new User();
            user.setId(Integer.parseInt(jsonObject.getString("age")));
            user.setName(jsonObject.getString("name"));
            temple.data = user;
        }
    }
}
