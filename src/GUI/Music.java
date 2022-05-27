package GUI;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

class Music {
    private static Clip clip;
    public Music(String path) {
        try {
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public static void changeVolume(int theNewVolume) throws IllegalAccessException {
        if (theNewVolume < 0 || theNewVolume >100){
            throw new IllegalAccessException();
        }
        FloatControl fC=(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        fC.setValue(20.0f * (float) Math.log10(theNewVolume /100.0));
    }


    public  void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void close() {
        clip.close();
    }
}