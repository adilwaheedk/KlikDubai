package com.khanstech.shop.klikdubai.tasks;

import android.os.AsyncTask;

import com.khanstech.shop.klikdubai.R;
import com.khanstech.shop.klikdubai.activities.MainActivity;
import com.khanstech.shop.klikdubai.models.Brand;
import com.khanstech.shop.klikdubai.models.Category;
import com.khanstech.shop.klikdubai.models.Product;
import com.khanstech.shop.klikdubai.models.Stock;
import com.khanstech.shop.klikdubai.models.SubCategory;
import com.khanstech.shop.klikdubai.models.User;
import com.khanstech.shop.klikdubai.utils.IntentHelper;
import com.khanstech.shop.klikdubai.utils.JsonConverter;
import com.khanstech.shop.klikdubai.utils.OKHttp;
import com.khanstech.shop.klikdubai.utils.PhoneFunctionality;

import java.util.List;

public class GetDataFromAPI extends AsyncTask<Void, Void, Integer> {

    private final static int category = 12;
    private final static int vendor = 13;
    private final static int sub_category_by_cat = 14;
    private final static int products_by_cat = 15;
    private final static int products_by_sub_cat = 16;
    private final static int product_by_id = 17;
    private final static int stock_by_prod = 18;
    private final static int user_by_id = 19;
    private final static int brands_by_cat = 20;

    private MainActivity activity;
    private String base_url;
    private int data_type;
    private Object converted_data;

    public GetDataFromAPI(MainActivity activity, int data_type) {
        this.activity = activity;
        this.data_type = data_type;
        this.base_url = activity.getString(R.string.url_api_get_rows);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        OKHttp http = new OKHttp();
        try {
            String url = base_url;
            switch (data_type) {
                case category:
                    url += "category";
                    break;
                case vendor:
                    url += "vendor";
                    break;
                case sub_category_by_cat:
                    url += "sub_category_by_cat/id/";
                    break;
                case products_by_cat:
                    url += "products_by_cat/id/";
                    break;
                case products_by_sub_cat:
                    url += "products_by_sub_cat/id/";
                    break;
                case product_by_id:
                    url += "product_by_id/id/";
                    break;
                case stock_by_prod:
                    url += "stock_by_prod/id/";
                    break;
                case user_by_id:
                    url += "user_by_id/id/";
                    break;
                case brands_by_cat:
                    url += "brands_by_cat/id/";
                    break;
                default:
                    return 3;
            }
            String response = http.getCall(url);
            if (http.responseCode == 200) {
                switch (data_type) {
                    case category:
                        converted_data = JsonConverter.parseJsonToCategoryList(response);
                        break;
                    case vendor:
                        converted_data = JsonConverter.parseJsonToVendorList(response);
                        break;
                    case sub_category_by_cat:
                        converted_data = JsonConverter.parseJsonToSubCategoryList(response);
                        break;
                    case products_by_cat:
                        converted_data = JsonConverter.parseJsonToProductList(response);
                        break;
                    case products_by_sub_cat:
                        converted_data = JsonConverter.parseJsonToProductList(response);
                        break;
                    case product_by_id:
                        converted_data = JsonConverter.parseJsonToProductList(response);
                        break;
                    case stock_by_prod:
                        converted_data = JsonConverter.parseJsonToStockList(response);
                        break;
                    case user_by_id:
                        converted_data = JsonConverter.parseJsonToUser(response);
                        break;
                    case brands_by_cat:
                        converted_data = JsonConverter.parseJsonToBrandList(response);
                        break;
                }
                return 1;
            } else return 2;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    protected void onPostExecute(Integer feedback) {
        super.onPostExecute(feedback);
        if (feedback == -1) {
            PhoneFunctionality.makeToast(activity, activity.getString(R.string.error));
        } else if (feedback == 2) {
            PhoneFunctionality.makeToast(activity, activity.getString(R.string.api_error));
        } else if (feedback == 3) {
            PhoneFunctionality.makeToast(activity, activity.getString(R.string.bad_request));
        } else {
            if (converted_data instanceof List<?>) {
                List<?> obj = (List<?>) converted_data;
                if (obj.get(0) instanceof Product) {
                    IntentHelper.addObjectForKey(converted_data, "ProductList");
                } else if (obj.get(0) instanceof Category) {
                    IntentHelper.addObjectForKey(converted_data, "CategoryList");
                } else if (obj.get(0) instanceof SubCategory) {
                    IntentHelper.addObjectForKey(converted_data, "SubCategoryList");
                } else if (obj.get(0) instanceof Stock) {
                    IntentHelper.addObjectForKey(converted_data, "StockList");
                } else if (obj.get(0) instanceof Brand) {
                    IntentHelper.addObjectForKey(converted_data, "BrandList");
                }
            } else if (converted_data instanceof User) {
                IntentHelper.addObjectForKey(converted_data, "User");
            }
        }
    }
}
