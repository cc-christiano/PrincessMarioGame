import java.awt.*;

public class Book {

    public Image pic;
    public int dx = -2;
    public int dy = 0;
    public int xpos;
    public int ypos;
    public int width = 75;
    public int height = 75;
    public boolean isAlive = true;
    public Rectangle rec;

    public Book(int paramXpos, int paramYpos){
        xpos = paramXpos;
        ypos = paramYpos;
        rec = new Rectangle (xpos, ypos, width, height);
    }

    public void move(){

        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle (xpos, ypos, width, height);

    }


}
