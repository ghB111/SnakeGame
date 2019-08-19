class block {
  int x,y;
  int dir; 
  block () {
    x=0;
    y=0;
  }
  void show(){
    rect(x,y,scale,scale); 
  }
};
