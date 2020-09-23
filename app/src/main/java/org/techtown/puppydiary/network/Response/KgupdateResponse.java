package org.techtown.puppydiary.network.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public class KgupdateResponse {

    private int status;
    private boolean success;
    private String message;
    private List<Kgupdate> data;

    public int getStatus(){
        return status;
    }

    public boolean getSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }

    public List<Kgupdate> getData(){ return data; }

    public class Kgupdate {

        private int kgIdx;
        private int userIdx;
        private int year;

        private String jan;
        private String feb;
        private String mar;
        private String apr;
        private String may;
        private String jun;
        private String jul;
        private String aug;
        private String sep;
        private String oct;
        private String nov;
        private String dec;

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

        public String getJan(){
            return jan;
        }

        public String getFeb(){
            return feb;
        }

        public String getMar(){
            return mar;
        }

        public String getApr(){
            return apr;
        }

        public String getMay(){
            return may;
        }

        public String getJun(){
            return jun;
        }

        public String getJul(){
            return jul;
        }

        public String getAug(){
            return aug;
        }

        public String getSep(){
            return sep;
        }

        public String getOct(){
            return oct;
        }

        public String getNov(){
            return nov;
        }

        public String getDec(){
            return dec;
        }

        public String getMonth(){
            return month;
        }

        public float getKg(){
            return kg;
        }

    }

}
