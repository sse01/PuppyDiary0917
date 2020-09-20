package org.techtown.puppydiary.network.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ShowDayResponse {

    private int status;
    private boolean success;
    private String message;
    private JsonObject data;
    private int idcalendar;
    private int userIdx;
    private int year;
    private int month;
    private int date;
    private String memo;
    private int inject;
    private int water;
    private String photo;

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

    public int getIdcalendar(){
        idcalendar = data.getAsJsonObject().get("idcalendar").getAsInt();
        return idcalendar;
    }

    public int getUserIdx(){
        userIdx = data.getAsJsonObject().get("userIdx").getAsInt();
        return userIdx;
    }

    public int getYear(){
        year = data.getAsJsonObject().get("year").getAsInt();
        return year;
    }

    public int getMonth(){
        month = data.getAsJsonObject().get("month").getAsInt();
        return month;
    }

    public int getDate(){
        date = data.getAsJsonObject().get("date").getAsInt();
        return date;
    }

    public String getMemo(){
        memo = data.getAsJsonObject().get("memo").getAsString();
        return memo;
    }

    public int getInject(){
        inject = data.getAsJsonObject().get("inject").getAsInt();
        return inject;
    }

    public int getWater(){
        water = data.getAsJsonObject().get("water").getAsInt();
        return water;
    }

    public String getPhoto(){
        photo = data.getAsJsonObject().get("photo").getAsString();
        return photo;
    }
}
