package demo.com.xiongyantest01.utils.web.interfacepackage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiongyan on 2017/8/10.
 */

public class WebMap extends WebMaps{
    private Map<String, String> map = new HashMap<String, String>();

    public WebMap() {

    }

    public void put(String key, String value) {
        if(key == null || value == null)
            return;
        map.put(key, value);

    }

    public String getValue(String key){

        if(map.get(key) == null){
            return "";
        }

        return map.get(key);
    }

    public Map<String, String> getMap() {
        return map;
    }

}
