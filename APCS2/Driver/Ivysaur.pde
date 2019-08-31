class Ivysaur extends Bulbasaur {

  Ivysaur( float maxH ) {
    super(maxH);
    front = loadImage("fivysaur.png");
    back = loadImage("bivysaur.png");
  }

  Monster evolve() {
    Monster evov = new Venusaur(maxHp + 20);
    return evov;
  }
}
