package gameplay;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;

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

    ArrayList<Bullet> bullets = new ArrayList<>(); // initiate an empty arraylist to track bullets
//  **********************************************************************************************

    /*
    -----------------
    CLASS METHODS
    -----------------
     */
    public void DrawSprite(GL gl, int x, int y, int index, float xScale, float yScale, double angle,float red, float green, float blue) {

        gl.glPushMatrix();
        gl.glTranslated(x / (MAX_WIDTH / 2.0) - 1, y / (MAX_HEIGHT / 2.0) - 1, 0);
        gl.glRotated(angle,0,0,1);
        gl.glScaled(0.01 * xScale, 0.01 *yScale, 1);
        //System.out.println(x +" " + y);
        gl.glColor3f(red, green, blue);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2d(0.4, 0);
        gl.glVertex2d(0, 0);
        gl.glVertex2d(0, 0.4);
        gl.glVertex2d(0.4, 0.4);
        gl.glEnd();
        gl.glPopMatrix();

    }

//  ------------------------------------------------ handle keys
    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode); // return true if the wanted keyCode does exist in keyBits (is being pressed right now)
    }

    public void handleKeyPress() {

        /*
        HANDLE PLAYER1 MOVEMENT AND ATTACK
         */
        if (isKeyPressed(KeyEvent.VK_UP)) {
            if (player1.getY() < MAX_HEIGHT - 2) {
                player1.incrementY(1);
            }
        }
        if (isKeyPressed(KeyEvent.VK_DOWN)) {
            if (player1.getY() > 0) {
                player1.decrementY(1);
            }
        }
        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            if (player1.getX() > 0) {
                player1.decrementX(1);
            }
        }
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (player1.getX() < MAX_WIDTH - 2) {
                player1.incrementX(1);
            }
        }
        if(isKeyPressed(KeyEvent.VK_L)){
            bullets.add(new Bullet(player1.getX(), player1.getY()));
        }

        /*
        HANDLE PLAYER2 MOVEMENT AND ATTACK
         */
        if(multiPlayer) {
            if (isKeyPressed(KeyEvent.VK_W)) {
                if (player2.getY() < MAX_HEIGHT - 2) {
                    player2.incrementY(1);
                }
            }
            if (isKeyPressed(KeyEvent.VK_S)) {
                if (player2.getY() > 0) {
                    player2.decrementY(1);
                }
            }
            if (isKeyPressed(KeyEvent.VK_A)) {
                if (player2.getX() > 0) {
                    player2.decrementX(1);
                }
            }
            if (isKeyPressed(KeyEvent.VK_D)) {
                if (player2.getX() < MAX_WIDTH - 2) {
                    player2.incrementX(1);
                }
            }
            if (isKeyPressed(KeyEvent.VK_G)) {
                bullets.add(new Bullet(player2.getX(), player2.getY()));
            }
        }
    }
//  ------------------------------------------------ handle keys

    /*
    ---------------------------------
    GLEventListener interface methods
    ---------------------------------
     */
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(0f, 0f, 0f, 0f); // initiate background color

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        handleKeyPress(); // handle input

//      --------------------------------------------------------------------------------------------------------------------- draw players
        if(player1.isAlive()) { // checks if player2 is alive before drawing
            DrawSprite(gl, player1.getX(), player1.getY(), 0, 5, 5, 0, 1f, 0f, 0f); // draw player1 at the specified x and y each frame
        }
        if(multiPlayer) { // check first if it's multiplayer
            if (player2.isAlive()) { // checks if player2 is alive before drawing
                DrawSprite(gl, player2.getX(), player2.getY(), 0, 5, 5, 0, 0f, 1f, 0f); // draw player2 at the specified x and y each frame
            }
        }
//      --------------------------------------------------------------------------------------------------------------------- draw players

//      --------------------------------------------------------------------------------------------------------------------- draw bullets
        Iterator<Bullet> iterator = bullets.iterator(); // making iterator of bullets to use its remove method to avoid "ConcurrentModificationException"
        while (iterator.hasNext()) { // check if there are still elements
            Bullet bullet = iterator.next(); // get next element as bullet

            if (bullet.getX() < MAX_WIDTH) { // if bullet is still in the screen draw it then increment its x for next frame
                DrawSprite(gl, bullet.getX(), bullet.getY(), 0, 5, 5, 0, 0f, 0f, 1f);
                bullet.incrementX(1);

            } else { // if not then remove the bullet to lower sources usage (performance)
                iterator.remove();
            }
        }
    }
//      ---------------------------------------------------------------------------------------------------------------------- draw bullets
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

