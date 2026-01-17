package com.aimlite;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {

    private List<LeaderboardEntry> entries = new ArrayList<>();

    public Leaderboard() {
        // 3 pemain default
        entries.add(new LeaderboardEntry("Dini", 150));
        entries.add(new LeaderboardEntry("Alisya", 140));
        entries.add(new LeaderboardEntry("Ecyy", 130));
        sort();
    }

    public void add(String name, int score) {
        entries.add(new LeaderboardEntry(name, score));
        sort();
    }

    private void sort() {
        entries.sort(Comparator.comparingInt(LeaderboardEntry::getScore).reversed());
    }

    public List<LeaderboardEntry> getEntries() {
        return entries;
    }
}
