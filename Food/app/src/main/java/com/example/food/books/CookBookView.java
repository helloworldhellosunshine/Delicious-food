package com.example.food.books;

import com.example.food.cookbook.beans.CookBook;

import java.util.List;

/**
 * Created by 王帝 on 2017/9/16.
 */

public interface CookBookView {
    public void setCooKBookList(List<CookBook> list);
    public void setFail();
}
