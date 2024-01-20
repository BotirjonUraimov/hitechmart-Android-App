package com.example.hitechmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.hitechmart.base.BaseActivity;
import com.example.hitechmart.databinding.ActivityConfirmEmailBinding;

public class ConfirmEmailActivity extends BaseActivity<ActivityConfirmEmailBinding> {
    @Override
    protected ActivityConfirmEmailBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityConfirmEmailBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmEmailActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmEmailActivity.this, NewPasswordActivity.class);
                startActivity(intent);
            }
        });

    }
}
