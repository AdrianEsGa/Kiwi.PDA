package com.xander.kiwipda.Model.Entities;

import android.graphics.drawable.Drawable;

public class Table {

    private int _id;
    private String _name;
    private String _description;
    private Drawable _image;

    public Table (int id, String name, String description, Drawable image){
        _id = id;
        _name = name;
        _description = description;
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
