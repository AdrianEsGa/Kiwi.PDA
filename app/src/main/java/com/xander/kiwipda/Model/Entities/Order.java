package com.xander.kiwipda.Model.Entities;

public class Order {
    private int _id;
    private String _name;

    public String Name() {
        return this._name;
    }

    public Order (int id){
        _id = id;
        _name = "Pedido " + _id;
    }

    public String toString(){
        return _name;
    }
}
