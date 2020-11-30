package com.zero.system.entity.login;

public class LoginData {

    private Level level_info;
    private int money;
    private String uname;



    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
    public Level getLevel_info() {
        return level_info;
    }

    public void setLevel_info(Level level_info) {
        this.level_info = level_info;
    }
}
