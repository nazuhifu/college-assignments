package com.game;

import com.game.character.Character;
import com.game.character.Warrior;
import com.game.character.Mage;
import com.game.monster.Monster;
import com.game.shop.Shop;

import java.util.*;

public class Main {
    private static final Random random = new Random();
    public static final Scanner scanner = new Scanner(System.in);

    public static void main() {
        System.out.println("Choose your character:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.print("Enter your choice: ");
        int characterChoice = scanner.nextInt();

        Character player;

        if (characterChoice == 1) {
            player = new Warrior("Albert", 100, 40, 150);
        } else {
            player = new Mage("Gandalf", 80, 60, 150);
        }

        Shop shop = new Shop();

        while (true) {
            showMenu();
            int choice = getUserInput();

            switch (choice) {
                case 1 -> {
                    enterBattleMode(player);
                    pressAny();
                }
                case 2 -> {
                    player.showInventory();
                    pressAny();
                }
                case 3 -> {
                    player.getInfo();
                    pressAny();
                }
                case 4 -> {
                    shop.showShopMenu(player);  // Open the shop to buy potions
                    pressAny();
                }
                case 5 -> {
                    System.out.println("Exiting the game. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showMenu() {
        clearScreen();
        System.out.println("\n=== Menu ===");
        System.out.println("1. Enter Battle Mode");
        System.out.println("2. Show Inventory");
        System.out.println("3. Character Profile");
        System.out.println("4. Visit Shop");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static int getUserInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear the invalid input
            }
        }
    }

    private static void enterBattleMode(Character character) {
        clearScreen();
        System.out.println("\nEntering Battle Mode...");
        battleMode(character);
        if (character.getHealth() == 0) {
            character.setHealth(50);
        }
    }

    private static void battleMode(Character character) {
        int numberOfMonsters = random.nextInt(3) + 1;
        List<Monster> monsterList = generateMonsters(numberOfMonsters);

        for (Monster monster : monsterList) {
            System.out.printf("A wild %s appears!%n", monster.getName());
            System.out.printf("%s has %d health and %d damage.%n", monster.getName(), monster.getHealth(), monster.getDamage());

            while (monster.isAlive() && character.isAlive()) {
                try {
                    characterTurn(character, monster);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid action choice, try again.");
                    scanner.nextLine();  // clear the invalid input
                    continue;
                }
                if (monster.isAlive()) {
                    monsterTurn(character, monster);
                }
            }

            if (!monster.isAlive()) {
                int goldReward = monster.getLevel() * 10;
                System.out.printf("You defeated %s! You earned %d gold.%n", monster.getName(), goldReward);
                character.addGold(goldReward);
            }
        }
    }

    private static List<Monster> generateMonsters(int numberOfMonsters) {
        List<Monster> monsterList = new ArrayList<>();
        for (int i = 0; i < numberOfMonsters; i++) {
            String monsterName = String.format("Monster %d", i + 1);
            int level = random.nextInt(5) + 1;
            int health = level * 20;
            int damage = level * 5;
            monsterList.add(new Monster(monsterName, level, health, damage));
        }
        return monsterList;
    }

    private static void characterTurn(Character character, Monster monster) {
        System.out.println("\n=== Your Turn ===");
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.println("3. Use Item");
        System.out.print("Choose an action: ");

        int action = scanner.nextInt();

        switch (action) {
            case 1 -> {
                character.attack();
                monster.takeDamage(character.getDamage());
                System.out.printf("%s takes %d damage%n", monster.getName(), character.getDamage());
                System.out.printf("%n%s has %d health remaining.%n", monster.getName(), monster.getHealth());
                pressAny();
            }
            case 2 -> {
                character.defend();
                pressAny();
            }
            case 3 -> {
                try {
                    character.useItem();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid item choice. Please try again.");
                }
                pressAny();
            }
            default -> System.out.println("Invalid action. Please try again.");
        }
    }

    private static void monsterTurn(Character character, Monster monster) {
        try {
            int damage = monster.getDamage();
            character.takeDamage(damage);
            clearScreen();
            System.out.printf("%s is attacking %s.%n", monster.getName(), character.getName());
            System.out.printf("%s takes %d damage!%n", character.getName(), damage);
            System.out.printf("%s has %d health remaining.%n", character.getName(), character.getHealth());

            if (!character.isAlive()) {
                System.out.println("You have been defeated!");
                System.out.println("You will be revived with 50 health");
                pressAny();
                return;
            }

        } catch (Exception e) {
            System.out.println("An error occurred during the monster's turn.");
//            e.printStackTrace();
        }
        pressAny();
    }

    private static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private static void pressAny()
    {
        System.out.print("\nPress Enter key to continue... ");
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }
}
