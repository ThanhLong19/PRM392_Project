package com.project_prm392.entity;

import java.io.Serializable;
import java.net.URL;

public class Category implements Serializable {
    private int id;
    private String imageUrl;
    private String categoryName;

    public Category(int id, String categoryName, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.categoryName = categoryName;
    }

    public Category(String imageUrl, String categoryName) {
        this.imageUrl = imageUrl;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
