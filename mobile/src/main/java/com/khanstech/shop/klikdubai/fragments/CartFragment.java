package com.khanstech.shop.klikdubai.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khanstech.shop.klikdubai.R;
import com.khanstech.shop.klikdubai.activities.MainActivity;

public class CartFragment extends Fragment {

    private MainActivity mainActivity;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        mainActivity = (MainActivity) getActivity();

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mainActivity.action_search.setVisible(true);
        mainActivity.action_cart.setVisible(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.action_search.setVisible(false);
        mainActivity.action_cart.setVisible(false);
    }
}
