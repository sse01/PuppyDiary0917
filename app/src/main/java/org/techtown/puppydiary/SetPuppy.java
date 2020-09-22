package org.techtown.puppydiary;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.puppydiary.calendarmenu.CalendarTab;
import org.techtown.puppydiary.network.Data.RegisterData;
import org.techtown.puppydiary.network.Response.RegisterResponse;
import org.techtown.puppydiary.network.RetrofitClient;
import org.techtown.puppydiary.network.ServiceApi;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetPuppy extends AppCompatActivity {
    private  static final int REQUEST_CODE = 0;
    de.hdodenhof.circleimageview.CircleImageView imageView;
    ActionBar actionBar;
    private ServiceApi service;

    EditText b_day;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpuppy);

        actionBar = getSupportActionBar();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffD6336B));
        getSupportActionBar().setTitle("댕댕이어리");
        actionBar.setIcon(R.drawable.white_puppy) ;
        actionBar.setDisplayUseLogoEnabled(true) ;
        actionBar.setDisplayShowHomeEnabled(true) ;


        HashMap<String, String>header = new HashMap<>();
        service = RetrofitClient.getClient().create(ServiceApi.class);

        TextView textView = findViewById(R.id.textView);
        SpannableString content = new SpannableString("우리 집 댕댕이는요");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);

        Calendar cal = Calendar.getInstance();
        b_day = findViewById(R.id.bd_input);

        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);


        b_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        SetPuppy.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month + 1;
                                String date = year + "/ " + month + "/ " + day;
                                b_day.setText(date);
                            }
                        }, year, month, day);
                dialog.show();
            }
        });


        imageView = findViewById(R.id.profile);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        final EditText puppy_name = findViewById(R.id.name_input);
        final EditText age_ = findViewById(R.id.age_input);
        final EditText birth_ = findViewById(R.id.bd_input);
        final RadioButton option_male = (RadioButton) findViewById(R.id.male);
        final RadioButton option_female = (RadioButton) findViewById(R.id.female);


        Button button = findViewById(R.id.finish_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( !(puppy_name.getText().equals("")) && !(age_.getText().equals("")) ) {
                    String puppyname = puppy_name.getText().toString();
                    Integer age = Integer.parseInt("" + age_.getText());
                    String birth = birth_.getText().toString();
                    int gender = 0; // 1이 남자, 2가 여자

                    if (option_male.isChecked() && (!option_female.isChecked())) {
                        gender = 1;
                    } else if ((!option_male.isChecked()) && option_female.isChecked()) {
                        gender = 2;
                    }

                    infoInputCheck(new RegisterData(puppyname, age, birth, gender));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "인자가 입력되지 않았습니다", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    imageView.setImageBitmap(img);
                } catch (Exception e) {

                }
            }
            else if(resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }



    private void infoInputCheck(final RegisterData data){
        service.registerinfo(data).enqueue(new Callback<RegisterResponse>() {

            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse result = response.body();
                Toast.makeText(SetPuppy.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if(result.getMessage().equals("강아지 정보 등록 성공")){
                    Intent intent = new Intent(getApplicationContext(), CalendarTab.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(SetPuppy.this, "강아지 정보 등록 에러 발생", Toast.LENGTH_SHORT).show();
                //Log.e("강아지 정보 등록 에러 발생", t.getMessage());
            }
        });
    }


}