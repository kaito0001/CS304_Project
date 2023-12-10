package gameplay.Players;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.BitSet;
import java.util.Iterator;
import Texture.*;

public class GameGLEventListener extends AnimationListener {
    /*
    -----------------
    CLASS VARIABLES
    -----------------
     */
    boolean multiPlayer = true; // declare the user choice if the game is single or multiplayer

    private final int MAX_WIDTH = 100, MAX_HEIGHT = 100; // set max height and width to translate sprites using integers

    private final BitSet keyBits = new BitSet(256); // a bitset used to handle multi keys pressed at the same time

    Player player1 = new Player(10,40); // initiating player1 at position (10,40)
    Player player2 = new Player(10,60); // initiating player2 at position (10,60)

//    ArrayList<Bullet> bullets = new ArrayList<>(); // initiate an empty arraylist to track bullets

    static String[] textureNames = {  // array containing paths of the pictures used
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
            "Bullet.png"
    };

    // pictures indexes
    int[] player1Move = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,16, 17, 18, 19},
          player2Move = {20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39};

    int player1AnimationIndex = 0, player2AnimationIndex = 0; // players animation indexes to change pictures while moving


    // preparing the pictures to be rendered and used
    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    static int[] textures = new int[textureNames.length];

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
                if (player1.getY() < MAX_HEIGHT - 3) {
                    player1.moveUp(1);
                }
            }
            if (isKeyPressed(KeyEvent.VK_DOWN)) {
                if (player1.getY() > 4) {
                    player1.moveDown(1);
                }
            }
            if (isKeyPressed(KeyEvent.VK_LEFT)) {
                if (player1.getX() > 4) {
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
        if(multiPlayer) {
            if(player2.isAlive()) {
                // check if any of player2 movement keys is pressed
                if (isKeyPressed(KeyEvent.VK_W) || isKeyPressed(KeyEvent.VK_S) || isKeyPressed(KeyEvent.VK_A) || isKeyPressed(KeyEvent.VK_D)) {

                    // make it draw next picture for player2 in next frame
                    player2AnimationIndex++;
                    player2AnimationIndex %= 20;

                    if (isKeyPressed(KeyEvent.VK_W)) {
                        if (player2.getY() < MAX_HEIGHT - 3) {
                            player2.moveUp(1);
                        }
                    }
                    if (isKeyPressed(KeyEvent.VK_S)) {
                        if (player2.getY() > 4) {
                            player2.moveDown(1);
                        }
                    }
                    if (isKeyPressed(KeyEvent.VK_A)) {
                        if (player2.getX() > 4) {
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
        gl.glClearColor(1.0f, 0f, 0f, 1.0f);    //This Will Clear The Background Color To Black

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

        handleKeyPress(); // handle input

//      ----------------------------------------------------draw player1 bullets------------------------------------------------------
        Iterator<Bullet> iterator1 = player1.getBullets().iterator(); // making iterator of bullets to use its remove method to avoid "ConcurrentModificationException"
        while (iterator1.hasNext()) { // check if there are still elements
            Bullet bullet = iterator1.next(); // get next element as bullet

            if (bullet.getX() < MAX_WIDTH) { // if bullet is still in the screen draw it then increment its x for next frame
                bullet.drawBullet(gl, bullet.getX(), bullet.getY(), 40, 3, 3);
                bullet.move(1);

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
                bullet.move(1);

            } else { // if not then remove the bullet to lower sources usage (performance)
                iterator2.remove();
            }
        }
//      ----------------------------------------------------draw player2 bullets------------------------------------------------------

//      ----------------------------------------------------draw players------------------------------------------------------
        if(player1.isAlive()) { // checks if player2 is alive before drawing
            player1.drawPlayer(gl, player1.getX(), player1.getY(), player1Move[player1AnimationIndex], 10, 10); // draw player1 at the specified x and y each frame
        }
        if(multiPlayer) { // check first if it's multiplayer
            if (player2.isAlive()) { // checks if player2 is alive before drawing
                player2.drawPlayer(gl, player2.getX(), player2.getY(), player2Move[player2AnimationIndex], 10, 10); // draw player2 at the specified x and y each frame
            }
        }
//      ----------------------------------------------------draw players------------------------------------------------------
    }

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

}

