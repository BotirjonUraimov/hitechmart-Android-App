package com.example.hitechmart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.example.hitechmart.base.BaseActivity;
import com.example.hitechmart.databinding.ActivityMainBinding;
import com.example.hitechmart.fragments.CartFragment;
import com.example.hitechmart.fragments.HomeFragment;
import com.example.hitechmart.fragments.ProductsFragment;
import com.example.hitechmart.fragments.ProfileFragment;
import com.example.hitechmart.util.Tab;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private Tab currentTab;
    private HomeFragment homeFragment;
    private ProductsFragment productsFragment;
    private CartFragment cartFragment;
    private ProfileFragment profileFragment;

    @Override
    protected ActivityMainBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentTab = Tab.HOME;
        onNavigationTabSelected(currentTab);
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.homeTab) {
                    onNavigationTabSelected(Tab.HOME);
                } else if (itemId == R.id.productsTab) {
                    onNavigationTabSelected(Tab.PRODUCTS);
                } else if (itemId == R.id.cartTab) {
                    onNavigationTabSelected(Tab.CART);
                } else if (itemId == R.id.profileTab) {
                    onNavigationTabSelected(Tab.PROFILE);
                }

                return true;
            }
        });

    }

    private void onNavigationTabSelected(Tab tab) {

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(currentTab.getTag());

        if (fragment != null)
            getSupportFragmentManager().beginTransaction().hide(fragment).commit();
        currentTab = tab;
        switch (tab) {

            case HOME:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, homeFragment, tab.getTag()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(homeFragment).commit();
                }

                setTitle("Home");
                break;

            case PRODUCTS:

                if (productsFragment == null) {
                    productsFragment = new ProductsFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, productsFragment, tab.getTag()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(productsFragment).commit();
                }
                setTitle("Products");
                break;

            case CART:

                if (cartFragment == null) {
                    cartFragment = new CartFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, cartFragment, tab.getTag()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(cartFragment).commit();
                }

                setTitle("Cart");

                break;

            case PROFILE:

                if (profileFragment == null) {
                    profileFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, profileFragment, tab.getTag()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(profileFragment).commit();
                }
                setTitle("Profile");
                break;
        }
    }


}