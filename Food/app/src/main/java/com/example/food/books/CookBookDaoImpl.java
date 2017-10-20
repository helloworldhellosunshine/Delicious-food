package com.example.food.books;

import android.content.Context;
import android.util.Log;


import com.android.volley.VolleyError;

import com.example.food.cookbook.beans.CookBook;
import com.example.food.cookbook.beans.TagCategoryList;
import com.example.food.networking.MyVolley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by 王帝 on 2017/9/16.
 */

public class CookBookDaoImpl implements CookBookDao,MyVolley.Callback {

    CookBookDaoListener listener;

    @Override
    public void getCookBookListById(Context context, String id, CookBookDaoListener listener) {
        this.listener=listener;
        String url="http://apis.juhe.cn/cook/index?dtype=&pn=&rn=&format=&key=61bb33f5a0d3fc921b0f47577900f2cb&cid="+id;
        //String url="http://apis.juhe.cn/cook/queryid?id=100&dtype=&key=61bb33f5a0d3fc921b0f47577900f2cb";
        MyVolley myVolley=MyVolley.newMyVolley();
        myVolley.stringRequestGet(context,url,this);
    }

    @Override
    public void getCookBookListByKey(Context context, String key, CookBookDaoListener listener) {
        this.listener=listener;
        String url="http://apis.juhe.cn/cook/query.php?dtype=&pn=&rn=&albums=&=&key=61bb33f5a0d3fc921b0f47577900f2cb&menu="+ key;
        MyVolley myVolley=MyVolley.newMyVolley();
        myVolley.stringRequestGet(context,url,this);

    }

    @Override
    public void onStringSuccess(String response) {
        Gson gson=new Gson();
        try{
            JSONObject jsonObject=new JSONObject(response);
            JSONObject jsonObject1=jsonObject.getJSONObject("result");

            TagCategoryList tc1=gson.fromJson(jsonObject1.toString(),
                    new TypeToken<TagCategoryList>(){}.getType());
            List<CookBook> list=tc1.getData();
            listener.onSuccess(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(VolleyError error) {
        listener.onFail();
    }
}
