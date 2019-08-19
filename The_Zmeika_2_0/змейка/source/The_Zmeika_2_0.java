import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class The_Zmeika_2_0 extends PApplet {

final int scale = 40; 
final int wait = 720/4;
final int fruitAmount =3;

ArrayList<Fruit> fruits;
Snake snake;

int score = 0;
long time;
boolean dead = false;



public void setup(){
  
  fruits = new ArrayList<Fruit>();
  snake = new Snake();
  time = millis();
  for (int i = 0; i<fruitAmount; i++) {
    fruits.add(new Fruit());
  }
};


public void draw(){
  
  if (focused) {
    if (dead) {delay(2500);exit();}
    background(254,255,10);
    strokeWeight(6);
    grid();
    showfruits();
    snake.show();
    snake.checkkey();
    if (snake.dead()) { delay(1000); death(); dead=true;} else {
      if (millis()>=time+wait) {
        for (int i = 0; i < fruits.size(); i++){
          if (fruits.get(i).x==snake.x && fruits.get(i).y==snake.y) {snake.eat(); score+=100; destroyfruit(i);}
          }
        snake.update(); time=millis();}
    
        }
    
  }
  push();
  showScore();
  pop();
};
class block {
  int x,y;
  int dir; 
  block () {
    x=0;
    y=0;
  }
  public void show(){
    rect(x,y,scale,scale); 
  }
};
class Fruit {
    int x,y;
    public void show() {
      fill(255,113,220);
     rect(x,y,scale,scale);  
    }
    Fruit() {
      boolean notok = true;
      while(notok) {
        notok = false;
        x = PApplet.parseInt(random(0,width/scale))*scale;
        y = PApplet.parseInt(random(0,height/scale))*scale;
        for (int i = 0; i < fruits.size(); i++) {
         if (x==fruits.get(i).x&&y==fruits.get(i).y) {notok=true; break;} //еще проверка на тело
       }
        if (!notok) {
          for (int i = 0; i<snake.tail.size(); i++ ) {
            if (x==snake.tail.get(i).x && y==snake.tail.get(i).y ) {notok = true; break;}
           }
         }  
       }
    }
};
public void grid() {
  for (int i = scale; i < width; i+=scale) {
   line(i,0,i,height);
   line(0,i,width,i);
  }
    
};
public void showfruits() {
   for(int i = 0;i < fruits.size(); i++) {
    fruits.get(i).show(); 
   }  
};

public void destroyfruit(int i) {
  fruits.remove(i);
  fruits.add(new Fruit());
};
public void death() {
   background(0);
   textSize(55);
   fill(252,3,7);
   text("YOU DIED", width/3,height/2);
   text("Your score : " + score,width/4,2*height/3);
};
public void showScore() {
  int sc = score;
  int h = scale-scale/4;
  textSize(34);
  fill(17,184,247,170);
  if(!dead)
  {
    for (int i = 1; i<=5; i++) {
      text(PApplet.parseInt(sc%10),width-i*scale+scale/4,h);
      sc/=10;
    }
  }
};
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
  
  public void eat(){
    tail.add(new block()); 
  }
  
  public void show() {
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
  public void update() {
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
  
  public void updatetail() {
    for (int i = tail.size()-1; i > 0; i--) {
        tail.get(i).x = tail.get(i-1).x;
        tail.get(i).y = tail.get(i-1).y;
    }
    tail.get(0).x = x;
    tail.get(0).y = y;
  
}
  
  public void checkkey() {
   if (keyPressed) {
    if ((key=='W' || key == 'w' || key=='ц'|| key=='Ц' || keyCode==UP)&&(tail.size()<2 || dir!=2)) {dir = 0;}
    if ((key=='D' || key == 'd' || key=='в'|| key=='В' || keyCode==RIGHT)&&(tail.size()<2 || dir!=3)) {dir=1;}
    if ((key=='S' || key == 's' || key=='ы'|| key=='Ы' || keyCode==DOWN)&&(tail.size()<2 || dir!=0)) {dir=2;}
    if ((key=='A' || key == 'a' || key=='ф'|| key=='Ф' || keyCode==LEFT)&&(tail.size()<2 || dir!=1)) {dir=3;}
   }
  }
  
  public boolean dead() {
     
    for(int i = 0; i < tail.size(); i++) {
     if (x==tail.get(i).x && y==tail.get(i).y) {return true;} 
    }
    return false;
  }
  
};
  public void settings() {  size(800,800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "The_Zmeika_2_0" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
