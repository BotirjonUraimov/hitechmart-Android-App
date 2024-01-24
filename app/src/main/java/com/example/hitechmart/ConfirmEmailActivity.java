package com.example.hitechmart;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hitechmart.base.BaseActivity;
import com.example.hitechmart.databinding.ActivityConfirmEmailBinding;
import com.example.hitechmart.model.VerifyCodeRequest;
import com.example.hitechmart.model.VerifyCodeResponse;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmEmailActivity extends BaseActivity<ActivityConfirmEmailBinding> {
    @Override
    protected ActivityConfirmEmailBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityConfirmEmailBinding.inflate(inflater);
    }



    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String code = intent.getStringExtra("verificationCode"); //this is a 4 digit number in string

        long duration = 2 * 60 * 1000;
        // Create CountDownTimer
        countDownTimer = new CountDownTimer(duration, 1000) { // Tick every 1 second
            public void onTick(long millisUntilFinished) {
                // Convert milliseconds to minutes and seconds
                long minutes = (millisUntilFinished / 1000) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;

                // Format and display in TextView
                String timeFormatted = String.format(Locale.getDefault(), "%02dm:%02ds", minutes, seconds);
                binding.tvTimer.setText("Send code again " + timeFormatted);
            }

            public void onFinish() {
                binding.tvTimer.setText("Send code again 00m:00s " + " Please try again");
                binding.btnVerify.setText("Send Again");
                // You can also handle the completion of the timer here
            }
        }.start();


        if (code != null && code.length() == 4) {
            for (int i = 0; i < code.length(); i++) {
                int index = i; // Need to declare final or effectively final for use in lambda
                new Handler().postDelayed(() -> {
                    char c = code.charAt(index);
                    switch (index) {
                        case 0:
                            binding.code1.setText(String.valueOf(c));
                            break;
                        case 1:
                            binding.code2.setText(String.valueOf(c));
                            break;
                        case 2:
                            binding.code3.setText(String.valueOf(c));
                            break;
                        case 3:
                            binding.code4.setText(String.valueOf(c));
                            break;
                    }
                }, 1000 * i); // Delay i seconds
            }
        }




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

                VerifyCodeRequest verifyCodeRequest = new VerifyCodeRequest();
                verifyCodeRequest.setEmail(intent.getStringExtra("email"));
                verifyCodeRequest.setCode(intent.getStringExtra("verificationCode"));


                String code1 = binding.code1.getText().toString();
                String code2 = binding.code2.getText().toString();
                String code3 = binding.code3.getText().toString();
                String code4 = binding.code4.getText().toString();

                Call<VerifyCodeResponse> call = mainApi.verifyCode(verifyCodeRequest);

                call.enqueue(new Callback<VerifyCodeResponse>() {
                    @Override
                    public void onResponse(Call<VerifyCodeResponse> call, Response<VerifyCodeResponse> response) {
                        VerifyCodeResponse verifyCodeResponse  = response.body();
                        if(response.isSuccessful()) {



                            Intent intent = new Intent(ConfirmEmailActivity.this, NewPasswordActivity.class);
                            intent.putExtra("access_token", verifyCodeResponse.getAccess_token());
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onFailure(Call<VerifyCodeResponse> call, Throwable t) {



                    }
                });





            }
        });

        binding.code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(binding.code1.getText().toString().length() == 1) {
                    binding.code2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(binding.code2.getText().toString().length() == 1) {
                    binding.code3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(binding.code3.getText().toString().length() == 1) {
                    binding.code4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(binding.code4.getText().toString().length() == 1) {
                    binding.btnVerify.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.tvUserEmail.setText(intent.getStringExtra("email") + " Code: " + intent.getStringExtra("verificationCode"));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Cancel the timer if the activity is destroyed
        }
    }
}
