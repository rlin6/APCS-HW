class WorldScreen {

  PImage gym, pokeCenter, grass;
  int x, y;

  WorldScreen() {
    grass = loadImage("grass.png");
    grass.resize(20, 20);
    gym = loadImage("gym.png");
    gym.resize(112, 75);
    pokeCenter = loadImage("hospital.png");
    pokeCenter.resize(75, 75);
  }

  void grassFill() {
    for (int x= 0; x<800; x+=20) {
      for (int y= 0; y<800; y+=20) {
        image(grass, x, y);
      }
    }
  }

  void placeBuildings() {
    imageMode(CORNER);
    image(pokeCenter, 50, 50);
    image(gym, 465, 478);
  }

  void load() {
    background(#006400);
    grassFill();
    //horizontal road
    fill(198, 140, 83);
    noStroke();
    rect(0, 350, 300, 30);
    //roads to pokeCenter
    noStroke();
    rect(0, 125, 300, 30);
    noStroke();
    rect(285, 125, 30, 450);
    //path to pokeMart
    noStroke();
    //path to gym
    rect(300, 545, 300, 30);
    noStroke();
    placeBuildings();
  }
}
