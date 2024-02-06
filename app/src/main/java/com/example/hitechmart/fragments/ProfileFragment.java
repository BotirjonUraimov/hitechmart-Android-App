package com.example.hitechmart.fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.hitechmart.base.BaseFragment;
import com.example.hitechmart.databinding.FragmentProfileBinding;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {
    @Override
    protected FragmentProfileBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentProfileBinding.inflate(inflater, parent, toAttach);
    }


}
