import java.awt.*;

//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************

public class GameHQ implements Runnable, KeyListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public boolean gamePlaying = false;
    public boolean gameOver1 = false;
    public boolean isPaused = false;
    public boolean gameOver2 = false;

    public Princess belle;
    public Image bellePic;
    public Image belleVill;
    public Box[] boxes;
    public Box[] boxes2;
    public Box[] boxesJump;
    public Book book1;
    public Image book1Pic;
    public Score score;
    public Image scorePic;
    public Image startScreen;
    public Image winScreenC;
    public Castle castle;
    public Image castlePic;
    public Book[] books;
    public SoundFile song;
    public Princess cinderella;
    public Image cinderellaPic;
    public Image palace;
    public Image endGameC;
    public Castle castleC;
    public Image castleCPic;


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        GameHQ ex = new GameHQ();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public GameHQ() { // BasicGameApp constructor

        setUpGraphics();
        canvas.addKeyListener(this);
        startScreen = Toolkit.getDefaultToolkit().getImage("startScreen2.png");
        belle = new Princess("belle", 200, 700);
        belle.ypos = 700-belle.height;
        bellePic = Toolkit.getDefaultToolkit().getImage("Belle.png");
        belleVill = Toolkit.getDefaultToolkit().getImage("belleVillage2.png");
        boxes = new Box[10000];
        boxes2 = new Box[10000];
        boxesJump = new Box[10000];
        book1 = new Book(450,300);
        book1Pic = Toolkit.getDefaultToolkit().getImage("book2.png");
        score = new Score(50,50);
        scorePic = Toolkit.getDefaultToolkit().getImage("score.png");
        castle = new Castle(3400,400);
        castlePic = Toolkit.getDefaultToolkit().getImage("castle.png");
        books = new Book[10000];
        song = new SoundFile("Arcade Action 05.wav");
        cinderella = new Princess ("cinderella", 0,700);
        cinderella.ypos = 700-cinderella.height;
        cinderellaPic = Toolkit.getDefaultToolkit().getImage("cinderella.png");
        palace = Toolkit.getDefaultToolkit().getImage("palace.png");
        endGameC = Toolkit.getDefaultToolkit().getImage("endScreenC.png");
        castleC = new Castle(6800,400);
        castleCPic = Toolkit.getDefaultToolkit().getImage("castleC.png");

        for(int x = 0; x < boxes.length; x = x +1) {
            boxes[x] = new Box(x * 75 + 775, 625);
            boxes[x].pic = Toolkit.getDefaultToolkit().getImage("box.png");
        }

        for(int x = 0; x < boxes.length; x = x +1) {
            if (Math.random() < .15 && boxes[x].isAlive == false && x < boxes.length-2) {
                boxes[x].isAlive = true;
                boxes[x+1].isAlive = true;
                boxes[x+2].isAlive = true;
            }

        } //level 1

        for(int x = 0; x < boxes2.length; x = x +1){
            boxes2[x] = new Box(x*75 + 850, 550);
            boxes2[x].pic = Toolkit.getDefaultToolkit().getImage("box.png");

        }//level 2

        for(int x = 0; x < boxes2.length; x = x +1) {
            if (Math.random() < .15 && boxes2[x].isAlive == false && x < boxes2.length - 2) {
                boxes2[x].isAlive = true;
                boxes2[x + 1].isAlive = true;
                boxes2[x+2].isAlive = true;
            }
        }

        for(int x = 0; x < boxesJump.length; x = x +1){
            boxesJump[x] = new Box(x*75 + 370, 370);
            boxesJump[x].pic = Toolkit.getDefaultToolkit().getImage("box.png");

        }//boxes too jump on top off

        for(int x = 0; x < boxesJump.length; x = x +1) {
            if (Math.random() < .1 && boxesJump[x].isAlive == false && x < boxesJump.length - 2){
                boxesJump[x].isAlive = true;
                boxesJump[x + 1].isAlive = true;
                boxesJump[x + 2].isAlive = true;
            }
        }

        for(int x = 0; x < books.length; x = x + 1){
            books[x] = new Book((int)(Math.random()*1000000), (int)(Math.random()*700));
            books[x].pic = Toolkit.getDefaultToolkit().getImage("book2.png");
        } //books

//        for(int x = 0; x < books.length; x = x + 1){
//            if (Math.random() < 0.0000001 && books[x].isAlive == false){
//                books[x].isAlive = true;
//            }
//        }

        winScreenC = Toolkit.getDefaultToolkit().getImage("endGame.png");

        //variable and objects
        //create (construct) the objects needed for the game

    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            if (gamePlaying == true && gameOver2 == false) {
                moveThings();  //move all the game objects
            }
            render();  // paint the graphics
            pause(10); // sleep for 10 ms

        }
    }

    public void moveThings() {
        //call the move() code for each object
        belle.rec = new Rectangle(belle.xpos, belle.ypos, belle.width, belle.height);

        if(belle.ypos <=0 && belle.isJumping == true) {
            belle.isJumping = false;
        } // not to go above ceiling
        else if(belle.ypos >= 700 - belle.height && belle.isJumping == false){
            belle.ypos = 700 - belle.height;
        } // not go below floor

        else {
            if (belle.dy < 9) {
                belle.dy = belle.dy + 1;
            } // speed fall if below terminal velocity
            if (belle.isJumping == true && belle.ypos >= 700 - belle.height) {
                belle.isJumping = false;
            } // landing on the floor
        }

        for (int x = 0; x < boxes.length; x++) {
            if (belle.rec.intersects(boxes[x].rec) == true && boxes[x].isAlive == true) {
                belle.ypos = boxes[x].ypos - belle.height;
                belle.isJumping = false;
            }
        } //level 1

        for (int x = 0; x < boxes2.length; x++) {
            if (belle.rec.intersects(boxes2[x].rec) == true && boxes2[x].isAlive == true) {
                belle.ypos = boxes2[x].ypos - belle.height;
                belle.isJumping = false;
            }
        }//level 2

        for (int x = 0; x < boxesJump.length; x++) {
            if (belle.rec.intersects(boxesJump[x].rec) == true && boxesJump[x].isAlive == true) {
                belle.ypos = boxesJump[x].ypos - belle.height;
                belle.isJumping = false;
            }
        } //boxes to jump on

        for(int x = 0; x < books.length; x++){
            if(belle.rec.intersects(books[x].rec)==true && books[x].isAlive == true){
                belle.points = belle.points + 200;
                books[x].isAlive = false;
                song.play();
            }

        } // getting the book

        belle.ypos = belle.ypos + belle.dy;

        //jumping
        if(belle.rec.intersects(castle.rec)){
            gameOver1 = true;
            levelup();
            gameOver1 = false;
        } //belle getting book
        belle.xpos = belle.xpos +belle.dx;
        if(belle.rightIsPressed == true) {
            belle.dx = 2;
        } else if(belle.leftIsPressed == true){
            belle.dx = -2;
        } else {
            belle.dx = 0;
        }
        //right and left

        if(belle.xpos < 0){
            belle.xpos = 0;
        }

        else if(belle.xpos > 1000 - belle.width){
            belle.xpos = 1000 - belle.width;
        }
        else{
            belle.xpos = belle.xpos + belle.dx;

        }

        for(int x = 0; x < boxes.length; x++) {
            boxes[x].move();
        }//side scroller boxes1

        for(int x = 0; x < boxes2.length; x++){
            boxes2[x].move();
        }//side scroller boxes2

        for(int x = 0; x < boxesJump.length; x++){
            boxesJump[x].move();
        }//side scroller boxes 3

        for(int x = 0; x < books.length; x++){
            books[x].move();
        } // side scroller books

        if(belle.rec.intersects(castleC.rec)){
            gameOver2 = true;
        } //ending game

        castle.move(); //side scroller castle
        castleC.move(); // side scroller for cinderella castle
    }

    public void levelup() { // change from belle level to cinderella level
        if(gameOver1 == true) {
            bellePic = Toolkit.getDefaultToolkit().getImage("cinderella.png");
            belleVill = Toolkit.getDefaultToolkit().getImage("palace.png");
            for(int x = 0; x < books.length; x = x + 1){
                books[x].pic = Toolkit.getDefaultToolkit().getImage("glassSlipper.png");
            }

            for(int x = 0; x < boxes.length; x = x +1) {
                boxes[x].pic = Toolkit.getDefaultToolkit().getImage("blueBox.png");
            }

            for(int x = 0; x < boxes2.length; x = x +1){
                boxes2[x].pic = Toolkit.getDefaultToolkit().getImage("blueBox.png");

            }

            for(int x = 0; x < boxesJump.length; x = x +1){
                boxesJump[x].pic = Toolkit.getDefaultToolkit().getImage("blueBox.png");

            }

            castle.isAlive = false;

        }
    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        if(gamePlaying == false){
            // gamge instructions
            // start screen background image
            // "press enter to begin"
            g.drawImage(startScreen,0,0,1000,700,null);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Snell RoundHand", Font.PLAIN, 60));
            g.drawString("Press enter to begin!!", 250,200);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            g.drawString("You are Belle!", 400, 300 );
            g.drawString("Try to collect as many books as possible before you reach the castle!", 125, 330);
            g.drawString("Press the right arrow to go right", 270, 360);
            g.drawString("Press the left arrow to go left", 300,390);
            g.drawString("Press the up arrow to jump", 330,420);
            g.drawString("Have fun!", 400,450);



        } // start screen

        else if(gamePlaying == true && gameOver1 == false && gameOver2 == false) {

            g.drawImage(belleVill, 0, 0, 1000, 700, null);
            g.drawImage(bellePic, belle.xpos, belle.ypos, belle.width, belle.height, null);
//                g.drawRect(belle.rec.x, belle.rec.y, belle.rec.width, belle.rec.height);
            for (int x = 0; x < boxes.length; x++) {
                if (boxes[x].isAlive == true) {
                    g.drawImage(boxes[x].pic, boxes[x].xpos, boxes[x].ypos, boxes[x].width, boxes[x].height, null);
                }

            } //level1
            for (int x = 0; x < boxes2.length; x++) {
                if (boxes2[x].isAlive == true) {
                    g.drawImage(boxes2[x].pic, boxes2[x].xpos, boxes2[x].ypos, boxes2[x].width, boxes2[x].height, null);
                }

            } // level 2
            for (int x = 0; x < boxesJump.length; x++) {
                if(boxesJump[x].isAlive == true){
                    g.drawImage(boxesJump[x].pic, boxesJump[x].xpos, boxesJump[x].ypos, boxesJump[x].width, boxesJump[x].height, null);
                }

            } //boxes to jump on

            for(int x = 0; x < books.length; x++){
                if (books[x].isAlive == true) {
                    g.drawImage(books[x].pic, books[x].xpos, books[x].ypos, books[x].width, books[x].height, null);
                }
            } //books


            g.drawImage(scorePic, score.xpos, score.ypos, score.width, score.height, null);
            g.setFont(new Font("Times Roman", Font.PLAIN, 30));
            g.setColor(Color.BLACK);
            g.drawString("Points: " + belle.points, 57, 100);
            g.drawImage(castlePic, castle.xpos, castle.ypos, castle.width, castle.height, null);
            g.drawImage(castleCPic, castleC.xpos, castleC.ypos, castleC.width, castleC.height, null);

        }
        // game play


        else if(gameOver2 == true && gameOver1 == false){
            g.drawImage(winScreenC, 0, 0, 1000, 700, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Snell RoundHand", Font.PLAIN, 60));
            g.drawString("YOU WIN!", 380, 450);
        } // you win screen after belle goes into castle and also this leads to next level



        g.dispose();
        bufferStrategy.show();
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();
        System.out.println("key pressed is " + key + ", Key Code: " + keyCode);

        if(keyCode == 38 && belle.isJumping == false){
            belle.isJumping = true;
            belle.dy = -20;
        } // space bar

        if(keyCode == 39){
            belle.rightIsPressed = true;

        } // right arrow key

        if(keyCode == 37) {
            belle.leftIsPressed = true;
        } // left arrow key
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == 39){
            belle.rightIsPressed = false;
        } // right arrow key

        if(keyCode == 37){
            belle.leftIsPressed = false;
        } // left arrow key

        if(keyCode == 10){
            gamePlaying = true;
        } // enter key

        if(keyCode == 67){
            gameOver1 = true;
            gamePlaying = false;
        } // c key
    }
}


