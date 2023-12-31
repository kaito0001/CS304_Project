package gameplay.zombies;



import gameplay.Players.GameGLEventListener;

import javax.media.opengl.GL;

public class Zombie {
    private final int MAX_WIDTH = 100, MAX_HEIGHT = 100;
    private double x, y;
    private double direction;


    public Zombie(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public void Move(double PX, double PY, double speed) {

        double dirX = PX - x;
        double dirY = PY - y;

        double distance = Math.sqrt(dirX * dirX + dirY * dirY);

        if (distance > 0) {
            dirX /= distance;
            dirY /= distance;
        }

        x += dirX * speed;
        y += dirY * speed;
         direction = Math.toDegrees(Math.atan2(-dirY, -dirX));
    }
    public void Move2(double player1X, double player1Y, double player2X, double player2Y, double speed) {
        // Calculate the average position of the two players
        double distanceToPlayer1 = calculateDistance(player1X, player1Y);
        double distanceToPlayer2 = calculateDistance(player2X, player2Y);

        if (distanceToPlayer1 < distanceToPlayer2) {
            Move(player1X, player1Y, speed);
        } else {
            Move(player2X, player2Y, speed);
        }
    }

    private double calculateDistance(double targetX, double targetY) {
        double dirX = targetX - x;
        double dirY = targetY - y;
        return Math.sqrt(dirX * dirX + dirY * dirY);
    }



    public  void DrawZombie(GL gl, double x, double y, int index, float xScale, float yScale){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, GameGLEventListener.textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated( x/(MAX_WIDTH/2.0) - 1, y/(MAX_HEIGHT/2.0) - 1, 0);
        gl.glScaled(0.01*xScale, 0.01*yScale, 1);
        gl.glRotated(direction, 0, 0, 1);
        gl.glBegin(GL.GL_QUADS);

        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1f, -1f,-1f);

        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1f, -1f,-1f);

        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1f, 1f,-1f);

        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1f, 1f,-1f);

        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }



}
