Ball[] ballz;  //array to hold balls
boolean firstClick;  //controls if first click happened

void setup() {
  size(600, 600);  //screen size 
  int num =(int)(random(25, 36));  //random number of balls
  ballz = new Ball[num];  //init ballz as array of num balls 
  for (int i = 0; i < ballz.length; i++) {  //iterate through array
    ballz[i] = new Ball();  //init each ball in array 
  }
  ballz[0].state = Ball.DEAD;  //first ball set as DEAD to grow on click 
  firstClick = false;  //click has not happened yet
}

void mouseClicked() {
  if ( firstClick == false ) {  //if haven't clicked
    ballz[0].x = mouseX;  //DEAD ball created on coordinates of mouse
    ballz[0].y = mouseY;
    ballz[0].rad = 1;  //start with small radius
    ballz[0].state = Ball.GROWING;  //change its state to grow
    firstClick = true;  //first click has happened 
  }
}

void draw() {
  background(0);  //set background to black 
  for (int i = 0; i < ballz.length; i++) {  //iterate through each ball 
    for (int x = 0; x < ballz.length; x++) { //compare each ball to each other 
      if ( ballz[i].isTouching( ballz[x] )) {  //check if "touching" -see Ball
        ballz[i].state = Ball.GROWING;  //set first ball that touched to grow
        break;  //break out of checking against other balls 
      }
    }
    ballz[i].run();  //run the ball actions
  }
}
