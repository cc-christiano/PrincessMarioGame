import java.awt.*;

public class castle {
    public Image pic;
    public int dx = -1;
    public int dy = 0;
    public int xpos;
    public int ypos;
    public int width = 200;
    public int height = 200;
    public boolean isAlive = true;
    public Rectangle rec;

    public castle(int paramXpos, int paramYpos){
        xpos = paramXpos;
        ypos = paramYpos;
        rec = new Rectangle (xpos, ypos, width, height);
    }

    public void move(){
        xpos = xpos + dx;

        rec = new Rectangle(xpos, ypos, width, height);
    }
}
