package com.example.food.category;

import android.content.Context;

import com.example.food.category.beans.Category;

/**
 * Created by 王帝 on 2017/9/15.
 */

public interface CategoryDao {
    public void getCategory(Context ctx, CategoryDaoLister listener);
    interface CategoryDaoLister{
        public void onSuccess(Category category);
        public void onFail();
    }
}
