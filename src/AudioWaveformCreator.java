import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AudioWaveformCreator {
    AudioInputStream audioInputStream;
    Vector<Line2D.Double> lines = new Vector<Line2D.Double>();
    String errStr;
    Capture capture = new Capture();
    double duration, seconds;
    File file;
    String fileName;
    SamplingGraph samplingGraph;
    String waveformFilename;
    Color imageBackgroundColor = new Color(20,20,20);
    
    public AudioWaveformCreator(String fileName, String waveformFilename) throws UnsupportedAudioFileException, IOException {
        file = new File(fileName);
        this.waveformFilename = waveformFilename;
    }
    
    public void createAudioInputStream() throws Exception {
        if (file != null && file.isFile()) {
            try {
                errStr = null;
                audioInputStream = AudioSystem.getAudioInputStream(file);
                fileName = file.getName();
                long milliseconds = (long)((audioInputStream.getFrameLength() * 1000) / audioInputStream.getFormat().getFrameRate());
                duration = milliseconds / 1000.0;
                samplingGraph = new SamplingGraph();
                samplingGraph.createWaveForm(null);                
            } catch (Exception ex) { 
                reportStatus(ex.toString());
                throw ex;
            }
        } else {
            reportStatus("Audio file required.");
        }
    }
    /**
     * Render a WaveForm.
     */
    class SamplingGraph implements Runnable {

        private Thread thread;
        private Font font10 = new Font("serif", Font.PLAIN, 10);
        private Font font12 = new Font("serif", Font.PLAIN, 12);
        Color jfcBlue = new Color(000, 000, 255);
        Color pink = new Color(255, 175, 175);
 

        public SamplingGraph() {
        }


        public void createWaveForm(byte[] audioBytes) {

            lines.removeAllElements();  // clear the old vector

            AudioFormat format = audioInputStream.getFormat();
            if (audioBytes == null) {
                try {
                    audioBytes = new byte[
                        (int) (audioInputStream.getFrameLength() 
                        * format.getFrameSize())];
                    audioInputStream.read(audioBytes);
                } catch (Exception ex) { 
                    reportStatus(ex.getMessage());
                    return; 
                }
            }
            int w = 500;
            int h = 200;
            int[] audioData = null;
            if (format.getSampleSizeInBits() == 16) {
                 int nlengthInSamples = audioBytes.length / 2;
                 audioData = new int[nlengthInSamples];
                 if (format.isBigEndian()) {
                    for (int i = 0; i < nlengthInSamples; i++) {
                         /* First byte is MSB (high order) */
                         int MSB = (int) audioBytes[2*i];
                         /* Second byte is LSB (low order) */
                         int LSB = (int) audioBytes[2*i+1];
                         audioData[i] = MSB << 8 | (255 & LSB);
                     }
                 } else {
                     for (int i = 0; i < nlengthInSamples; i++) {
                         /* First byte is LSB (low order) */
                         int LSB = (int) audioBytes[2*i];
                         /* Second byte is MSB (high order) */
                         int MSB = (int) audioBytes[2*i+1];
                         audioData[i] = MSB << 8 | (255 & LSB);
                     }
                 }
             } else if (format.getSampleSizeInBits() == 8) {
                 int nlengthInSamples = audioBytes.length;
                 audioData = new int[nlengthInSamples];
                 if (format.getEncoding().toString().startsWith("PCM_SIGN")) {
                     for (int i = 0; i < audioBytes.length; i++) {
                         audioData[i] = audioBytes[i];
                     }
                 } else {
                     for (int i = 0; i < audioBytes.length; i++) {
                         audioData[i] = audioBytes[i] - 128;
                     }
                 }
            }
               
            int frames_per_pixel = audioBytes.length / format.getFrameSize()/w;
            byte my_byte = 0;
            double y_last = 0;
            int numChannels = format.getChannels();
            for (double x = 0; x < w && audioData != null; x++) {
                int idx = (int) (frames_per_pixel * numChannels * x);
                if (format.getSampleSizeInBits() == 8) {
                     my_byte = (byte) audioData[idx];
                } else {
                     my_byte = (byte) (128 * audioData[idx] / 32768 );
                }
                double y_new = (double) (h * (128 - my_byte) / 256);
                lines.add(new Line2D.Double(x, y_last, x, y_new));
                y_last = y_new;
            }
            saveToFile(waveformFilename);
        }

        
        public void saveToFile(String filename) {            
            int w = 500;
            int h = 200;
            int INFOPAD = 15;

            BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bufferedImage.createGraphics();
            
            createSampleOnGraphicsContext(w, h, INFOPAD, g2);            
            g2.dispose();
            // Write generated image to a file
            try {
                // Save as PNG
                File file = new File(filename);
                ImageIO.write(bufferedImage, "png", file);
            } catch (IOException e) {
            }
        }


        private void createSampleOnGraphicsContext(int w, int h, int INFOPAD, Graphics2D g2) {            
            g2.setBackground(imageBackgroundColor);
            g2.clearRect(0, 0, w, h);
            g2.setColor(Color.white);
            g2.fillRect(0, h-INFOPAD, w, INFOPAD);

            if (errStr != null) {
                g2.setColor(jfcBlue);
                g2.setFont(new Font("serif", Font.BOLD, 18));
                g2.drawString("ERROR", 5, 20);
                AttributedString as = new AttributedString(errStr);
                as.addAttribute(TextAttribute.FONT, font12, 0, errStr.length());
                AttributedCharacterIterator aci = as.getIterator();
                FontRenderContext frc = g2.getFontRenderContext();
                LineBreakMeasurer lbm = new LineBreakMeasurer(aci, frc);
                float x = 5, y = 25;
                lbm.setPosition(0);
                while (lbm.getPosition() < errStr.length()) {
                    TextLayout tl = lbm.nextLayout(w-x-5);
                    if (!tl.isLeftToRight()) {
                        x = w - tl.getAdvance();
                    }
                    tl.draw(g2, x, y += tl.getAscent());
                    y += tl.getDescent() + tl.getLeading();
                }
            } else if (capture.thread != null) {
                g2.setColor(Color.black);
                g2.setFont(font12);
                //g2.drawString("Length: " + String.valueOf(seconds), 3, h-4);
            } else {
                g2.setColor(Color.black);
                g2.setFont(font12);
                //g2.drawString("File: " + fileName + "  Length: " + String.valueOf(duration) + "  Position: " + String.valueOf(seconds), 3, h-4);

                if (audioInputStream != null) {
                    // .. render sampling graph ..
                    g2.setColor(jfcBlue);
                    for (int i = 1; i < lines.size(); i++) {
                        g2.draw((Line2D) lines.get(i));
                    }

                    // .. draw current position ..
                    if (seconds != 0) {
                        double loc = seconds/duration*w;
                        g2.setColor(pink);
                        g2.setStroke(new BasicStroke(3));
                        g2.draw(new Line2D.Double(loc, 0, loc, h-INFOPAD-2));
                    }
                }
            }
        }
    
        public void start() {
            thread = new Thread(this);
            thread.setName("SamplingGraph");
            thread.start();
            seconds = 0;
        }

        public void stop() {
            if (thread != null) {
                thread.interrupt();
            }
            thread = null;
        }

        public void run() {
            seconds = 0;
            while (thread != null) {
                if ( (capture.line != null) && (capture.line.isActive()) ) {
                    long milliseconds = (long)(capture.line.getMicrosecondPosition() / 1000);
                    seconds =  milliseconds / 1000.0;
                }
                try { thread.sleep(100); } catch (Exception e) { break; }                              
                while ((capture.line != null && !capture.line.isActive())) 
                {
                    try { thread.sleep(10); } catch (Exception e) { break; }
                }
            }
            seconds = 0;
        }
    } // End class SamplingGraph

    /** 
     * Reads data from the input channel and writes to the output stream
     */
    class Capture implements Runnable {

        TargetDataLine line;
        Thread thread;

        public void start() {
            errStr = null;
            thread = new Thread(this);
            thread.setName("Capture");
            thread.start();
        }

        public void stop() {
            thread = null;
        }
        
        private void shutDown(String message) {
            if ((errStr = message) != null && thread != null) {
                thread = null;
                samplingGraph.stop();                
                System.err.println(errStr);
            }
        }

        public void run() {

            duration = 0;
            audioInputStream = null;
            
            // define the required attributes for our line, 
            // and make sure a compatible line is supported.

            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, 
                format);
                        
            if (!AudioSystem.isLineSupported(info)) {
                shutDown("Line matching " + info + " not supported.");
                return;
            }

            // get and open the target data line for capture.

            try {
                line = (TargetDataLine) AudioSystem.getLine(info);
                line.open(format, line.getBufferSize());
            } catch (LineUnavailableException ex) { 
                shutDown("Unable to open the line: " + ex);
                return;
            } catch (SecurityException ex) { 
                shutDown(ex.toString());
                JavaSound.showInfoDialog();
                return;
            } catch (Exception ex) { 
                shutDown(ex.toString());
                return;
            }

            // play back the captured audio data
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int frameSizeInBytes = format.getFrameSize();
            int bufferLengthInFrames = line.getBufferSize() / 8;
            int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
            byte[] data = new byte[bufferLengthInBytes];
            int numBytesRead;
            
            line.start();

            while (thread != null) {
                if((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
                    break;
                }
                out.write(data, 0, numBytesRead);
            }

            // we reached the end of the stream.  stop and close the line.
            line.stop();
            line.close();
            line = null;

            // stop and close the output stream
            try {
                out.flush();
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // load bytes into the audio input stream for playback

            byte audioBytes[] = out.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
            audioInputStream = new AudioInputStream(bais, format, audioBytes.length / frameSizeInBytes);

            long milliseconds = (long)((audioInputStream.getFrameLength() * 1000) / format.getFrameRate());
            duration = milliseconds / 1000.0;

            try {
                audioInputStream.reset();
            } catch (Exception ex) { 
                ex.printStackTrace(); 
                return;
            }

            samplingGraph.createWaveForm(audioBytes);
        }
    } // End class Capture    
    
    public static void main(String [] args) throws Exception {
        if (args.length != 2) {
            printUsage();
            System.exit(1);
        }                
        AudioWaveformCreator awc = new AudioWaveformCreator(args[0], args[1]);
        awc.createAudioInputStream();
    }
    
    private void reportStatus(String msg) {
        if ((errStr = msg) != null) {
            System.out.println(errStr);            
        }
    }
    
    private static void printUsage() {
        System.out.println("AudioWaveformCreator usage: java AudioWaveformCreator.class [path to audio file for generating the image] [path to save waveform image to]");
    }
}