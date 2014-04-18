package com.example.restfulrequest;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.volley.AuthFailureError;
import android.volley.RequestQueue;
import android.volley.Response.ErrorListener;
import android.volley.Response.Listener;
import android.volley.toolbox.StringRequest;
import android.volley.toolbox.Volley;

/**
 * Created by mattschoen on 4/14/14.
 */
public abstract class BaseRequest extends StringRequest {

    private String mDbId="5328bb1ae4b0e193a7674412";
    private static final String LOG_TAG="BaseAuthRequest";
    protected static RequestQueue mRequestQueue=null;

    public BaseRequest(Context ctx, int method, String url, Listener<String> listener, ErrorListener errorListener) {
        super(method, url, listener, errorListener);

        if(mRequestQueue == null)
            mRequestQueue=Volley.newRequestQueue(ctx);
    }

    public abstract void send();

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("X-Appery-Database-Id", mDbId);
        return headers;
    }
}

