package com.example.food;

import android.graphics.Color;
import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.VolleyError;

import com.example.food.category.beans.Category;
import com.example.food.category.beans.Category1;
import com.example.food.category.beans.Category2;
import com.example.food.cookbook.beans.CookBook;
import com.example.food.cookbook.beans.TagCategoryList;
import com.example.food.networking.MyVolley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements MyVolley.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void test(View view) {
        String url="http://apis.juhe.cn/cook/queryid?id=100&dtype=&key=61bb33f5a0d3fc921b0f47577900f2cb";
        //String url = "http://apis.juhe.cn/cook/category?parentid=&dtype=&key=61bb33f5a0d3fc921b0f47577900f2cb";
        MyVolley myVolley = MyVolley.newMyVolley();
        myVolley.stringRequestGet(this, url, this);
    }
    @Override

  public void onStringSuccess(String response) {
        Log.i("test", response);
        Gson gson=new Gson();
       /* try{
            JSONObject jsonObject=new JSONObject(response);
            JSONObject jsonObject1=jsonObject.getJSONObject("result");
            TagCategoryList tcl=gson.fromJson(jsonObject1.toString(),
                    new TypeToken<TagCategoryList>(){}.getType());
            List<CookBook> list=tcl.getData();
            for (CookBook c1:list){
                Log.i("test",c1.getTitle());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        //GSON
    /*  Gson gson=new Gson();
        Category c=gson.fromJson(response,new TypeToken<Category>(){}.getType());
        List<Category1> list1=c.getResult();
        for (Category1 c1:list1) {
            Log.i("test", c1.getName());
            List<Category2> list2 = c1.getList();
            for (Category2 c2 : list2) {
                Log.i("test", c2.getName());
            }
        }*/


    }
    @Override
    public void onFailure(VolleyError error) {
        Log.i("test", error.toString());
    }

}

















































