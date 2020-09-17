package org.techtown.puppydiary.network.Response;

public class SignupResponse {

    private int status;
    private boolean success;
    private String message;
    private Checkemail data;
    private Checkpassword pwd;
    private CheckpasswordConfirm pwdC;

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

    public CheckpasswordConfirm getPwdC(){
        return pwdC;
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

    public class CheckpasswordConfirm {
        private String passwordC;

        public String getPassword(){
            return passwordC;
        }
    }

}
