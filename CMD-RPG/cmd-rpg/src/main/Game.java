package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static final Scanner sc = new Scanner(System.in);
    private static BufferedReader readMonsters;

    private static int round = 0;
    private static int max_rounds = 10;

    private static ArrayList<Monster> monsters = new ArrayList<Monster>();
    
    public static void main(String[] args){
        Player player = new Player();
        Monster monster = new Monster();
        Shop shop = new Shop(max_rounds);
        initialize(player);
        while (round < max_rounds){
            System.out.println("############################\n" + player);
            phase1(monster);
            boolean survived = battle(player, monster);
            phase2(player, survived, shop, monster);
        }
        System.out.println("Victory! You defeated all the monsters!");
        System.out.println("Final stats:");
        System.out.println(player);
    }
    
    public static void initialize(Player p){
        getFilePath("Monsters.txt");
        
        getMonsters();
        
        System.out.print("Welcome! What is your name?\nName: ");
        String name = sc.nextLine();
        round = 0;
        p.setName(name); p.setAll();
    }
    
    private static void getFilePath(String filename) {
        Path currentRelativePath = Paths.get("");
        try {
            readMonsters = new BufferedReader(new FileReader(currentRelativePath.toAbsolutePath().toString() + "/" + filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void getMonsters() {
        try {
            String info = readMonsters.readLine();
            while (info.startsWith("*")){
                info = readMonsters.readLine();
            }
            while (info != null){
                monsters.add(Monster.parseFromString(info));
                info = readMonsters.readLine();
            }
            max_rounds = monsters.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void phase1(Monster m){
        round++;
        nextMonster(m);
        System.out.print("Going to battle! Press enter to continue");
        sc.nextLine();
    }
    
    public static void nextMonster(Monster m) {
        if (round < max_rounds){
            m.copyMonster(monsters.get(round - 1));
        }
        else m.copyMonster(monsters.get(monsters.size() - 1));
    }

    public static boolean battle(Player p, Monster m){
        System.out.println("Encountered monster: " + m.getName());
        System.out.println(String.format("(HP: %d, ATK: %d, DEF: %d)", m.getHealth(), m.getAttack(), m.getDefense()));
        while(p.getHealth() > 0) {
            System.out.print("Press enter to attack\n---------------------");
            sc.nextLine();
            m.takeDamage(p.getAttack());
            if (m.getHealth() <= 0) break;
            p.takeDamage(m.getAttack());
            System.out.println(p.stats() + "\nVS");
            System.out.println(m + "\n---------------------");
        }
        if (p.getHealth() <= 0) return false;
        return true;
    }

    /**
     * Phase 2, procedures after battle.
     * @param p
     * @param b
     * @param s
     * @param m
     */
    public static void phase2(Player p, Boolean b, Shop s, Monster m) {
        if (!b){
            if (gameover(p)) return;
            else System.exit(0);
        } 
        p.getReward(m);

        //Winning the game
        if (round == max_rounds){
            return;
        }

        s.display(round);
        System.out.println("\n" + p.shopping());

        System.out.println("Do you wish to buy something? (Y/N)");
        if(yesNoInput()){
            System.out.println("Do you wish to buy a weapon, armor or shield? (W/A/S)");
            Item wanted = w_a_s_Input((Weapon) s.currentlySelling(round).get(0), (Armor) s.currentlySelling(round).get(1), (Shield) s.currentlySelling(round).get(2));
            if (p.getCoins() < wanted.getCost())
            {
             System.out.println("Not enough coins"); return;
            }    
            purchasing(p, wanted);
        }
    }

    public static void purchasing(Player p, Item wanted){
        if (wanted.canBePurchased(p)){
            p.setCoins(p.getCoins() - wanted.getCost());
            if (wanted instanceof Weapon){
                p.setEquipped_weapon((Weapon) wanted);
            }
            else if (wanted instanceof Armor){
                p.setEquipped_armor((Armor) wanted);
            }
            else if (wanted instanceof Shield){
                p.setEquipped_shield((Shield) wanted);
            }
            else if (wanted.getName().equals("Potion")){
                p.setHealth(p.getHealth() + wanted.getStat());
                if (p.getHealth() > p.getMax_health()) p.setHealth(p.getMax_health());
            } 
        }
    }

    public static boolean gameover(Player p){
        System.out.println("You died! Do you want to continue? (Y/N)");
        if (yesNoInput()){
            initialize(p);
            return true;
        }
        System.out.println("Thank you for playing!");
        sc.nextLine();
        return false;
    }

    public static boolean yesNoInput(){
        String choice = "";
        while (true){
            choice = sc.nextLine().toLowerCase();
            if (choice.equals("y")) return true;
            else if (choice.equals("n")) return false;
        }
    }

    public static Item w_a_s_Input(Weapon weapon, Armor armor, Shield shield){
        String choice = "";
        while (true){
            choice = sc.nextLine().toLowerCase();
            if (choice.equals("w")) return weapon;
            else if (choice.equals("a")) return armor;
            else if (choice.equals("s")) return shield;
        }
    }
}