package org.techtown.puppydiary.network.Response;

public class SigninResponse {

    private int status;
    private boolean success;
    private String message;
    private Checkemail data;
    private Checkpassword pwd;

    public int getStatus(){
        return status;
    }

    public boolean getSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }

    public Checkemail getData(){
        return data;
    }

    public Checkpassword getPwd(){
        return pwd;
    }

    public class Checkemail {
        private String email;

        public String getEmail(){
            return email;
        }
    }

    public class Checkpassword {
        private String password;

        public String getPassword(){
            return password;
        }
    }

}
