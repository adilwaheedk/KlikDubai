package com.khanstech.shop.klikdubai.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khanstech.shop.klikdubai.R;
import com.khanstech.shop.klikdubai.activities.MainActivity;

public class CompareItemsFragment extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View rootView = inflater.inflate(R.layout.fragment_compare_items, container, false);
        final MainActivity mainActivity = (MainActivity) getActivity();

        return rootView;
    }
}
