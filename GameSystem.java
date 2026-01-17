package com.aimlite;

public class GameSystem {
    private int speed;

    public GameSystem(String difficulty) {
        switch (difficulty) {
            case "EASY" -> speed = 1600;
            case "MEDIUM" -> speed = 1200;
            case "GOD" -> speed = 1100;
        }
    }

    public int getSpeed() {
        return speed;
    }
}
