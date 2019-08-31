class Monster { 

  protected float hp, maxHp;
  protected PImage front, back;

  Monster(float maxH) {
    maxHp = maxH;
    hp = maxH;
  }

  //Accessors 

  float getHp() {
    return hp;
  }

  float getMaxHp() {
    return maxHp;
  }

  PImage getFront() {
    return front;
  }

  PImage getBack() {
    return back;
  }

  //Mutators

  float setHp( float newHp ) {
    float temp = hp;
    hp = newHp;
    return temp;
  }

  float setMaxHp( float newMaxHp) {
    float temp = maxHp;
    maxHp = newMaxHp;
    return temp;
  }

  //Attack method 

  void attack(Monster other) {
    int multi = (int) (random(0, 1));
    other.setHp( multi * this.getMaxHp() );
  }

  //To be healed

  void heal() {
    hp = maxHp;
  }
}
