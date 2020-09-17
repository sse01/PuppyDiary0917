package org.techtown.puppydiary;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.puppydiary.network.Response.SignupResponse;
import org.techtown.puppydiary.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        actionBar = getSupportActionBar();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffD6336B));
        getSupportActionBar().setTitle("댕댕이어리");
        actionBar.setIcon(R.drawable.white_puppy);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        // 중복 확인 버튼 눌렀을 때 (작성해야함)
        Button btn_check = findViewById(R.id.btn_emailcheck);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        // 회원가입 버튼 눌렀을 때 (작성해야함)
        Button btn_sign = findViewById(R.id.btn_signup);
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
/*
    public boolean callEmailAlreadyCheck(String email) {
        boolean isRight = false;

        //Retrofit 호출
        SignupResponse signupResponse = new SignupResponse(email);
        Call<SignupResponse> call = RetrofitClient.getApiService().usersignup(signupResponse);
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (!response.isSuccessful()) {
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                SignupResponse checkAlready = response.body();
                Log.d("연결이 성공적 : ", response.body().toString());
                if (signupResponse.getEmail() == "can use this email") {
                    Log.d("중복검사: ", "중복된 이메일이 아닙니다");
                    signupResponse.setRight(true);
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
            }
        });

        return signupResponse.isRight();
    }
   */

}
