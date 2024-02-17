package com.example.hitechmart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.hitechmart.adapter.IndicatorAdapter;
import com.example.hitechmart.base.BaseFragment;
import com.example.hitechmart.databinding.FragmentBannerBinding;
import com.example.hitechmart.databinding.FragmentImageBinding;
import com.example.hitechmart.model.Banner;
import com.example.hitechmart.model.ProductImage;

public class ImageFragment extends BaseFragment<FragmentImageBinding> {
    @Override
    protected FragmentImageBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentImageBinding.inflate(inflater, parent, toAttach);
    }

    private String imageUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        imageUrl = (String) bundle.getString("imageUrl");


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(binding.image).load(imageUrl).into(binding.image);
    }
}
