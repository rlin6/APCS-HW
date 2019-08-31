class Venusaur extends Ivysaur {

  Venusaur( float maxH ) {
    super(maxH);
    front = loadImage("fivysaur.png");
    back = loadImage("bivysaur.png");
  }

  Monster evolve() {
    return null;
  }
}
