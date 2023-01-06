package com.khanstech.shop.klikdubai.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khanstech.shop.klikdubai.R;
import com.khanstech.shop.klikdubai.activities.MainActivity;
import com.khanstech.shop.klikdubai.models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountPurchaseHistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_account_purchase_history, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();

        Account.PurchaseHistory purchaseHistory = new Account.PurchaseHistory();
        purchaseHistory.tv_no = "0";
        purchaseHistory.tv_date = "01 Jan, 2016";
        purchaseHistory.tv_total = "$1500.00";
        purchaseHistory.tv_payment_status = "PAID";
        purchaseHistory.tv_delivery_status = "DELIVERED";
        purchaseHistory.tv_invoice = "invoice_1";
        List<Account.PurchaseHistory> purchaseHistoryList = new ArrayList<>();
        purchaseHistoryList.add(purchaseHistory);

        RecyclerView list = (RecyclerView) v.findViewById(R.id.list_purchase_history);
        PurchaseHistoryAdapter adapter = new PurchaseHistoryAdapter(purchaseHistoryList);
        list.setLayoutManager(new LinearLayoutManager(mainActivity));
        list.setItemAnimator(new DefaultItemAnimator());
        //list.addItemDecoration(new SimpleDividerItemDecoration(mainActivity, LinearLayoutManager.VERTICAL, 4f));
        list.setAdapter(adapter);
        return v;
    }

    private class PurchaseHistoryAdapter extends RecyclerView.Adapter<PurchaseHistoryAdapter.MyViewHolder> {

        private List<Account.PurchaseHistory> purchaseHistoryList;

        public PurchaseHistoryAdapter(List<Account.PurchaseHistory> purchaseHistoryList) {
            this.purchaseHistoryList = purchaseHistoryList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_account_purchase_history, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Account.PurchaseHistory purchaseHistory = purchaseHistoryList.get(position);
            holder.tv_no.setText(purchaseHistory.tv_no);
            holder.tv_date.setText(purchaseHistory.tv_date);
            holder.tv_total.setText(purchaseHistory.tv_total);
            holder.tv_payment_status.setText(purchaseHistory.tv_payment_status);
            holder.tv_delivery_status.setText(purchaseHistory.tv_delivery_status);
            holder.tv_invoice.setText(purchaseHistory.tv_invoice);
        }

        @Override
        public int getItemCount() {
            return purchaseHistoryList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            protected final TextView tv_no, tv_date, tv_total, tv_payment_status, tv_delivery_status, tv_invoice;

            MyViewHolder(View v) {
                super(v);
                tv_no = (TextView) v.findViewById(R.id.tv_no);
                tv_date = (TextView) v.findViewById(R.id.tv_date);
                tv_total = (TextView) v.findViewById(R.id.tv_total);
                tv_payment_status = (TextView) v.findViewById(R.id.tv_payment_status);
                tv_delivery_status = (TextView) v.findViewById(R.id.tv_delivery_status);
                tv_invoice = (TextView) v.findViewById(R.id.tv_invoice);
            }
        }
    }
}
