package org.example;

public class Player {
    private long id;
    private String name;
    private int bestScore;

    public Player(long id, String name, int bestScore) {
        this.id = id;
        this.name = name;
        this.bestScore = bestScore;
    }

    public Player() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}
