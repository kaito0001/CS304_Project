package UI;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL [] soundURL = new URL[3];

    public Sound(){
        soundURL[0] = getClass().getResource("../Assets/Sound/Home/Home.wav");
        soundURL[1] = getClass().getResource("../Assets/Sound/Zombies/Zombies.wav");
        soundURL[2] = getClass().getResource("../Assets/Sound/Buttons/Click.wav");
    }

    public void setFile (int i) {
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.stop(); // add this line to stop the current sound
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
    public void stop () {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close(); // add this line to clear out the queued data
        }
    }

    public void play () {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    public void loop () {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }


}