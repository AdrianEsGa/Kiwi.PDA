package com.xander.kiwipda.Model.Entities;

public class Employee {
    private int _id;
    private String _name;

    public String Name() {
        return this._name;
    }

    public Employee (int id, String name){
        _id = id;
        _name = name;
    }

    public String toString(){
        return _name;
    }

}

