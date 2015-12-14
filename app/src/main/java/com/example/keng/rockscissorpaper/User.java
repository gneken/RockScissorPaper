package com.example.keng.rockscissorpaper;

/**
 * Created by Keng on 2015-12-12.
 */
public class User {

    private String name;
    private int score;
    private String weapon;

    public User(String name) {
        this.name = name;
        score = 0;
    }

    public void increaseScore () {
        score++;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void addWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getWeapon() {
        return weapon;
    }
}
