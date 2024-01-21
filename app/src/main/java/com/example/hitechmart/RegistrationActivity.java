package com.example.hitechmart;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.hitechmart.base.BaseActivity;
import com.example.hitechmart.databinding.ActivityRegistrationBinding;
import com.example.hitechmart.model.User;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        binding.btnAcceptCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());

            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = binding.etFirstName.getText().toString();
                String lastName = binding.etLastname.getText().toString();
                String email = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();
                String confirmPassword = binding.etConfirmPassword.getText().toString();
                String address = binding.teAddress.getText().toString();
                String phoneNumber = binding.etPhoneNumber.getText().toString();
                String addressDetails = binding.teAddressDetails.getText().toString();
                String postCode = binding.etPostCode.getText().toString();

                //checking input is empty
                if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !password.isEmpty() && !postCode.isEmpty()
                && !confirmPassword.isEmpty() && !address.isEmpty() && !addressDetails.isEmpty() && !phoneNumber.isEmpty()) {

                        if (password.equals(confirmPassword)){
                            User user = new User();
                            user.setFirst_name(firstName);
                            user.setLast_name(lastName);
                            user.setEmail(email);
                            user.setPassword(password);
                            user.setAddress(address + " " + addressDetails + ", " + postCode);
                            user.setPhone_number(phoneNumber);

                            Log.d("User", new Gson().toJson(user));

                            Call<User> call = mainApi.createUser(user);

                            call.enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    User newUser = response.body();

                                    if(response.isSuccessful() && newUser != null) {
                                        preferenceManager.setValue("isLogin", true);
                                        preferenceManager.setValue("access_token", newUser.getAccess_token());
                                        preferenceManager.setValue("id", newUser.getId());
                                        preferenceManager.setValue("email", newUser.getEmail());
                                        preferenceManager.setValue("first_name", newUser.getFirst_name());
                                        preferenceManager.setValue("last_name", newUser.getLast_name());
                                        preferenceManager.setValue("phone_number", newUser.getPhone_number());
                                        preferenceManager.setValue("address", newUser.getAddress());
//                                        preferenceManager.setValue("refresh_token", newUser.getRefresh_token());

                                        CharSequence text = "Registration is completed";
                                        int duration = Toast.LENGTH_LONG;
                                        Toast toast = Toast.makeText(RegistrationActivity.this, text, duration);
                                        toast.show();


                                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Log.d("User", "Error on receiving response from API");
                                    }

                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {

                                }
                            });
                        }else {
                            binding.passwordError.setVisibility(View.VISIBLE);
                        }


                } else {
                    binding.signUpError.setVisibility(View.VISIBLE);
                }



            }
        });

    }
}
