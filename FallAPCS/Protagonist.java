//Team 3: Team Thinking Entity - Jude Grodesky, Brandon Chong, Ricky Lin
//APCS1 pd02
//HW28 -- Ye Olde Role Playing Game
//2017-11-09

public class Protagonist{
    
    //Attributes every Protagonist needs
    private String name;
    private int HP;
    private int strength;
    private int defense;
    private double attack_rating;
    
    //constructor (customizable name)
    public Protagonist (String newName){
	name = newName;
	HP = 100;
	strength = 50;
	defense = 50;
	attack_rating = 0.5;
    }

    //Returns whether the Protagonist is alive or dead ( 0 HP )
    public boolean isAlive(){
	if (HP <= 0){
	    return false;
	}
	else{
	    return true;
	}
    }

    //Accessor for defense
    public int getDefense(){
	return defense;
    }
    
    //Accessor for name
    public String getName(){
	return name;
    }

    //Reduces the Protagonist's HP
    public void lowerHP(int loss){
	HP -= loss;
    }

    //Attacks a monster and deals damage
    public int attack(Monster m){
	int damage = (int)(strength * attack_rating) - m.getDefense();
	if (damage < 0){
	    damage = 0;
	}
	m.lowerHP(damage);
	return damage;
    }

    //Ready's a special attack by reducing defence and increasing attack
    public void specialize(){
	defense -= 25;
	attack_rating += 0.5;
    }
    
    //Resets for a normal attack by reducing attack and increasing defense
    public void normalize(){
	defense += 25;
	attack_rating -= 0.5;
    }

    
    
}
