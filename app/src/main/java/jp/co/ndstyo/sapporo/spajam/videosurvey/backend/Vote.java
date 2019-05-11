package jp.co.ndstyo.sapporo.spajam.videosurvey.backend;

public class Vote {

    private String userName;
    private int money;

    public Vote(String userName, int money) {
        this.userName = userName;
        this.money = money;
    }

    public String getUserName(){
        return userName;
    }

    public int getMoney() {
        return money;
    }
}
