package com.project_prm392.entity;

import java.io.Serializable;

public class Item implements Serializable {
    private int itemId;
    private int categoryId;
    private String itemName;
    private String itemUrl;
    private int price;
    int calories;
    private String description;

    public Item(int itemId, int categoryId, String itemName, String itemUrl, int price, int calories, String description) {
        this.itemId = itemId;
        this.categoryId = categoryId;
        this.itemName = itemName;
        this.itemUrl = itemUrl;
        this.price = price;
        this.calories = calories;
        this.description = description;
    }

    public Item(int categoryId, String itemName, String itemUrl, int price, int calories, String description) {
        this.categoryId = categoryId;
        this.itemName = itemName;
        this.itemUrl = itemUrl;
        this.price = price;
        this.calories = calories;
        this.description = description;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", categoryId=" + categoryId +
                ", itemName='" + itemName + '\'' +
                ", itemUrl='" + itemUrl + '\'' +
                ", price=" + price +
                ", calories=" + calories +
                ", description='" + description + '\'' +
                '}';
    }
}
