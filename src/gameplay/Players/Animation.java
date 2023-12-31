package gameplay.Players;

import com.sun.opengl.util.*;
import java.awt.*;
import javax.media.opengl.*;
import javax.swing.*;

public class Animation extends JFrame {

    public static void main(String[] args) {
        new Animation();
    }


    public Animation() {
        GLCanvas glcanvas;
        Animator animator;

        AnimationListener listener = new GameGLEventListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        glcanvas.addMouseListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(24);
        animator.add(glcanvas);
        animator.start();

        setTitle("Guns");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        glcanvas.requestFocus();
    }
}
