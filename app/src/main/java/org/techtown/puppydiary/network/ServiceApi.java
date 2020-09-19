package org.techtown.puppydiary.network;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.puppydiary.Login;
import org.techtown.puppydiary.Pwd;
import org.techtown.puppydiary.network.Data.CheckemailData;
import org.techtown.puppydiary.network.Data.FindpwData;
import org.techtown.puppydiary.network.Data.MyinfoData;
import org.techtown.puppydiary.network.Data.ProfileData;
import org.techtown.puppydiary.network.Data.RegisterData;
import org.techtown.puppydiary.network.Data.SigninData;
import org.techtown.puppydiary.network.Data.SignupData;
import org.techtown.puppydiary.network.Data.UpdatepwData;
import org.techtown.puppydiary.network.Response.CheckemailResponse;
import org.techtown.puppydiary.network.Response.FindpwResponse;
import org.techtown.puppydiary.network.Response.MyinfoResponse;
import org.techtown.puppydiary.network.Response.ProfileResponse;
import org.techtown.puppydiary.network.Response.RegisterResponse;
import org.techtown.puppydiary.network.Response.SigninResponse;
import org.techtown.puppydiary.network.Response.SignupResponse;
import org.techtown.puppydiary.network.Response.UpdatepwResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServiceApi {

    String jwtToken = Login.jwtToken;


    @POST("/user/signup") //회원가입
    Call<SignupResponse> usersignup(@Body SignupData data);
    /*
    {
        "email" : "jooe0824@naver.com",
        "password" : "123123",
        "passwordConfirm" : "123123"
    }
    */

    @POST("/user/signin") //로그인
    Call<SigninResponse> usersignin(@Body SigninData data);
    /*
    {
        "email" : "jooe0824@naver.com",
        "password" : "123123"
    }
    */

    @POST("/user/checkemail") //이메일 중복확인
    Call<CheckemailResponse> checkemail(@Body CheckemailData data);
    /*
    {
        "email" : "hyohyo@naver.com"
    }
     */

    @POST("/user/findpw") //비밀번호 찾기
    Call<FindpwResponse> findpw (@Body FindpwData data);
    /*
    {
        "email" : "hyohyo@naver.com"
    }
    */


    @POST("/user/updatepw") //비밀번호 업데이트
    Call<UpdatepwResponse> updatepw (@Body UpdatepwData data);
    /*
    {
    "email" : "ga0@naver.com",
    "password" : "123123",
    "newpassword" : "1231234",
    "passwordConfirm" : "1231234"
    }
     */



    @POST("/mypage/registermyinfo") //강아지 정보 등록/업데이트
    Call<RegisterResponse> registerinfo (@Body RegisterData data);
    /*
    {
        "puppyname" : "maru",
        "age" : "4",
        "birth" : "2017.12.19",
        "gender" : "1"
    }
     */


    @POST("/user/profile") //프로필 사진 업데이트
    Call<ProfileResponse> profile (@Body ProfileData data);
    /*
    form-date
    {
        "key" : "file type"
        "values" : "file location"
    }
     */

    @GET("/mypage/myinfo") //강아지 정보 조회
    Call<MyinfoResponse> myinfo (@Body MyinfoData data);
}



//public interface MemberFactoryIm {
//    //통신 담당 메소드 구현.
//    @GET("login")
//    Call<List<User>> login(@Query("userEmail") String email, @Query("userPwd") String password);
//
//    @POST("join")
//    Call<Res_join> join(@Body Req_join user); //보낼때는 Req로 보내고 받을 때는 Res로 받음.
//
//    @GET("dupl")
//    Call<String> dupl(@Query("userEmail") String email);
//}