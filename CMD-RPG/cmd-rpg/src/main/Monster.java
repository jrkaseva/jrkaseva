package main;

public class Monster extends Entity {
    private int value;
    private int exp_value;
    
    public Monster(){}

    public Monster(String name, int level, int health, int attack, int defense, int value, int exp_value) {
        super(name, level, health, attack, defense);
        this.value = value;
        this.exp_value = exp_value;
    }

    public int getValue() {
        return value;
    }

    public int getExpValue() {
        return exp_value;
    }

    // "name | level | health | attack | defense | value | exp_value"
    public static Monster parseFromString(String s){
        String[] arr = s.split("\\|");
        int[] ints = new int[6];
        for (int i = 1; i < arr.length; i++){
            ints[i-1] = Integer.parseInt(arr[i].trim());
        }
        return new Monster(arr[0].trim(), ints[0], ints[1], ints[2], ints[3], ints[4], ints[5]);
        
    }

    @Override
    public String toString(){
        return String.format("Monster: %s (LVL %d)\nHealth: %d/%d | Attack: %d | Defense: %d", name.toUpperCase(), level, health, max_health, attack, defense);
    }

    public void setValue(int i) {
        value = i;
    }

    public void setExp_Value(int i) {
        exp_value = i;
    }

    public void copyMonster(Monster m){
        setName(m.getName()); setLevel(m.getLevel()); setHealth(m.getHealth()); setMax_health(m.getMax_health()); setAttack(m.getAttack());
        setDefense(m.getDefense()); setValue(m.getValue()); setExp_Value(m.getExpValue());
    }
    
    public static void main(String[] args){
        Monster m = parseFromString("Koira | 2 |10 | 2 |2 | 2 | 5");
        System.out.println(m);
    }
}
