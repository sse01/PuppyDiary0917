package org.techtown.puppydiary.network.Response;

import com.google.gson.JsonArray;

public class ShowMonthResponse {

    private int status;
    private boolean success;
    private String message;
    private JsonArray data;
    private int date;
    private int inject;
    private int water;

    public int getStatus(){
        return status;
    }

    public boolean getSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }

    public JsonArray getData(){ return data; }

    public int getDate(){
        date = data.getAsJsonObject().get("date").getAsInt();
        return date;
    }

    public int getInject(){
        inject = data.getAsJsonObject().get("inject").getAsInt();
        return inject;
    }

    public int getWater(){
        water = data.getAsJsonObject().get("water").getAsInt();
        return water;
    }
}
