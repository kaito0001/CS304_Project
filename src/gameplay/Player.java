package gameplay;

public class Player {
    /*
    -----------------
    CLASS VARIABLES
    -----------------
     */
    private int x, y; // x and y position of the player1

    private int lives = 3; // initiating 3 lives for player1

    private boolean alive = true; // starting with player1 being alive
//  ------------------------------------------------------------------

    /*
    -----------------
    CLASS METHODS
    -----------------
     */

    // initiate a player1 with position being x and y and then save them
    Player(int x, int y) {
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
    4 methods to increment or decrement x and y with a "value"
     */
    void incrementX(int value) {
        x += value;
    }

    void decrementX(int value) {
        x -= value;
    }

    void incrementY(int value) {
        y += value;
    }

    void decrementY(int value) {
        y -= value;
    }

    // a method to decrement lives of player1
    void decrementLives() {
        if (alive) { // first checks if the player1 is still alive
            lives--; // if player1 still alive decrement lives by 1

            if (lives == 0) { // then check if lives reached 0
                alive = false; // then player1 is dead
            }
        }
    }
}
