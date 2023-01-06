package com.khanstech.shop.klikdubai.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.khanstech.shop.klikdubai.R;

/**
 * Helper class for fragments related functions
 */
public class FragmentHelper {
    /**
     * Replace fragment with the new fragment
     *
     * @param fragment     Parent fragment context
     * @param gotoFragment New fragment class object
     */
    public static void replaceFragment(Fragment fragment, Object gotoFragment) {
        replaceFragment(fragment, gotoFragment, null);
    }

    /**
     * Replace fragment with the new fragment
     *
     * @param fragment     Parent fragment context
     * @param gotoFragment New fragment class object
     * @param tag          Name for the fragment, to later get the fragment
     */
    public static void replaceFragment(Fragment fragment, Object gotoFragment, String tag) {
        FragmentTransaction transaction = fragment.getFragmentManager().beginTransaction();
        doTransaction(transaction, gotoFragment, tag);
    }

    public static void replaceFragment(AppCompatActivity activity, Object gotoFragment, String tag) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        doTransaction(transaction, gotoFragment, tag);
    }

    private static void doTransaction(FragmentTransaction transaction, Object gotoFragment, String tag) {
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (tag != null) transaction.replace(R.id.fragment_container, (Fragment) gotoFragment, tag);
        else transaction.replace(R.id.fragment_container, (Fragment) gotoFragment);
        transaction.addToBackStack(null).commit();
    }

    /**
     * Add new fragment on front of old fragment
     *
     * @param fragment     Parent fragment context
     * @param gotoFragment New fragment class object
     */
    public static void addFragment(Fragment fragment, Object gotoFragment) {
        FragmentTransaction transaction = fragment.getFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.add(R.id.fragment_container, (Fragment) gotoFragment);
        transaction.addToBackStack(null).commit();
    }
}
