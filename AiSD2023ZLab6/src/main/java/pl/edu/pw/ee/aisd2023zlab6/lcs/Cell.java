package pl.edu.pw.ee.aisd2023zlab6.lcs;

public class Cell {
    int value;
    direction direction;
    
    public void Cell(){
        value = 0;
        direction = direction.NONE;
    }
    
    public void setValue(int value){
        this.value = value;
       
    }
    
    public void setDirection(enum direction){
        this.direction = direction;
    }
}

enum direction{
    LEFT,
    TOP,
    DIAGONAL,
    NONE;
}
