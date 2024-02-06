package com.example.hitechmart.fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.hitechmart.base.BaseFragment;
import com.example.hitechmart.databinding.FragmentCartBinding;

public class CartFragment extends BaseFragment<FragmentCartBinding> {
    @Override
    protected FragmentCartBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentCartBinding.inflate(inflater, parent, toAttach);
    }
}
