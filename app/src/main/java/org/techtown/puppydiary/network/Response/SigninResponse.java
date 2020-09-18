package org.techtown.puppydiary.network.Response;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.JsonObject;

public class SigninResponse {

    private int status;
    private boolean success;
    private String message;
    private JsonObject data;
    private int userIdx;


    public int getStatus(){
        return status;
    }

    public boolean getSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }

    public JsonObject getData(){ return data; }

    public int getUserIdx(){
        userIdx = data.getAsJsonObject().get("userIdx").getAsInt();
        return userIdx;
    }

    public void save(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("USERIDX", userIdx).apply();
    }

    public int load(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt("USERIDX", 0);
    }



}