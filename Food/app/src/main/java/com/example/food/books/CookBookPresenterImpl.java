package com.example.food.books;

import android.content.Context;

import com.example.food.cookbook.beans.CookBook;

import java.util.List;

/**
 * Created by 王帝 on 2017/9/16.
 */

public class CookBookPresenterImpl implements CookBookPresenter, CookBookDao.CookBookDaoListener {

    CookBookDao dao;
    CookBookView cookBookView;

    public CookBookPresenterImpl(CookBookView cookBookView){
        this.cookBookView=cookBookView;
        dao=new CookBookDaoImpl();
    }
    @Override
    public void getCookBookListById(Context ctx, String id) {
        dao.getCookBookListById(ctx,id,this);
    }

    @Override
    public void getCookBookListByKey(Context ctx, String key) {
        dao.getCookBookListByKey(ctx,key,this);
    }

    @Override
    public void onSuccess(List<CookBook> list) {
          cookBookView.setCooKBookList(list);
    }

    @Override
    public void onFail() {
        cookBookView.setFail();
    }
}
