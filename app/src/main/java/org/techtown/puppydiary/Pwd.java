package org.techtown.puppydiary;

import android.content.Intent;
import android.drm.DrmStore;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.puppydiary.accountmenu.MoneyTab;
import org.techtown.puppydiary.calendarmenu.CalendarTab;
import org.techtown.puppydiary.kgmenu.KgTab;

public class Pwd extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);

        //MoneyEdit.context = getApplicationContext();
        actionBar = getSupportActionBar();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffD6336B));
        getSupportActionBar().setTitle("댕댕이어리");
        actionBar.setIcon(R.drawable.white_puppy);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        TextView textView = findViewById(R.id.textView);
        SpannableString content = new SpannableString("내 정보 수정");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);

        Button calen = findViewById(R.id.calendar);
        calen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_calendar = new Intent(getApplicationContext(), CalendarTab.class); //일단 바로 검색결과 띄음
                startActivity(intent_calendar);
            }
        });

        Button kg = findViewById(R.id.kg);
        kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_kg = new Intent(getApplicationContext(), KgTab.class); //일단 바로 검색결과 띄음
                startActivity(intent_kg);
            }
        });

        Button money = findViewById(R.id.account);
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_money = new Intent(getApplicationContext(), MoneyTab.class); //일단 바로 검색결과 띄음
                startActivity(intent_money);
            }
        });

        Button puppy = findViewById(R.id.puppy);
        puppy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_puppy = new Intent(getApplicationContext(), MypuppyTab.class); //일단 바로 검색결과 띄음
                startActivity(intent_puppy);
            }
        });

        Button set_button = findViewById(R.id.pwd_finish);
        set_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SetPuppy.class);
                startActivity(intent);
            }
        });



        // 완료 버튼 누르면 새로운 비밀번호 확인함.

        // 기존 비밀번호 맞는지 확인하고 새로운 비밀번호로 업데이트 하는 거 적어야함

        final EditText old_pwd = findViewById(R.id.old_pwd);
        final EditText pwd_new = findViewById(R.id.new_pwd);
        final EditText pwd_ck = findViewById(R.id.new_chk);


        Button new_fsh = findViewById(R.id.pwd_finish);
        new_fsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String old1 = old_pwd.getText().toString();
                String new1 = pwd_new.getText().toString();
                String new2 = pwd_ck.getText().toString();

                if ( old1.equals("") ){
                    Toast.makeText(getApplicationContext(), "기존 비밀번호를 입력하세요", Toast.LENGTH_LONG).show();
                }
                else if ( new1.equals("") ) {
                    Toast.makeText(getApplicationContext(), "새로운 비밀번호를 입력하세요", Toast.LENGTH_LONG).show();
                }
                else if ( new2.equals("") ) {
                    Toast.makeText(getApplicationContext(), "새로운 비밀번호 확인칸을 입력하세요", Toast.LENGTH_LONG).show();
                }
                else{
                    if (new1.equals(new2)) {
                        Intent intent = new Intent(getApplicationContext(), MypuppyTab.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "새로운 비밀번호와 비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
}