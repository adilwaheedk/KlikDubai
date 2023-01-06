package com.khanstech.shop.klikdubai.fragments;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khanstech.shop.klikdubai.R;
import com.khanstech.shop.klikdubai.activities.MainActivity;
import com.khanstech.shop.klikdubai.adapters.ProductHorizontalList;
import com.khanstech.shop.klikdubai.models.Category;
import com.khanstech.shop.klikdubai.models.Product;
import com.khanstech.shop.klikdubai.utils.IntentHelper;
import com.khanstech.shop.klikdubai.utils.PhoneFunctionality;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private MainActivity mainActivity;
    private List<Category> categoryList;
    private ArrayList<Product> productList;
    private View rootView;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home, container, false);
            mainActivity = (MainActivity) getActivity();

            // Set Check Home button in drawer menu
            NavigationView navigationView = (NavigationView) mainActivity.findViewById(R.id.nav_view);
            navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

            Object obj1 = IntentHelper.getObjectForKey("CategoryList");
            Object obj2 = IntentHelper.getObjectForKey("ProductList");

            if (obj1 != null && obj2 != null) {

                categoryList = (List<Category>) obj1;
                productList = (ArrayList<Product>) obj2;

                // Init Categories on Home Screen
                Category cat1 = categoryList.get(2);
                Category cat2 = categoryList.get(6);
                Category cat3 = categoryList.get(7);
                Category cat4 = categoryList.get(8);

                // Find Views
                TextView tv_item_cat1 = (TextView) rootView.findViewById(R.id.tv_item_cat1);
                TextView tv_item_cat2 = (TextView) rootView.findViewById(R.id.tv_item_cat2);
                TextView tv_item_cat3 = (TextView) rootView.findViewById(R.id.tv_item_cat3);
                TextView tv_item_cat4 = (TextView) rootView.findViewById(R.id.tv_item_cat4);
                RecyclerView lv_item_cat1 = (RecyclerView) rootView.findViewById(R.id.lv_item_cat1);
                RecyclerView lv_item_cat2 = (RecyclerView) rootView.findViewById(R.id.lv_item_cat2);
                RecyclerView lv_item_cat3 = (RecyclerView) rootView.findViewById(R.id.lv_item_cat3);
                RecyclerView lv_item_cat4 = (RecyclerView) rootView.findViewById(R.id.lv_item_cat4);

                // Set Values
                tv_item_cat1.setText(cat1.category_name);
                tv_item_cat2.setText(cat2.category_name);
                tv_item_cat3.setText(cat3.category_name);
                tv_item_cat4.setText(cat4.category_name);

                // Categorize All Products
                List<Product> cat1_prod = new ArrayList<>();
                for (int i = 0; i < productList.size(); i++) {
                    Product obj = productList.get(i);
                    if (obj.category.equals(cat1.category_id)) {
                        cat1_prod.add(obj);
                        productList.remove(i);
                    }
                }

                List<Product> cat2_prod = new ArrayList<>();
                for (int i = 0; i < productList.size(); i++) {
                    Product obj = productList.get(i);
                    if (obj.category.equals(cat2.category_id)) {
                        cat2_prod.add(obj);
                        productList.remove(i);
                    }
                }

                List<Product> cat3_prod = new ArrayList<>();
                for (int i = 0; i < productList.size(); i++) {
                    Product obj = productList.get(i);
                    if (obj.category.equals(cat3.category_id)) {
                        cat3_prod.add(obj);
                        productList.remove(i);
                    }
                }

                List<Product> cat4_prod = new ArrayList<>();
                for (int i = 0; i < productList.size(); i++) {
                    Product obj = productList.get(i);
                    if (obj.category.equals(cat4.category_id)) {
                        cat4_prod.add(obj);
                        productList.remove(i);
                    }
                }

                // Init Lists
                lv_item_cat1 = initListView(lv_item_cat1);
                lv_item_cat2 = initListView(lv_item_cat2);
                lv_item_cat3 = initListView(lv_item_cat3);
                lv_item_cat4 = initListView(lv_item_cat4);

                // Set List Adapters
                lv_item_cat1.setAdapter(new ProductHorizontalList(mainActivity, cat1_prod));
                lv_item_cat2.setAdapter(new ProductHorizontalList(mainActivity, cat2_prod));
                lv_item_cat3.setAdapter(new ProductHorizontalList(mainActivity, cat3_prod));
                lv_item_cat4.setAdapter(new ProductHorizontalList(mainActivity, cat4_prod));
            } else {
                PhoneFunctionality.makeToast(mainActivity, "Data not found", true);
                mainActivity.exitApp();
            }
        }
        return rootView;
    }

    private RecyclerView initListView(RecyclerView rv) {
        rv.setHasFixedSize(true);
        LinearLayoutManager layout = new LinearLayoutManager(mainActivity);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(layout);
        //rv.setItemAnimator(new DefaultItemAnimator());
        //rv.addItemDecoration(new SimpleDividerItemDecoration(mainActivity, LinearLayoutManager.HORIZONTAL, 4f));
        return rv;
    }
}
