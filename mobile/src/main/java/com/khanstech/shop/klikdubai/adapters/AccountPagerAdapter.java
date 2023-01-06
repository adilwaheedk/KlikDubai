package com.khanstech.shop.klikdubai.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.khanstech.shop.klikdubai.fragments.AccountEditInfoFragment;
import com.khanstech.shop.klikdubai.fragments.AccountInfoFragment;
import com.khanstech.shop.klikdubai.fragments.AccountPurchaseHistoryFragment;
import com.khanstech.shop.klikdubai.fragments.AccountWishListFragment;

public class AccountPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private final static int no_of_pages = 4;

    public AccountPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public int getCount() {
        return no_of_pages;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Fragment.instantiate(context, AccountInfoFragment.class.getName());
            case 1:
                return Fragment.instantiate(context, AccountPurchaseHistoryFragment.class.getName());
            case 2:
                return Fragment.instantiate(context, AccountWishListFragment.class.getName());
            case 3:
                return Fragment.instantiate(context, AccountEditInfoFragment.class.getName());
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Account Info";
            case 1:
                return "Purchases";
            case 2:
                return "Wishlist";
            case 3:
                return "Edit Info";
            default:
                return super.getPageTitle(position);
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }
}
