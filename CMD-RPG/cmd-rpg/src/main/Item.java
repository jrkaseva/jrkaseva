package main;

public class Item {
    protected String name;

    protected int cost;
    protected int stat;

    public Item(){
        name = "none";
        stat = 0;
        cost = 0;
    }
    
    public Item(String name, int stat, int cost) {
        this.name = name;
        this.stat = stat;
        this.cost = cost;
    }
    
    public boolean isBetterThanEquipped(Item i){
        if(stat > i.getStat()) return true;
        return false;
    }

    public String getName() {
        return name;
    }
    
    public int getStat() {
        return stat;
    }

    public int getCost() {
        return cost;
    }

    public boolean canBePurchased(Player p) {
        if (p.getCoins() >= cost){
            if (this instanceof Weapon){
                if (!(p.getEquipped_weapon() == null)){
                    if (!isBetterThanEquipped(p.getEquipped_weapon())) return false;
                }
                return true;
            }
            if (this instanceof Armor){
                if (!(p.getEquipped_armor() == null)){
                    if (!isBetterThanEquipped(p.getEquipped_armor())) return false;
                }
                return true;
            }
            if (this instanceof Shield){
                if (!(p.getEquipped_shield() == null)){
                    if (!isBetterThanEquipped(p.getEquipped_shield())) return false;
                }
                return true;
            }
        }
        return false;
    }

    // "type | NAME | Cost | Stat "
    public static Item parseFromString(String s){
        String[] arr = s.split("\\|");
        int[] ints = new int[2];
        for (int i = 2; i < arr.length; i++){
            ints[i-2] = Integer.parseInt(arr[i].trim());
        }
        if (arr[0].trim().toLowerCase().equals("weapon")){
            return new Weapon(arr[1].trim().toUpperCase(), ints[0], ints[1]);
        }
        else if (arr[0].trim().toLowerCase().equals("armor")){
            return new Armor(arr[1].trim().toUpperCase(), ints[0], ints[1]);
        }
        else if (arr[0].trim().toLowerCase().equals("shield")){
            return new Shield(arr[1].trim().toUpperCase(), ints[0], ints[1]);
        }
        else return new Item(arr[1].trim().toUpperCase(), ints[0], ints[1]);
    }
}