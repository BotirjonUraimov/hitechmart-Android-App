package com.example.hitechmart;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.hitechmart.base.BaseActivity;
import com.example.hitechmart.databinding.ActivityRegistrationBinding;
import com.example.hitechmart.databinding.ActivityResetPasswordBinding;
import com.example.hitechmart.model.VerifyEmailResponse;
import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                String email = binding.etEmail.getText().toString();

                if(!email.isEmpty() && isValidEmail(email)) {
                    Log.d("Email", email);
                    Call<VerifyEmailResponse> call = mainApi.verifyEmail(email);
                    call.enqueue(new Callback<VerifyEmailResponse>() {
                        @Override
                        public void onResponse(Call<VerifyEmailResponse> call, Response<VerifyEmailResponse> response) {

                            if(response.isSuccessful()) {
                                VerifyEmailResponse verResponse = response.body();
                                Intent intent = new Intent(ResetPasswordActivity.this, ConfirmEmailActivity.class);
                                intent.putExtra("verificationCode", verResponse.getCode());
                                intent.putExtra("email", verResponse.getEmail());
                                startActivity(intent);
                            } else {
                                binding.verificationEmailError.setVisibility(View.VISIBLE);
                            }
                        }
                        @Override
                        public void onFailure(Call<VerifyEmailResponse> call, Throwable t) {

                        }
                    });

                }else {
                    binding.verificationEmailError.setText("Please input right email address");
                    binding.verificationEmailError.setVisibility(View.VISIBLE);

                }


            }
        });
    }

    public static boolean isValidEmail(String email) {
        // Define the email pattern
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compile the regex into a pattern
        Pattern pattern = Pattern.compile(emailRegex);

        // If the email is null return false
        if (email == null) {
            return false;
        }

        // Create a matcher for the input string
        Matcher matcher = pattern.matcher(email);

        // Return whether the email matches the pattern
        return matcher.matches();
    }
}
