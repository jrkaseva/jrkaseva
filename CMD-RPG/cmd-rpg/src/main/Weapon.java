package main;

public class Weapon extends Item {

    public Weapon() {
    }

    public Weapon(String name, int stat, int cost) {
        super(name, stat, cost);
    }

    @Override
    public String toString() {
        return String.format("%s, ATK: %d, Cost: %d", name, stat, cost);
    }
}
