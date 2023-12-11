package gameplay.Players;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.BitSet;
import java.util.Iterator;
import Texture.*;
import UI.Menus;

public class GameGLEventListener extends AnimationListener {
    /*
    -----------------
    CLASS VARIABLES
    -----------------
     */
    boolean paused=false;
    String pause="playing";
    boolean helpInGame=false;
    Menus UI = new Menus();

    public static final int MAX_WIDTH = 100, MAX_HEIGHT = 100; // set max height and width to translate sprites using integers

    private final BitSet keyBits = new BitSet(256); // a bitset used to handle multi keys pressed at the same time

    Player player1 = new Player(21,40); // initiating player1 at position (21,40)
    Player player2 = new Player(21,60); // initiating player2 at position (21,60)


//    ArrayList<Bullet> bullets = new ArrayList<>(); // initiate an empty arraylist to track bullets

    public static String[] textureNames = {  // array containing paths of the pictures used
            // player1 pictures
            "Player1//P1move0.png", "Player1//P1move1.png", "Player1//P1move2.png", "Player1//P1move3.png", "Player1//P1move4.png",
            "Player1//P1move5.png", "Player1//P1move6.png", "Player1//P1move7.png", "Player1//P1move8.png", "Player1//P1move9.png",
            "Player1//P1move10.png", "Player1//P1move11.png", "Player1//P1move12.png", "Player1//P1move13.png", "Player1//P1move14.png",
            "Player1//P1move15.png", "Player1//P1move16.png", "Player1//P1move17.png", "Player1//P1move18.png", "Player1//P1move19.png",

            // player2 pictures
            "Player2//P2move0.png", "Player2//P2move1.png", "Player2//P2move2.png", "Player2//P2move3.png", "Player2//P2move4.png",
            "Player2//P2move5.png", "Player2//P2move6.png", "Player2//P2move7.png", "Player2//P2move8.png", "Player2//P2move9.png",
            "Player2//P2move10.png", "Player2//P2move11.png", "Player2//P2move12.png", "Player2//P2move13.png", "Player2//P2move14.png",
            "Player2//P2move15.png", "Player2//P2move16.png", "Player2//P2move17.png", "Player2//P2move18.png", "Player2//P2move19.png",

            // bullet picture
            "Bullet.png",

            // menus pictures
            "Views/Home.png","Buttons/Play.png","Buttons/Help.png","Buttons/About US.png","Buttons/Exit.png","Buttons/Music.png","Buttons/Mute.png"
            ,"Views/Menus/MultiPlayer.png","Views/Menus/Help.png","Views/Menus/Levels.png","Views/Menus/Login of One Player.png","Views/Menus/Login of Two Players.png","Views/Menus/About_US.png","Buttons/Pause.png","Views/Menus/Pause.png",

            // backGround picture
            "Night.png"
    };

    // pictures indexes
    int[] player1Move = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,16, 17, 18, 19},
          player2Move = {20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39};

    int player1AnimationIndex = 0, player2AnimationIndex = 0; // players animation indexes to change pictures while moving


    // preparing the pictures to be rendered and used
    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    public static int[] textures = new int[textureNames.length];

//  **********************************************************************************************

    /*
    -----------------
    CLASS METHODS
    -----------------
     */
    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode); // return true if the wanted keyCode does exist in keyBits (is being pressed right now)
    }

