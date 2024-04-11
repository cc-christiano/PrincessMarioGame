import java.awt.*;

public class score {
    public Image pic;
    public int dx = 0;
    public int dy = 0;
    public int xpos;
    public int ypos;
    public int width = 250;
    public int height = 75;
    public boolean isAlive = true;
    public Rectangle rec;

    public score(int paramXpos, int paramYpos){
        xpos = paramXpos;
        ypos = paramYpos;
        rec = new Rectangle (xpos, ypos, width, height);
    }
}
