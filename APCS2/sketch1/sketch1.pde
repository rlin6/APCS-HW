//Puneet Johal, Ricky Lin
//APCS2 pd02
//HW #52: Grow & Shrink
//2018-05-23 W 

boolean grow;  //variable controlling if it's time to grow or not
float _size;  //controls current size of circle
color c;  //color value of circle

void setup() {
  background(0);  //set background to black 
  grow = false;   //circle doesn't grow at first
  _size = 0;  //circle has no size yet
  size(1000, 1000);  //change size of screen to see clearer  
  c = color(212, 175, 55);  //set color to golden
  fill(c);  //fill in the color of circle
}

void draw() {
  clear();  //clear at the beginning to get rid of bands
  if (grow == true) {  //if allowed to grow 
    ellipse(500, 500, _size++, _size++);  //create new circle with increasing size 
  } else {  //if shrinking
    if ( _size >= 0 ) {  //ensures the circle doesn't go into negative size which would only make it grow again
      ellipse(500, 500, _size--, _size--);  //create new circle with decreasing size 
    }
  }
}

void mouseClicked() {  //when you click and the circle is growing, change grow to make it shrink and vice versa 
  if (grow == false) {  
    grow = true;
  } else {
    grow = false;
  }
}
