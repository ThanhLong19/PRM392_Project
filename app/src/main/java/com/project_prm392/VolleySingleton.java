package com.project_prm392;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static Context mContext;
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;

    public static VolleySingleton getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    private VolleySingleton(Context context) {
        mContext = context;
        mRequestQueue = getmRequestQueue();
    }

    private RequestQueue getmRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(JsonObjectRequest request) { mRequestQueue.add(request); }

    public <T> void addToRequestQueue(JsonArrayRequest request) { mRequestQueue.add(request); }
}
