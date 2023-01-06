package com.khanstech.shop.klikdubai.tasks;

import android.content.Intent;
import android.os.AsyncTask;

import com.khanstech.shop.klikdubai.R;
import com.khanstech.shop.klikdubai.activities.MainActivity;
import com.khanstech.shop.klikdubai.activities.SplashActivity;
import com.khanstech.shop.klikdubai.utils.IntentHelper;
import com.khanstech.shop.klikdubai.utils.JsonConverter;
import com.khanstech.shop.klikdubai.utils.OKHttp;
import com.khanstech.shop.klikdubai.utils.PhoneFunctionality;

public class GetHomeScreenData extends AsyncTask<Void, Void, Integer> {

    private SplashActivity activity;
    private String base_url;
    private Object categoryList, productList;

    public GetHomeScreenData(SplashActivity activity) {
        this.activity = activity;
        this.base_url = activity.getString(R.string.url_api);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        OKHttp http = new OKHttp();
        try {
            String response = http.getCall(base_url + "get/rows/category");
            if (http.responseCode == 200) {
                categoryList = JsonConverter.parseJsonToCategoryList(response);
                response = http.getCall(base_url + "get_newest_items");
                if (http.responseCode == 200) {
                    productList = JsonConverter.parseJsonToProductList(response);
                    return 1;
                }
            }
            return 2;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    protected void onPostExecute(Integer feedback) {
        super.onPostExecute(feedback);
        if (feedback == 1) {
            IntentHelper.addObjectForKey(categoryList, "CategoryList");
            IntentHelper.addObjectForKey(productList, "ProductList");
            activity.startActivity(new Intent(activity, MainActivity.class));
        } else if (feedback == -1) {
            PhoneFunctionality.makeToast(activity, activity.getString(R.string.error));
            activity.startActivity(new Intent(activity, SplashActivity.class));
        } else if (feedback == 2) {
            PhoneFunctionality.makeToast(activity, activity.getString(R.string.api_error));
            activity.startActivity(new Intent(activity, SplashActivity.class));
        }

    }
}
