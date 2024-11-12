package com.game.monster;

public class Monster {
    private final String name;
    private final int level;
    private int health;
    private final int damage;

    public Monster(String name, int level, int health, int damage) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health = Math.max(health - damage, 0);
    }
}
