package GUI;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

class Music
{
    private static Clip myClip;
    public Music(URL thePath)
    {
        try
        {
            //File file = new File(thePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(thePath);
            myClip = AudioSystem.getClip();
            myClip.open(audioStream);
        }
        catch (IOException | UnsupportedAudioFileException | LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }

    public static void changeVolume(int theNewVolume) throws IllegalAccessException
    {
        if (theNewVolume < 0 || theNewVolume >100)
        {
            throw new IllegalAccessException();
        }
        FloatControl fC=(FloatControl) myClip.getControl(FloatControl.Type.MASTER_GAIN);
        fC.setValue(20.0f * (float) Math.log10(theNewVolume /100.0));
    }
    public void loop()
    {
        myClip.setFramePosition(0);
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}