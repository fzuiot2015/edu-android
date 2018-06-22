package fzu.edu.util;

public class LoginUtil {
    public static Boolean login(String account, String password){
        //TODO:登录验证
        if(account.equals("admin")&&password.equals("123456")){
            return true;
        }
        return false;
    }

}
