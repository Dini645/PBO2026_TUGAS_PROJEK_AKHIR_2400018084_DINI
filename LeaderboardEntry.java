package com.aimlite;

public class LeaderboardEntry {
    private String name;
    private int score;

    public LeaderboardEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String toString() {
        return name + " - " + score;
    }

    public int getScore() {
        return score;
    }
}
