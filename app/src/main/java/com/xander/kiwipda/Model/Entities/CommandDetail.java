package com.xander.kiwipda.Model.Entities;


public class CommandDetail {
    private int _id;
    private int _commnadId;
    private Product _product;
    private int _quantity;
    private Product _combinedProduct;

    public CommandDetail() {}

    public CommandDetail(Product product, int quantity){
        _product = product;
        _quantity = quantity;
    }

    public CommandDetail(Product product, Product combinedProduct, int quantity){
        _product = product;
        _quantity = quantity;
        _combinedProduct = combinedProduct;
    }

    public Product GetProduct(){
        return _product;
    }

    public Product GetCombinedProduct(){
        return _combinedProduct;
    }

    public void SetCombinedProduct(Product combinedProduct){ _combinedProduct = combinedProduct; }

    public int GetQuantity(){
        return _quantity;
    }

    public void SetQuantity(int quantity){ _quantity = quantity; }

}
