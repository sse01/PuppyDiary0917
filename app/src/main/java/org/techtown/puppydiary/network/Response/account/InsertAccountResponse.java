package org.techtown.puppydiary.network.Response.account;

import java.util.List;

public class InsertAccountResponse {

    private int status;
    private boolean success;
    private String message;
    private List<InsertAccount> data;


    public int getStatus(){
        return status;
    }

    public boolean getSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }

    public List<InsertAccount> getData(){ return data; }

    public class InsertAccount{
        private int idaccount;
        private int userIdx;
        private int year;
        private int month;
        private int date;
        private String item;
        private int price;

        public int getIdaccount(){
            //idaccount = data.getAsJsonObject().get("idaccount").getAsInt();
            return idaccount;
        }

        public int getUserIdx(){
            //userIdx = data.getAsJsonObject().get("userIdx").getAsInt();
            return userIdx;
        }

        public int getYear(){
            //year = data.getAsJsonObject().get("year").getAsInt();
            return year;
        }

        public int getMonth(){
            //month = data.getAsJsonObject().get("month").getAsInt();
            return month;
        }

        public int getDate(){
            //date = data.getAsJsonObject().get("date").getAsInt();
            return date;
        }

        public String getItem(){
            //item = data.getAsJsonObject().get("item").getAsString();
            return item;
        }

        public int getPrice(){
            //price = data.getAsJsonObject().get("price").getAsInt();
            return price;
        }

    }

}
