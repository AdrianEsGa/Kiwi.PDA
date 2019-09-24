package com.xander.kiwipda.Model.Entities;

import android.graphics.drawable.Drawable;

public class Table {

    private int _id;
    private String _name;
    private Drawable _image;

    public Table (int id, String name,  Drawable image){
        _id = id;
        _name = name;
        _image = image;
    }

    public int GetId(){
        return _id;
    }

    public String GetName(){
        return _name;
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
