package gameplay;

public class Bullet {
    /*
    -----------------
    CLASS VARIABLES
    -----------------
     */
    private int x, y;
//  -----------------

    /*
    -----------------
    CLASS METHODS
    -----------------
     */

    // initiate a bullet with position being x and y and then save them
    Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    /*
    2 methods to increment or decrement x with a "value" ( NO NEED TO CHANGE Y AS THE BULLET ONLY MOVES HORIZONTALLY
     */
    void incrementX(int value) {
        x += value;
    }


}
