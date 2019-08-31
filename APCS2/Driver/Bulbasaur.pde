class Bulbasaur extends Monster {

  Bulbasaur( float maxH ) {
    super(maxH);
    front = loadImage("fbulbasaur.png");
    back = loadImage("bbulbasaur.png");
  }

  Monster evolve() {
    Monster evov = new Ivysaur(maxHp + 20);
    return evov;
  }
  
}
