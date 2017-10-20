package com.example.food.category;

import android.content.Context;

import com.example.food.category.beans.Category;

/**
 * Created by 王帝 on 2017/9/15.
 */

public class CategoryPresenterImpl implements CategoryPresenter,CategoryDao.CategoryDaoLister{
    Context ctx;
    CategoryView categoryView;
    CategoryDao dao;
    public CategoryPresenterImpl (Context ctx,CategoryView categoryView){
        this.ctx=ctx;
        this.categoryView=categoryView;
        dao=new CategoryDaoImpl();
    }

    @Override
    public void getCategory() {
        dao.getCategory(ctx,this);
    }

    @Override
    public void onSuccess(Category category) {
        categoryView.setCategory(category);
    }

    @Override
    public void onFail() {
        categoryView.setFail();
    }
}
