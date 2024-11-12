package com.game.character;

public class Mage extends Character {
    public Mage(String name, int health, int damage, int gold) {
        super(name, health, damage, gold);
    }

    @Override
    public void attack() {
        System.out.printf("\n%s casts a fireball.%n", getName());
    }

    @Override
    public void defend() {
        isDefending = true;
        System.out.printf("%s shields with magic, reducing damage.%n", getName());
    }
}
