import java.awt.*;

public class Princess {

    public String name;
    public int xpos;
    public int ypos;
    public int dx = 2;
    public int dy = 2;
    public int height = 200;
    public int width = 150;
    public boolean isAlive;
    public Rectangle rec;


    public Princess(String paramName, int paramXpos, int paramYpos){
        name = paramName;
        xpos = paramXpos;
        ypos = paramYpos;
        rec = new Rectangle(xpos, ypos, width, height);
    }
}
