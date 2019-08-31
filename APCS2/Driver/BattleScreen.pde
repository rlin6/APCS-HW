class BattleScreen {

  protected PImage background, front, back;
  protected Monster mine, yours;
  protected Queue<Monster> output;
  protected int action;
  protected boolean end, swap;

  BattleScreen(Queue<Monster> x, LLStack<Monster> y) {
    background = loadImage("battle.png");
    background.resize(600, 400);
    mine = x.dequeue();
    yours = y.pop();  
    back = mine.getBack();
    back.resize(300, 300);
    front = yours.getFront();
    front.resize(200, 200);
    action = 0;
    output = x;
    end = false;
    swap = false;
  }

  void load() {
    rect(0, 400, 300, 200, 10, 10, 10, 10);
    rect(300, 400, 300, 132, 10, 10, 10, 10);
    //rect(450, 400, 150, 66, 10, 10, 10, 10);
    //rect(300, 466, 150, 66, 10, 10, 10, 10);
    //rect(450, 466, 150, 66, 10, 10, 10, 10);
    rect(300, 532, 150, 66, 10, 10, 10, 10);
    rect(450, 532, 150, 66, 10, 10, 10, 10);
    display();
  }

  void display() {
    fill(0, 0, 0);
    imageMode(CORNER);
    image(background, 0, 0);
    image(front, 380, 60);
    image(back, 0, 205);
    textSize(20);
    text("Switch", 475, 560);
    text("Catch", 325, 560);
    textSize(40);
    text("What will", 50, 475);
    text("you do?", 60, 550);
    text("Attack", 390, 476); 
    fill(222, 184, 135);
    update();
  }

  void update() {
    if (action != 0) {
      if (action == 1) {
        mine.attack(yours);
        yours.attack(mine);
        // result();
      } else if (action == 2) {
        catching(mine, yours);
      } else if (action == 3) {
        // switch();
      }
    }
  }

  void mouseClicked() {
    if (mouseX > 300 && mouseX < 600 && mouseY > 400 && mouseY < 532) {
      action = 1;
    }
    if (mouseX > 300 && mouseX < 450 && mouseY > 532 && mouseY < 600) {
      action = 2;
    }
    if (mouseX > 450 && mouseX < 600 && mouseY > 532 && mouseY < 600) {
      action = 3;
    }
  }

  void catching( Monster my, Monster other ) {
    if (ash.getParty().size() < 6) { 
      float chance = random(0, 1);
      if ( 5 == 5
      //chance >= ( other.getHp() / other.getMaxHp() )
      ) {
        other.setHp( other.getMaxHp() );
        output.enqueue(other);
        output.enqueue(my);
        end = true;
      }
    }
  }

  void swap() {
    output.enqueue(mine);
    end = true;
  }

  boolean getEnd() {
    return end;
  }

  boolean getSwap() {
    return swap;
  }

  Queue<Monster> getOutput() {
    return output;
  }
  
  boolean setEnd( boolean newE ) {
    boolean temp = end;
    end = newE;
    return temp;
  }
}
