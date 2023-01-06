package com.khanstech.shop.klikdubai.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khanstech.shop.klikdubai.R;
import com.khanstech.shop.klikdubai.activities.MainActivity;
import com.khanstech.shop.klikdubai.adapters.AccountPagerAdapter;

public class AccountFragment extends Fragment {

    private MainActivity mainActivity;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        mainActivity = (MainActivity) getActivity();

        final TabLayout tabs = (TabLayout) rootView.findViewById(R.id.tabs_account);
        final ViewPager pager = (ViewPager) rootView.findViewById(R.id.viewPager_account);
        final AccountPagerAdapter adapter = new AccountPagerAdapter(getContext(), mainActivity.getSupportFragmentManager());

        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);

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
