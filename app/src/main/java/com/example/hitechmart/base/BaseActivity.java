package com.example.hitechmart.base;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<T extends ViewBinding>  extends AppCompatActivity {

    protected T binding;

    protected abstract T inflateViewBinding(LayoutInflater inflater);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // har bir activity uchun T tiofadagi ViewBinding qaytaradi. eslatma: Hamma ViewBindingslar ViewBindingdan extended qilingan
        binding = inflateViewBinding(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
