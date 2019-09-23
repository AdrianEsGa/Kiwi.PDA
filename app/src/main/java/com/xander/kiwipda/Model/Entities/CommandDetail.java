package com.xander.kiwipda.Model.Entities;

public class CommandDetail {
    private int _id;
    private int _commnadId;
    private int _productId;
    private int _quantity;
    private int _combinatedProductId;


    public CommandDetail(int productId, int quantity){
        _productId = productId;
        _quantity = quantity;
    }

    public int GetProductId(){
        return _productId;
    }

    public int GetQuantity(){
        return _quantity;
    }

    public int SetQuantity(int quantity){
        return _quantity = quantity;
    }

}
