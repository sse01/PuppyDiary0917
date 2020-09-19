package org.techtown.puppydiary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.puppydiary.accountmenu.DBHelper_money;
import org.techtown.puppydiary.calendarmenu.CalendarTab;
import org.techtown.puppydiary.calendarmenu.DBHelper_cal;
import org.techtown.puppydiary.kgmenu.DBHelper_kg;
import org.techtown.puppydiary.network.Data.SigninData;
import org.techtown.puppydiary.network.Response.SigninResponse;
import org.techtown.puppydiary.network.RetrofitClient;
import org.techtown.puppydiary.network.ServiceApi;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    ActionBar actionBar;
    private ServiceApi service;
    private TextView emailview;
    private TextView passwordview;
    String email;
    String password;

    public static String jwtToken = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        actionBar = getSupportActionBar();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffD6336B));
        getSupportActionBar().setTitle("댕댕이어리");
        actionBar.setIcon(R.drawable.white_puppy);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        final Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        emailview = (TextView) findViewById(R.id.tv_emaillogin);
        passwordview = (TextView) findViewById(R.id.tv_passwordlogin);


        // 로그인 누르면 다음 화면으로 넘어가게
        Button button_lgn = findViewById(R.id.btn_login);
        button_lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailview.getText().toString();
                password = passwordview.getText().toString();
                startLogin(new SigninData(email, password));
            }
        });

        TextView tv_findpwd = findViewById(R.id.tv_findpassword);
        tv_findpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_findpwd = new Intent(getApplicationContext(), Findpwd.class);
                startActivityForResult(intent_findpwd, 2000);
            }
        });

        // 회원가입 누르면 회원 가입으로 넘어가게
        TextView tv_join = findViewById(R.id.tv_join);
        tv_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_join = new Intent(getApplicationContext(), Signup.class);
                startActivityForResult(intent_join, 2000);
            }
        });
    }

    private void startLogin(SigninData data){
        service.usersignin(data).enqueue(new Callback<SigninResponse>() {
            @Override
            public void onResponse(Call<SigninResponse> call, Response<SigninResponse> response) {
                SigninResponse result = response.body();
                Toast.makeText(Login.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                //로그인 성공
                if(result.getSuccess()==true){

                    jwtToken = result.getJwtToken();

                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("TOKEN", jwtToken).apply();

                    //("Context-Type = application/

                    //int useridx = result.getUserIdx();

                    //result.save(getApplicationContext());

                    //DBHelper_user dbuser = new DBHelper_user(getApplicationContext(), "usertest.db", null, 1);

                    //각 db에 insert useridx
                    //dbuser.insert(useridx);

                    //달력 탭으로 시작
                    Intent intent_start = new Intent(getApplicationContext(), CalendarTab.class);
                    startActivityForResult(intent_start, 2000);
                }
            }

            @Override
            public void onFailure(Call<SigninResponse> call, Throwable t) {
                Toast.makeText(Login.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });
    }
}
