package demo.com.xiongyantest01.utils.web;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

/**
 * Created by xiongyan on 2017/8/10.
 */

public class CustomRequestMap extends CustomRequest{

    private Map<String, String> con;

    public CustomRequestMap(String url, Map<String, String> con, final CustomListener listener) {
        super(url, listener, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponse(null);
            }
        });
        this.con = con;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return con;
    }

}
