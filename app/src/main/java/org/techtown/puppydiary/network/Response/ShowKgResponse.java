package org.techtown.puppydiary.network.Response;

import com.google.gson.JsonArray;

import java.util.List;

public class ShowKgResponse {

    private int status;
    private boolean success;
    private String message;
    private List<ShowKg> data;

    public int getStatus(){
        return status;
    }

    public boolean getSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }

    public List<ShowKg> getData(){ return data; }

    public class ShowKg {

        private int kgIdx;
        private int userIdx;
        private int year;

        private String month;
        private float kg;

        public int getKgIdx(){
            return kgIdx;
        }

        public int getUserIdx(){
            return userIdx;
        }

        public int getYear(){
            return year;
        }

        public String getMonth(){ return month; }

        public float getKg(){
            return kg;
        }

    }

}
