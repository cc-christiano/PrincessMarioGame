import java.awt.*;

public class box {

    public Image pic;
    public int dx = -1;
    public int dy = 0;
    public int xpos;
    public int ypos;
    public int width = 75;
    public int height = 75;
    public boolean isAlive = false;
    public Rectangle rec;

    public box(int paramXpos, int paramYpos){
        xpos = paramXpos;
        ypos = paramYpos;
        rec = new Rectangle (xpos, ypos, width, height);
    }

    public void move(){
        xpos = xpos + dx;
        ypos = ypos + dy;

//        if(ypos < 0){
//            dy = -dy;
//        }
//
//        if(xpos < 0){
//            dx = -dx;
//        }
//
//        if(ypos + height > 700){
//            dy = -dy;
//        }
//
//        if(xpos + width > 1000){
//            dx = -dx;
//        }

        rec = new Rectangle(xpos, ypos, width, height);

    }
}
