Player ash;

public enum Screens {
  WORLD, BATTLE, LOSE, WIN
}

Screens curr = Screens.WORLD;
WorldScreen Wscreen;
BattleScreen Bscreen;
boolean newBattle;

void setup() {
  size(600, 600);
  ash = new Player( fillParty(), fillWild() );
  newBattle = true;
  Wscreen = new WorldScreen();
}

void draw() {
  if (curr == Screens.WORLD) {
    Wscreen.load();
    ash.move();
    ash.display();
    popup();
  } else if (curr == Screens.BATTLE) {
    if (newBattle) {
      Bscreen = new BattleScreen( ash.getParty(), ash.getWild() );
      newBattle = false;
    }
    if (Bscreen.getEnd() == false) {
      Bscreen.load();
    }
    else { 
      ash.setParty( Bscreen.getOutput() );
      newBattle = true;
      if (Bscreen.getSwap() == false) {
        curr = Screens.WORLD;
        Bscreen.setEnd(false);
      }
    }
  }
}

LLStack<Monster> fillWild() {
  LLStack<Monster> wild = new LLStack<Monster>();
  Monster bulbasaur = new Bulbasaur(20);
  wild.push(bulbasaur);
  return wild;
}

Queue<Monster> fillParty() {
  Queue<Monster> party = new Queue<Monster>();
  Monster bulbasaur = new Bulbasaur(30);
  party.enqueue(bulbasaur);
  return party;
}

void popup() {
  int rand = (int)(random(300));
  if ( ! ( (ash.getX() > 50 && ash.getX() < 125 && ash.getY() > 50 && ash.getY() < 125) || 
    (ash.getX() > 465 && ash.getX() < 577 && ash.getY() > 478 && ash.getY() < 553) ||
    (ash.getX() > 0 && ash.getX() < 300 && ash.getY() > 350 && ash.getY() < 380) || 
    (ash.getX() > 0 && ash.getX() < 300 && ash.getY() > 125 && ash.getY() < 155) || 
    (ash.getX() > 285 && ash.getX() < 315 && ash.getY() > 125 && ash.getY() < 575 ) ||
    (ash.getX() > 300 && ash.getX() < 600 && ash.getY() > 545 && ash.getY() < 575 ) ) ) {
    if ( rand % 300 == 0 ) {
      curr = Screens.BATTLE;
    }
  }
}
