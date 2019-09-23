package com.xander.kiwipda.Model.Entities;

import android.graphics.drawable.Drawable;

public class Product {

    private int _id;
    private String _name;
    private int _typeId;
    private Drawable _image;
    private int _quantity;

    public Product (int id, String name, int typeId, Drawable image){
        _id = id;
        _name = name;
        _typeId = typeId;
        _image = image;
        _quantity = 0;
    }

    public int GetId(){
        return _id;
    }

    public String GetName(){
        return _name;
    }

    public int GetType(){
        return _typeId;
    }

    public Drawable GetImage(){
        return _image;
    }

    public void SetImage(Drawable image){
        _image = image;
    }

    public void SetQuantity(int quantity){
        _quantity = quantity;
    }

    public int GetQuantity(){ return _quantity; }

    public String toString(){
        return _name;
    }
}
