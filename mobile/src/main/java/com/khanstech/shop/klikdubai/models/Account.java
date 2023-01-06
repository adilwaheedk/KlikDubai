package com.khanstech.shop.klikdubai.models;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Account {

    public ArrayList<PurchaseHistory> purchaseHistoryList = new ArrayList<>();
    public ArrayList<WishList> wishLists = new ArrayList<>();
    public ArrayList<Downloads> downloadsList = new ArrayList<>();

    public void addPurchaseHistory(PurchaseHistory obj) {
        purchaseHistoryList.add(obj);
    }

    public void addWishList(WishList obj) {
        wishLists.add(obj);
    }

    public void addDownloads(Downloads obj) {
        downloadsList.add(obj);
    }

    public static class PurchaseHistory {
        public String tv_no, tv_date, tv_total, tv_payment_status, tv_delivery_status, tv_invoice;
    }

    public static class WishList {
        public String tv_no, tv_name, tv_price;
        public Bitmap tv_image;
    }

    public static class Downloads {
        public String tv_no, tv_name, tv_download;
        public Bitmap tv_image;
    }
}
