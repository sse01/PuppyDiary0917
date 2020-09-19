package org.techtown.puppydiary.kgmenu;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.puppydiary.Login;
import org.techtown.puppydiary.R;
import org.techtown.puppydiary.calendarmenu.CalendarTab;
import org.techtown.puppydiary.network.Data.KgupdateData;
import org.techtown.puppydiary.network.Response.KgupdateResponse;
import org.techtown.puppydiary.network.Response.SigninResponse;
import org.techtown.puppydiary.network.RetrofitClient;
import org.techtown.puppydiary.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.techtown.puppydiary.kgmenu.KgTab.kg_month;
import static org.techtown.puppydiary.kgmenu.KgTab.year_kg;

public class KgPopup extends AppCompatActivity {
    String monthname;
    private  static Context context;
    ActionBar actionBar;
    //public static double puppykg; public으로 설정을 해주어야 다른 클래스에서 사용 가능
    public static double [][] puppykg = new double[15][12];
    String kgStr;
    EditText weight;
    Button okay;
    Button close;

    private ServiceApi service;
/*
    MainActivity mainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //puppykg = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_kg_upload);

        // MoneyEdit.context = getApplicationContext();
        actionBar = getSupportActionBar();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffD6336B));
        getSupportActionBar().setTitle("댕댕이어리");
        actionBar.setIcon(R.drawable.white_puppy) ;
        actionBar.setDisplayUseLogoEnabled(true) ;
        actionBar.setDisplayShowHomeEnabled(true) ;

        service = RetrofitClient.getClient().create(ServiceApi.class);

        monthname = kg_month;
        TextView Month = (TextView) findViewById(R.id.kgmonth);
        Month.setText(monthname);  //클릭한 달의 이름으로 setText

        okay = findViewById(R.id.kg_confirm);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weight = (EditText)findViewById(R.id.kg_weight); //weight edittext 가져오기, kgTab에서 사용해야 하는 것이므로 이 클래스에서는 public 설정
                kgStr = weight.getText().toString();

                UpdateKg(new KgupdateData(year_kg, monthname, kgStr));
                //button.setBackground(R.drawable.button_pressed);
                okay.setBackgroundResource(R.drawable.button_pressed); //확인 버튼 클릭시 color changed -> 다른 버튼들에도 추가해주면 좋음
                Intent intent_kgconfirm = new Intent(getApplicationContext(), KgTab.class);
                startActivity(intent_kgconfirm);

                //만약 jan의 kg 이 3.5 였다면 tostring으로 "3.5"=kgStr

                if(monthname.equals("January")) {
                    puppykg[year_kg][11] = Double.parseDouble(kgStr);
                    //double.parsedouble을 이용해서 string이었던 "3.5"를 double로 형변환시켜주어 저장 -> 저장 후 kgTab으로 가져감 (이 코드는 kgTab에 있음)
                }
                else if(monthname.equals("February")) {
                    puppykg[year_kg][10] = Double.parseDouble(kgStr);
                }
                else if(monthname.equals("March")) {
                    puppykg[year_kg][9] = Double.parseDouble(kgStr);
                }
                else if(monthname.equals("April")) {
                    puppykg[year_kg][8] = Double.parseDouble(kgStr);
                }
                else if(monthname.equals("May")) {
                    puppykg[year_kg][7] = Double.parseDouble(kgStr);
                }
                else if(monthname.equals("June")) {
                    puppykg[year_kg][6] = Double.parseDouble(kgStr);
                }
                else if(monthname.equals("July")) {
                    puppykg[year_kg][5] = Double.parseDouble(kgStr);
                }
                else if(monthname.equals("August")) {
                    puppykg[year_kg][4] = Double.parseDouble(kgStr);
                }
                else if(monthname.equals("September")) {
                    puppykg[year_kg][3] = Double.parseDouble(kgStr);
                }
                else if(monthname.equals("October")) {
                    puppykg[year_kg][2] = Double.parseDouble(kgStr);
                }
                else if(monthname.equals("November")) {
                    puppykg[year_kg][1] = Double.parseDouble(kgStr);
                }
                else if(monthname.equals("December")) {
                    puppykg[year_kg][0] = Double.parseDouble(kgStr);
                }

                //Log.d("kg1", "kg1" + kgStr);
                //Log.d("kg2", "kg2" + ((EditText)findViewById(R.id.kg_weight)).getText().toString());
                //Log.d("kgpopup", "kgpopup ." + puppykg);
            }
        });

        close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_kgclose = new Intent(getApplicationContext(), KgTab.class); //일단 바로 검색결과 띄음
                startActivity(intent_kgclose);
            }
        });
    }

    private void UpdateKg(KgupdateData data){
        service.kgupdate(data).enqueue(new Callback<KgupdateResponse>() {

            @Override
            public void onResponse(Call<KgupdateResponse> call, Response<KgupdateResponse> response) {
                KgupdateResponse result = response.body();
                Toast.makeText(KgPopup.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                if(result.getSuccess() == true){
                    Intent intent_start = new Intent(getApplicationContext(), KgTab.class);
                    startActivityForResult(intent_start, 2000);
                }
            }

            @Override
            public void onFailure(Call<KgupdateResponse> call, Throwable t) {
                Toast.makeText(KgPopup.this, "체중 업데이트 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("체중 업데이트 에러 발생", t.getMessage());
            }
        });
    }
}
