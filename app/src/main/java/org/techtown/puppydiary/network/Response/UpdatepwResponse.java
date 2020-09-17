package org.techtown.puppydiary.network.Response;

public class UpdatepwResponse {

    private int status;
    private boolean success;
    private String message;

    private Checkemail data;
    private Checkpassword pwd;
    private Checknewpassword newpwd;
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

    public Checknewpassword getnewPwd(){
        return newpwd;
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

    public class Checknewpassword {
        private String passwordN;

        public String getPasswordN(){
            return passwordN;
        }
    }

    public class CheckpasswordConfirm {
        private String passwordC;

        public String getPassword(){
            return passwordC;
        }
    }

}
