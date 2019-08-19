final int scale = 40; 
final int wait = 720/4;
final int fruitAmount =3;

ArrayList<Fruit> fruits;
Snake snake;

int score = 0;
long time;
boolean dead = false;



void setup(){
  size(800,800);
  fruits = new ArrayList<Fruit>();
  snake = new Snake();
  time = millis();
  for (int i = 0; i<fruitAmount; i++) {
    fruits.add(new Fruit());
  }
};


void draw(){
  
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
