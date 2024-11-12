package com.game.shop;

import com.game.character.Character;
import com.game.item.Potion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
    private final List<Potion> potionsForSale;

    public Shop() {
        potionsForSale = new ArrayList<>();
        potionsForSale.add(new Potion("Healing Potion", 50, 20, 20));
        potionsForSale.add(new Potion("Mana Potion", 50, 30, 30));
        potionsForSale.add(new Potion("Elixir Potion", 50, 40, 40));
    }

    public void showShopMenu(Character character) {
        System.out.println("\n=== Welcome to the Shop ===");
        System.out.printf("You have %d gold.%n", character.getGold());
        System.out.println("\nItems for Sale:");

        for (int i = 0; i < potionsForSale.size(); i++) {
            Potion potion = potionsForSale.get(i);
            System.out.printf("%d. %s +%d health (Price: %d gold)%n", i + 1, potion.getType(), potion.getHealingAmount(), potion.getPrice());
        }
        System.out.println("0. Exit Shop");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the potion you want to buy: ");
        int choice = scanner.nextInt() - 1;

        if (choice >= 0 && choice < potionsForSale.size()) {
            Potion potion = potionsForSale.get(choice);
            if (character.getGold() >= potion.getPrice()) {
                character.deductGold(potion.getPrice());
                character.addItem(new Potion(potion.getType(), 1, potion.getPrice(), potion.getHealingAmount())); // Add potion to character's inventory
                System.out.printf("You have bought a %s for %d gold.%n", potion.getType(), potion.getPrice());
            } else {
                System.out.println("You don't have enough gold to buy this item.");
            }
        } else if (choice == 0){
            System.out.println("Quitting Shop.");
        } else {
            System.out.println("Invalid choice, please try again.");
        }
    }
}
