package demo.com.xiongyantest01.utils.web;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by xiongyan on 2017/8/10.
 */

public class CustomRequest extends Request<String> {

    public static final int SET_CONNECTION_TIMEOUT = 20 * 1000;

    /**
     * Charset for request.
     */
    public static final String PROTOCOL_CHARSET = "utf-8";

    /**
     * Callback interface for delivering parsed responses.
     */
    public interface CustomListener {
        /**
         * Called when a response is received.
         *
         * @param response: 返回的数据(json)
         */
        public void onResponse(String response);
    }

    private CustomListener listener;

    public CustomRequest(String url, final CustomListener listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(SET_CONNECTION_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.listener = listener;
    }

    @Override
    protected void deliverResponse(String response) {
        if (listener != null) {
            listener.onResponse(response);
        }
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String jsonString = "";
        try {
            jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            VolleyLog.wtf("Unsupported Encoding of %s using %s", jsonString, PROTOCOL_CHARSET);
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }
}