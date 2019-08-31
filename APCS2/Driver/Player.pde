class Player {
  int x, y;
  protected int pokeDollar, pokeBall;
  protected Queue<Monster> _party; 
  protected LLStack<Monster> _wild;
  protected PImage front, back, left, right, face;
  protected boolean pokeCenter, gym;

  Player( Queue<Monster> party, LLStack<Monster> wild ) {
    x = 20;
    y = 360;
    front = loadImage("fash.png");
    back = loadImage("bash.png");
    left = loadImage("lash.png");
    right = loadImage("rash.png");
    front.resize(30, 30);
    back.resize(30, 30);
    left.resize(30, 30);
    right.resize(30, 30);
    face = front;
    pokeDollar = 0;
    pokeBall = 0;
    _party = party;
    _wild = wild;
    pokeCenter = false;
    gym = false;
  }

  //Accessors

  int getX() {
    return x;
  }

  int getY() {
    return y;
  }

  int getPokeDollar() {
    return pokeDollar;
  }

  int getPokeBall() {
    return pokeBall;
  }

  Queue<Monster> getParty() {
    return _party;
  }

  LLStack<Monster> getWild() {
    return _wild;
  }

  boolean getPokeCenter() {
    return pokeCenter;
  }

  boolean getGym() {
    return gym;
  }

  //Mutators 

  int setX( int newX ) {
    int temp = x;
    x = newX;
    return temp;
  }

  int setY( int newY ) {
    int temp = y;
    y = newY;
    return temp;
  }

  int setPokeDollar( int newP ) {
    int temp = pokeDollar;
    pokeDollar = newP;
    return temp;
  }

  int setPokeBall( int newP ) {
    int temp = pokeBall;
    pokeBall = newP;
    return temp;
  }

  Queue<Monster> setParty( Queue<Monster> newP ) {
    Queue<Monster> temp = _party;
    _party = newP;
    return temp;
  }

  LLStack<Monster> setWild( LLStack<Monster> newW ) {
    LLStack<Monster> temp = _wild;
    _wild = newW;
    return temp;
  }

  boolean setPokeCenter( boolean newP ) {
    boolean temp = pokeCenter;
    pokeCenter = newP;
    return temp;
  }

  boolean setGym( boolean newG ) {
    boolean temp = gym;
    gym = newG;
    return temp;
  }

  //Animation methods
  
  void display() {
    imageMode(CENTER);
    image(face, x, y);
  }

  void move() {
    if (keyPressed) {
      if (key == CODED) {
        if (keyCode == UP) {
          y--;
          face = back;
        }
        if (keyCode == DOWN) {
          y++;
          face = front;
        }
        if (keyCode == LEFT) {
          x--;
          face = left;
        }
        if (keyCode == RIGHT) {
          x++;
          face = right;
        }
      }
    }
    boundary();
   // checkPos();
  }

  void boundary() {
    if (x > 600) {
      x--;
    }
    if (x < 0) {
      x++;
    }
    if (y > 600) {
      y--;
    }
    if (y < 0) {
      y++;
    }
  }

  /*void checkPos() {
    if (x >=50 && x <= 125 && y>= 50 && y<= 125) {
      pokeCenter = true;
    } else if { x
      shop = false;
      hospital = false;
    }
  }*/
  
}
