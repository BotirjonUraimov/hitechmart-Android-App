package com.example.hitechmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.hitechmart.base.BaseActivity;
import com.example.hitechmart.databinding.ActivityChangedPasswordBinding;

public class changedPasswordActivity extends BaseActivity<ActivityChangedPasswordBinding> {
    @Override
    protected ActivityChangedPasswordBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityChangedPasswordBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(changedPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
