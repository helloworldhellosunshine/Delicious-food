package com.example.food.books;

import android.content.Context;

import com.example.food.cookbook.beans.CookBook;

import java.util.List;

/**
 * Created by 王帝 on 2017/9/16.
 */
public interface CookBookDao{
    interface CookBookDaoListener{
        public void onSuccess(List<CookBook> list);

        public void onFail();
    }
    public void getCookBookListById(Context context,String id,CookBookDaoListener listener);
    public void getCookBookListByKey(Context context,String key,CookBookDaoListener listener);
}

