package com.example.hitechmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.hitechmart.base.BaseActivity;
import com.example.hitechmart.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends BaseActivity<ActivityRegistrationBinding> {
    @Override
    protected ActivityRegistrationBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityRegistrationBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
