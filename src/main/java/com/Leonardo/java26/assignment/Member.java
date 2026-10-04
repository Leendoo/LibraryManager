package com.Leonardo.java26.assignment;

public class Member {
    private String id;
    private String name;
    private int activeLoans;
    private static final int MAX_LOANS = 3;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.activeLoans = 0;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getActiveLoans() { return activeLoans; }


     boolean canBorrow() {
        return activeLoans < MAX_LOANS;
    }

    public void incrementLoans() {
        activeLoans++;
    }

    public void decrementLoans() {
        if (activeLoans > 0) {
            activeLoans--;
        }
    }
}