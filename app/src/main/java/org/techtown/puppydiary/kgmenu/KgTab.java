package org.techtown.puppydiary.kgmenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.techtown.puppydiary.MypuppyTab;
import org.techtown.puppydiary.R;
import org.techtown.puppydiary.accountmenu.MoneyTab;
import org.techtown.puppydiary.calendarmenu.CalendarTab;
import org.techtown.puppydiary.network.Data.KgupdateData;
import org.techtown.puppydiary.network.Data.ShowKgData;
import org.techtown.puppydiary.network.Response.KgupdateResponse;
import org.techtown.puppydiary.network.Response.MyinfoResponse;
import org.techtown.puppydiary.network.Response.ShowKgResponse;
import org.techtown.puppydiary.network.RetrofitClient;
import org.techtown.puppydiary.network.ServiceApi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KgTab extends AppCompatActivity {
    private TextView tvDate;
    private  static Context context;
    ActionBar actionBar;
    Calendar mCal;
    public static String kg_month;
    public static int yearr; // kgPopUp으로 넘어가면서 넘겨줄거임.
    public int flag; //년도가 바뀌었는지 확인
    //double [] kgpuppy = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

    private ServiceApi service;

    float m1;
    float m2;
    float m3;
    float m4;
    float m5;
    float m6 = 0;
    float m7 = 0;
    float m8 = 0;
    float m9 = 0;
    float m10 = 0;
    float m11 = 0;
    float m12 = 0;

    BarDataSet ds;

    Button jan;
    Button feb;
    Button mar;
    Button apr;
    Button may;
    Button jun;
    Button jul;
    Button aug;
    Button sep;
    Button oct;
    Button nov;
    Button dec;

    static boolean jan_pr = false;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kg);

        tvDate = (TextView) findViewById(R.id.tv_date);
        mCal = Calendar.getInstance();

        // MoneyEdit.context = getApplicationContext();
        actionBar = getSupportActionBar();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffD6336B));
        getSupportActionBar().setTitle("댕댕이어리");
        actionBar.setIcon(R.drawable.white_puppy) ;
        actionBar.setDisplayUseLogoEnabled(true) ;
        actionBar.setDisplayShowHomeEnabled(true) ;

        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.custom_bar);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        Intent intent = new Intent(getIntent());
        flag = intent.getIntExtra("year", 0);

        // 캘린더 타이틀(년월 표시)을 세팅한다.
        if(flag ==0) {
            yearr = mCal.get(Calendar.YEAR);
            tvDate.setText(yearr + "년");
        }
        else {
            //Intent intent = new Intent(getIntent());
            yearr = flag; //intent.getIntExtra("year", 0);
            tvDate.setText(yearr + "년");
        }


        //하단탭 클릭 시
        Button cal = findViewById(R.id.calendar);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_calendar = new Intent(getApplicationContext(), CalendarTab.class);
                startActivity(intent_calendar);
            }
        });

        Button kg = findViewById(R.id.kg);
        kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_kg = new Intent(getApplicationContext(), KgTab.class);
                startActivity(intent_kg);
            }
        });

        Button money = findViewById(R.id.account);
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_money = new Intent(getApplicationContext(), MoneyTab.class);
                startActivity(intent_money);
            }
        });

        Button puppy = findViewById(R.id.puppy);
        puppy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_puppy = new Intent(getApplicationContext(), MypuppyTab.class);
                startActivity(intent_puppy);
            }
        });

        // Monthly KG upload BUTTON

        jan = findViewById(R.id.jan);
        jan.setOnClickListener(new View.OnClickListener() {
            //int jan = 0;
            @Override
            public void onClick(View v) {
                //jan.setSelected(true);
                jan.setBackgroundResource(R.drawable.button_pressed);
                Intent intent_kgjan = new Intent(getApplicationContext(), KgPopup.class);
                startActivity(intent_kgjan);
                kg_month = "January";
            }
        });
        feb = findViewById(R.id.feb);
        feb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feb.setBackgroundResource(R.drawable.button_pressed);
                Intent intent_kgfeb = new Intent(getApplicationContext(), KgPopup.class);
                startActivity(intent_kgfeb);
                kg_month = "February";
            }
        });
        mar = findViewById(R.id.mar);
        mar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mar.setBackgroundResource(R.drawable.button_pressed);
                Intent intent_kgmar = new Intent(getApplicationContext(), KgPopup.class);
                startActivity(intent_kgmar);
                kg_month = "March";
            }
        });
        apr = findViewById(R.id.apr);
        apr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apr.setBackgroundResource(R.drawable.button_pressed);
                Intent intent_kgapr = new Intent(getApplicationContext(), KgPopup.class);
                startActivity(intent_kgapr);
                kg_month = "April";
            }
        });
        may = findViewById(R.id.may);
        may.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                may.setBackgroundResource(R.drawable.button_pressed);
                Intent intent_kgmay = new Intent(getApplicationContext(), KgPopup.class);
                startActivity(intent_kgmay);
                kg_month = "May";
            }
        });
        jun = findViewById(R.id.jun);
        jun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jun.setBackgroundResource(R.drawable.button_pressed);
                Intent intent_kgjun = new Intent(getApplicationContext(), KgPopup.class);
                startActivity(intent_kgjun);
                kg_month = "June";
            }
        });
        jul = findViewById(R.id.jul);
        jul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jul.setBackgroundResource(R.drawable.button_pressed);
                Intent intent_kgjun = new Intent(getApplicationContext(), KgPopup.class);
                startActivity(intent_kgjun);
                kg_month = "July";
            }
        });
        aug= findViewById(R.id.aug);
        aug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aug.setBackgroundResource(R.drawable.button_pressed);
                Intent intent_kgjun = new Intent(getApplicationContext(), KgPopup.class);
                startActivity(intent_kgjun);
                kg_month = "August";
            }
        });
        sep = findViewById(R.id.sep);
        sep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sep.setBackgroundResource(R.drawable.button_pressed);
                Intent intent_kgjun = new Intent(getApplicationContext(), KgPopup.class);
                startActivity(intent_kgjun);
                kg_month = "September";
            }
        });
        oct = findViewById(R.id.oct);
        oct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oct.setBackgroundResource(R.drawable.button_pressed);
                Intent intent_kgjun = new Intent(getApplicationContext(), KgPopup.class);
                startActivity(intent_kgjun);
                kg_month = "October";
            }
        });
        nov = findViewById(R.id.nov);
        nov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nov.setBackgroundResource(R.drawable.button_pressed);
                Intent intent_kgjun = new Intent(getApplicationContext(), KgPopup.class);
                startActivity(intent_kgjun);
                kg_month = "November";
            }
        });
        dec = findViewById(R.id.dec);
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dec.setBackgroundResource(R.drawable.button_pressed);
                Intent intent_kgjun = new Intent(getApplicationContext(), KgPopup.class);
                startActivity(intent_kgjun);
                kg_month = "December";
            }
        });



        //상단 화살표로 전년도 후년도 클릭
        Button pvs_button = findViewById(R.id.previous);
        Button nxt_button = findViewById(R.id.next);

        pvs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yearr = yearr - 1 ;
                tvDate.setText(yearr + "년");
                flag = 1;

                HorizontalBarChart chart = (HorizontalBarChart) findViewById(R.id.chart);

                chart.getXAxis().setDrawGridLines(false); //grid 선 없애주기
                XAxis x = chart.getXAxis(); //x라는 변수 만들어서 이용
                x.setPosition(XAxis.XAxisPosition.BOTTOM); //x축 왼쪽으로 옮기기
                x.setTextSize(0);
                x.setTextColor(0x00000000); //x 변수 안 보이게 설정

                ArrayList<BarEntry> entries = new ArrayList();
                BarData data = new BarData(getDataSet());


                final ArrayList<String> labels = new ArrayList<>();
                labels.add("Jan");
                labels.add("Feb");
                labels.add("Mar");
                labels.add("Apr");
                labels.add("May");
                labels.add("Jun");
                labels.add("Jul");
                labels.add("Aug");
                labels.add("Sep");
                labels.add("Oct");
                labels.add("Nov");
                labels.add("Dec");

                // chart.setDescription();
                chart.getXAxis().setDrawAxisLine(false);
                chart.setData(data); // 아래 setData 불러옴
                chart.setFitBars(true);
                chart.animateXY(2000, 2000); //애니메이션 기능 추가
                chart.invalidate(); //invalidate 해줘야 함

            }
        });
        nxt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yearr = yearr + 1;
                tvDate.setText(yearr + "년");
                flag = 1;

                HorizontalBarChart chart = (HorizontalBarChart) findViewById(R.id.chart);

                chart.getXAxis().setDrawGridLines(false); //grid 선 없애주기
                XAxis x = chart.getXAxis(); //x라는 변수 만들어서 이용
                x.setPosition(XAxis.XAxisPosition.BOTTOM); //x축 왼쪽으로 옮기기
                x.setTextSize(0);
                x.setTextColor(0x00000000); //x 변수 안 보이게 설정

                ArrayList<BarEntry> entries = new ArrayList();
                BarData data = new BarData(getDataSet());

                final ArrayList<String> labels = new ArrayList<>();
                labels.add("Jan");
                labels.add("Feb");
                labels.add("Mar");
                labels.add("Apr");
                labels.add("May");
                labels.add("Jun");
                labels.add("Jul");
                labels.add("Aug");
                labels.add("Sep");
                labels.add("Oct");
                labels.add("Nov");
                labels.add("Dec");

                // chart.setDescription();
                chart.getXAxis().setDrawAxisLine(false);
                chart.setData(data); // 아래 setData 불러옴
                chart.setFitBars(true);
                chart.animateXY(2000, 2000); //애니메이션 기능 추가
                chart.invalidate(); //invalidate 해줘야 함
            }
        });

        //Intent intent = new Intent(KgTab.this, KgPopup.class);
        //intent.putExtra("year", yearr);

        //가로 그래프 horizontalbarchart

        if (flag ==0 || flag > 1) {

            HorizontalBarChart chart = (HorizontalBarChart) findViewById(R.id.chart);

            chart.getXAxis().setDrawGridLines(false); //grid 선 없애주기
            XAxis x = chart.getXAxis(); //x라는 변수 만들어서 이용
            x.setPosition(XAxis.XAxisPosition.BOTTOM); //x축 왼쪽으로 옮기기
            x.setTextSize(0);
            x.setTextColor(0x00000000); //x 변수 안 보이게 설정

            ArrayList<BarEntry> entries = new ArrayList();
            BarData data = new BarData(getDataSet());

            final ArrayList<String> labels = new ArrayList<>();
            labels.add("Jan");
            labels.add("Feb");
            labels.add("Mar");
            labels.add("Apr");
            labels.add("May");
            labels.add("Jun");
            labels.add("Jul");
            labels.add("Aug");
            labels.add("Sep");
            labels.add("Oct");
            labels.add("Nov");
            labels.add("Dec");

            // chart.setDescription();
            chart.getXAxis().setDrawAxisLine(false);
            chart.setData(data); // 아래 setData 불러옴
            chart.setFitBars(true);
            chart.animateXY(2000, 2000); //애니메이션 기능 추가
            chart.invalidate(); //invalidate 해줘야 함
        }
    }



    private ArrayList<String> getXAxisValues() { //x축 라벨 추가
        ArrayList<String> labels = new ArrayList();
        labels.add("Jan");
        labels.add("Feb");
        labels.add("Mar");
        labels.add("Apr");
        labels.add("May");
        labels.add("Jun");
        labels.add("Jul");
        labels.add("Aug");
        labels.add("Sep");
        labels.add("Oct");
        labels.add("Nov");
        labels.add("Dec");

        return labels;

    }

    private BarDataSet getDataSet() { //표시할 데이터 추가

        final ArrayList<BarEntry> entries = new ArrayList<>();

        /*
        service.showkg(yearr).enqueue(new Callback<ShowKgResponse>() {
            @Override
            public void onResponse(Call<ShowKgResponse> call, Response<ShowKgResponse> response) {
                ShowKgResponse showkg = response.body();
                if (response.isSuccessful()) {
                    List<ShowKgResponse.ShowKg> my = showkg.getData();
                    for(ShowKgResponse.ShowKg sk : my){
                       entries.add(new BarEntry( )
                    }

                    if(my != null){
                        for(int i=0; i<my.size(); i++){
                            switch (my.get(i).getMonth()){
                                case "January" : {
                                    kg[0] = my.get(i).getKg();
                                    System.out.println("kg 0 : " + kg[0]);
                                    break;
                                }
                                case "February" : {
                                    kg[1] = my.get(i).getKg();
                                    System.out.println("kg 1 : " + kg[1]);
                                    break;
                                }
                                case "March" : {
                                    kg[2] = my.get(i).getKg();
                                    System.out.println("kg 2 : " + kg[2]);
                                    break;
                                }
                                case "April" : {
                                    kg[3] = my.get(i).getKg();
                                    System.out.println("kg 3 : " + kg[3]);
                                    break;
                                }
                                case "May" : {
                                    kg[4] = my.get(i).getKg();
                                    System.out.println("kg 4 : " + kg[4]);
                                    break;
                                }
                                case "June" : {
                                    kg[5] = my.get(i).getKg();
                                    System.out.println("kg 5 : " + kg[5]);
                                    break;
                                }
                                case "July" : {
                                    kg[6] = my.get(i).getKg();
                                    System.out.println("kg 6 : " + kg[6]);
                                    break;
                                }
                                case "August" : {
                                    kg[7] = my.get(i).getKg();
                                    System.out.println("kg 7 : " + kg[7]);
                                    break;
                                }
                                case "September" : {
                                    kg[8] = my.get(i).getKg();
                                    System.out.println("kg 8 : " + kg[8]);
                                    break;
                                }
                                case "October" : {
                                    kg[9] = my.get(i).getKg();
                                    System.out.println("kg 9 : " + kg[9]);
                                    break;
                                }
                                case "November" : {
                                    kg[10] = my.get(i).getKg();
                                    System.out.println("kg 10 : " + kg[10]);
                                    break;
                                }
                                case "December" : {
                                    kg[11] = my.get(i).getKg();
                                    System.out.println("kg 11 : " + kg[11]);
                                    break;
                                }
                            }
                        }
                    }

                }
            }


            @Override
            public void onFailure(Call<ShowKgResponse> call, Throwable t) {
                Toast.makeText(KgTab.this, "kg 조회 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("kg 조회 에러 발생", t.getMessage());
            }
        });
*/
        ShowKg();
        entries.add(new BarEntry(12f, Float.parseFloat(String.valueOf(m1))));
        entries.add(new BarEntry(11f, m2));
        entries.add(new BarEntry(10f, m3));
        entries.add(new BarEntry(9f, m4));
        entries.add(new BarEntry(8f, m5));
        entries.add(new BarEntry(7f, m6));
        entries.add(new BarEntry(6f, 3));
        entries.add(new BarEntry(5f, 2));
        entries.add(new BarEntry(4f, m9));
        entries.add(new BarEntry(3f, m10));
        entries.add(new BarEntry(2f, m11));
        entries.add(new BarEntry(1f, m12));

        ds = new BarDataSet(entries,"체중(kg)");//속성값
        ds.setColors(ColorTemplate.VORDIPLOM_COLORS);//color random
        return ds;
    }

    private void ShowKg(){
        service.showkg(yearr).enqueue(new Callback<ShowKgResponse>() {
            @Override
            public void onResponse(Call<ShowKgResponse> call, Response<ShowKgResponse> response) {
                ShowKgResponse showkg = response.body();
                if(response.isSuccessful()){
                    List<ShowKgResponse.ShowKg> my = showkg.getData();
                    if(my != null){
                        m1 = my.get(0).getKg();
                        m2 = my.get(1).getKg();
                        m3 = my.get(2).getKg();
                        m4 = my.get(3).getKg();
                        m5 = my.get(4).getKg();
                        m6 = my.get(5).getKg();
                        m7 = my.get(6).getKg();
                        m8 = my.get(7).getKg();
                        m9 = my.get(8).getKg();
                        m10 = my.get(9).getKg();
                        m11 = my.get(10).getKg();
                        m12 = my.get(11).getKg();
                        System.out.println(m1+ ", " + m2 + ", " + m3 + ", " + m4 + ", " + m5);
                    }else{
                        m1 = 0;
                        m2 = 0;
                        m3 = 0;
                        m4 = 0;
                        m5 = 0;
                        m6 = 0;
                        m7 = 0;
                        m8 = 0;
                        m9 = 0;
                        m10 = 0;
                        m11 = 0;
                        m12 = 0;
                    }
                }
            }

            @Override
            public void onFailure(Call<ShowKgResponse> call, Throwable t) {

            }
        });
    }
}