package com.khanstech.shop.klikdubai.activities;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.khanstech.shop.klikdubai.R;
import com.khanstech.shop.klikdubai.fragments.AccountFragment;
import com.khanstech.shop.klikdubai.fragments.AllCategoriesFragment;
import com.khanstech.shop.klikdubai.fragments.CartFragment;
import com.khanstech.shop.klikdubai.fragments.CompareItemsFragment;
import com.khanstech.shop.klikdubai.fragments.ContactUsFragment;
import com.khanstech.shop.klikdubai.fragments.HomeFragment;
import com.khanstech.shop.klikdubai.fragments.RegisterCustomerFragment;
import com.khanstech.shop.klikdubai.fragments.RegisterVendorFragment;
import com.khanstech.shop.klikdubai.fragments.ReturnPolicyFragment;
import com.khanstech.shop.klikdubai.utils.DialogHelper;
import com.khanstech.shop.klikdubai.utils.ExceptionHandler;
import com.khanstech.shop.klikdubai.utils.FragmentHelper;
import com.khanstech.shop.klikdubai.utils.PhoneFunctionality;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    public MenuItem action_cart, action_search;

    private static boolean APP_EXIT = false;
    private SearchView searchView;
    private SearchManager searchManager;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private MainActivity mainActivity;
    private TextView cart_qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));

        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainActivity = MainActivity.this;

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                //final HomeFragment homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(getString(R.string.home_tag));
                final CartFragment cartFragment = (CartFragment) fragmentManager.findFragmentByTag(getString(R.string.cart_tag));
                final AccountFragment accountFragment = (AccountFragment) fragmentManager.findFragmentByTag(getString(R.string.account_tag));
                if ((cartFragment != null && cartFragment.isVisible()) ||
                        (accountFragment != null && accountFragment.isVisible())) {
                    toggle.setDrawerIndicatorEnabled(false);
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                } else {
                    toggle.setDrawerIndicatorEnabled(true);
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                }
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //region Navigation Header Items
        View nav_layout = navigationView.getHeaderView(0);
        View btn_account = nav_layout.findViewById(R.id.btn_account);
        View link_login = nav_layout.findViewById(R.id.link_login);

        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = fragmentManager.beginTransaction();
                FragmentHelper.replaceFragment(MainActivity.this, new AccountFragment(), getString(R.string.account_tag));
            }
        });
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START);
                loginCustomer();
            }
        });
        //endregion

        // When activity starts first time
        if (savedInstanceState == null) {

            // Display Home fragment
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment(), getString(R.string.home_tag));
            transaction.commit();

            // Auto User Log-In
            //if (PreferenceHelper.getInt(this, PreferenceHelper.KEEP_LOGGED) == 1)
            //new UserLoginTask(this, PreferenceHelper.getString(this, PreferenceHelper.LOGGED_EMAIL),
            //        PreferenceHelper.getString(this, PreferenceHelper.LOGGED_PASS),
            //        PreferenceHelper.getString(this, PreferenceHelper.LOGGED_USER_TYPE), 1).execute();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // Control HomeFragment
            final HomeFragment homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(getString(R.string.home_tag));
            if (homeFragment != null && homeFragment.isVisible()) {
                if (!APP_EXIT) {
                    // App Exit Warning
                    PhoneFunctionality.makeToast(this, getString(R.string.exit_app_message));
                    APP_EXIT = true;
                } else {
                    exitApp();
                }
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        action_cart = menu.findItem(R.id.action_cart);
        action_search = menu.findItem(R.id.action_search);
        setupSearch(action_search);
        setupCart(action_cart);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                navHome();
                break;
            case R.id.nav_register:
                navRegister();
                break;
            case R.id.nav_compare:
                navCompareItems();
                break;
            case R.id.nav_all_cat:
                navAllCategories();
                break;
            case R.id.nav_language:
                PhoneFunctionality.makeToast(this, "Language Feature Coming Soon!");
                return false;
            case R.id.nav_contact_us:
                navContactUs();
                break;
            case R.id.nav_return_policy:
                navReturnPolicy();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    //region Toolbar methods
    public void setToolbarTitle(String msg) {
        toolbar.setTitle(msg);
    }

    public void setToolbarSubTitle(String msg) {
        toolbar.setSubtitle(msg);
    }
    // endregion

    private void setupSearch(MenuItem menuItem) {
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(
                new ComponentName(this, MainActivity.class)));
        searchView.setIconifiedByDefault(false);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(
                new ComponentName(this, SearchActivity.class)));
    }

    private void setupCart(MenuItem menuItem) {
        View action_cart_view = menuItem.getActionView();
        action_cart_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentHelper.replaceFragment(mainActivity, new CartFragment(),
                        getString(R.string.cart_tag));
            }
        });
        cart_qty = (TextView) action_cart_view.findViewById(R.id.cart_qty);
    }

    public void updateCart(int quantity) {
        if (cart_qty != null) cart_qty.setText(String.valueOf(quantity));
        else PhoneFunctionality.makeToast(mainActivity, "Cart failed to update");
    }

    private void loginCustomer() {
        Dialog dialog = DialogHelper.createCustomDialog(this, R.layout.dialog_signin, Gravity.NO_GRAVITY);
        dialog.show();
    }

    private void navHome() {
        final HomeFragment homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(getString(R.string.home_tag));
        if (homeFragment == null) startActivity(new Intent(mainActivity, MainActivity.class));
        else while (!homeFragment.isVisible()) fragmentManager.popBackStackImmediate();
    }

    private void navRegister() {
        final Dialog dialog = DialogHelper.createCustomDialog(this, R.layout.dialog_menu_register, Gravity.NO_GRAVITY, false);
        dialog.show();

        dialog.findViewById(R.id.menu_reg_customer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentHelper.replaceFragment(mainActivity, new RegisterCustomerFragment(),
                        getString(R.string.reg_customer_tag));
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.menu_reg_vendor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentHelper.replaceFragment(mainActivity, new RegisterVendorFragment(),
                        getString(R.string.reg_vendor_tag));
                dialog.dismiss();
            }
        });
    }

    private void navCompareItems() {
        FragmentHelper.replaceFragment(mainActivity, new CompareItemsFragment(),
                getString(R.string.compare_items_tag));
    }

    private void navContactUs() {
        FragmentHelper.replaceFragment(mainActivity, new ContactUsFragment(),
                getString(R.string.contact_us_tag));
    }

    private void navAllCategories() {
        FragmentHelper.replaceFragment(mainActivity, new AllCategoriesFragment(),
                getString(R.string.all_cat_tag));
    }

    private void navReturnPolicy() {
        FragmentHelper.replaceFragment(mainActivity, new ReturnPolicyFragment(),
                getString(R.string.return_policy_tag));
    }

    // Exit from Application
    public void exitApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
