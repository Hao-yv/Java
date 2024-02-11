package assist;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AePlayWave extends Thread {
    private String fileName;

    public AePlayWave(String wvaFile) {
        this.fileName = wvaFile;
    }

    @Override
    public void run() {
        File soundFile = new File(fileName);
        AudioInputStream audioInputStream = null;

        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auLine = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auLine = (SourceDataLine) AudioSystem.getLine(info);
            auLine.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        }

        auLine.start();
        int nByteRead = 0;
        // 作为缓冲
        byte[] abData = new byte[512];


        try {
            while (nByteRead != -1) {
                nByteRead = audioInputStream.read(abData, 0, abData.length);
                if(nByteRead >= 0){
                    auLine.write(abData, 0, nByteRead);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

