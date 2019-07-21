package logins;

import cn.bmob.v3.BmobUser;

public class Person extends BmobUser {

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    private String check ;


}
