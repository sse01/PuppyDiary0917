package org.techtown.puppydiary.network.Response;

public class FindpwResponse {

    private int status;
    private boolean success;
    private String message;
    private Findpw data;

    public int getStatus(){
        return status;
    }

    public boolean getSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }

    public Findpw getData(){
        return data;
    }

    public class Findpw {
        private String toemail;
        private String subject;

        public String getToemail(){
            return toemail;
        }

        public String getSubject(){
            return subject;
        }
    }

}
