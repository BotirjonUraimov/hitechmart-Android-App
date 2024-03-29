package com.example.hitechmart;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.hitechmart.base.BaseActivity;
import com.example.hitechmart.databinding.ActivityNewPasswordBinding;
import com.example.hitechmart.model.ResetRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPasswordActivity extends BaseActivity<ActivityNewPasswordBinding> {
    @Override
    protected ActivityNewPasswordBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityNewPasswordBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String access_token = intent.getStringExtra("access_token");




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

                String newPassword = binding.etPassword.getText().toString();
                String conformNewPassword = binding.etConfirmPassword.getText().toString();

                if(newPassword.equals(conformNewPassword) && !newPassword.isEmpty() && !conformNewPassword.isEmpty()) {

                    ResetRequest resetRequest = new ResetRequest(newPassword, conformNewPassword);
                    Log.d("resetRequest", new Gson().toJson(resetRequest));

                    Call<JsonObject> call = mainApi.resetPassword("Bearer " + intent.getStringExtra("access_token"), resetRequest);

                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if(response.isSuccessful()){
                                Intent intent = new Intent(NewPasswordActivity.this, changedPasswordActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });



                }else {
                    binding.resetPasswordError.setVisibility(View.VISIBLE);
                }




            }
        });

        binding.ivHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the selected state
                v.setSelected(!v.isSelected());

                // Toggle password visibility
                if (v.isSelected()) {
                    // Show password
                    binding.etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    // Hide password
                    binding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

            }
        });

        binding.ivHideConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the selected state
                v.setSelected(!v.isSelected());

                // Toggle password visibility
                if (v.isSelected()) {
                    // Show password
                    binding.etConfirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    // Hide password
                    binding.etConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

            }
        });



    }
}
