package com.khanstech.shop.klikdubai.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class CartCrud {

    private DatabaseHelper dbh;
    private String table_name = DatabaseHelper.TABLE_CART;

    public CartCrud(Context ctx) {
        dbh = new DatabaseHelper(ctx);
    }

    public void openDB() throws NullPointerException {
        dbh.sqldb = dbh.getWritableDatabase();
    }

    public void closeDB() {
        dbh.sqldb = dbh.getReadableDatabase();
        if (dbh.sqldb != null && dbh.sqldb.isOpen())
            dbh.sqldb.close();
    }

    public void insertRow(Cart m) {
        ContentValues cv = setValues(m);
        dbh.sqldb.insert(table_name, null, cv);
    }

    public int deleteAllRows() {
        return dbh.sqldb.delete(table_name, null, new String[]{});
    }

    public ArrayList<Cart> selectAllRows() {
        ArrayList<Cart> rows = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + table_name;
        Cursor c = dbh.sqldb.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                rows.add(getValues(c));
            } while (c.moveToNext());
        }
        c.close();
        return rows;
    }

    private ContentValues setValues(Cart m) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.total, m.total);
        cv.put(DatabaseHelper.tax, m.tax);
        cv.put(DatabaseHelper.shipping, m.shipping);
        cv.put(DatabaseHelper.grand_total, m.grand_total);
        return cv;
    }

    private Cart getValues(Cursor c) {
        Cart m = new Cart();
        m.total = c.getFloat(c.getColumnIndex(DatabaseHelper.total));
        m.tax = c.getFloat(c.getColumnIndex(DatabaseHelper.tax));
        m.shipping = c.getFloat(c.getColumnIndex(DatabaseHelper.shipping));
        m.grand_total = c.getFloat(c.getColumnIndex(DatabaseHelper.grand_total));
        return m;
    }
}

