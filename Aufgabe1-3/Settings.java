import javax.swing.*;
import java.awt.*;

/**
 * GOOD: Eine Klasse mit vielen Settings, die gesammelt an Objekte übergeben werden können.
 * Da wir oft viele Parameter übergeben mussten, haben wir diese Klasse eingeführt.
 *
 *
 * */
public class Settings {

    /** Window size X and Y */
    private int windowX = 1024;
    private int windowY = 768;

    /** Number of random Animals */
    private int randomBirds = 1;

    /**number of frames of one simulation */
    private long renderIterations = 5000;

    private JFrame frame = null;
    private Window window = null;



    /**color for background. Last value (a) is the opacity. (0= invisible; 255= completely visible) */
    private Color bgColor = new Color(160, 82, 45, 200);;


    public Settings() {

    }

    public Color getBgColor() {
        return bgColor;
    }

    public int getWindowX() {
        return windowX;
    }

    public int getWindowY() {
        return windowY;
    }


    public int getRandomBirds() {
        return randomBirds;
    }


    public long getRenderIterations() {
        return renderIterations;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Window getWindow() {
        return window;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setWindow(Window window) {
        this.window = window;
    }
}

