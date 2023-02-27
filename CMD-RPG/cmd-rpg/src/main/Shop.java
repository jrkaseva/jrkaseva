package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Shop {
    private static BufferedReader readItems;

    private int max_round = 0;
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    private ArrayList<Armor> armors = new ArrayList<Armor>();;
    private ArrayList<Shield> shields = new ArrayList<Shield>();;

    public Shop(int max_round) {
        Path currentRelativePath = Paths.get("");
        try {
            readItems = new BufferedReader(new FileReader(currentRelativePath.toAbsolutePath().toString() + "/Items.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.max_round = max_round;
        String s;
        try {
            s = readItems.readLine();
            while(s.startsWith("*")) s = readItems.readLine();
            while(s != null){
                Item i = Item.parseFromString(s);
                if (i instanceof Weapon) weapons.add((Weapon) i);
                if (i instanceof Armor) armors.add((Armor) i);
                if (i instanceof Shield) shields.add((Shield) i);
                s = readItems.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void display(int round){
        if (round == max_round) return;
        System.out.println("Entering shop\n**********************************");
        if (round <= 2){
            System.out.println(weapons.get(0));
            System.out.println(armors.get(0));
            System.out.println(shields.get(0));
        }
        else if (round <= 4){
            System.out.println(weapons.get(1));
            System.out.println(armors.get(1));
            System.out.println(shields.get(1));
        }
        else if (round <= 6){
            System.out.println(weapons.get(2));
            System.out.println(armors.get(1));
            System.out.println(shields.get(1));
        }
        else if (round <= 8){
            System.out.println(weapons.get(3));
            System.out.println(armors.get(2));
            System.out.println(shields.get(2));
        }
        else {
            System.out.println(weapons.get(4));
            System.out.println(armors.get(3));
            System.out.println(shields.get(3));
        }
    }

    public ArrayList<Item> currentlySelling(int round){
        ArrayList<Item> selling = new ArrayList<Item>();
        if (round <= 2){
            selling.add(weapons.get(0));
            selling.add(armors.get(0));
            selling.add(shields.get(0));
        }
        else if (round <= 4){
            selling.add(weapons.get(1));
            selling.add(armors.get(1));
            selling.add(shields.get(1));
        }
        else if (round <= 6){
            selling.add(weapons.get(2));
            selling.add(armors.get(1));
            selling.add(shields.get(1));
        }
        else if (round <= 8){
            selling.add(weapons.get(3));
            selling.add(armors.get(2));
            selling.add(shields.get(2));
        }
        else {
            selling.add(weapons.get(4));
            selling.add(armors.get(3));
            selling.add(shields.get(3));
        }
        return selling;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public ArrayList<Armor> getArmors() {
        return armors;
    }

    public void setArmors(ArrayList<Armor> armors) {
        this.armors = armors;
    }

    public ArrayList<Shield> getShields() {
        return shields;
    }

    public void setShields(ArrayList<Shield> shields) {
        this.shields = shields;
    }
    
}
