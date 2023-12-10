package gameplay.Players;

import javax.media.opengl.GL;

public class Bullet {
    /*
    -----------------
    CLASS VARIABLES
    -----------------
     */
    private double x; // x position of the bullet
    private final double y; // y position of the bullet (THE BULLET MOVES ONLY HORIZONTALLY)

    private final int MAX_WIDTH = 100, MAX_HEIGHT = 100; // set max height and width to translate sprites using integers
//  -----------------

    /*
    -----------------
    CLASS METHODS
    -----------------
     */

    // initiate a bullet with position being x and y and then save them
    Bullet(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    /*
    a method to move the bullet as it only moves right
     */
    void move(double value) {
        x += value;
    }

    // drawing bullet
    void drawBullet(GL gl, double x, double y, int index, float xScale, float yScale){

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, GameGLEventListener.textures[index]);    // Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x / (MAX_WIDTH / 2.0) - 1, y / (MAX_HEIGHT / 2.0) - 1, 0);
        gl.glRotated(270,0,0,1);
        gl.glScaled(0.01 * xScale, 0.01 * yScale, 1);
        //System.out.println(x +" " + y);
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

}
