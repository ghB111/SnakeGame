class Snake {
  int x,y;
  int dir;
  ArrayList<block> tail;
  
  Snake() {
    x = width/2;
    y = height/2;
    dir = 0;  
    tail = new ArrayList<block>();
  }
  
  void eat(){
    tail.add(new block()); 
  }
  
  void show() {
    fill(19,188,59); push();
   rect(x,y,scale,scale);
    fill(255);
    if(dir==0){
    circle(x+scale/4,y+scale/4,18);
    circle(x+scale/4+scale/2,y+scale/4,18);}
    if (dir==1) {
    circle(x+3*scale/4,y+scale/4,18);
    circle(x+scale/4+scale/2,y+3*scale/4,18);}
    if (dir==2) {
    circle(x+scale/4,y+3*scale/4,18);
    circle(x+3*scale/4,y+3*scale/4,18);
    }
    if (dir==3) {
    circle(x+scale/4,y+scale/4,18);
    circle(x+scale/4,y+3*scale/4,18);
    }
    pop();
   for (int i = 0; i<tail.size(); i++) {
     tail.get(i).show();  
   }
  }
  void update() {
   if(tail.size()>0){ updatetail();};
   switch(dir) {
    case 0:
      y-=scale;break;
    case 1:
      x+=scale;break;
    case 2:
      y+=scale;break;
    case 3:
      x-=scale;break;
   }
   if (x>=width) {x=0;}
   if (y>=height) {y=0;}
   if (x<0) {x=width;}
   if (y<0) {y=height;}
  };
  
  void updatetail() {
    for (int i = tail.size()-1; i > 0; i--) {
        tail.get(i).x = tail.get(i-1).x;
        tail.get(i).y = tail.get(i-1).y;
    }
    tail.get(0).x = x;
    tail.get(0).y = y;
  
}
  
  void checkkey() {
   if (keyPressed) {
    if ((key=='W' || key == 'w' || key=='ц'|| key=='Ц' || keyCode==UP)&&(tail.size()<2 || dir!=2)) {dir = 0;}
    if ((key=='D' || key == 'd' || key=='в'|| key=='В' || keyCode==RIGHT)&&(tail.size()<2 || dir!=3)) {dir=1;}
    if ((key=='S' || key == 's' || key=='ы'|| key=='Ы' || keyCode==DOWN)&&(tail.size()<2 || dir!=0)) {dir=2;}
    if ((key=='A' || key == 'a' || key=='ф'|| key=='Ф' || keyCode==LEFT)&&(tail.size()<2 || dir!=1)) {dir=3;}
   }
  }
  
  boolean dead() {
     
    for(int i = 0; i < tail.size(); i++) {
     if (x==tail.get(i).x && y==tail.get(i).y) {return true;} 
    }
    return false;
  }
  
};
