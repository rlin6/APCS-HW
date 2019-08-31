//Team 3: Team Thinking Entity - Jude Grodesky, Brandon Chong, Ricky Lin
//APCS1 pd02
//HW28 -- Ye Olde Role Playing Game
//2017-11-09

public class Monster{

    //Attributes every Monster has
    private int HP;
    private int strength;
    private int defense;
    private int attack_rating;

    //Inintializes instance varibales
    public Monster() {
	HP = 150;
	strength = (int)(Math.random() * 45) + 20;
	defense = 20;
	attack_rating = 1;
    }

    //Returns whether the Monster is alive or dead ( 0 HP )
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

    //Reduces the Monster's HP
    public void lowerHP(int loss){
	HP -= loss;
    }

    //Attacks a warrior and deals damage
    public int attack(Protagonist w){
	int damage = (int)(strength * attack_rating) - w.getDefense();
	if (damage < 0){
	    damage = 0;
	}
	w.lowerHP(damage);
	return damage;
    }
}
