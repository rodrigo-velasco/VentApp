package com.example.rodrigo.ventapp;

public class object_product
{
    String name; //Nombre del producto
    double buyingPrice; //Precio al que se compra
    double sellingPrice; //Precio al que se vende
    int quantity; //Cantidad disponible
    String description; //Descripción del producto
    byte[] photo; //Foto

    public object_product(String name, double sellingPrice, int quantity, String description, byte[] photo)
    {
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.description = description;
        this.photo = photo;
    }
}
