package com.khanstech.shop.klikdubai.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class CartItemCrud {

    private DatabaseHelper dbh;
    private String table_name = DatabaseHelper.TABLE_CARTITEM;

    public CartItemCrud(Context ctx) {
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

    public void insertRow(CartItem m) {
        ContentValues cv = setValues(m);
        dbh.sqldb.insert(table_name, null, cv);
    }

    public int deleteRow(String product_id) {
        return dbh.sqldb.delete(table_name, DatabaseHelper.product_id + " =? ", new String[]{product_id});
    }

    public int deleteAllRows() {
        return dbh.sqldb.delete(table_name, null, new String[]{});
    }

    public CartItem selectRowById(String product_id) {
        CartItem row = new CartItem();
        String selectQuery = "SELECT * FROM " + table_name + " WHERE " +
                DatabaseHelper.product_id + " = '" + product_id + "'";
        Cursor c = dbh.sqldb.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            row = getValues(c);
        }
        c.close();
        return row;
    }

    public ArrayList<CartItem> selectAllRows() {
        ArrayList<CartItem> rows = new ArrayList<>();
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

    private ContentValues setValues(CartItem m) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.product_id, m.product_id);
        cv.put(DatabaseHelper.image, m.image);
        cv.put(DatabaseHelper.title, m.title);
        cv.put(DatabaseHelper.quantity, m.quantity);
        cv.put(DatabaseHelper.price, m.price);
        cv.put(DatabaseHelper.tax, m.tax);
        cv.put(DatabaseHelper.total, m.total);
        return cv;
    }

    private CartItem getValues(Cursor c) {
        CartItem m = new CartItem();
        m.product_id = c.getLong(c.getColumnIndex(DatabaseHelper.product_id));
        m.image = c.getBlob(c.getColumnIndex(DatabaseHelper.image));
        m.title = c.getString(c.getColumnIndex(DatabaseHelper.title));
        m.quantity = c.getFloat(c.getColumnIndex(DatabaseHelper.quantity));
        m.tax = c.getFloat(c.getColumnIndex(DatabaseHelper.tax));
        m.total = c.getFloat(c.getColumnIndex(DatabaseHelper.total));
        return m;
    }
}

