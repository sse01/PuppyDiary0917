package org.techtown.puppydiary.accountmenu;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.puppydiary.R;
import org.techtown.puppydiary.network.Data.AccountUpdateData;
import org.techtown.puppydiary.network.Response.AccountUpdateResponse;
import org.techtown.puppydiary.network.RetrofitClient;
import org.techtown.puppydiary.network.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//moneyTab item click 시 나오는 수정 삭제 화면
public class MoneyEdit extends AppCompatActivity {

    private  static Context context;
    ActionBar actionBar;

    TextView tv_date;
    EditText et_price;
    EditText et_memo;

    int getyear = 0;
    int getmonth = 0;
    int getday = 0;
    int price = 0;
    String memo = null;

    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_edit);

        MoneyEdit.context = getApplicationContext();
        actionBar = getSupportActionBar();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffD6336B));
        getSupportActionBar().setTitle("댕댕이어리");
        actionBar.setIcon(R.drawable.white_puppy) ;
        actionBar.setDisplayUseLogoEnabled(true) ;
        actionBar.setDisplayShowHomeEnabled(true) ;

        service = RetrofitClient.getClient().create(ServiceApi.class);

        final Intent intent = new Intent(getIntent());
        getyear = intent.getIntExtra("year", 0);
        getmonth = intent.getIntExtra("month", 0);
        getday = intent.getIntExtra("day", 0);
        memo = intent.getStringExtra("memo");
        price = intent.getIntExtra("price", 0);


        final String getdate = getyear + "/" + getmonth + "/" + getday;


        et_price = (EditText) findViewById(R.id.price_data);
        et_price.setText(Integer.toString(price));

        et_memo = (EditText) findViewById(R.id.memo_data);
        et_memo.setText(memo);

        // 날짜 고정 : 수정 불가
        tv_date = findViewById(R.id.date_data);
        tv_date.setText(getdate);


        Button close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price = Integer.parseInt(et_price.getText().toString());
                //price = et_price.getText().toString();
                memo = et_memo.getText().toString();
                AccountUpdate(new AccountUpdateData(getyear, getmonth, getday, memo, price));
            }
        });

        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //dbHelper.delete(useridx, position, getyear, getmonth, getday);
                Intent intent_after = new Intent(MoneyEdit.this, MoneyTab.class);
                intent_after.putExtra("after_year", getyear);
                intent_after.putExtra("after_month", getmonth);
                intent_after.putExtra("after_day", getday);
                startActivity(intent_after);
                Toast toastView = Toast.makeText(getApplicationContext(), "삭제되었습니다" , Toast.LENGTH_LONG);
                toastView.show();
            }
        });
    }

    private void AccountUpdate(final AccountUpdateData data){
        service.accountupdate(data).enqueue(new Callback<AccountUpdateResponse>() {
            @Override
            public void onResponse(Call<AccountUpdateResponse> call, Response<AccountUpdateResponse> response) {
                AccountUpdateResponse result = response.body();
                //List<AccountUpdateResponse.AccountUpdate> my = result.getData();
                //System.out.println("accountUPDATE!!! : " + my.get(0).getMonth() + my.get(0).getDate());
                Toast.makeText(MoneyEdit.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if(result.getSuccess() == true){
                    Intent intent_after = new Intent(MoneyEdit.this, MoneyTab.class);
                    intent_after.putExtra("after_year", getyear);
                    intent_after.putExtra("after_month", getmonth);
                    intent_after.putExtra("after_day", getday);
                    startActivity(intent_after);
                }
            }

            @Override
            public void onFailure(Call<AccountUpdateResponse> call, Throwable t) {
                Toast.makeText(MoneyEdit.this, "가계부 업데이트 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("가계부 업데이트 에러 발생", t.getMessage());
            }
        });
    }

}
