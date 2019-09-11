package com.xander.kiwipda.Model.Entities;

import android.graphics.drawable.Drawable;

public class Product {

    private int _id;
    private String _name;
    private String _description;
    private int _typeId;
    private Drawable _image;

    public Product (int id, String name, String description, int _typeId, Drawable image){
        _id = id;
        _name = name;
        _description = description;
        _typeId = _typeId;
        _image = image;
    }

    public String GetName(){
        return _name;
    }

    public String GetDescription(){
        return _description;
    }

    public Drawable GetImage(){
        return _image;
    }

    public void SetImage(Drawable image){
        _image = image;
    }

    public String toString(){
        return _name;
    }
}
