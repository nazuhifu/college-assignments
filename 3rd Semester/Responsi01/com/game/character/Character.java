package com.game.character;

import com.game.item.Item;
import com.game.item.Potion;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public abstract class Character implements RPGCharacter {
    private final String name;
    private int health;
    private final int damage;
    private final List<Item> inventory;
    protected boolean isDefending;
    private int gold;

    public Character(String name, int health, int damage, int gold) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.inventory = new ArrayList<>();
        this.isDefending = false;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health += health;
    }

    public int getDamage() {
        return damage;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public void deductGold(int amount) {
        if (gold >= amount) {
            gold -= amount;
        } else {
            System.out.print("You don't have enough gold!\n");
        }
    }

    public void takeDamage(int damage) {
        if (isDefending) {
            damage /= 2;
            isDefending = false;
        }
        health = Math.max(health - damage, 0);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void showInventory() {
        System.out.printf("%n=== Inventory (%s) ===%n", getName());
        for (int i = 0; i < inventory.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, inventory.get(i).getInfo());
        }
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void useItem() {
        if (inventory.isEmpty()) {
            System.out.println("You have no items in your inventory.");
            return;
        }

        System.out.println("\n=== Choose an Item to Use ===");
        // Display the inventory for the user to choose an item
        for (int i = 0; i < inventory.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, inventory.get(i).getInfo());
        }

        System.out.print("Enter the number of the item you want to use: ");
        try {
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt() - 1;

            if (choice < 0 || choice >= inventory.size()) {
                System.out.println("Invalid choice. Please try again.");
                return;
            }

            Item item = inventory.get(choice);
            if (item != null) {
                Potion potion = (Potion) item;

                if (potion.getQuantity() <= 0) {
                    System.out.println("This potion is out of stock.");
                } else {
                    // Use the potion
                    potion.use();

                    int healingAmount = potion.getHealingAmount(); // Potion restores 50 health
                    health = Math.min(100, health + healingAmount); // Max health is 100
                    System.out.printf("You used a %s and restored %d health.%n", potion.getType(), healingAmount);

                    // Display remaining potions
                    System.out.printf("You have %d %s left.%n", potion.getQuantity(), potion.getType());
                }
            } else {
                System.out.println("This item cannot be used.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid item number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid item index. Please select a valid item.");
        }
    }

    public void getInfo() {
        System.out.printf("%nName: %s%nHP: %d%nDamage: %d", getName(), getHealth(), getDamage());
    }
}
