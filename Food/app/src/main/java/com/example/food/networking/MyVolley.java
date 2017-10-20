package com.example.food.networking;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.example.food.MainActivity;
import com.example.food.category.beans.Category;

import java.net.URL;
import java.util.Map;

import javax.security.auth.callback.Callback;

/**
 * Created by 王帝 on 2017/9/14.
 */

public class MyVolley {

    private static MyVolley myVolley;

    public static MyVolley newMyVolley(){
        if (myVolley == null){
            myVolley=new MyVolley();
        }
        return myVolley;
    }
    private Callback callback;
    private StringRequest stringRequest;
    private Context context;

    public void stringRequestGet(Context context, String url, Callback callback){
        RequestQueue queue= Volley.newRequestQueue(context);
        this.callback=callback;
        stringRequest=new StringRequest(Request.Method.GET, url,stringListener,errorListener);
        queue.add(stringRequest);
    }
    public void stringRequestPost(Context context, String url, final Map map,Callback callback){
        RequestQueue queue=Volley.newRequestQueue(context);
        this.callback=callback;
        stringRequest=new StringRequest(Request.Method.POST,url,stringListener,errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
    }
   private Response.Listener<String>stringListener=new Response.Listener<String>(){
        @Override
        public void onResponse(String response) {
            callback.onStringSuccess(response);
        }
    };
    private Response.ErrorListener errorListener=new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            callback.onFailure(error);
        }
    };
    public interface Callback{
        public void onStringSuccess(String response);
        public void onFailure(VolleyError error);
    }
}