//  --------------------handle keys----------------------
    public void handleKeyPress() {
        /*
        HANDLE PLAYER1 MOVEMENT AND ATTACK
         */
        if(player1.isAlive()){
        // check if any of player1 movement keys is pressed
        if (isKeyPressed(KeyEvent.VK_UP) || isKeyPressed(KeyEvent.VK_DOWN) || isKeyPressed(KeyEvent.VK_LEFT) || isKeyPressed(KeyEvent.VK_RIGHT)) {

            // make it draw next picture for player1 in next frame
            player1AnimationIndex++;
            player1AnimationIndex %= 20;

            if (isKeyPressed(KeyEvent.VK_UP)) {
                if (player1.getY() < MAX_HEIGHT - 27) {
                    player1.moveUp(1);
                }
            }
            if (isKeyPressed(KeyEvent.VK_DOWN)) {
                if (player1.getY() > 9) {
                    player1.moveDown(1);
                }
            }
            if (isKeyPressed(KeyEvent.VK_LEFT)) {
                if (player1.getX() > 21) {
                    player1.moveLeft(1);
                }
            }
            if (isKeyPressed(KeyEvent.VK_RIGHT)) {
                if (player1.getX() < MAX_WIDTH - 4) {
                    player1.moveRight(1);
                }
            }
        }
        if (isKeyPressed(KeyEvent.VK_L)) {
            if (player1.isGunReady()) { // check to let player1 fire or not
                player1.fire();
            }
        }
        player1.reloading();
    }

        /*
        HANDLE PLAYER2 MOVEMENT AND ATTACK
         */
        if(UI.isMultiPlayer()) {
            if(player2.isAlive()) {
                // check if any of player2 movement keys is pressed
                if (isKeyPressed(KeyEvent.VK_W) || isKeyPressed(KeyEvent.VK_S) || isKeyPressed(KeyEvent.VK_A) || isKeyPressed(KeyEvent.VK_D)) {

                    // make it draw next picture for player2 in next frame
                    player2AnimationIndex++;
                    player2AnimationIndex %= 20;

                    if (isKeyPressed(KeyEvent.VK_W)) {
                        if (player2.getY() < MAX_HEIGHT - 27) {
                            player2.moveUp(1);
                        }
                    }
                    if (isKeyPressed(KeyEvent.VK_S)) {
                        if (player2.getY() > 9) {
                            player2.moveDown(1);
                        }
                    }
                    if (isKeyPressed(KeyEvent.VK_A)) {
                        if (player2.getX() > 21) {
                            player2.moveLeft(1);
                        }
                    }
                    if (isKeyPressed(KeyEvent.VK_D)) {
                        if (player2.getX() < MAX_WIDTH - 4) {
                            player2.moveRight(1);
                        }
                    }
                }
                if (isKeyPressed(KeyEvent.VK_G)) {
                    if (player2.isGunReady()) { // check to let player2 fire or not
                        player2.fire();
                    }
                }
                player2.reloading();
            }
        }
    }
