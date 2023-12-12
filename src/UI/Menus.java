package UI;

import com.sun.opengl.util.j2d.TextRenderer;
import gameplay.Players.Player;

import javax.media.opengl.GL;

import java.awt.*;
import java.util.ArrayList;

import static gameplay.Players.GameGLEventListener.*;

public class Menus {

    private int loginIndex = 52;
    private String current = "home";
    private String difficulty;
    TextRenderer textRenderer;

    private boolean multiPlayer = false; // declare the user choice if the game is single or multiplayer

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public void setMultiPlayer(int loginIndex, boolean multiPlayer){
        this.loginIndex = loginIndex;
        this.multiPlayer = multiPlayer;
    }

    public boolean isMultiPlayer(){
        return multiPlayer;
    }

    public void setCurrent(String current){
        this.current = current;
    }

    public String getCurrent(){
        return current;
    }

    public void currentPage(GL gl){
        if (current.equals("home")) {
            DrawBackGround(gl, 0, 0, 41);
            DrawSprite(gl, 50, 47, 42, 2, 1);
            DrawSprite(gl, 50, 37, 78, 2, 1);
            DrawSprite(gl, 50, 27, 43, 2, 1);
            DrawSprite(gl, 50, 17, 44, 2, 1);
            DrawSprite(gl, 50, 7, 45, 2, 1);
            DrawSprite(gl, 45, 55, 46, 1, 1);
            DrawSprite(gl, 55, 55, 47, 1, 1);
        }
        if (current.equals("player")){
            DrawBackGround(gl, 0, 0, 41);
            DrawSprite(gl, 50, 47, 48, 6, 7);
        }
        if (current.equals("high score")){
            DrawBackGround(gl, 0, 0, 41);
            DrawSprite(gl, 50, 50, 79, 6, 7);
            int h = 500;
            for(Player player: scores) {
                String score = player.getName() + " :  " + player.getScore();
                textRenderer = new TextRenderer(new Font("Arial", 1, 40));
                textRenderer.beginRendering(700, 700);
                textRenderer.setColor(Color.WHITE);
                textRenderer.setSmoothing(true);
                textRenderer.draw(score, 238, h);
                textRenderer.setColor(Color.white);
                textRenderer.endRendering();
                h -= 30;
            }
        }
        if (current.equals("help")) {
            DrawBackGround(gl, 0, 0, 41);
            DrawSprite(gl, 50, 50, 49, 6, 7);

        }
        if (current.equals("about")) {
            DrawBackGround(gl, 0, 0, 41);
            DrawSprite(gl, 50, 50, 53, 6, 7);

        }

        if (current.equals("levels")){
            DrawBackGround(gl, 0, 0, 41);
            DrawSprite(gl, 50, 47, 50, 6, 7);
        }
        if (current.equals("login") && !isMultiPlayer()) {
            DrawBackGround(gl, 0, 0, 41);
            DrawSprite(gl, 50, 47, loginIndex, 6, 7);
            textRenderer = new TextRenderer(new Font("Arial", 1, 40));
            textRenderer.beginRendering(700, 700);
            textRenderer.setColor(Color.WHITE);
            textRenderer.setSmoothing(true);
            textRenderer.draw(S1 , 238 , 330);
            textRenderer.setColor(Color.white);
            textRenderer.endRendering();
        }
        if (current.equals("login") && isMultiPlayer()) {
            DrawBackGround(gl, 0, 0, 41);
            DrawSprite(gl, 50, 47, loginIndex, 6, 7);
            textRenderer = new TextRenderer(new Font("Arial", 1, 25));
            textRenderer.beginRendering(700, 700);
            textRenderer.setColor(Color.WHITE);
            textRenderer.setSmoothing(true);
            textRenderer.draw(S1 , 240 , 351);
            textRenderer.draw(S2 , 240 , 260);
            textRenderer.setColor(Color.white);
            textRenderer.endRendering();
        }

    }
//  -----------------------------------------------------check the current page and draw it -----------------------------------------

    //  -----------------------------------------------------draw backGround function----------------------------------------------------
    public void DrawBackGround(GL gl,int x, int y, int index){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);
        gl.glPushMatrix();
//        gl.glTranslated( x/(MAX_WIDTH/2.0) - 1, y/(MAX_HEIGHT/2.0) - 1, 0);
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
//  -----------------------------------------------------draw backGround function----------------------------------------------------

    //  -----------------------------------------------------draw sprite function-------------------------------------------------------
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
//  -----------------------------------------------------draw sprite function-------------------------------------------------------
}
