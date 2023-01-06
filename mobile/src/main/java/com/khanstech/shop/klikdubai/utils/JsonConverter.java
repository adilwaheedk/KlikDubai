package com.khanstech.shop.klikdubai.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.khanstech.shop.klikdubai.models.Brand;
import com.khanstech.shop.klikdubai.models.Category;
import com.khanstech.shop.klikdubai.models.Constants;
import com.khanstech.shop.klikdubai.models.Product;
import com.khanstech.shop.klikdubai.models.Stock;
import com.khanstech.shop.klikdubai.models.SubCategory;
import com.khanstech.shop.klikdubai.models.User;
import com.khanstech.shop.klikdubai.models.Vendor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class responsible of Converting JSON String to Objects by mapping required values.
 */
public class JsonConverter {

    /**
     * Get String value from Simple JSON Object
     *
     * @param jsonData Json object to get value from
     * @param name     Name of key mapped by required value
     * @return Value of type String
     * @throws JSONException
     */
    public static String getValueFromJSON(String jsonData, String name) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject.getString(name);
    }

    /**
     * Get String value from Simple JSON Object
     *
     * @param jsonData Json object to get value from
     * @param term     Term contain in name of key mapped by required value
     * @return Value of type String
     * @throws JSONException
     */
    public static String getValueFromJSONByKey(String jsonData, String term) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonData);
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (key.contains(term)) return jsonObject.getString(key);
        }
        return null;
    }

    /**
     * Mapping of Json string to list of strings
     *
     * @param jsonData Json data to parse
     * @return List of string
     * @throws JSONException
     */
    public static List<String> parseJsonToLabelStrings(String jsonData) throws JSONException {
        JSONArray jsonArr = new JSONArray(jsonData);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++)
            list.add(jsonArr.getJSONObject(i).getString("label").trim());
        return list;
    }

    /**
     * Mapping of Json string to get list of categories
     *
     * @param jsonData Json data to parse
     * @return List of Category object
     * @throws JSONException
     */
    public static List<Category> parseJsonToCategoryList(String jsonData) throws JSONException {
        JSONArray jsonArr = new JSONArray(jsonData);
        List<Category> list = new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);
            Category obj = new Category();
            obj.category_id = object.getString("category_id").trim();
            obj.category_name = object.getString("category_name").trim();
            obj.description = object.getString("description").trim();
            list.add(obj);
        }
        return list;
    }

    /**
     * Mapping of Json string to get list of Sub categories
     *
     * @param jsonData Json data to parse
     * @return List of SubCategory object
     * @throws JSONException
     */
    public static List<SubCategory> parseJsonToSubCategoryList(String jsonData) throws JSONException {
        JSONArray jsonArr = new JSONArray(jsonData);
        List<SubCategory> list = new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);
            SubCategory obj = new SubCategory();
            obj.sub_category_id = object.getString("sub_category_id").trim();
            obj.sub_category_name = object.getString("sub_category_name").trim();
            obj.category = object.getString("category").trim();
            list.add(obj);
        }
        return list;
    }

    /**
     * Mapping of Json string to get list of Products
     *
     * @param jsonData Json data to parse
     * @return List of Product object
     * @throws JSONException
     */
    public static List<Product> parseJsonToProductList(String jsonData) throws JSONException {
        JSONArray jsonArr = new JSONArray(jsonData);
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);
            Product obj = new Product();
            obj.product_id = object.getString("product_id").trim();
            obj.title = object.getString("title").trim();
            obj.category = object.getString("category").trim();
            obj.sub_category = object.getString("sub_category").trim();
            obj.num_of_imgs = object.getString("num_of_imgs").trim();
            obj.sale_price = object.getString("sale_price").trim();
            obj.purchase_price = object.getString("purchase_price").trim();
            obj.shipping_cost = object.getString("shipping_cost").trim();
            obj.add_timestamp = object.getString("add_timestamp").trim();
            obj.featured = object.getString("featured").trim();
            obj.tag = object.getString("tag").trim();
            obj.status = object.getString("status").trim();
            obj.brand = object.getString("brand").trim();
            obj.current_stock = object.getString("current_stock").trim();
            obj.unit = object.getString("unit").trim();
            obj.discount = object.getString("discount").trim();
            obj.discount_type = object.getString("discount_type").trim();
            obj.tax = object.getString("tax").trim();
            obj.tax_type = object.getString("tax_type").trim();
            obj.color = object.getString("color").trim();
            obj.options = object.getString("options").trim();
            obj.added_by = object.getString("added_by").trim();
            obj.deal = object.getString("deal").trim();
            list.add(obj);
        }
        return list;
    }

    /**
     * Mapping of Json string to get list of Stock
     *
     * @param jsonData Json data to parse
     * @return List of Stock object
     * @throws JSONException
     */
    public static List<Stock> parseJsonToStockList(String jsonData) throws JSONException {
        JSONArray jsonArr = new JSONArray(jsonData);
        List<Stock> list = new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);
            Stock obj = new Stock();
            obj.stock_id = object.getString("stock_id").trim();
            obj.type = object.getString("type").trim();
            obj.category = object.getString("category").trim();
            obj.sub_category = object.getString("sub_category").trim();
            obj.product = object.getString("product").trim();
            obj.quantity = object.getString("quantity").trim();
            obj.rate = object.getString("rate").trim();
            obj.total = object.getString("total").trim();
            obj.reason_note = object.getString("reason_note").trim();
            obj.datetime = object.getString("datetime").trim();
            obj.sale_id = object.getString("sale_id").trim();
            obj.added_by = object.getString("added_by").trim();
            list.add(obj);
        }
        return list;
    }

    /**
     * Mapping of Json string to get User
     *
     * @param jsonData Json data to parse
     * @return User object
     * @throws JSONException
     */
    public static User parseJsonToUser(String jsonData) throws JSONException {
        JSONArray jsonArr = new JSONArray(jsonData);
        if (jsonArr.length() > 0) {
            JSONObject object = jsonArr.getJSONObject(0);
            User obj = new User();
            obj.user_id = object.getString("user_id").trim();
            obj.username = object.getString("username").trim();
            obj.surname = object.getString("surname").trim();
            obj.email = object.getString("email").trim();
            obj.phone = object.getString("phone").trim();
            obj.address1 = object.getString("address1").trim();
            obj.address2 = object.getString("address2").trim();
            obj.city = object.getString("city").trim();
            obj.zip = object.getString("zip").trim();
            obj.langlat = object.getString("langlat").trim();
            obj.password = object.getString("password").trim();
            obj.fb_id = object.getString("fb_id").trim();
            obj.g_id = object.getString("g_id").trim();
            obj.g_photo = object.getString("g_photo").trim();
            obj.creation_date = object.getString("creation_date").trim();
            obj.google_plus = object.getString("google_plus").trim();
            obj.skype = object.getString("skype").trim();
            obj.facebook = object.getString("facebook").trim();
            ArrayList<String> wishList = new ArrayList<>();
            JSONArray wishlist_JSON = object.getJSONArray("wishlist");
            for (int j = 0; j < wishlist_JSON.length(); j++) {
                wishList.add(wishlist_JSON.getString(j));
            }
            obj.wishlist = wishList;
            obj.last_login = object.getString("last_login").trim();
            return obj;
        }
        return null;
    }

    /**
     * Mapping of Json string to get list of Vendors
     *
     * @param jsonData Json data to parse
     * @return List of Vendor object
     * @throws JSONException
     */
    public static List<Vendor> parseJsonToVendorList(String jsonData) throws JSONException {
        JSONArray jsonArr = new JSONArray(jsonData);
        List<Vendor> list = new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);
            Vendor obj = new Vendor();
            obj.vendor_id = object.getString("vendor_id").trim();
            obj.name = object.getString("name").trim();
            obj.email = object.getString("email").trim();
            obj.password = object.getString("password").trim();
            obj.company = object.getString("company").trim();
            obj.display_name = object.getString("display_name").trim();
            obj.address1 = object.getString("address1").trim();
            obj.address2 = object.getString("address2").trim();
            obj.status = object.getString("status").trim();
            obj.membership = object.getString("membership").trim();
            obj.create_timestamp = object.getString("create_timestamp").trim();
            obj.approve_timestamp = object.getString("approve_timestamp").trim();
            obj.member_timestamp = object.getString("member_timestamp").trim();
            obj.member_expire_timestamp = object.getString("member_expire_timestamp").trim();
            obj.details = object.getString("details").trim();
            obj.last_login = object.getString("last_login").trim();
            obj.facebook = object.getString("facebook").trim();
            obj.skype = object.getString("skype").trim();
            obj.google_plus = object.getString("google_plus").trim();
            obj.twitter = object.getString("twitter").trim();
            obj.youtube = object.getString("youtube").trim();
            obj.pinterest = object.getString("pinterest").trim();
            obj.stripe_details = object.getString("stripe_details").trim();
            obj.paypal_email = object.getString("paypal_email").trim();
            obj.preferred_payment = object.getString("preferred_payment").trim();
            obj.cash_set = object.getString("cash_set").trim();
            obj.stripe_set = object.getString("stripe_set").trim();
            obj.paypal_set = object.getString("paypal_set").trim();
            obj.phone = object.getString("phone").trim();
            obj.keywords = object.getString("keywords").trim();
            obj.description = object.getString("description").trim();
            obj.lat_lang = object.getString("lat_lang").trim();
            list.add(obj);
        }
        return list;
    }

    /**
     * Mapping of Json string to get list of Brand
     *
     * @param jsonData Json data to parse
     * @return List of Brand object
     * @throws JSONException
     */
    public static List<Brand> parseJsonToBrandList(String jsonData) throws JSONException {
        JSONArray jsonArr = new JSONArray(jsonData);
        List<Brand> list = new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);
            Brand obj = new Brand();
            obj.brand_id = object.getString("brand_id").trim();
            obj.name = object.getString("name").trim();
            obj.description = object.getString("description").trim();
            obj.category = object.getString("category").trim();
            obj.added_by = object.getString("added_by").trim();
            list.add(obj);
        }
        return list;
    }
}
