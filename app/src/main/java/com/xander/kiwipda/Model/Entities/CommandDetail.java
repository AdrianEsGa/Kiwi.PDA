package com.xander.kiwipda.Model.Entities;


public class CommandDetail {
    private int _id;
    private int _commnadId;
    private Product _product;
    private int _quantity;
    private int _combinatedProductId;

    public CommandDetail() {}

    public CommandDetail(Product product, int quantity){
        _product = product;
        _quantity = quantity;
    }

    public Product GetProduct(){
        return _product;
    }

    public int GetQuantity(){
        return _quantity;
    }

    public void SetQuantity(int quantity){ _quantity = quantity; }

}
