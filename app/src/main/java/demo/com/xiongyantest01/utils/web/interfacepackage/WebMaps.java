package demo.com.xiongyantest01.utils.web.interfacepackage;

import java.util.Map;

/**
 * Created by xiongyan on 2017/8/10.
 */

public abstract class WebMaps {
    public abstract void put(String key, String value);

    public abstract String getValue(String key);

    public abstract Map<String, String> getMap();
}
