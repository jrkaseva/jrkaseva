package main;

public class Shield extends Item {

    public Shield() {
    }

    public Shield(String name, int stat, int cost) {
        super(name, stat, cost);
    }

    @Override
    public String toString() {
        return String.format("%s, DEF: %d, Cost: %d", name, stat, cost);
    }
}
