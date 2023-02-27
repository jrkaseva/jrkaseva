package main;

public class Player extends Entity {
    private int coins = 0;
    private Weapon equipped_weapon = new Weapon("None", 0, 0);
    private Armor equipped_armor = new Armor("None", 0, 0);
    private Shield equipped_shield = new Shield("None", 0, 0);
    private int experience = 0;
    private int exp_to_lvlup;

    public Player(){}
    
    /**
     * @param name of Player
     * @param level of Player
     * @param health of Player
     * @param attack of Player
     * @param defense of Player
     * @param exp_to_lvlup of Player
     */
    public Player(String name, int level, int health, int attack, int defense, int exp_to_lvlup) {
        super(name, level, health, attack, defense);
        this.name = name;
        this.exp_to_lvlup = exp_to_lvlup;
        max_health = health;
    }
    
    public int getMax_health() {
        if (equipped_armor != null) return max_health + equipped_armor.getStat();
        return max_health;
    }

    public int getCoins() {
        return coins;
    }
    
    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Weapon getEquipped_weapon() {
        return equipped_weapon;
    }
    
    public void setEquipped_weapon(Weapon equipped_weapon) {
        if (this.equipped_weapon != null) attack -= this.equipped_weapon.getStat();
        this.equipped_weapon = equipped_weapon;
        attack += equipped_weapon.getStat();
    }
    
    public Armor getEquipped_armor() {
        return equipped_armor;
    }
    
    public void setEquipped_armor(Armor equipped_armor) {
        if (this.equipped_armor != null)
        {
         health -= this.equipped_armor.getStat(); max_health -= this.equipped_armor.getStat();
        }    
        this.equipped_armor = equipped_armor;
        health += equipped_armor.getStat();
        max_health += equipped_armor.getStat();
        
    }
    
    public Shield getEquipped_shield() {
        return equipped_shield;
    }
    
    public void setEquipped_shield(Shield equipped_shield) {
        if (this.equipped_shield != null) defense -= this.equipped_shield.getStat();
        this.equipped_shield = equipped_shield;
        defense += equipped_shield.getStat();
    }
    
    public int getExperience() {
        return experience;
    }
    
    public void setExperience(int experience) {
        this.experience = experience;
    }
    
    public int getExp_to_lvlup() {
        return exp_to_lvlup;
    }
    
    public void setExp_to_lvlup(int exp_to_lvlup) {
        this.exp_to_lvlup = exp_to_lvlup;
    }
    
    public boolean canLevelUp(){
        return experience >= exp_to_lvlup;
    }

    public void setAll(){
        setExperience(0); setExp_to_lvlup(10); setCoins(0); setEquipped_weapon(new Weapon());
        setEquipped_armor(new Armor()); setEquipped_shield(new Shield());
        setLevel(1); setHealth(10); setMax_health(10); setAttack(3); setDefense(1);
    }
    
    public void levelUp(){
        level++;
        experience -= exp_to_lvlup;
        max_health += 5;
        attack += 1;
        if ((level - 1) % 2 == 0){
            defense += 1;
        }
        health = max_health;
        System.out.println("You leveled up!");
    }

    public String shopping(){
        return String.format("Player: %s (LVL %d, EXP %d/%d)\nHealth: %d/%d | Attack: %d | Defense: %d\nCoins: %d\nArmor: %s\nWeapon: %s\nShield: %s\n**********************************", name, level, experience, exp_to_lvlup, health, max_health, attack, defense, coins, equipped_armor, equipped_weapon, equipped_shield);
    }

    public void getReward(Monster monster){
        System.out.println("You killed a " + monster.getName() + "! You gained " + monster.getValue() + " coins and " + monster.getExpValue() + " exp.");
        coins += monster.getValue();
        experience += monster.getExpValue();
        if (canLevelUp()) levelUp();
    }

    /**
     * Entity's health is attacked
     */
    @Override
    public void takeDamage(int incoming_damage){
        int post_mitigated_damage = incoming_damage - defense;
        if (post_mitigated_damage > 0) health -= post_mitigated_damage;
    }
    
    @Override
    public String toString(){
        return String.format("Player: %s (LVL %d)\nHealth: %d/%d (%d + %d) %s\nAttack: %d (%d + %d) %s\nDefense: %d (%d + %d) %s", 
        name, level, health, max_health, max_health - equipped_armor.getStat(), equipped_armor.getStat(), equipped_armor, attack, attack - equipped_weapon.getStat(), equipped_weapon.getStat(), equipped_weapon,
        defense, defense - equipped_shield.getStat(), equipped_shield.getStat(), equipped_shield);
    }

    public String stats(){
        return String.format("Player: %s (LVL %d)\nHealth: %d/%d | Attack: %d | Defense: %d", 
        name, level, health, max_health, attack, defense);
    }
}
