package com.example.hitechmart;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;

import com.example.hitechmart.base.BaseActivity;
import com.example.hitechmart.databinding.ActivityLoginBinding;
import com.example.hitechmart.model.Token;
import com.example.hitechmart.model.User;
import com.example.hitechmart.remote.MainApi;
import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    @Override
    protected ActivityLoginBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityLoginBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.moveToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        binding.etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                boolean isValidEmail = isValidEmail(s);
                binding.verifyEmail.setVisibility(isValidEmail ? View.VISIBLE : View.INVISIBLE);
                binding.invalidEmail.setVisibility(!isValidEmail ? View.VISIBLE : View.INVISIBLE);

            }
        });

        binding.resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });


        binding.hideShowPassword.setOnClickListener(new View.OnClickListener() {
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

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()) {

                    User user = new User();
                    user.setEmail(email);
                    user.setPassword(password);
                    Log.d("User", new Gson().toJson(user));

                    Call<User> call = mainApi.login(user);


                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User authUser = response.body();

                            if(response.isSuccessful() && authUser!=null) {
                                preferenceManager.setValue("isLogin", true);
                                preferenceManager.setValue("access_token", authUser.getAccess_token());
                                preferenceManager.setValue("id", authUser.getId());
                                preferenceManager.setValue("email", authUser.getEmail());
                                preferenceManager.setValue("first_name", authUser.getFirst_name());
                                preferenceManager.setValue("last_name", authUser.getLast_name());
                                preferenceManager.setValue("phone_number", authUser.getPhone_number());
                                preferenceManager.setValue("address", authUser.getAddress());
                                preferenceManager.setValue("refresh_token", authUser.getRefresh_token());


                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);

                            } else {
                                binding.signInError.setVisibility(View.VISIBLE);
                            }


                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });


                }
            }
        });

    }

    public static boolean isValidEmail(Editable email) {
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
