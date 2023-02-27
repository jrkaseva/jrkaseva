package main;

public class Entity {
    protected String name = "jr. Kaseva";
    protected int level = 1;
    protected int health = 1;
    protected int max_health = 1;
    protected int attack = 1;
    protected int defense = 1;

    public Entity(){}

    /**
     * @param level of Entity
     * @param health of Entity
     * @param attack of Entity
     * @param defense of Entity
     */
    public Entity(String name, int level, int health, int attack, int defense) {
        this.name = name;
        this.level = level;
        this.health = health;
        max_health = health;
        this.attack = attack;
        this.defense = defense;
    }

    /**
     * @return level of Entity
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * @return health of Entity
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health of Entity
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return attack of Entity
     */
    public int getAttack() {
        return attack;
    }

    /**
     * @param attack of Entity
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * @return defense of Entity
     */
    public int getDefense() {
        return defense;
    }

    /**
     * @param defense of Entity
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Entity's health is attacked
     */
    public void takeDamage(int incoming_damage){
        int post_mitigated_damage = incoming_damage - defense;
        if (post_mitigated_damage > 0) health -= post_mitigated_damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMax_health() {
        return max_health;
    }

    public void setMax_health(int max_health) {
        this.max_health = max_health;
    }
}
