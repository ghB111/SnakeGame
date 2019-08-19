void grid() {
  for (int i = scale; i < width; i+=scale) {
   line(i,0,i,height);
   line(0,i,width,i);
  }
    
};
void showfruits() {
   for(int i = 0;i < fruits.size(); i++) {
    fruits.get(i).show(); 
   }  
};

void destroyfruit(int i) {
  fruits.remove(i);
  fruits.add(new Fruit());
};
void death() {
   background(0);
   textSize(55);
   fill(252,3,7);
   text("YOU DIED", width/3,height/2);
   text("Your score : " + score,width/4,2*height/3);
};
void showScore() {
  int sc = score;
  int h = scale-scale/4;
  textSize(34);
  fill(17,184,247,170);
  if(!dead)
  {
    for (int i = 1; i<=5; i++) {
      text(int(sc%10),width-i*scale+scale/4,h);
      sc/=10;
    }
  }
};
