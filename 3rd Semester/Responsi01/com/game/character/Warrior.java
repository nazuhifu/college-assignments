package com.game.character;

public class Warrior extends Character {
    public Warrior(String name, int health, int damage, int gold) {
        super(name, health, damage, gold);
    }

    @Override
    public void attack() {
        System.out.printf("\n%s slashes with a sword.%n", getName());
    }

    @Override
    public void defend() {
        isDefending = true;
        System.out.printf("%s is defending and will take reduced damage.%n", getName());
    }
}
