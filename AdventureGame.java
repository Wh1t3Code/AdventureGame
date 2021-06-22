import java.util.Random;
import java.util.Scanner;

public class AdventureGame {
    public static void main(String[] args) {
        // System objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        final int PERCENT = 100;
        
        // enemies
        String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin", "Vampire", "Dragon", "Werewolf",
                            "Ghost", "Spider", "Mummy", "Snake"};
        int maxEnemyHealth = 100;
        int enemyAttackDamage = 50;

        // player
        int playerHealth = 150;
        int maxPlayerHealth = 150;
        int playerAttackDamage = 40;
        int playerRunChanceWithoutHit = 90; // Percentage
        String playerName = "";

        // health potions
        int numOfHealthPotions = 3;
        int healthPotionHealAmount = 25;
        int healthPotionDropChance = 20; // Percentage

        // Game
        boolean running = true;
        String divider = "---------------------------------------------";
        System.out.println("\tWelcome to the Creepy Cave");
        System.out.println(divider + "\n");
        System.out.print("Enter your name: ");
        playerName = in.nextLine();
        System.out.println("\nAre you sure you want to start? There are many dangers ahead.\n");
        System.out.print("Yes or No?\n");
        while(true) {
            String confirmation = in.next();
            if(confirmation.toLowerCase().equals("no")) {
                endGame(in, playerName);
            } else if(confirmation.toLowerCase().equals("yes")) {
                System.out.println("\nGoodluck " + playerName + " you'll need it!!!");
                System.out.println(divider + "\n");
                break;
            } else {
                System.out.println("Invalid Command!");  
            }
        }
    
        GAME:
        while(running) {
            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];

            System.out.println("\t" + enemy + " has appeared!\n");

            while(enemyHealth > 0) {
                System.out.println("\tYour HP: " + playerHealth);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run");

                String input = in.next();
                if(input.equals("1")) {
                    int damageDealt = rand.nextInt(playerAttackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);

                    enemyHealth -= damageDealt;
                    playerHealth -= damageTaken;

                    System.out.println("\tYou strike the " + enemy + " for " + damageDealt + " damage.");
                    System.out.println("\tYou receive  " + damageTaken + " damage from the " + enemy + "\n");
                    if(playerHealth <= 0) {
                        break;
                    }
                    if(enemyHealth <= 0) {
                        System.out.println("\t" + enemy + " was defeated!");
                    }
                } else if(input.equals("2")) {
                    if(numOfHealthPotions > 0) {
                        if(playerHealth < maxPlayerHealth) {
                            if(playerHealth + healthPotionHealAmount >= maxPlayerHealth) {
                                playerHealth = maxPlayerHealth;
                            } else {
                                playerHealth += healthPotionHealAmount;
                            }
                            numOfHealthPotions--;
                            System.out.println("\tYou drink a health potion, you have healed for " + healthPotionHealAmount + ".\n");
                            System.out.println("\tYou now have " + playerHealth + " HP.");
                            System.out.println("\tYou have  " + numOfHealthPotions + " health potions left."); 
                        } else {
                            System.out.println("\tYou are at max health!\n");
                        }
                    } else {
                        System.out.println("\tYou have no health potions left! Defeat enemies for a chance to get one.");
                    }
                } else if(input.equals("3")) {
                    if(rand.nextInt(PERCENT) < playerRunChanceWithoutHit) {
                        System.out.println("\tYou run away from the " + enemy + "!\n");
                        continue GAME;
                    } else {
                        System.out.println("\tYou run away from the " + enemy + " but got hit!\n");
                        int damageTaken = rand.nextInt(enemyAttackDamage);
                        playerHealth -= damageTaken;
                        if(playerHealth <= 0) {
                            break;
                        } else {
                            continue GAME;
                        }
                    }
                } else {
                    System.out.println("\tInvalid Command!\n");
                }

            }
            if(playerHealth <= 0) {
                System.out.println("\tYou have died!\n");
                endGame(in, playerName);
            }
            
            System.out.println("---------------------------------------------");
            if(rand.nextInt(PERCENT) < healthPotionDropChance) {
                numOfHealthPotions++;
                System.out.println("\tThe enemy dropped a health potion!");
                System.out.println("\tYou now have " + numOfHealthPotions + " health potions(s) left.");
            }
            
            System.out.println("\tYou have " + playerHealth + " HP left."); 
            
            System.out.println("---------------------------------------------");
            System.out.println("\tWhat would you like to do?");
            System.out.println("\t1. Continue fighting");
            System.out.println("\t2. Exit Cave");
            
            String userInput = in.next();
            
            while(!userInput.equals("1") && !userInput.equals("2")) {
                System.out.println("\tInvalid Command!\n");
                userInput = in.next();
            }
            
            if(userInput.equals("1")) {
                System.out.println("\tYou continue on your adventure!\n");
            } else if(userInput.equals("2")) {
                System.out.println("\tYou exit the cave, successful from your adventures!\n");
                endGame(in, playerName);
            }

        }
            endGame(in, playerName);

    }

    public static void endGame(Scanner in, String playerName) {
        System.out.println("###############");
        System.out.println("  THANKS FOR PLAYING! " + playerName);
        System.out.println("###############");
        in.close();
        System.exit(0);
    }



}