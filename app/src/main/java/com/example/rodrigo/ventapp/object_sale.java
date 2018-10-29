package com.example.rodrigo.ventapp;

public class object_sale
{
    String date; //Fecha de la compra
    int client; //ID del cliente
    int product; //ID del producto
    double total; //Total a pagar por la compra
    Boolean payCash; //Paga de contado?
    Boolean payCredit; //Paga a crédito?

    public object_sale(String date, int client, int product, double total, Boolean payCash, Boolean payCredit)
    {
        this.date = date;
        this.client = client;
        this.product = product;
        this.total = total;
        this.payCash = payCash;
        this.payCredit = payCredit;
    }
}
