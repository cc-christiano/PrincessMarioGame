import java.awt.*;

public class Princess {

    public String name;
    public int xpos;
    public int ypos;
    public int dx = 0;
    public int dy = 2;
    public int height = 100;
    public int width = 75;
    public boolean isAlive;
    public Rectangle rec;
    public boolean isJumping = false;
    public boolean rightIsPressed;
    public boolean leftIsPressed;
    public int points = 0;



    public Princess(String paramName, int paramXpos, int paramYpos){
        name = paramName;
        xpos = paramXpos;
        ypos = paramYpos;
        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void move(){


    }




}
