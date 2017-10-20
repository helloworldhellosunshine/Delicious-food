package com.example.food.category;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.food.category.beans.Category;
import com.example.food.networking.MyVolley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by 王帝 on 2017/10/2.
 */

public class CategoryDaoImpl implements CategoryDao,MyVolley.Callback {

    CategoryDaoLister listener;
    @Override
    public void getCategory(Context ctx, CategoryDaoLister listener) {
        this.listener=listener;
        String url = "http://apis.juhe.cn/cook/category?parentid=&dtype=&key=61bb33f5a0d3fc921b0f47577900f2cb";
        MyVolley myVolley=MyVolley.newMyVolley();
        myVolley.stringRequestGet(ctx,url,this);
    }

    @Override
    public void onStringSuccess(String response) {
        Gson gson=new Gson();
        Category c=gson.fromJson(response,new TypeToken<Category>(){}.getType());
        listener.onSuccess(c);
    }

    @Override
    public void onFailure(VolleyError error) {
       listener.onFail();
    }
}
