package com.khanstech.shop.klikdubai.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sInstance;
    private static String DATABASE_NAME = "KlikDubaiDatabase";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CART = "Cart";
    public static final String TABLE_CARTITEM = "CartItem";
    public static final String product_id = "_id";
    public static final String image = "image";
    public static final String title = "title";
    public static final String quantity = "quantity";
    public static final String price = "price";
    public static final String tax = "tax";
    public static final String total = "total";
    public static final String shipping = "shipping";
    public static final String grand_total = "grand_total";

    public SQLiteDatabase sqldb;

    // Create Table Queries
    private static final String CREATE_TABLE_CART = "CREATE TABLE "
            + TABLE_CART + " ( " + total + " REAL, " + tax + " REAL, "
            + shipping + " REAL, " + grand_total + " REAL);";

    private static final String CREATE_TABLE_CARTITEM = "CREATE TABLE "
            + TABLE_CARTITEM + " ( " + product_id + " INTEGER PRIMARY KEY, " + image + " BLOB, "
            + title + " TEXT, " + quantity + " REAL, " + price + " REAL, " + tax + " REAL, " + total + " REAL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (sInstance == null)
            sInstance = new DatabaseHelper(context.getApplicationContext());
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CART);
        db.execSQL(CREATE_TABLE_CARTITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_CARTITEM);
        onCreate(db);
    }
}

