package com.example.hitechmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.hitechmart.base.BaseActivity;
import com.example.hitechmart.databinding.ActivityNewPasswordBinding;

public class NewPasswordActivity extends BaseActivity<ActivityNewPasswordBinding> {
    @Override
    protected ActivityNewPasswordBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityNewPasswordBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewPasswordActivity.this, ConfirmEmailActivity.class);
                startActivity(intent);
            }
        });
        binding.moveToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewPasswordActivity.this, changedPasswordActivity.class);
                startActivity(intent);
            }
        });



    }
}
