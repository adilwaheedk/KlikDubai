package com.khanstech.shop.klikdubai.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khanstech.shop.klikdubai.R;
import com.khanstech.shop.klikdubai.activities.MainActivity;
import com.khanstech.shop.klikdubai.models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountWishListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_account_wishlist, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();

        Account.WishList wishList = new Account.WishList();
        wishList.tv_no = "0";
        wishList.tv_name = "My Mobile";
        wishList.tv_price = "$1500.00";
        wishList.tv_image = BitmapFactory.decodeResource(getResources(), R.drawable.item_xl_5754385_6004719);
        List<Account.WishList> wishLists = new ArrayList<>();
        wishLists.add(wishList);

        RecyclerView list = (RecyclerView) v.findViewById(R.id.list_purchase_history);
        WishListAdapter adapter = new WishListAdapter(wishLists);
        list.setLayoutManager(new LinearLayoutManager(mainActivity));
        list.setItemAnimator(new DefaultItemAnimator());
        //list.addItemDecoration(new SimpleDividerItemDecoration(mainActivity, LinearLayoutManager.VERTICAL, 4f));
        list.setAdapter(adapter);
        return v;
    }

    private class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.MyViewHolder> {

        private List<Account.WishList> wishLists;

        public WishListAdapter(List<Account.WishList> wishLists) {
            this.wishLists = wishLists;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_account_wishlist, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Account.WishList purchaseHistory = wishLists.get(position);
            holder.tv_no.setText(purchaseHistory.tv_no);
            holder.tv_name.setText(purchaseHistory.tv_name);
            holder.tv_price.setText(purchaseHistory.tv_price);
            holder.tv_image.setImageBitmap(purchaseHistory.tv_image);

        }

        @Override
        public int getItemCount() {
            return wishLists.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            protected final TextView tv_no, tv_name, tv_price;
            protected final ImageView tv_image, btn_add_cart, btn_remove_wish;

            protected MyViewHolder(View v) {
                super(v);
                tv_no = (TextView) v.findViewById(R.id.tv_no);
                tv_name = (TextView) v.findViewById(R.id.tv_name);
                tv_price = (TextView) v.findViewById(R.id.tv_price);
                tv_image = (ImageView) v.findViewById(R.id.tv_image);
                btn_add_cart = (ImageView) v.findViewById(R.id.btn_add_cart);
                btn_remove_wish = (ImageView) v.findViewById(R.id.btn_remove_wish);
            }
        }
    }
}
