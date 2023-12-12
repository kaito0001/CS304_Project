package gameplay.Players;

import javax.media.opengl.GL;
import java.util.ArrayList;

import static gameplay.Players.GameGLEventListener.MAX_HEIGHT;
import static gameplay.Players.GameGLEventListener.MAX_WIDTH;

public class Player {
    /*
    -----------------
    CLASS VARIABLES
    -----------------
     */
    private double x, y; // x and y position of the player

    private String name;

    private int handleSpreading = 7; // keep 7 frames between bullets from the same player

    private int lives = 3; // initiating 3 lives for player

    private boolean alive = true; // starting with player being alive

    int score = 0;

    private ArrayList<Bullet> bullets = new ArrayList<>();
//  ------------------------------------------------------------------

    /*
    -----------------
    CLASS METHODS
    -----------------
     */

    // initiate a player with position being x and y and then save them
    Player(double x, double y) {
        this.x = x;
        this.y = y;
    }

    void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    /*
    4 methods to handle movement
     */
    void moveRight(double value) {
        x += value;
    }

    void moveLeft(double value) {
        x -= value;
    }

    void moveUp(double value) {
        y += value;
    }

    void moveDown(double value) {
        y -= value;
    }

    /*
    2 methods to handle score
     */
    void gotAKill(){
        score++;
    }

    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }

    /*
    4 methods to handle firing
     */
    boolean isGunReady(){
        if(handleSpreading >= 7){ // if 7 frames or more have already passed since last bullet then the player can fire
            handleSpreading = 0; // start new 7 frames counter
            return true;
        }
        return false;
    }

    void reloading(){
        handleSpreading++;
    }

    void fire(){
        bullets.add(new Bullet(this.getX() + 2, this.getY() - 2.5));
    }

    ArrayList<Bullet> getBullets(){
        return bullets;
    }

    int getLives(){
        return lives;
    }

    // a method to decrement lives of player
    void decrementLives() {
        if (isAlive()) { // first checks if the player is still alive
            lives--; // if player still alive decrement lives by 1

            if (lives == 0) { // then check if lives reached 0
                alive = false; // then player is dead
            }
        }
    }

    // return player is alive or dead
    boolean isAlive(){
        return alive;
    }

    // drawing player
    void drawPlayer(GL gl, double x, double y, int index, float xScale, float yScale){

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, GameGLEventListener.textures[index]);    // Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x / (MAX_WIDTH / 2.0) - 1, y / (MAX_HEIGHT / 2.0) - 1, 0);
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