//  --------------------handle keys----------------------

    /*
    ---------------------------------
    GLEventListener interface methods
    ---------------------------------
     */
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(0f, 0f, 0f, 1.0f);    //This Will Clear The Background Color To Black

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = Texture.TextureReader.readTexture(assetsFolderName + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//               mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Image data
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        UI.currentPage(gl);

        if(UI.getCurrent().equals("game")) {
           if (!paused){
               handleKeyPress(); // handle input
           }
            DrawBackGround(gl);
            DrawSprite(gl, 95, 95, 54, 1, 1);


//      ----------------------------------------------------draw player1 bullets------------------------------------------------------
                Iterator<Bullet> iterator1 = player1.getBullets().iterator(); // making iterator of bullets to use its remove method to avoid "ConcurrentModificationException"
                while (iterator1.hasNext()) { // check if there are still elements
                    Bullet bullet = iterator1.next(); // get next element as bullet

                    if (bullet.getX() < MAX_WIDTH) { // if bullet is still in the screen draw it then increment its x for next frame
                        bullet.drawBullet(gl, bullet.getX(), bullet.getY(), 40, 3, 3);
                       if (!paused){
                           bullet.move(1);
                       }

                    } else { // if not then remove the bullet to lower sources usage (performance)
                        iterator1.remove();
                    }

                }
//      ----------------------------------------------------draw player1 bullets------------------------------------------------------

//      ----------------------------------------------------draw player2 bullets------------------------------------------------------
                Iterator<Bullet> iterator2 = player2.getBullets().iterator(); // making iterator of bullets to use its remove method to avoid "ConcurrentModificationException"
                while (iterator2.hasNext()) { // check if there are still elements
                    Bullet bullet = iterator2.next(); // get next element as bullet

                    if (bullet.getX() < MAX_WIDTH) { // if bullet is still in the screen draw it then increment its x for next frame
                        bullet.drawBullet(gl, bullet.getX(), bullet.getY(), 40, 3, 3);
                        if (!paused) {
                            bullet.move(1);
                        }
                    } else { // if not then remove the bullet to lower sources usage (performance)
                        iterator2.remove();
                    }
                }
//      ----------------------------------------------------draw player2 bullets------------------------------------------------------

//      ----------------------------------------------------draw players------------------------------------------------------

                if (player1.isAlive()) { // checks if player2 is alive before drawing
                    player1.drawPlayer(gl, player1.getX(), player1.getY(), player1Move[player1AnimationIndex], 10, 10); // draw player1 at the specified x and y each frame
                }
                if (UI.isMultiPlayer()) { // check first if it's multiplayer
                    if (player2.isAlive()) { // checks if player2 is alive before drawing
                        player2.drawPlayer(gl, player2.getX(), player2.getY(), player2Move[player2AnimationIndex], 10, 10); // draw player2 at the specified x and y each frame
                    }
                }
//      ----------------------------------------------------draw players------------------------------------------------------
            if (paused&&!helpInGame) {
                DrawSprite(gl, 50, 50, 55, 6, 7);
            }
            if (helpInGame) {
                DrawSprite(gl, 50, 50, 49, 6, 7);

            }
            }


    }
    public void resetGame(){
        player1.getBullets().clear();
        player2.getBullets().clear();
    }
    public void DrawBackGround(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length - 1]);
        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
    //-----------------------------------------------------draw backGround function-------------------------------------------------------

    public void DrawSprite(GL gl,int x, int y, int index, float scaleX,float scaleY){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);
        gl.glPushMatrix();
        gl.glTranslated( x/(MAX_WIDTH/2.0) - 1, y/(MAX_HEIGHT/2.0) - 1, 0);
        gl.glScaled(0.1*scaleX, 0.1*scaleY, 1);
//        System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
    //-----------------------------------------------------draw sprite function-------------------------------------------------------

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

    /*
    ---------------------------------
    KeyListener interface methods
    ---------------------------------
     */
    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode(); // if a key is pressed get its code and store it in keyBits
        keyBits.set(keyCode);

    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode(); // if a key is released get its code and remove it from keyBits
        keyBits.clear(keyCode);
    }

    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

