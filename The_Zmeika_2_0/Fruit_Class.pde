class Fruit {
    int x,y;
    void show() {
      fill(255,113,220);
     rect(x,y,scale,scale);  
    }
    Fruit() {
      boolean notok = true;
      while(notok) {
        notok = false;
        x = int(random(0,width/scale))*scale;
        y = int(random(0,height/scale))*scale;
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
