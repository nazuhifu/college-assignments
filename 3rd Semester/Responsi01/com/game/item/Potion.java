package com.game.item;

public class Potion extends Item {
    private final String type;
    private final int healingAmount;

    public Potion(String type, int quantity, int price, int healingAmount) {
        super(price, quantity);
        this.type = type;
        this.healingAmount = healingAmount;
    }

    public String getType() {
        return type;
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    @Override
    public void use() {
        if (getQuantity() > 0) {
            setQuantity(getQuantity() - 1);
            System.out.printf("Using %s potion. Healing for %d HP.%n", type, healingAmount);
        } else {
            System.out.println("No more potions left.");
        }
    }

    @Override
    public String getInfo() {
        return String.format("Potion (Type: %s, Qty: %d, Price: %d)", getType(), getQuantity(), getPrice());
    }
}
