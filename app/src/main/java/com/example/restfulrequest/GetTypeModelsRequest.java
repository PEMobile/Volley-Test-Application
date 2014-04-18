package com.example.restfulrequest;

/**
 * Created by mattschoen on 4/14/14.
 */
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.content.Context;
import android.util.Log;

import com.example.listeners.TypeModelsListener;
import com.example.listeners.WebServiceErrorListener;

public class GetTypeModelsRequest extends BaseRequest {

    private static final String LOG_TAG = "GetManufacturerModelsRequestAsync";

    public GetTypeModelsRequest(Context ctx, String typeName, TypeModelsListener listener, WebServiceErrorListener errorListener) throws UnsupportedEncodingException {
        super(ctx, Method.GET, "https://api.appery.io/rest/1/db/collections/AutomobileTable?where=" + URLEncoder.encode("{\"Type\":\"" + typeName + "\"}", "UTF-8"), listener, errorListener);
    }

    public void send() {

        try {
            mRequestQueue.add(this);
        }
        catch(Exception e) {
            Log.e(LOG_TAG, "Close ticket send exception: " + e.getLocalizedMessage());
        }
    }
}

