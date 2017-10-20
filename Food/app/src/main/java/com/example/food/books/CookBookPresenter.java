package com.example.food.books;

import android.content.Context;

/**
 * Created by 王帝 on 2017/9/16.
 */

public interface CookBookPresenter {
    public void getCookBookListById(Context ctx,String id);
    public void getCookBookListByKey(Context ctx,String key);
}
