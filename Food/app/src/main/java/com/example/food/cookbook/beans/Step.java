package com.example.food.cookbook.beans;

import java.io.Serializable;

/**
 * Created by 王帝 on 2017/9/15.
 */

public class Step implements Serializable{
    String img;
    String step;
    public Step() {
    }

    public Step(String img, String step) {
        this.img = img;
        this.step = step;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }


}
