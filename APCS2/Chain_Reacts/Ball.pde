class Ball {

  final static int MOVING = 0; //controls which state is which 
  final static int GROWING = 1; 
  final static int SHRINKING = 2; 
  final static int DEAD = 3; 

  final float CHANGE_FACTOR = .25;  //what factor ball is increasing/decreasing by
  final float MAX_RADIUS = 50; //max possible radius 

  color c;  //color  
  float x;  //x coordinates
  float y;  //y coordinates
  float dx; //speed of x
  float dy; //speed of y
  float rad; //radius 
  int state; //current state

  Ball() {
    state = MOVING;  //each ball start out moving (except for the click ball)
    float r = random(256); //random rgb values
    float g = random(256);
    float b = random(256);
    c = color ( r, g, b);  //random color
    rad = 10; //set radius of 10 
    x = random( (width - rad) );  //set random coordinates off borders
    y = random( (height - rad) );
    dx = random(20) - 10;  //random speed
    dy = random(20) - 10;
  }

  void bounce() {
    if (x <= 0 || x >= 600 ) {  //if encounter borders 
      dx = -dx; //bounce off
    }
    if (y <= 0 || y >= 600 ) {
      dy = -dy;
    }
  }

  void move() {
    x += dx;   //move by adding speed to coordinates
    y += dy;
    bounce();  //check for bounce 
  }

  boolean isTouching( Ball other ) { 
    return ((!(this.equals(other))) &&  //current ball is not the same as other ball
      (other.state == GROWING || other.state == SHRINKING) &&  //other ball is growing or shrinking
      ((this.rad + other.rad) >= (dist(this.x, this.y, other.x, other.y))) &&  //balls are touching
      (state == MOVING));  //this ball is moving
  }

  void run() {
    if ( state == MOVING ) {  //moving functions 
      move();  //run move 
      fill(c);  //fill with color 
      noStroke();  //remove borders
      ellipse(x, y, 2*rad, 2*rad);  //create the circle
    } 
    if ( state == GROWING ) {  //growing functions 
      if ( rad < MAX_RADIUS ) {  //if haven't reach max radius
        rad += CHANGE_FACTOR;  //increment radius
      } else {
        state = SHRINKING;  //otherwise start shrinking
      }
      fill(c);
      noStroke();
      ellipse(x, y, 2*rad, 2*rad);
    }
    if ( state == SHRINKING ) {  //shrinking functions 
      if ( rad > 0 ) {  //if radius isn't 0 yet
        rad -= CHANGE_FACTOR;  //decrement radius 
      } else {
        state = DEAD;  //once it reaches zero it dies and no longer runs creation functions
      }
      fill(c);
      noStroke();
      ellipse(x, y, 2*rad, 2*rad);
    }
  }
}
