package com.example.food.cookbook.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 王帝 on 2017/9/15.
 */

public class CookBook implements Serializable{

    String id;
    String title;
    String tags;
    String imtro;
    String ingredients;
    String burden;
    List<String> albums;
    List<Step> steps;

    public CookBook() {

    }

    public CookBook(String id, String title, String tags, String imtro,
                    String ingredients, String burden, List<String> albums, List<Step> steps) {
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.imtro = imtro;
        this.ingredients = ingredients;
        this.burden = burden;
        this.albums = albums;
        this.steps = steps;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImtro() {
        return imtro;
    }

    public void setImtro(String imtro) {
        this.imtro = imtro;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getBurden() {
        return burden;
    }

    public void setBurden(String burden) {
        this.burden = burden;
    }

    public List<String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }


}
