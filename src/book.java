import java.awt.*;

public class book {

    public Image pic;
    public int dx = -1;
    public int dy = 0;
    public int xpos;
    public int ypos;
    public int width = 75;
    public int height = 75;
    public boolean isAlive = false;
    public Rectangle rec;

    public book(int paramXpos, int paramYpos){
        xpos = paramXpos;
        ypos = paramYpos;
        rec = new Rectangle (xpos, ypos, width, height);
    }

    public void move(){

        xpos = xpos + dx;
        ypos = ypos + dy;
    }


}