//  ---------------------------------------------buttons check -----------------------------------------------------------------
    @Override
    public void mousePressed(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        Component c = e.getComponent();
        double width = c.getWidth();
        double height = c.getHeight();

        // get click position
        int xClick = (int)((x / width) * 100);
        int yClick = 100 - (int)(100 * y / height);
        System.out.println(xClick+","+yClick);


//      ------------------------------handle home page buttons-------------------------------
        switch (UI.getCurrent()) {
            case "home" -> {
                if (xClick >= 40 && xClick <= 57 && yClick >= 45 && yClick <= 50) {
                    UI.setCurrent("player");

                } else if (xClick >= 40 && xClick <= 57 && yClick <= 40 && yClick >= 35) {
                    UI.setCurrent("help");

                } else if (xClick >= 40 && xClick <= 57 && yClick <= 30 && yClick >= 25) {
                    UI.setCurrent("about");

                } else if (xClick >= 40 && xClick <= 57 && yClick <= 20 && yClick >= 15) {
                    System.exit(0);
                }
            }
//      ------------------------------handle home page buttons-------------------------------

//      ---------------------------handle game mode page buttons-----------------------------
            case "player" -> {
                if (xClick >= 40 && xClick <= 57 && yClick >= 45 && yClick <= 50) { // game mode (singlePlayer)
                    UI.setCurrent("levels");
                    UI.setMultiPlayer(52, false);

                } else if (xClick >= 40 && xClick <= 57 && yClick >= 37 && yClick <= 43) { // game mode (multiPlayer)
                    UI.setCurrent("levels");
                    UI.setMultiPlayer(51, true);

                } else if (xClick >= 63 && xClick <= 69 && yClick >= 75 && yClick <= 79) { // exit button has been clicked
                    UI.setCurrent("home");
                }
            }
//      ---------------------------handle game mode page buttons-----------------------------

//      ---------------------------handle difficulty page buttons----------------------------
            case "levels" -> {
                if (xClick >= 38 && xClick <= 62 && yClick >= 53 && yClick <= 59) { // the player has chosen easy
                    UI.setCurrent("login");
                    UI.setDifficulty("easy");

                } else if (xClick >= 38 && xClick <= 62 && yClick <= 50 && yClick >= 45) { // the player has chosen medium
                    UI.setCurrent("login");
                    UI.setDifficulty("medium");

                } else if (xClick >= 38 && xClick <= 62 && yClick <= 42 && yClick >= 38) { // the player has chosen hard
                    UI.setCurrent("login");
                    UI.setDifficulty("hard");

                } else if (xClick >= 63 && xClick <= 69 && yClick >= 75 && yClick <= 79) { // exit button has been clicked
                    UI.setCurrent("player");
                }
            }
//      ---------------------------handle difficulty page buttons----------------------------

//      ------------------------------handle help page buttons----------------------------
            case "help" -> {
                if (xClick >= 69 && xClick <= 72 && yClick >= 77 && yClick <= 81) { // exit button has been clicked
                    UI.setCurrent("home");
                }
            }
//      ------------------------------handle help page buttons----------------------------

//      ------------------------------handle credits page buttons----------------------------
            case "about" -> {
                if (xClick >= 64 && xClick <= 68 && yClick >= 76 && yClick <= 81) { // exit button has been clicked
                    UI.setCurrent("home");
                }
            }
//      ------------------------------handle credits page buttons----------------------------

//      ------------------------------handle login credits buttons----------------------------
            case "login" -> {
                if (xClick >= 67 && xClick <= 71 && yClick >= 75 && yClick <= 81) { // exit button has been clicked
                    UI.setCurrent("home");

                } else if (xClick >= 44 && xClick <= 53 && yClick >= 30 && yClick <= 36) { // start game has been pressed
                    UI.setCurrent("game");
                }
            }
            case "game" -> {
                if (xClick >= 91 && xClick <= 98 && yClick >= 91 && yClick <= 100) { // pause button has been clicked
                    paused=true;
                    pause="pause";


                }
            }



        }
//      --------------------------------------- handel pause button ----------------------------------------------------
        if (pause.equals("pause")&&!helpInGame) {
            if (xClick >= 63 && xClick <= 69 && yClick >= 78 && yClick <= 83) { // exit button has been clicked
                UI.setCurrent("game");
                paused = false;
                pause="playing";

            }else if (xClick >= 38 && xClick <= 62 && yClick >= 56 && yClick <= 62){// resume button
                paused=false;
            }else if (xClick >= 38 && xClick <= 62 && yClick >= 48 && yClick <= 54){// help in game button
                helpInGame=true;
            }else if (xClick >= 38 && xClick <= 62 && yClick >= 40 && yClick <= 45){// back to home menu button
                UI.setCurrent("home");
                pause="playing";
                paused=false;
                resetGame();
            }else if (xClick >= 51 && xClick <= 58 && yClick >= 32 && yClick <= 37){// mute button

            }else if (xClick >= 41 && xClick <= 48 && yClick >= 31 && yClick <= 37){//music button

            }
        }
        if (helpInGame){
            if (xClick >= 69 && xClick <= 72 && yClick >= 76 && yClick <= 80) { // exit button has been clicked
                helpInGame=false;

            }
        }
//      --------------------------------------- handel pause button ----------------------------------------------------
    }

//  ---------------------------------------------buttons check -----------------------------------------------------------------


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

