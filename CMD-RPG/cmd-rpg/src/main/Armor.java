package main;

public class Armor extends Item {

    public Armor() {
    }

    public Armor(String name, int stat, int cost) {
        super(name, stat, cost);
    }

    @Override
    public String toString() {
        return String.format("%s, HP: %d, Cost: %d", name, stat, cost);
    }
}
