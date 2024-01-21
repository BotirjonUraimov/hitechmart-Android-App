package com.example.hitechmart.base;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.example.hitechmart.remote.MainApi;
import com.example.hitechmart.util.PreferenceManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseActivity<T extends ViewBinding>  extends AppCompatActivity {


    private static String BASE_URL = "http://dev-api.shoppingmall.beknumonov.com";
    protected T binding;

    public PreferenceManager preferenceManager;

    public MainApi mainApi;

    protected abstract T inflateViewBinding(LayoutInflater inflater);



    //for using later
    public boolean hasActionBar() {
        return false;
    }

    public boolean backButtonClickActivated() {
        return true;
    }

    public boolean hasBackButton() {
        return false;
    }

    public int getBackButtonIcon() {
        return 0;
    }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // har bir activity uchun T tiofadagi ViewBinding qaytaradi. eslatma: Hamma ViewBindingslar ViewBindingdan extended qilingan
        binding = inflateViewBinding(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceManager = PreferenceManager.getInstance(this);




        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient().newBuilder().
                addInterceptor(logging).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mainApi = retrofit.create(MainApi.class);







    }
}
