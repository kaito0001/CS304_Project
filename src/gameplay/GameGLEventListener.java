package gameplay;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.BitSet;

public class GameGLEventListener extends AnimationListener {

boolean multiPlayer = true;

    int maxWidth = 100;
    int maxHeight = 100;
public BitSet keyBits = new BitSet(256);

Player player = new Player(0,0);
Player player2 = new Player(50,50);
ArrayList<Bullet> bullets = new ArrayList<>();

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(0f, 0f, 0f, 0f);

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        handleKeyPress();

        // Handle input for Player 2
        handleKeyPressPlayer2();
        DrawSprite(gl, player.getX(), player.getY(), 0,5,5,0,1f,0f,0f);

        DrawSprite(gl, player2.getX(), player2.getY(), 0,5,5,0,0f,1f,0f);

        for(Bullet bullet: bullets){
            DrawSprite(gl, bullet.getX(), bullet.getY(), 0,5,5,0,0f,0f,1f);
            bullet.incrementX(1);
        }


    }

    public void DrawSprite(GL gl, int x, int y, int index, float xScale, float yScale, double angle,float red, float green, float blue) {
           // Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x / (maxWidth / 2.0) - 1, y / (maxHeight / 2.0) - 1, 0);
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



    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

    public void handleKeyPress() {

        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            if (player.getX() > 0) {
                player.decrementX(1);
            }
        }
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (player.getX() < 98) {
                player.incrementX(1);
            }
        }
        if (isKeyPressed(KeyEvent.VK_DOWN)) {
            if (player.getY() > 0) {
                player.decrementY(1);
            }
        }
        if (isKeyPressed(KeyEvent.VK_UP)) {
            if (player.getY() < 98) {
                player.incrementY(1);
            }
        }
        if(isKeyPressed(KeyEvent.VK_L)){
            bullets.add(new Bullet(player.getX(), player.getY()));
        }


    }
    public void handleKeyPressPlayer2(){

            if (isKeyPressed(KeyEvent.VK_A)) {
                if (player2.getX() > 0) {
                    player2.decrementX(1);
                }
            }
            if (isKeyPressed(KeyEvent.VK_D)) {
                if (player2.getX() < 98) {
                    player2.incrementX(1);
                }
            }
            if (isKeyPressed(KeyEvent.VK_S)) {
                if (player2.getY() > 0) {
                    player2.decrementY(1);
                }
            }
            if (isKeyPressed(KeyEvent.VK_W)) {
                if (player2.getY() < 98) {
                    player2.incrementY(1);
                }
            }
            if(isKeyPressed(KeyEvent.VK_G)){
            bullets.add(new Bullet(player2.getX(), player2.getY()));
            }

    }

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);

    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);

    }

    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }
}

