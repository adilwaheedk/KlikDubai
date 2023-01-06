package com.khanstech.shop.klikdubai.database;

import java.sql.Blob;

public class CartItem {
    public Long product_id;
    public byte[] image;
    public String title;
    public Float quantity;
    public Float price;
    public Float tax;
    public Float total;
}
