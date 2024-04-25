import java.awt.*;

public class Castle {
    public Image pic;
    public int dx = -2;
    public int dy = 0;
    public int xpos;
    public int ypos;
    public int width = 300;
    public int height = 300;
    public boolean isAlive = true;
    public Rectangle rec;

    public Castle(int paramXpos, int paramYpos){
        xpos = paramXpos;
        ypos = paramYpos;
        rec = new Rectangle (xpos, ypos, width, height);
    }

    public void move(){
        xpos = xpos + dx;

        rec = new Rectangle(xpos, ypos, width, height);
    }
}
