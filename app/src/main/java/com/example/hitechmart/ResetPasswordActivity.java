package com.example.hitechmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.hitechmart.base.BaseActivity;
import com.example.hitechmart.databinding.ActivityRegistrationBinding;
import com.example.hitechmart.databinding.ActivityResetPasswordBinding;

public class ResetPasswordActivity extends BaseActivity<ActivityResetPasswordBinding> {


    @Override
    protected ActivityResetPasswordBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityResetPasswordBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.moveToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPasswordActivity.this, ConfirmEmailActivity.class);
                startActivity(intent);
            }
        });
    }
}
